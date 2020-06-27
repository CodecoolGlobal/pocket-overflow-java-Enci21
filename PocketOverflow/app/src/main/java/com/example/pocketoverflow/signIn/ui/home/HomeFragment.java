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


public class HomeFragment extends Fragment {

    @BindView(R.id.patronusTextView)
    TextView patronusTextView;

    @BindView(R.id.myHouse)
    TextView house;

    @BindView(R.id.userName)
    TextView userName;

    @BindView(R.id.animagus)
    TextView animagus;

    @BindView(R.id.animagusText)
    TextView animagusText;

    @BindView(R.id.dumbledoresArmy)
    ImageView dumbledoresArmy;

    @BindView(R.id.orderOfThePhoenix)
    ImageView orderOfThePhoenix;

    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        patronusTextView.setText(sharedPref.getString("patronus", ""));
        userName.setText(sharedPref.getString("userName", ""));
        house.setText(sharedPref.getString("house", "").replace("\"", ""));
        if (!sharedPref.getString("animagus", "").equals("")) {
            animagus.setText(sharedPref.getString("animagus", ""));
            animagus.setVisibility(View.VISIBLE);
            animagusText.setVisibility(View.VISIBLE);

        }
        if (sharedPref.getBoolean("DA", false)) {
            dumbledoresArmy.setVisibility(View.VISIBLE);
        }
        if (sharedPref.getBoolean("OF", false)) {
            orderOfThePhoenix.setVisibility(View.VISIBLE);
        }


        if (house.getText().equals("Gryffindor")) {
            view.setBackgroundResource(R.drawable.gryffindor_side_nav);
        } else if (house.getText().equals("Hufflepuff")) {
            view.setBackgroundResource(R.drawable.huffle_side_nav);
        } else if (house.getText().equals("Ravenclaw")) {
            view.setBackgroundResource(R.drawable.ravenclaw_side_nav);
        } else if (house.getText().equals("Slytherin")) {
            view.setBackgroundResource(R.drawable.slytherin_side_nav);
        }
        return view;
    }
}