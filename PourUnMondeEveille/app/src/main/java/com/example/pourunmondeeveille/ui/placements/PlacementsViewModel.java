package com.example.pourunmondeeveille.ui.placements;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;
import com.example.pourunmondeeveille.model.placements.Placement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class PlacementsViewModel extends ViewModel {
    private static final String TAG = "PlacementsViewModel";
    private ApiService apiService;
    private final MutableLiveData<List<Placement>> placementsLiveData = new MutableLiveData<>();
    private List<Placement> PlacementsList = new ArrayList<>();
    private List<Placement> originalPlacementsList = new ArrayList<>();

    public void setFamillesAccueilLiveData(List<Placement> placements) {
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

    }
}
