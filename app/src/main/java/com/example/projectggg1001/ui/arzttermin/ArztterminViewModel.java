package com.example.projectggg1001.ui.arzttermin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArztterminViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ArztterminViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is arzttermin fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}