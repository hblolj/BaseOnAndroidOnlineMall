package com.app.baseonandroidonlinemall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.cart.CartItem;
import com.app.baseonandroidonlinemall.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txj on 15/2/17.
 */
public class CheckoutAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<CartItem> items = new ArrayList<>();

    public CheckoutAdapter(Context context, List<CartItem> list) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.items.clear();
        this.items.addAll(list);
    }

    public CheckoutAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void addNewData(List<CartItem> list){
        this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = this.mInflater.inflate(R.layout.item_product_type1, null);
            holder.title = (TextView) view.findViewById(R.id.tv_title);
            holder.price = (TextView) view.findViewById(R.id.tv_price);
            holder.quantity = (TextView) view.findViewById(R.id.tv_num);
            holder.icon = (ImageView) view.findViewById(R.id.iv_image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        CartItem cartItem = items.get(position);
        Glide.with(mContext)
                .load(RetrofitService.HOME3_HOST + cartItem.getGoodpicture())
                .placeholder(R.drawable.placeholder_empty_square)
                .into(holder.icon);
        //Utils.getImage(mContext, holder.icon, list.get(position).goods.img);
        holder.title.setText(cartItem.getGoodname());
        holder.price.setText(String.format("价格：￥%.2f", Utils.tryParseDouble(cartItem.getCurrprice(), 0F)));
        holder.quantity.setText(String.format("数量：%s", cartItem.getQuantity()));

        return view;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    static class ViewHolder {
        ImageView icon;
        TextView title;
        TextView price;
        TextView quantity;
    }
}
