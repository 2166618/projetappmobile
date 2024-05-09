package com.example.pourunmondeeveille.bd;

import com.example.pourunmondeeveille.model.ConnexionRequest;
import com.example.pourunmondeeveille.model.ConnexionResponse;
import com.example.pourunmondeeveille.model.Utilisateur;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("auth/login/")  // Endpoint pour la connexion
    Call<ConnexionResponse> connexion(@Body ConnexionRequest request);

    @POST("users/create/")
    Call<ResponseBody> creerUtilisateur(@Body Utilisateur utilisateur);

    @GET("/familles-accueil/")  // L'URL de votre endpoint
    Call<List<FamilleAccueil>> getFamillesAccueil();

}
