package com.example.pocketoverflow.signIn.ui.commonRoom;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketoverflow.R;
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


public class CommonRoomFragment extends Fragment implements CommonRoomContract.View {

    String house;
    String userName;
    View root;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.headOfHouse)
    TextView headOfHouse;

    @BindView(R.id.houseGhost)
    TextView houseGhost;

    @BindView(R.id.mascot)
    TextView mascot;

    @BindView(R.id.founder)
    TextView founder;

    @BindView(R.id.school)
    TextView school;

    @BindView(R.id.membersRecyclerView)
    RecyclerView membersRecyclerView;

    JsonPlaceHolderApi jsonPlaceHolderApi;
    private CommonRoomPresenter commonRoomPresenter;
    String apiKey;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MemberAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_commonroom, container, false);
        ButterKnife.bind(this, root);
        apiKey = "$2a$10$lxDvwgZJ/JrK2rKd9uNFzOQcCXds1WyJkvMU/dnyIbdvVSNrKjTjy";

        commonRoomPresenter = new CommonRoomPresenter();

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        house = sharedPref.getString("house", "");
        userName = sharedPref.getString("userName", "");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.potterapi.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        fetchData();


        return root;
    }

    private void fetchData() {
        compositeDisposable.add(jsonPlaceHolderApi.getHouseById("5a05e2b252f721a3cf2ea33f", apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<House>>() {
                    @Override
                    public void accept(List<House> houses) {
                        displayData(houses.get(0));
                    }
                }));
    }

    private void displayData(House house) {
        adapter = new MemberAdapter(house.getMembers());
        membersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        membersRecyclerView.setAdapter(adapter);

        headOfHouse.setText(house.getHeadOfHouse());
        name.setText(house.getName());
        houseGhost.setText(house.getHouseGhost());
        school.setText(house.getSchool());
        founder.setText(house.getFounder());
        mascot.setText(house.getMascot());
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}