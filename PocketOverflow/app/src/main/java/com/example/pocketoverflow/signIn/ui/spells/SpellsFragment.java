package com.example.pocketoverflow.signIn.ui.spells;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketoverflow.R;
import com.example.pocketoverflow.signIn.ui.JsonPlaceHolderApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpellsFragment extends Fragment {

    JsonPlaceHolderApi jsonPlaceHolderApi;
    String apiKey;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    SpellAdapter adapter;

    @BindView(R.id.recyclerViewSpells)
    RecyclerView recyclerViewSpells;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_spells, container, false);
        ButterKnife.bind(this, root);
        apiKey = "$2a$10$lxDvwgZJ/JrK2rKd9uNFzOQcCXds1WyJkvMU/dnyIbdvVSNrKjTjy"; //environment variable?

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.potterapi.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        fetchData();

        return root;
    }

    private void fetchData() {
        compositeDisposable.add(jsonPlaceHolderApi.getSpells(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Spell>>() {
                    @Override
                    public void accept(List<Spell> spells) throws Exception {
                        displayData(spells);
                    }
                }));
    }

    private void displayData(List<Spell> spells) {

        adapter = new SpellAdapter(spells);
        recyclerViewSpells.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSpells.setAdapter(adapter);

    }
}