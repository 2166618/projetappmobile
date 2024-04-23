package com.example.pourunmondeeveille.ui.connexion;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.ConnexionRequest;
import com.example.pourunmondeeveille.model.ConnexionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConnexionViewModel extends ViewModel {
    private ApiService apiService;
    private MutableLiveData<ConnexionResponse> connexionResponseLiveData = new MutableLiveData<>();

    public ConnexionViewModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public MutableLiveData<ConnexionResponse> getConnexionResponse() {
        return connexionResponseLiveData;
    }

    public void connexionUtilisateur(String nomUtilisateur, String motDePasse) {
        ConnexionRequest connexionRequest = new ConnexionRequest(nomUtilisateur, motDePasse);

        apiService.connexion(connexionRequest).enqueue(new Callback<ConnexionResponse>() {
            @Override
            public void onResponse(Call<ConnexionResponse> call, Response<ConnexionResponse> response) {
                if (response.isSuccessful()) {
                    connexionResponseLiveData.setValue(response.body());
                } else {
                    // Gérer l'erreur de réponse HTTP
                    connexionResponseLiveData.setValue(null);
                    Log.e("MainActivity", "Erreur lors de la réponse HTTP");
                }
            }

            @Override
            public void onFailure(Call<ConnexionResponse> call, Throwable t) {
                // Gérer les échecs de réseau
                connexionResponseLiveData.setValue(null);
                Log.e("MainActivity", "Échec de réseau");
            }
        });
    }

}
