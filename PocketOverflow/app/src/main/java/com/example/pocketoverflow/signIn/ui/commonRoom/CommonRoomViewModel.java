package com.example.pocketoverflow.signIn.ui.commonRoom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CommonRoomViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CommonRoomViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}