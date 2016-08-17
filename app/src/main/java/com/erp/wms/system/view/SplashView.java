package com.erp.wms.system.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.erp.wms.R;

public class SplashView extends LinearLayout {

    public SplashView(Context context) {
        super(context);

        this.setOrientation(VERTICAL);

        Drawable draw = context.getResources().getDrawable(R.drawable.warehouse);

        ImageView image = new ImageView(context);
        image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        image.setImageDrawable(draw);
        this.addView(image);
    }
}
