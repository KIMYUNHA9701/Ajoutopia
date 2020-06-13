package com.example.firebasetest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasetest.MemberInfo;
import com.example.firebasetest.R;

import java.util.List;

public class MypageAdapter extends RecyclerView.Adapter<MypageAdapter.PostViewHolder> {

    private List<MemberInfo> datas;
    public MypageAdapter(List<MemberInfo> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mypage,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        MemberInfo data = datas.get(position);
        holder.name.setText(data.getName());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        private TextView name;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (itemView).findViewById(R.id.myName);

        }
    }
}
