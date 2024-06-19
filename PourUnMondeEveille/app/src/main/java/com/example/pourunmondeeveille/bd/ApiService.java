package com.example.pourunmondeeveille.bd;

import com.example.pourunmondeeveille.model.connexion.ConnexionRequest;
import com.example.pourunmondeeveille.model.connexion.ConnexionResponse;
import com.example.pourunmondeeveille.model.connexion.TokenResponse;
import com.example.pourunmondeeveille.model.connexion.Utilisateur;
import com.example.pourunmondeeveille.model.enfants.Enfant;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;
import com.example.pourunmondeeveille.model.placements.Placement;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/token/")  // Endpoint pour la connexion
    Call<TokenResponse> connexion(@Body ConnexionRequest request);

    @POST("/users/create/")
    Call<ResponseBody> creerUtilisateur(@Body Utilisateur utilisateur);

    @GET("/familles-accueil-2/")
    Call<List<FamilleAccueil>> getFamillesAccueil();

    @GET("/enfants/")
    Call<List<Enfant>> getEnfants();

    @GET("/placements/")
    Call<List<Placement>> getPlacements();

}
