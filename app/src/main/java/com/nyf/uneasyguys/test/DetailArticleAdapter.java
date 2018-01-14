package com.nyf.uneasyguys.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nyf.uneasyguys.test.Model.ArticleModel;

import java.util.List;

/**
 * Created by sesan on 2018-01-14.
 */

public class DetailArticleAdapter extends BaseAdapter {

    public DetailArticleAdapter(List<ArticleModel> articleModels,Context context){
       super(articleModels,context);
    }


    @Override
    public  BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_context,parent,false);

        final BaseViewHolder baseViewHolder = new BaseViewHolder(v);

        baseViewHolder.itemView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final int position = baseViewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(v.getContext(), ArticleDetailActivity.class);
                    intent.putExtra("id", articleModels.get(position).getId());
                    v.getContext().startActivity(intent);
                }

            }
        });

        return baseViewHolder;
    }


}
