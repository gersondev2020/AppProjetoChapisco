package com.gautomation.chapiscorob.ui.Config;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gautomation.chapiscorob.R;

import static android.content.ContentValues.TAG;

public class ConfigViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConfigViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}