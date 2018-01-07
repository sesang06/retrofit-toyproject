package com.nyf.uneasyguys.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by sesan on 2017-12-17.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTextView;
    public TextView createdTimeTextView;
    public TextView userTextView;
    public TextView pointTextView;
    public ImageView profileImageView;

    public BaseViewHolder(View itemView) {

        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
        createdTimeTextView = (TextView) itemView.findViewById(R.id.created_time_text_view);
        userTextView = (TextView) itemView.findViewById(R.id.user_text_view);
        profileImageView = (ImageView) itemView.findViewById(R.id.user_image_view);
        pointTextView = (TextView) itemView.findViewById(R.id.point_text_view);

    }

}
