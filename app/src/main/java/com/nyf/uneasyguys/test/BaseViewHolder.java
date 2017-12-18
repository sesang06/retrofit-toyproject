package com.nyf.uneasyguys.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sesan on 2017-12-17.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTextView;
    public TextView createdTimeTextView;
    public BaseViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.item_context, parent, false));

        titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
        createdTimeTextView = (TextView) itemView.findViewById(R.id.created_time_text_view);

    }

}
