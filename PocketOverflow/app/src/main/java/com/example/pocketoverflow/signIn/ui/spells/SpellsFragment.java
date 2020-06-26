package com.example.pocketoverflow.signIn.ui.spells;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketoverflow.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpellsFragment extends Fragment implements SpellContract.SpellView {

    @BindView(R.id.loading)
    ProgressBar loading;
    private SpellAdapter adapter;
    private String house;
    private SpellPresenter presenter;

    @BindView(R.id.frameGrayoverlay)
    FrameLayout frameLayout;
    private ArrayList<Spell> spells = new ArrayList<>();

    @BindView(R.id.recyclerViewSpells)
    RecyclerView recyclerViewSpells;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_spells, container, false);
        ButterKnife.bind(this, root);
        presenter = new SpellPresenter(this);
        presenter.fetchData();

        if (savedInstanceState == null) {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            house = sharedPref.getString("house", "").replace("\"", "");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showLoading();
                    hideLoading();
                }
            }, 2000);
        } else {
            hideLoading();
            house = savedInstanceState.getString("HOUSE");
            adapter = new SpellAdapter(savedInstanceState.getParcelableArrayList("SPELLS"));
        }

        switch (house) {
            case "Gryffindor":
                root.setBackgroundResource(R.drawable.gryffindor_side_nav);
                break;
            case "Hufflepuff":
                root.setBackgroundResource(R.drawable.huffle_side_nav);
                break;
            case "Ravenclaw":
                root.setBackgroundResource(R.drawable.ravenclaw_side_nav);
                break;
            case "Slytherin":
                root.setBackgroundResource(R.drawable.slytherin_side_nav);
                break;
        }
        return root;
    }


    @Override
    public void displayData(List<Spell> spells) {
        adapter = new SpellAdapter(spells);
        recyclerViewSpells.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSpells.setAdapter(adapter);
    }

    public void showLoading() {
        frameLayout.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        recyclerViewSpells.setVisibility(View.GONE);
    }

    public void hideLoading() {
        frameLayout.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        recyclerViewSpells.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("HOUSE", house);
        outState.putParcelableArrayList("SPELLS", spells);
    }
}