package com.app.baseonandroidonlinemall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import com.app.baseonandroidonlinemall.R;

/**
 * Created by txj on 15/2/16.
 * Modified by xh on 15/3/12
 *
 * &#xa07a;
 */
public class IconTextView extends android.support.v7.widget.AppCompatTextView {

    private static final String FONT_FILE_ICON = "iconfont.ttf";
    private static final String FONT_FILE_LL_ICON = "LLIconfont.ttf";

    public IconTextView(Context context) {
        super(context);

        if (!isInEditMode()) {
            initView(context, FONT_FILE_ICON);
        }
    }

    public IconTextView(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) {
            int fontFileId;
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.IconTextView,
                    0, 0);

            try {
                fontFileId = a.getInteger(R.styleable.IconTextView_fontFile, 1);
            } finally {
                a.recycle();
            }

            initView(context, (fontFileId == 0 ?
                    FONT_FILE_LL_ICON :
                    FONT_FILE_ICON));
        }
    }

    private void initView(Context context, String fontFile) {
        Typeface font = Typeface.createFromAsset(
                context.getAssets(),
                "fonts/" + fontFile);
        setTypeface(font);
    }
}
