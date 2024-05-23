package com.example.pourunmondeeveille.ui.apropos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AProposViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AProposViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a propos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}