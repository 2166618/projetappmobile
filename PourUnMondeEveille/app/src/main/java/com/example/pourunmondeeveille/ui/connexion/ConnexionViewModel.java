package com.example.pourunmondeeveille.ui.connexion;

import static com.example.pourunmondeeveille.ui.connexion.ConnexionFragment.getAppContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.connexion.ConnexionRequest;
import com.example.pourunmondeeveille.model.connexion.TokenResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConnexionViewModel extends ViewModel {
    private ApiService apiService;
    private MutableLiveData<TokenResponse> connexionResponseLiveData = new MutableLiveData<>();

    public ConnexionViewModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public MutableLiveData<TokenResponse> getConnexionResponse() {
        return connexionResponseLiveData;
    }

    public void connexionUtilisateur(String nomUtilisateur, String motDePasse) {
        ConnexionRequest connexionRequest = new ConnexionRequest(nomUtilisateur, motDePasse);

        apiService.connexion(connexionRequest).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    TokenResponse tokenResponse = response.body();
                    if (tokenResponse != null) {
                        String accessToken = tokenResponse.getAccessToken();
                        String refreshToken = tokenResponse.getRefreshToken();

                        // Stocker les tokens dans SharedPreferences ou tout autre moyen de stockage sécurisé
                        setAuthTokens(accessToken, refreshToken);

                        // Informer les observateurs de la réussite de la connexion
                        connexionResponseLiveData.setValue(tokenResponse);
                    }
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
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                // Inclure le message de l'exception dans le log
                String failureMessage = "Échec du serveur. Erreur : " + t.getMessage();
                connexionResponseLiveData.setValue(null);  // Indiquer un échec
                Log.e("ConnexionViewModel", failureMessage, t);  // Log avec stack trace
            }
        });
    }

    private void setAuthTokens(String accessToken, String refreshToken) {
        // Stockez les tokens dans SharedPreferences ou tout autre moyen de stockage sécurisé
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("accessToken", accessToken);
        editor.putString("refreshToken", refreshToken);
        editor.apply();
    }

}
