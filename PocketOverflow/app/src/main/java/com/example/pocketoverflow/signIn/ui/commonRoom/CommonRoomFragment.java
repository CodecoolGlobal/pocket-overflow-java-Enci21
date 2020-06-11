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

import com.example.pocketoverflow.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    private CommonRoomPresenter commonRoomPresenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_commonroom, container, false);
        ButterKnife.bind(this, root);
        String apiKey = "$2a$10$lxDvwgZJ/JrK2rKd9uNFzOQcCXds1WyJkvMU/dnyIbdvVSNrKjTjy";

        commonRoomPresenter = new CommonRoomPresenter();

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        house = sharedPref.getString("house", "");
        userName = sharedPref.getString("userName", "");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.potterapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<House>> call = jsonPlaceHolderApi.getHouseById("5a05e2b252f721a3cf2ea33f", apiKey);
        call.enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                }
                headOfHouse.setText(response.body().get(0).getHeadOfHouse());
                name.setText(response.body().get(0).getName());
                houseGhost.setText(response.body().get(0).getHouseGhost());
                school.setText(response.body().get(0).getSchool());
                founder.setText(response.body().get(0).getFounder());
                mascot.setText(response.body().get(0).getMascot());
            }

            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {
                System.out.println(t);
            }

        });


        return root;
    }
}