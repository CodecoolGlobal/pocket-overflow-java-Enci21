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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CommonRoomFragment extends Fragment implements CommonRoomContract.CommonRoomView {

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

    private String houseId;
    private String house;
    private View root;
    private ArrayList<MembersItem> members = new ArrayList<>();
    private MemberAdapter adapter;
    private CommonRoomPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_commonroom, container, false);
        ButterKnife.bind(this, root);

        if (savedInstanceState == null) {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            house = sharedPref.getString("house", "").replace("\"", "");
            presenter = new CommonRoomPresenter(this, getActivity().getApplication());

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
            presenter.fetchData();

        } else {
            houseId = savedInstanceState.getString("HOUSEID");
            house = savedInstanceState.getString("HOUSE");
            headOfHouse.setText(savedInstanceState.getString("HEADOFHOUSE"));
            name.setText(savedInstanceState.getString("NAME"));
            houseGhost.setText(savedInstanceState.getString("HOUSEGHOST"));
            school.setText(savedInstanceState.getString("SCHOOL"));
            founder.setText(savedInstanceState.getString("FOUNDER"));
            mascot.setText(savedInstanceState.getString("MASCOT"));
            members.addAll(savedInstanceState.getParcelableArrayList("MEMBERS"));
            adapter = new MemberAdapter(members);
            membersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            membersRecyclerView.setAdapter(adapter);
        }

        switch (house) {
            case "Gryffindor":
                root.setBackgroundResource(R.drawable.gryffindor_side_nav);
                logo.setImageResource(R.drawable.gryffindor_logo);
                break;
            case "Hufflepuff":
                root.setBackgroundResource(R.drawable.huffle_side_nav);
                logo.setImageResource(R.drawable.hufflepuff_logo);
                break;
            case "Ravenclaw":
                root.setBackgroundResource(R.drawable.ravenclaw_side_nav);
                logo.setImageResource(R.drawable.ravenclaw_logo);
                break;
            case "Slytherin":
                root.setBackgroundResource(R.drawable.slytherin_side_nav);
                logo.setImageResource(R.drawable.slytherin_logo);
                break;
        }
        return root;
    }


    @Override
    public void displayData(House house) {
        members = (ArrayList<MembersItem>) house.getMembers();
        adapter = new MemberAdapter(members);
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
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("HOUSEID", houseId);
        outState.putString("HOUSE", house);
        outState.putString("HEADOFHOUSE", headOfHouse.getText().toString());
        outState.putString("NAME", name.getText().toString());
        outState.putString("HOUSEGHOST", houseGhost.getText().toString());
        outState.putString("SCHOOL", school.getText().toString());
        outState.putString("FOUNDER", founder.getText().toString());
        outState.putString("MASCOT", mascot.getText().toString());
        outState.putParcelableArrayList("MEMBERS", members);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getHouseId() {
        return houseId;
    }
}