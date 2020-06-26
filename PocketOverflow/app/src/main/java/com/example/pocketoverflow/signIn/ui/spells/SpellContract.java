package com.example.pocketoverflow.signIn.ui.spells;

import com.example.pocketoverflow.BasePresenter;
import com.example.pocketoverflow.BaseView;

import java.util.List;

public interface SpellContract {

    interface SpellView extends BaseView {
        void displayData(List<Spell> spells);
    }

    interface SpellPresenter extends BasePresenter<SpellView> {
    }
}
