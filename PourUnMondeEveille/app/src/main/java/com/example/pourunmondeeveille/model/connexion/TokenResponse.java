package com.example.pourunmondeeveille.model.connexion;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("access")
    private String accessToken;

    @SerializedName("refresh")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
