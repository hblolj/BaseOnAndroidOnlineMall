package com.app.baseonandroidonlinemall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Object parseResponse(String responseBody) throws JSONException {
        Object result = null;
        //trim the string to prevent start with blank, and test if the string is valid JSON, because the parser don't do this :(. If Json is not valid this will return null
        responseBody = responseBody.trim();
        if (responseBody.startsWith("{") || responseBody.startsWith("[")) {
            result = new JSONTokener(responseBody).nextValue();
        }
        if (result == null) {
            result = responseBody;
        }
        return result;
    }

    public static Bitmap scaleBitmap(Bitmap bm, int pixel) {
        int srcHeight = bm.getHeight();
        int srcWidth = bm.getWidth();


        if (srcHeight > pixel || srcWidth > pixel) {
            float scale_y = 0;
            float scale_x = 0;
            if (srcHeight > srcWidth) {
                scale_y = ((float) pixel) / srcHeight;
                scale_x = scale_y;
            } else {
                scale_x = ((float) pixel) / srcWidth;
                scale_y = scale_x;
            }

            Matrix matrix = new Matrix();
            matrix.postScale(scale_x, scale_y);
            Bitmap dstbmp = Bitmap.createBitmap(bm, 0, 0, srcWidth, srcHeight, matrix, true);
            return dstbmp;
        } else {
            return Bitmap.createBitmap(bm);
        }
    }

    public static Bitmap scaleBitmap(Bitmap bm, int dstHeight, int dstWidth) {
        if (bm == null) return null;//java.lang.NullPointerException
        int srcHeight = bm.getHeight();
        int srcWidth = bm.getWidth();
        if (srcHeight > dstHeight || srcWidth > dstWidth) {
            float scale_y = ((float) dstHeight) / srcHeight;
            float scale_x = ((float) dstWidth) / srcWidth;
            float scale = scale_y < scale_x ? scale_y : scale_x;
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap dstbmp = Bitmap.createBitmap(bm, 0, 0, srcWidth, srcHeight, matrix, true);
            return dstbmp;
        } else {
            return Bitmap.createBitmap(bm);
        }
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }

    public static void startActivity(Activity activity) {

    }

    public static void hideSoftInput(Context context, IBinder windowToken) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    /**
     * 根据手机的分辨率从dp转成为px
     */
    public static int pxFromDip(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px转成为 dp
     */
    public static int dipFromPx(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int tryParseInteger(String value, int def) {
        int i;

        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            i = def;
        }

        return i;
    }

    public static double tryParseDouble(String value, double def) {
        double d;

        try {
            d = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            d = def;
        }

        return d;
    }

    public static Date tryParseDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(value);

        } catch (ParseException ex) {
            d = null;
        }

        return d;
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * 将文件生成位图
     * @param path
     * @return
     * @throws IOException
     */
    public static BitmapDrawable getImageDrawable(String path)
            throws IOException
    {
        //打开文件
        File file = new File(path);
        if(!file.exists())
        {
            return null;
        }

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bt = new byte[1024];

        //得到文件的输入流
        InputStream in = new FileInputStream(file);

        //将文件读出到输出流中
        int readLength = in.read(bt);
        while (readLength != -1) {
            outStream.write(bt, 0, readLength);
            readLength = in.read(bt);
        }

        //转换成byte 后 再格式化成位图
        byte[] data = outStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// 生成位图
        BitmapDrawable bd = new BitmapDrawable(bitmap);

        return bd;
    }

    public static int getWindowsWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
        return width;
    }

    public static void scaleImageView(ImageView iv, int pix){
        RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams) iv.getLayoutParams();
        p.height = pix;
        p.width = pix;
        iv.setLayoutParams(p);
    }

    public static String timeFormat(String timelong){
        String str_tl;
        int tl = Integer.parseInt(timelong);
        int hour = 0;
        int minute = tl / 60;
        int second = tl % 60;
        if (minute >= 60){
            hour = minute / 60;
            minute = minute % 60;
        }

        if (hour == 0){
            str_tl = minute + "'" + second + "''";
        }else{
            str_tl = hour + "'" + minute + "'" + second + "''";
        }

        return str_tl;
    }

    public static String createImageFromBitmap(Bitmap bitmap, Context context) {
        String fileName = "tagPicture";
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            FileOutputStream fo = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
            fileName = null;
        }
//        Log.i("fileName", fileName);
        System.out.println("fileName: " + fileName);
        return fileName;
    }
}
