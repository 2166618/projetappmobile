package com.example.pourunmondeeveille.ui.familles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FamillesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FamillesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is familles fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}