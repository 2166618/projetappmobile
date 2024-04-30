package com.example.pourunmondeeveille.ui.connexion;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.ConnexionRequest;
import com.example.pourunmondeeveille.model.ConnexionResponse;

import java.io.IOException;

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
                    // Inclure le code de statut HTTP et un message d'erreur
                    int statusCode = response.code();  // Récupérer le code de statut HTTP
                    String errorMessage = "Erreur lors de la réponse HTTP. Code de statut : " + statusCode;
                    if (response.errorBody() != null) {
                        try {
                            // Essayer d'obtenir des détails supplémentaires depuis le corps de l'erreur
                            errorMessage += ". Détails : " + response.errorBody().string();
                        } catch (IOException e) {
                            errorMessage += ". Impossible de récupérer les détails de l'erreur.";
                        }
                    }
                    connexionResponseLiveData.setValue(null);  // Indiquer un échec
                    Log.e("ConnexionViewModel", errorMessage);  // Log avec détails supplémentaires
                }
            }

            @Override
            public void onFailure(Call<ConnexionResponse> call, Throwable t) {
                // Inclure le message de l'exception dans le log
                String failureMessage = "Échec du serveur. Erreur : " + t.getMessage();
                connexionResponseLiveData.setValue(null);  // Indiquer un échec
                Log.e("ConnexionViewModel", failureMessage, t);  // Log avec stack trace
            }
        });
    }

}
