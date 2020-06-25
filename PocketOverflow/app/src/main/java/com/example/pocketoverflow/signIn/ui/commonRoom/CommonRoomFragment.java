package com.example.pocketoverflow.signIn.ui.commonRoom;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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


public class CommonRoomFragment extends Fragment {

    String house;
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

    @BindView(R.id.logoBack)
    ImageView logo;

    JsonPlaceHolderApi jsonPlaceHolderApi;
    String apiKey;
    String houseId;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MemberAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_commonroom, container, false);
        ButterKnife.bind(this, root);
        apiKey = "$2a$10$lxDvwgZJ/JrK2rKd9uNFzOQcCXds1WyJkvMU/dnyIbdvVSNrKjTjy";

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        house = sharedPref.getString("house", "").replace("\"", "");

        if (house.equals("Gryffindor")) {
            root.setBackgroundResource(R.drawable.gryffindor_side_nav);
            logo.setImageResource(R.drawable.gryffindor_logo);
        } else if (house.equals("Hufflepuff")) {
            root.setBackgroundResource(R.drawable.huffle_side_nav);
            logo.setImageResource(R.drawable.hufflepuff_logo);
        } else if (house.equals("Ravenclaw")) {
            root.setBackgroundResource(R.drawable.ravenclaw_side_nav);
            logo.setImageResource(R.drawable.ravenclaw_logo);
        } else if (house.equals("Slytherin")) {
            root.setBackgroundResource(R.drawable.slytherin_side_nav);
            logo.setImageResource(R.drawable.slytherin_logo);
        }

        switch (house) {
            case "Gryffindor":
                houseId = "5a05e2b252f721a3cf2ea33f";
                break;
            case "Ravenclaw":
                houseId = "5a05da69d45bd0a11bd5e06f";
                break;
            case "Hufflepuff":
                houseId = "5a05dc58d45bd0a11bd5e070";
                break;
            case "Slytherin":
                houseId = "5a05dc8cd45bd0a11bd5e071";
                break;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.potterapi.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        fetchData();

        return root;
    }

    private void fetchData() {
        compositeDisposable.add(jsonPlaceHolderApi.getHouseById(houseId, apiKey)
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