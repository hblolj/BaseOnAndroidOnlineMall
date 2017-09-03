package com.app.baseonandroidonlinemall.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/4/13.
 */

public class ActivityCollctor {

    public static List<Activity> sActivities = new ArrayList<>();

    public static void addActivity(Activity activity){
        sActivities.add(activity);
    }

    public static void removeActivity(Activity activity){
        sActivities.remove(activity);
    }

    public static void finishAll(){
        for (Activity a : sActivities){
            if (!a.isFinishing()){
                a.finish();
            }
        }
    }
}
