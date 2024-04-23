package com.example.pourunmondeeveille.bd;

import com.example.pourunmondeeveille.model.ConnexionRequest;
import com.example.pourunmondeeveille.model.ConnexionResponse;
import com.example.pourunmondeeveille.model.Utilisateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("utilisateurs")
    Call<List<Utilisateur>> getUtilisateurs();

    @POST("utilisateurs")
    Call<Utilisateur> postUtilisateur(@Body Utilisateur utilisateur);

    @POST("auth/connexion/")
    Call<ConnexionResponse> connexion(@Body ConnexionRequest request);


}
