package com.app.baseonandroidonlinemall.utils;

import android.content.Context;
import android.os.Bundle;

import com.app.baseonandroidonlinemall.api.bean.hot.focusAdItem;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import java.util.List;

/**
 * Created by long on 2016/8/24.
 * 滑动器帮助类
 */
public final class SliderHelper {

    private static final String SLIDER_KEY = "SliderKey";

    private SliderHelper() {
        throw new RuntimeException("SliderHelper cannot be initialized!");
    }

    /**
     * 初始化广告滑动器
     *
     * @param context
     * @param sliderLayout
     * @param items
     */
    public static void initAdSlider(final Context context, SliderLayout sliderLayout, List<focusAdItem> items) {
        sliderLayout.removeAllSliders();
        for (focusAdItem item : items) {
            TextSliderView textSliderView = new TextSliderView(context);
            // initialize a SliderLayout
            textSliderView.description(item.getName())
                    .image(item.getImg())
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            if (slider.getBundle() != null) {
//                                NewsInfo.AdData adData = slider.getBundle().getParcelable(SLIDER_KEY);
//                                if (adData != null) {
//                                    if (NewsUtils.isNewsPhotoSet(adData.getTag())) {
//                                        PhotoSetActivity.launch(context, adData.getUrl());
//                                    } else if (NewsUtils.isNewsSpecial(adData.getTag())) {
//                                        SpecialActivity.launch(context, adData.getUrl());
//                                    } else {
////                                        NewsDetailActivity.launch(context, adData.getUrl());
//                                        NewsArticleActivity.launch(context, adData.getUrl());
//                                    }
//                                }
                            }

                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle().putParcelable(SLIDER_KEY, adData);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
    }
}
