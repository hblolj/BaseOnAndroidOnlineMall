package com.app.baseonandroidonlinemall.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.app.baseonandroidonlinemall.R;

/**
 * Created by XH on 2015/5/15.
 */
public class FragmentUtil {
    public static final int OUT_OF_TRANSACTION = -1;

    FragmentActivity mContext;
    FragmentTransaction mTransaction;
    int mContainerViewId;

    public FragmentUtil(FragmentActivity context, int containerViewId) {
        mContext = context;
        mContainerViewId = containerViewId;
        mTransaction = null;
    }

    public void beginTransaction() {
        if (mTransaction == null) {
            mTransaction = mContext.getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);
        }
    }

    public int commit() {
        return commit(false);
    }

    public int commit(boolean addToBackStack) {
        int id = OUT_OF_TRANSACTION;

        if (mTransaction != null) {
            if (addToBackStack) {
                mTransaction.addToBackStack(null);
            }

            id = mTransaction.commit();
            mTransaction = null;
        }

        return id;
    }

    public void add(Fragment fragment) {
        if (mTransaction != null) {
            mTransaction.add(mContainerViewId, fragment);
        }
    }

    public void remove(Fragment fragment) {
        if (mTransaction != null) {
            mTransaction.remove(fragment);
        }
    }

    public void removeImmediate(Fragment fragment) {
        if (mTransaction == null) {
            mContext.getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

    public void replace(Fragment fragment) {
        if (mTransaction != null) {
            mTransaction.replace(mContainerViewId, fragment);
        }
    }

    public void attach(Fragment fragment) {
        if (mTransaction != null) {
            mTransaction.attach(fragment);
        }
    }

    public void attachImmediate(Fragment fragment) {
        if (mTransaction == null) {
            mContext.getSupportFragmentManager()
                    .beginTransaction()
                    .attach(fragment)
                    .commit();
        }
    }

    public void detach(Fragment fragment) {
        if (mTransaction != null) {
            mTransaction.detach(fragment);
        }
    }

    public void detachImmediate(Fragment fragment) {
        if (mTransaction != null) {
            mContext.getSupportFragmentManager()
                    .beginTransaction()
                    .detach(fragment)
                    .commit();
        }
    }

    public void show(Fragment fragment) {
        if (fragment == null || fragment.isVisible()) {
            return;
        }

        if (mTransaction != null) {
            FragmentManager manager = mContext.getSupportFragmentManager();

            if (manager.getFragments() != null) {
                for (Fragment addedFragment : manager.getFragments()) {
                    if (addedFragment != null && addedFragment.isVisible()) {
                        mTransaction.hide(addedFragment);
                    }
                }
            }

            if (fragment.isDetached()) {
                mTransaction.attach(fragment);
                mTransaction.show(fragment);
            } else {
                if (!fragment.isAdded()) {
                    mTransaction.add(mContainerViewId, fragment);
                } else {
                    mTransaction.show(fragment);
                }
            }
        }
    }
}
