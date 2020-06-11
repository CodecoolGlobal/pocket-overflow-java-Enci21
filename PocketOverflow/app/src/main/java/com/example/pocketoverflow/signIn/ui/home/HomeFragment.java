package com.example.pocketoverflow.signIn.ui.home;

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

import com.example.pocketoverflow.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements HomeContract.View {

    @BindView(R.id.patronusTextView)
    TextView patronusTextView;
    @BindView(R.id.myHouse)
    TextView house;
    @BindView(R.id.wand)
    TextView wand;
    @BindView(R.id.animagus)
    TextView animagus;
    @BindView(R.id.dumbledoresArmy)
    ImageView dumbledoresArmy;
    @BindView(R.id.orderOfThePhoenix)
    ImageView orderOfThePhoenix;
    private HomePresenter homePresenter;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        homePresenter = new HomePresenter(this);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        patronusTextView.setText(sharedPref.getString("patronus", ""));
        house.setText(sharedPref.getString("house", ""));
        animagus.setText(sharedPref.getString("animagus", ""));
        if (sharedPref.getBoolean("DA", false)) {
            dumbledoresArmy.setVisibility(View.VISIBLE);
        }
        if (sharedPref.getBoolean("OF", false)) {
            orderOfThePhoenix.setVisibility(View.VISIBLE);
        }
        return view;
    }
}