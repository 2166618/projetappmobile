package com.example.pourunmondeeveille.bd;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)  // Temps d'attente pour établir une connexion
                    .readTimeout(30, TimeUnit.SECONDS)  // Temps d'attente pour lire une réponse
                    .writeTimeout(30, TimeUnit.SECONDS)  // Temps d'attente pour écrire des données
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://projetapi.vercel.app/")  // Remplace par l'URL de ton API
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)  // Utilise le client OkHttp avec les délais d'attente configurés
                    .build();
        }
        return retrofit;
    }
}

