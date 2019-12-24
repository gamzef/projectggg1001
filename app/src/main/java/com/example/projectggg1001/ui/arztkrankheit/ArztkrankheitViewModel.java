package com.example.projectggg1001.ui.arztkrankheit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArztkrankheitViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ArztkrankheitViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is arztkrankheit fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}