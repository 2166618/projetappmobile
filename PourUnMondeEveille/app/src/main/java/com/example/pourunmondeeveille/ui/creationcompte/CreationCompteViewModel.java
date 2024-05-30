package com.example.pourunmondeeveille.ui.creationcompte;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.connexion.Utilisateur;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreationCompteViewModel extends ViewModel {
    private ApiService apiService;
    private MutableLiveData<Boolean> userCreated = new MutableLiveData<>();
    private MutableLiveData<String> errorMessages = new MutableLiveData<>();

    public CreationCompteViewModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<Boolean> isUserCreated() {
        return userCreated;
    }

    public LiveData<String> getErrorMessages() {
        return errorMessages;
    }

    public void createUser(String username, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            errorMessages.setValue("Les mots de passe ne correspondent pas.");
            return;
        }

        Utilisateur nouvelUtilisateur = new Utilisateur(username, email, password);

        Call<ResponseBody> call = apiService.creerUtilisateur(nouvelUtilisateur);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    userCreated.setValue(true);
                } else {
                    // Handle different errors based on status code or error body
                    errorMessages.setValue("Erreur de création de compte : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                errorMessages.setValue("Échec de la connexion au serveur.");
            }
        });
    }
}
