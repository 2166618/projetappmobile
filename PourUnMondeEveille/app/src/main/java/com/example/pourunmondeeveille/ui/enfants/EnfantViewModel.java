package com.example.pourunmondeeveille.ui.enfants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pourunmondeeveille.bd.ApiService;
import com.example.pourunmondeeveille.bd.RetrofitClient;

import retrofit2.Retrofit;

public class EnfantViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private ApiService apiService;

    public EnfantViewModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);

        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

//    public void getItems(Callback<List<YourModel>> callback) {
//        apiService.getItems().enqueue(callback);
//    }
//
//    public void saveItem(YourModel item, Callback<YourModel> callback) {
//        apiService.postItem(item).enqueue(callback);
//    }
}