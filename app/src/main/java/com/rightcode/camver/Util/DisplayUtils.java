package com.rightcode.camver.Util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DisplayUtils {

    public static int getDisplayWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;

        return width;
    }

    public static int getDisplayHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int height = dm.heightPixels;

        return height;
    }

    public static int dpToPx(Context context, float dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static float pxToDp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;

        if (density == 1.0) {
            density *= 4.0;
        } else if (density == 1.5) {
            density *= (8/3);
        } else if (density == 2.0) {
            density *= 2.0;
        }

        return px / density;
    }
}
