package com.example.pourunmondeeveille.ui.familles;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public void fetchFamillesAccueil(String accessToken) {
        // Ajouter le token d'accès à l'en-tête Authorization
        String authorizationHeader = "Bearer " + accessToken;

        // Créer un OkHttpClient avec le token d'accès dans l'en-tête
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request newRequest = originalRequest.newBuilder()
                                .header("Authorization", authorizationHeader)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        // Créer une nouvelle instance de Retrofit avec le client OkHttpClient
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://projetapi.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Créer une instance de l'interface ApiService avec le nouveau Retrofit
        ApiService apiService = retrofit.create(ApiService.class);

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