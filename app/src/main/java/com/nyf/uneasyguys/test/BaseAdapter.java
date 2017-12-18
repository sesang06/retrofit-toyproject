package com.nyf.uneasyguys.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyf.uneasyguys.test.Model.ArticleModel;

/**
 * Created by sesan on 2017-12-17.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private ArticleModel[] articleModels;
    public BaseAdapter(ArticleModel[] articleModels){
        articleModels = articleModels;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_context,parent,false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(parent.getContext(),parent);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.createdTimeTextView.setText(articleModels[position].getCreatedTime());
        holder.titleTextView.setText(articleModels[position].getText());
    }

    @Override
    public int getItemCount() {
        return articleModels.length;
    }
}
