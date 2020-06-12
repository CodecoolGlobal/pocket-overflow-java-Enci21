package com.example.pocketoverflow.signIn.ui.spells;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketoverflow.R;

import java.util.List;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.SpellHolder> {

    List<Spell> spells;

    public SpellAdapter(List<Spell> spells) {
        this.spells = spells;
    }

    @NonNull
    @Override
    public SpellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spell_item, parent, false);
        return new SpellAdapter.SpellHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellHolder holder, int position) {
        Spell current = spells.get(position);

        holder.spell.setText(current.getSpell());
        holder.effect.setText(current.getEffect());
        holder.type.setText(current.getType());
    }

    @Override
    public int getItemCount() {
        return spells.size();
    }

    public class SpellHolder extends RecyclerView.ViewHolder {

        SpellAdapter adapter;
        TextView spell;
        TextView type;
        TextView effect;


        public SpellHolder(@NonNull View itemView, SpellAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            spell = itemView.findViewById(R.id.spell);
            type = itemView.findViewById(R.id.type);
            effect = itemView.findViewById(R.id.effect);
        }
    }
}
