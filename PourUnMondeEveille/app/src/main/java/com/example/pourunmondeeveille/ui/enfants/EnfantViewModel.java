package com.example.pourunmondeeveille.ui.enfants;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.enfants.Enfant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EnfantViewModel extends ViewModel {
    private static final String TAG = "EnfantsViewModel";
    private ApiService apiService;

    private final MutableLiveData<List<Enfant>> enfantsLiveData = new MutableLiveData<>();
    private List<Enfant> enfantsList = new ArrayList<>();
    private List<Enfant> originalEnfantsList = new ArrayList<>();

    public void setEnfantsLiveData(List<Enfant> familles) {
        enfantsList = familles;
        originalEnfantsList = new ArrayList<>(familles);
        enfantsLiveData.setValue(familles);
    }

    public List<Enfant> getOriginalEnfantsList() {
        return originalEnfantsList;
    }

    public EnfantViewModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<List<Enfant>> getEnfantLiveData() {
        return enfantsLiveData;
    }

    public void fetchEnfants() {
        Call<List<Enfant>> call = apiService.getEnfants();
        call.enqueue(new Callback<List<Enfant>>() {
            @Override
            public void onResponse(Call<List<Enfant>> call, Response<List<Enfant>> response) {
                if (response.isSuccessful()) {
                    List<Enfant> enfants = response.body();
                    setEnfantsLiveData(enfants);
                    Log.d(TAG, "fetchEnfants: Succes fetch enfants");
                } else {
                    Log.e(TAG, "fetchEnfants: Reponse infructueuse, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Enfant>> call, Throwable t) {
                Log.e(TAG, "fetchEnfants: Fail fetch enfants", t);
            }
        });
    }

}