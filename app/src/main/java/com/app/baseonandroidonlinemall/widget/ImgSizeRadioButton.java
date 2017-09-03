package com.app.baseonandroidonlinemall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RadioButton;

import com.app.baseonandroidonlinemall.R;

/**
 * 可以控制图片大小的RadioButton
 * Created by JieChen on 2017/4/4.
 */

public class ImgSizeRadioButton extends android.support.v7.widget.AppCompatRadioButton {

    private int mImgHeight, mImgWidth;

    public ImgSizeRadioButton(Context context) {
        this(context, null);
    }

    public ImgSizeRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImgSizeRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
        mImgHeight = dp2px(30);
        mImgWidth = dp2px(30);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImgSizeRadioButton);

        for (int i = 0, count = typedArray.getIndexCount(); i < count; i++) {
            int attr = typedArray.getIndex(i);

            switch (attr) {
                case R.styleable.ImgSizeRadioButton_radio_button_img_height:
                    mImgHeight = typedArray.getDimensionPixelSize(R.styleable.ImgSizeRadioButton_radio_button_img_height, mImgHeight);
                    break;

                case R.styleable.ImgSizeRadioButton_radio_button_img_width:
                    mImgWidth = typedArray.getDimensionPixelOffset(R.styleable.ImgSizeRadioButton_radio_button_img_width, mImgWidth);
                    break;

                case R.styleable.ImgSizeRadioButton_radio_button_drawable_left:
                    drawableLeft = typedArray.getDrawable(attr);
                    break;

                case R.styleable.ImgSizeRadioButton_radio_button_drawable_top:
                    drawableTop = typedArray.getDrawable(attr);
                    break;

                case R.styleable.ImgSizeRadioButton_radio_button_drawable_right:
                    drawableRight = typedArray.getDrawable(attr);
                    break;

                case R.styleable.ImgSizeRadioButton_radio_button_drawable_bottom:
                    drawableBottom = typedArray.getDrawable(attr);
                    break;
            }
        }

        typedArray.recycle();

        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawableLeft, Drawable drawableTop, Drawable drawableRight, Drawable drawableBottom) {
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, mImgWidth, mImgHeight);
        }
        if (drawableTop != null) {
            drawableTop.setBounds(0, 0, mImgWidth, mImgHeight);
        }
        if (drawableRight != null) {
            drawableRight.setBounds(0, 0, mImgWidth, mImgHeight);
        }
        if (drawableBottom != null) {
            drawableBottom.setBounds(0, 0, mImgWidth, mImgHeight);
        }

        setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    private int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal, getResources().getDisplayMetrics());
    }
}
