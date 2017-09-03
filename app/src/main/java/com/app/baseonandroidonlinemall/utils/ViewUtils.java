package com.app.baseonandroidonlinemall.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by hblolj on 2016/8/17.
 * 测量View的宽和高
 */
public class ViewUtils {

    /**
     * 获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
     * @param view
     * @param isHeight
     * @return
     */
    public static int getViewHeight(View view, boolean isHeight){
        int result;
        if(view==null)return 0;
        if(isHeight){
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(h,0);
            result =view.getMeasuredHeight();
        }else{
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(0,w);
            result =view.getMeasuredWidth();
        }
        return result;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int height = 0;
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);

            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
            // 计算子项View 的宽高
            listItem.measure(desiredWidth, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        height = ViewUtils.getViewHeight(listView, true);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        params.height = params.height;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);

        height = ViewUtils.getViewHeight(listView, true);

        System.out.println(height);
    }

    public static int getDefaultDisplay(boolean isWidth, Context context){
        int param;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        if (isWidth){
            param = wm.getDefaultDisplay().getWidth();// 获取屏幕宽度
        }else {
            param = wm.getDefaultDisplay().getHeight();// 获取屏幕高度
        }
        return param;
    }

    public static void setLayoutParams(View view, int left, int top, int right, int bottom, int width, int height){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                width, height);
        lp.setMargins(left, top, right, bottom);
        view.setLayoutParams(lp);
    }

    public static void setLayoutParamsByRelativeLayout(View view, int left, int top, int right, int bottom, int width, int height){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                width, height);
        lp.setMargins(left, top, right, bottom);
        view.setLayoutParams(lp);
    }

    public static void setLayoutParams2(View view, int left, int top, int right, int bottom, int width, int height){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                width, height);
        lp.setMargins(left, top, right, bottom);
        lp.gravity = Gravity.CENTER;
        view.setLayoutParams(lp);
    }

    public static void setViewGroupLayoutParams(View view, int width, int height){
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                width, height);
        view.setLayoutParams(lp);
    }

//    public static void setViewGroupLayoutParams(ImageView view, int width, int height){
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
//        lp.width = width;
//        lp.height = height;
//        view.setLayoutParams(lp);
////        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
////                width, height);
////        view.setLayoutParams(lp);
//    }

    /**
     * 获取设备屏幕密度dpi，每寸所包含的像素点
     * @param context
     * @return
     */
    public static float getDeviceDensityDpi(Context context){
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * dp转px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale +0.5f);
    }

    /**
     * px转dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale +0.5f);
    }
}
