package com.example.pocketoverflow.signIn.ui.commonRoom;

import com.example.pocketoverflow.BasePresenter;
import com.example.pocketoverflow.BaseView;

public interface CommonRoomContract {

    interface CommonRoomView extends BaseView {

        String getHouseId();

        void showLoading();

        void hideLoading();

        void displayData(House house);

    }

    interface CommonRoomPresenter<V> extends BasePresenter<V> {

    }
}
