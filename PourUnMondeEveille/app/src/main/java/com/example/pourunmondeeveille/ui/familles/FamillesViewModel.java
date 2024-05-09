package com.example.pourunmondeeveille.ui.familles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FamillesViewModel extends ViewModel {
    private ApiService apiService;

    private final MutableLiveData<List<FamilleAccueil>> famillesAccueil = new MutableLiveData<>();

    public FamillesViewModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<List<FamilleAccueil>> getFamillesAccueil() {
        return famillesAccueil;  // Retourne le LiveData pour que le Fragment puisse s'abonner
    }

    public void fetchFamillesAccueil() {
        Call<List<FamilleAccueil>> call = apiService.getFamillesAccueil();
        call.enqueue(new Callback<List<FamilleAccueil>>() {
            @Override
            public void onResponse(Call<List<FamilleAccueil>> call, Response<List<FamilleAccueil>> response) {
                if (response.isSuccessful()) {
                    List<FamilleAccueil> familles = response.body();
                    famillesAccueil.setValue(familles);  // Met à jour le MutableLiveData
                } else {
                    // Gérez les erreurs de réponse
                }
            }

            @Override
            public void onFailure(Call<List<FamilleAccueil>> call, Throwable t) {
                // Gérez les erreurs de connexion
            }
        });
    }

}