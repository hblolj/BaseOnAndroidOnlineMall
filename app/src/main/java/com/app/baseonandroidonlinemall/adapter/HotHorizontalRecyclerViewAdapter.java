package com.app.baseonandroidonlinemall.adapter;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.baseonandroidonlinemall.R;
import com.app.baseonandroidonlinemall.api.RetrofitService;
import com.app.baseonandroidonlinemall.api.bean.Good;
import com.app.baseonandroidonlinemall.api.bean.hot.spceialAdItem;
import com.app.baseonandroidonlinemall.utils.ViewUtils;
import com.app.baseonandroidonlinemall.widget.SolidImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;
/**
 * Created by hblolj on 2017/5/9.
 * Hot页商品组下横向滑动商品组的RecyclerView的适配器
 */

public class HotHorizontalRecyclerViewAdapter extends BaseQuickAdapter<spceialAdItem.Items, BaseViewHolder>{


    public HotHorizontalRecyclerViewAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public HotHorizontalRecyclerViewAdapter(@LayoutRes int layoutResId, @Nullable List<spceialAdItem.Items> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, spceialAdItem.Items items) {

        int width = ViewUtils.getDefaultDisplay(true,mContext);// 获取屏幕宽度
        int height = ViewUtils.getDefaultDisplay(false, mContext);//获取屏幕高度
        Logger.d("smallpicture: " + RetrofitService.HOME3_HOST + items.getSmallpicture());
        String smallpicture = items.getSmallpicture().replace("\\", "/");
        Logger.d("smallpicture: " + RetrofitService.HOME3_HOST + smallpicture);
        String str = RetrofitService.HOME3_HOST + smallpicture;
        if ("更多好货".equals(items.getAlias())){
            //最后一个元素，显示更多好货图片
            helper.getView(R.id.ll_item_good).setVisibility(View.GONE);
            helper.getView(R.id.ll_more).setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(str)
                    .fitCenter()
                    .into((ImageView) helper.getView(R.id.iv_more));
            ((ImageView) helper.getView(R.id.iv_more)).setImageResource(R.drawable.view_more_go);
            ViewUtils.setLayoutParams((ImageView) helper.getView(R.id.iv_more), 0, 0, 0, 0, width/3, height/4);
        }else {
            helper.getView(R.id.ll_item_good).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_more).setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(RetrofitService.HOME3_HOST + smallpicture)
                    .into(((ImageView) helper.getView(R.id.iv_item_image)));
            helper.setText(R.id.tv_item_title, items.getTitle())
                    .setText(R.id.tv_detail_price, "￥" + items.getPrice())
                    .setText(R.id.tv_detail_market_price,"￥" + items.getMprice());
            ((TextView) helper.getView(R.id.tv_detail_market_price)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
            ViewUtils.setLayoutParams(((ImageView) helper.getView(R.id.iv_item_image)), 5, 0, 5, 0, width/3, height/5);
            ViewUtils.setLayoutParams(((TextView) helper.getView(R.id.tv_item_title)), 5, 0, 5, 0, width/3, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    class ViewHolder extends BaseViewHolder{

        LinearLayout ll_item_good;
        SolidImageView iv_item_image;
        TextView mTitle;
        TextView mDetailPrice;//平台价
        TextView mDetailMarketPrice;//市场价
        ImageView iv_more;
        LinearLayout ll_more;

        public ViewHolder(View view) {
            super(view);
            ll_item_good = (LinearLayout) view.findViewById(R.id.ll_item_good);
            iv_item_image = (SolidImageView) view.findViewById(R.id.iv_item_image);
            mTitle = (TextView) view.findViewById(R.id.tv_item_title);
            mDetailPrice = (TextView) view.findViewById(R.id.tv_detail_price);
            mDetailMarketPrice = (TextView) view.findViewById(R.id.tv_detail_market_price);
            ll_more = (LinearLayout) view.findViewById(R.id.ll_more);
            iv_more = (ImageView) view.findViewById(R.id.iv_more);
        }
    }
}
