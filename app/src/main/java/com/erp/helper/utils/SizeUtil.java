package com.erp.helper.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class SizeUtil {
    public static int getPxFromDp(Context context, int dps) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int pixel = Math.round(dps * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

        final float scale = context.getResources().getDisplayMetrics().density;
        int pixel_2 = (int) (dps * scale + 0.5f);

        return pixel_2;
    }
}
