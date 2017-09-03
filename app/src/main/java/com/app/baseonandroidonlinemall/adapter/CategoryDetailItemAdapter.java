package com.app.baseonandroidonlinemall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.baseonandroidonlinemall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/5/7.
 */

public class CategoryDetailItemAdapter extends RecyclerView.Adapter<CategoryDetailItemAdapter.ViewHolder> implements View.OnClickListener {

    private List<String> mDetailItem = new ArrayList<>();
    private Context mContext;
    private onItemClickListener mOnItemClickListener;

    public CategoryDetailItemAdapter(List<String> detailItem, Context context) {
        mContext = context;
        mDetailItem.clear();
        mDetailItem.addAll(detailItem);
    }

    @Override
    public CategoryDetailItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_category_detail_item_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_item.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryDetailItemAdapter.ViewHolder holder, int position) {
        holder.tv_item.setTag(mDetailItem.get(position));
        holder.tv_item.setText(mDetailItem.get(position));
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    public static interface onItemClickListener{
        void onItemClick(View view, String item);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDetailItem.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_item;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
