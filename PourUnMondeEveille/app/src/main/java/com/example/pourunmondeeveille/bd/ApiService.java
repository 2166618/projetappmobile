package com.example.pourunmondeeveille.bd;

import com.example.pourunmondeeveille.model.ConnexionRequest;
import com.example.pourunmondeeveille.model.ConnexionResponse;
import com.example.pourunmondeeveille.model.Utilisateur;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("auth/login/")  // Endpoint pour la connexion
    Call<ConnexionResponse> connexion(@Body ConnexionRequest request);

    @POST("users/create/")
    Call<ResponseBody> creerUtilisateur(@Body Utilisateur utilisateur);



}
