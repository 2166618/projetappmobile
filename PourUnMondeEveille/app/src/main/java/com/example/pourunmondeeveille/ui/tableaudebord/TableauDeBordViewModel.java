package com.example.pourunmondeeveille.ui.tableaudebord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TableauDeBordViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TableauDeBordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Tableau de bord fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}