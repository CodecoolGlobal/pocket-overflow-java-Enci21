package com.example.pocketoverflow.signIn.ui.commonRoom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketoverflow.R;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {

    List<MembersItem> members;

    public MemberAdapter(List<MembersItem> members) {
        this.members = members;
    }

    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
        return new MemberAdapter.MemberHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        MembersItem member = members.get(position);
        holder.name.setText(member.getName());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class MemberHolder extends RecyclerView.ViewHolder {
        final MemberAdapter memberAdapter;
        TextView name;

        public MemberHolder(@NonNull View itemView, final MemberAdapter memberAdapter) {
            super(itemView);
            this.memberAdapter = memberAdapter;
            name = itemView.findViewById(R.id.memberName);
        }
    }
}
