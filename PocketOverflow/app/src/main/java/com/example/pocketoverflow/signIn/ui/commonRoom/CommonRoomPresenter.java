package com.example.pocketoverflow.signIn.ui.commonRoom;

import android.app.Application;

import com.example.pocketoverflow.BuildConfig;
import com.example.pocketoverflow.apiservice.DataApiService;
import com.example.pocketoverflow.apiservice.RetrofitClient;
import com.example.pocketoverflow.roomDB.UserRepository;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommonRoomPresenter implements CommonRoomContract.CommonRoomPresenter<CommonRoomContract.CommonRoomView> {

    CommonRoomContract.CommonRoomView view;
    UserRepository repository;
    DataApiService dataApiService;

    public CommonRoomPresenter(CommonRoomContract.CommonRoomView view, Application app) {
        this.view = view;
        repository = new UserRepository(app);
        dataApiService = RetrofitClient.getClient().create(DataApiService.class);
    }

    public void fetchData() {
        dataApiService.getHouseById(view.getHouseId(), BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<House>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<House> houses) {
                        view.displayData(houses.get(0));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                });
    }
}
