package com.example.pocketoverflow.signIn.ui.spells;

import com.example.pocketoverflow.BuildConfig;
import com.example.pocketoverflow.apiservice.DataApiService;
import com.example.pocketoverflow.apiservice.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SpellPresenter implements SpellContract.SpellPresenter {

    SpellContract.SpellView view;
    Retrofit retrofit = RetrofitClient.getClient();
    DataApiService dataApiService = retrofit.create(DataApiService.class);

    public SpellPresenter(SpellContract.SpellView view) {
        this.view = view;
    }

    public void fetchData() {
        dataApiService.getSpells(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Spell>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Spell> spells) {
                        view.setSpells((ArrayList<Spell>) spells);
                        view.displayData(spells);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
