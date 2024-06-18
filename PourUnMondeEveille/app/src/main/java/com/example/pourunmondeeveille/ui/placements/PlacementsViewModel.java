package com.example.pourunmondeeveille.ui.placements;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.enfants.Enfant;
import com.example.pourunmondeeveille.model.placements.Placement;

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

public class PlacementsViewModel extends ViewModel {
    private static final String TAG = "PlacementsViewModel";
    private ApiService apiService;
    private final MutableLiveData<List<Placement>> placementsLiveData = new MutableLiveData<>();
    private List<Placement> PlacementsList = new ArrayList<>();
    private List<Placement> originalPlacementsList = new ArrayList<>();

    public void setPlacementsLiveData(List<Placement> placements) {
        PlacementsList = placements;
        originalPlacementsList = new ArrayList<>(placements); // Met à jour la liste originale
        placementsLiveData.setValue(placements);
    }

    public List<Placement> getOriginalPlacementsList() {
        return originalPlacementsList;
    }

    public PlacementsViewModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public MutableLiveData<List<Placement>> getPlacementsLiveData() {
        return placementsLiveData;
    }

    public void fetchPlacements(String accessToken){
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

        Call<List<Placement>> call = apiService.getPlacements();
        call.enqueue(new Callback<List<Placement>>() {
            @Override
            public void onResponse(Call<List<Placement>> call, Response<List<Placement>> response) {
                if (response.isSuccessful()) {
                    List<Placement> placements = response.body();
                    setPlacementsLiveData(placements);
                    Log.d(TAG, "fetchPlacements: Succes fetch placements");
                } else {
                    Log.e(TAG, "fetchPlacements: Reponse infructueuse, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Placement>> call, Throwable t) {
                Log.e(TAG, "fetchPlacements: Fail fetch placements", t);
            }
        });
    }
}
