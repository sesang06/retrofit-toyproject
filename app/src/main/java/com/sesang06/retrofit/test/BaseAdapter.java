package com.sesang06.retrofit.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sesang06.retrofit.test.Model.ArticleModel;

import java.util.List;

/**
 * Created by sesan on 2017-12-17.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    protected List<ArticleModel> articleModels;
    private Context context;



    public BaseAdapter(List<ArticleModel> articleModels,Context context){
        this.context = context;
        this.articleModels = articleModels;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_context,parent,false);

        BaseViewHolder baseViewHolder = new BaseViewHolder(v);
        baseViewHolder.itemView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext() , ArticleListActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.createdTimeTextView.setText(articleModels.get(position).getCreatedTime());
        holder.titleTextView.setText(articleModels.get(position).getText());
        holder.userTextView.setText(articleModels.get(position).getUser());
        holder.pointTextView.setText("point: "+articleModels.get(position).getPoint());
        Glide.with(context)
                .load(articleModels.get(position).getImage())
                .into(holder.profileImageView);

    }

    @Override
    public int getItemCount() {
        return articleModels.size();
    }
}
