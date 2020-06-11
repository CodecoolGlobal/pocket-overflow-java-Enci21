package com.example.pocketoverflow.signIn.ui.spells;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpellsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpellsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}