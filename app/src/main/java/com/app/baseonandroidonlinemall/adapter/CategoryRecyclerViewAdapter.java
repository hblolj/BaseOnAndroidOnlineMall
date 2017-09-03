package com.app.baseonandroidonlinemall.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.bean.CategoryData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/5/7.
 */

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private List<CategoryData> mCategoryDatas = new ArrayList<>();
    private Context mContext;
    private OnBigPictureClickListener mOnBigPictureClickListener = null;

    public CategoryRecyclerViewAdapter(List<CategoryData> categoryDatas, Context context) {
        mContext = context;
        mCategoryDatas.clear();
        mCategoryDatas.addAll(categoryDatas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_category_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImageView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryData data = mCategoryDatas.get(position);
        Glide.with(mContext)
                .load(data.getBig_picture())
                .centerCrop()
                .into(holder.mImageView);
        holder.mImageView.setTag(position);
        CategoryDetailItemAdapter adapter = new CategoryDetailItemAdapter(data.getItems(), mContext);
        adapter.setOnItemClickListener(new CategoryDetailItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, String item) {
                mOnBigPictureClickListener.onItemClick(view, item);

            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        holder.mDetailItem.setLayoutManager(layoutManager);
        holder.mDetailItem.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (mOnBigPictureClickListener != null){
            mOnBigPictureClickListener.onBigPictureClick(v, (Integer) v.getTag());
        }
    }

    public void setItems(List<CategoryData> data) {
        mCategoryDatas.clear();
        mCategoryDatas.addAll(data);
        notifyDataSetChanged();
    }

    public static interface OnBigPictureClickListener{
        void onBigPictureClick(View view, int position);

        void onItemClick(View view, String item);
    }

    public void setOnBigPictureClickListener(OnBigPictureClickListener onBigPictureClickListener) {
        mOnBigPictureClickListener = onBigPictureClickListener;
    }

    @Override
    public int getItemCount() {
        return mCategoryDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private RecyclerView mDetailItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_category);
            mDetailItem = (RecyclerView) itemView.findViewById(R.id.rl_category_item);
        }
    }
}
