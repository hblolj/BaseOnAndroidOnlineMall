package com.app.baseonandroidonlinemall.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.bean.hot.hotBrandItem;
import com.app.baseonandroidonlinemall.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2016/8/15.
 */
public class HotGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    public ArrayList<hotBrandItem> hotBrandList = new ArrayList<>();
    private Intent mIntent;
    private Bundle mBundle;


    public void setNewData(List<hotBrandItem> hots){
        this.hotBrandList.clear();
        this.hotBrandList.addAll(hots);
        notifyDataSetChanged();
    }

    public HotGridViewAdapter(Context context) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public HotGridViewAdapter(List<hotBrandItem> hots, Context context) {
        this.hotBrandList.clear();
        this.hotBrandList.addAll(hots);
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return hotBrandList.size();
    }

    @Override
    public Object getItem(int position) {
        return hotBrandList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_hot_gv, null);
            holder.ll_grid = (LinearLayout) convertView.findViewById(R.id.ll_grid);
            holder.ib_icon = (ImageView) convertView.findViewById(R.id.grid_icon);
            holder.tx_name = (TextView) convertView.findViewById(R.id.grid_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ll_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String) v.getTag(R.id.HotBrand_Id);
                String name = (String) v.getTag(R.id.HotBrand_Name);
                Toast.makeText(mContext, id + " : " + name, Toast.LENGTH_SHORT).show();
//                mIntent = new Intent(mContext, ProductListActivity.class);
//                mBundle = new Bundle();
//                mBundle.putInt(ProductListActivity.SEARCH_ARG_BY, ProductListActivity.BRAND_CATEGORY);
//                mBundle.putString(ProductListActivity.SEARCH_ARG_CATEGORY_ID, id);
//                mBundle.putString(ProductListActivity.SEARCH_ARG_CATEGORY_NAME, name);
//                mIntent.putExtras(mBundle);
//                if (mIntent != null){
//                    mContext.startActivity(mIntent);
//                }
            }
        });
        hotBrandItem item = hotBrandList.get(position);
        //适配数据
        holder.tx_name.setText(item.getName());
        Glide.with(mContext)
                .load(item.getImg())
                .centerCrop()
                .into(holder.ib_icon);
        int width = ViewUtils.getDefaultDisplay(true, mContext);
        ViewUtils.setLayoutParams(holder.ib_icon, 0, 4, 0, 2, width/6, width/6);
        ViewUtils.setLayoutParams(holder.tx_name, 5, 0, 5, 2, width/6, LinearLayout.LayoutParams.WRAP_CONTENT);

        holder.ll_grid.setTag(R.id.HotBrand_Id, item.getId());
        holder.ll_grid.setTag(R.id.HotBrand_Name, item.getName());
        return convertView;
    }

    public class ViewHolder{
        LinearLayout ll_grid;
        ImageView ib_icon;
        TextView tx_name;
    }
}
