package com.example.pourunmondeeveille.ui.familles;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FamillesViewModel extends ViewModel {
    private static final String TAG = "FamillesViewModel";
    private ApiService apiService;
    private final MutableLiveData<List<FamilleAccueil>> famillesAccueilLiveData = new MutableLiveData<>();
    private List<FamilleAccueil> famillesList = new ArrayList<>();
    private List<FamilleAccueil> originalFamillesList = new ArrayList<>();
    public void setFamillesAccueilLiveData(List<FamilleAccueil> familles) {
        famillesList = familles;
        originalFamillesList = new ArrayList<>(familles); // Met à jour la liste originale
        famillesAccueilLiveData.setValue(familles);
    }

    public List<FamilleAccueil> getOriginalFamillesList() {
        return originalFamillesList;
    }

    public FamillesViewModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<List<FamilleAccueil>> getFamillesAccueilLiveData() {
        return famillesAccueilLiveData;  // Retourne le LiveData pour que le Fragment puisse s'abonner
    }

    public void fetchFamillesAccueil() {
        Call<List<FamilleAccueil>> call = apiService.getFamillesAccueil();
        call.enqueue(new Callback<List<FamilleAccueil>>() {
            @Override
            public void onResponse(Call<List<FamilleAccueil>> call, Response<List<FamilleAccueil>> response) {
                if (response.isSuccessful()) {
                    List<FamilleAccueil> familles = response.body();
                    setFamillesAccueilLiveData(familles);
                    Log.d(TAG, "fetchFamillesAccueil: Successfully fetched families");
                } else {
                    Log.e(TAG, "fetchFamillesAccueil: Response unsuccessful, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<FamilleAccueil>> call, Throwable t) {
                Log.e(TAG, "fetchFamillesAccueil: Failed to fetch families", t);
            }
        });
    }

}