package com.example.pocketoverflow.signIn.ui.commonRoom;

import com.example.pocketoverflow.BasePresenter;
import com.example.pocketoverflow.BaseView;

public interface CommonRoomContract {

    interface CommonRoomView extends BaseView {

        void showLoading();

        void hideLoading();

        String getHouseId();

        void displayData(House house);

    }

    interface CommonRoomPresenter<V> extends BasePresenter<V> {

    }
}
