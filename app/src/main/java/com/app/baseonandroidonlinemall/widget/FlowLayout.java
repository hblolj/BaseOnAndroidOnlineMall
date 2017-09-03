package com.app.baseonandroidonlinemall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Hashtable;

public class FlowLayout extends RelativeLayout {
	
	private int widthSpec = 10;
	private int heightSpec = 5;
	
	int mLeft, mRight, mTop, mBottom, currentBottom;
	Hashtable<View, Position> map = new Hashtable<View, Position>();

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public FlowLayout(Context context) {
		super(context);

	}
	
	public void setSpec(int widthSpec,int heightSpec){
		this.widthSpec = widthSpec;
		this.heightSpec = heightSpec;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			Position pos = map.get(child);
			if (pos != null) {
				child.layout(pos.left, pos.top, pos.right, pos.bottom);
			} else {
				Log.i("MyLayout", "error");
			}
		}

	}

	public int getPosition(int IndexInRow, int childIndex) {
		if (IndexInRow > 0) {
			return getPosition(IndexInRow - 1, childIndex - 1)
					+ getChildAt(childIndex - 1).getMeasuredWidth() + widthSpec;
		}
		return 0;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		mLeft = 0;
		mRight = 0;
		mTop = 0;
		mBottom = 0;
		int j = 0;
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			Position position = new Position();
			View view = getChildAt(i);
			mLeft = getPosition(i - j, i);
			mRight = mLeft + view.getMeasuredWidth();
			if (mRight >= width) {
				j = i;
				mLeft = getPosition(i - j, i);
				mRight = mLeft + view.getMeasuredWidth();
				mTop += getChildAt(i).getMeasuredHeight() + heightMeasureSpec;
			}
			mBottom = mTop + view.getMeasuredHeight();
			position.left = mLeft;
			position.top = mTop;
			position.right = mRight;
			position.bottom = mBottom;
			map.put(view, position);
		}
		setMeasuredDimension(width, mBottom);
	}

	private class Position {
		int left, top, right, bottom;
	}

}