package com.app.baseonandroidonlinemall.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.baseonandroidonlinemall.R;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by hblolj on 2017/6/13.
 */

public class MyViewHolder extends BaseViewHolder {

    public ImageView iv_big_picture;
    public RecyclerView rl_horizontal;

    public MyViewHolder(View view) {
        super(view);
        iv_big_picture = (ImageView) view.findViewById(R.id.iv_good_group_big_picture);
        rl_horizontal = (RecyclerView) view.findViewById(R.id.rl_good_group_horizontal);
    }
}
