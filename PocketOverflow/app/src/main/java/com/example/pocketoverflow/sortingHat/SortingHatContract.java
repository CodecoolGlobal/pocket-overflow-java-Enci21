package com.example.pocketoverflow.sortingHat;

import com.example.pocketoverflow.BasePresenter;
import com.example.pocketoverflow.BaseView;

public interface SortingHatContract {

    interface SortingHatView extends BaseView {
        void setHouse(String house);
    }

    interface SortingHatPresenter extends BasePresenter<SortingHatView> {

    }
}
