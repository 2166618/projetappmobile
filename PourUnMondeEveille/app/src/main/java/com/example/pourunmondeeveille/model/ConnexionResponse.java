package com.example.pourunmondeeveille.model;

import com.google.gson.annotations.SerializedName;

public class ConnexionResponse {
    @SerializedName("success")
    private boolean success;  // Indique si la connexion a réussi

    @SerializedName("message")
    private String message;  // Message d'erreur ou de succès

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
