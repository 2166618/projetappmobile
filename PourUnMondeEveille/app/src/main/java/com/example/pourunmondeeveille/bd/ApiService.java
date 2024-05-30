package com.example.pourunmondeeveille.bd;

import com.example.pourunmondeeveille.model.connexion.ConnexionRequest;
import com.example.pourunmondeeveille.model.connexion.ConnexionResponse;
import com.example.pourunmondeeveille.model.connexion.Utilisateur;
import com.example.pourunmondeeveille.model.enfants.Enfant;
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

    @GET("/familles-accueil/")
    Call<List<FamilleAccueil>> getFamillesAccueil();

    @GET("/enfants/")
    Call<List<Enfant>> getEnfants();

}
