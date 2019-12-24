package com.example.projectggg1001.ui.mmedikamente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MMedikamenteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MMedikamenteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mmedikamente fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}