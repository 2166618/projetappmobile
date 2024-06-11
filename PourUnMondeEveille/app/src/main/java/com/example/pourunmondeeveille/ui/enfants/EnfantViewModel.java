package com.example.pourunmondeeveille.ui.enfants;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.enfants.Enfant;

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

    public void fetchEnfants(String accessToken) {
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
                .baseUrl("https://projetapi.vercel.app/")  // Remplacez par l'URL de votre API
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Créer une instance de l'interface ApiService avec le nouveau Retrofit
        ApiService apiService = retrofit.create(ApiService.class);

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