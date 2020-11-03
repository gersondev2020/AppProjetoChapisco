package com.gautomation.chapiscorob.ui.ControleManual;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ControleManualViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ControleManualViewModel() {
        mText = new MutableLiveData<>();

        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}