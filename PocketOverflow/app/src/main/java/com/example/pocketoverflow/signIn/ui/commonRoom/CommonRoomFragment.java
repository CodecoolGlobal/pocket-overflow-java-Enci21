package com.example.pocketoverflow.signIn.ui.commonRoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pocketoverflow.R;

public class CommonRoomFragment extends Fragment {

    private CommonRoomViewModel commonRoomViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        commonRoomViewModel =
                ViewModelProviders.of(this).get(CommonRoomViewModel.class);
        View root = inflater.inflate(R.layout.fragment_commonroom, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        commonRoomViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}