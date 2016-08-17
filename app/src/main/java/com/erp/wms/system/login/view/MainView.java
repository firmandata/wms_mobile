package com.erp.wms.system.login.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DrawableUtil;
import com.erp.wms.R;
import com.erp.wms.api.SessionAPI;

public class MainView extends LinearLayout {

    private EditText etUserName;
    private EditText etPassword;

    public MainView(Context context) {
        super(context);
    }

    public MainView(Context context, final RestfulResponse response) {
        this(context);

        this.setOrientation(VERTICAL);
        this.setScrollBarStyle(LinearLayout.SCROLLBARS_OUTSIDE_OVERLAY);
        this.setClipToPadding(false);
        this.setPadding(
                (int) context.getResources().getDimension(R.dimen.layout_padding_left),
                (int) context.getResources().getDimension(R.dimen.layout_padding_top),
                (int) context.getResources().getDimension(R.dimen.layout_padding_right),
                (int) context.getResources().getDimension(R.dimen.layout_padding_bottom)
        );

        LinearLayout logoArea = new LinearLayout(context);
        logoArea.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        logoArea.setOrientation(LinearLayout.VERTICAL);
        logoArea.setGravity(Gravity.CENTER_HORIZONTAL);
        logoArea.setPadding(0, 0, 0, 20);

            Drawable draw = context.getResources().getDrawable(R.drawable.warehouse);

            ImageView image = new ImageView(context);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            image.setImageDrawable(draw);
            logoArea.addView(image);

            TextView lblTitle = new TextView(context);
            lblTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblTitle.setText("Warehouse Management System");
            logoArea.addView(lblTitle);

        this.addView(logoArea);

        TextView lblUsername = new TextView(context);
        lblUsername.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lblUsername.setText("Username");
        this.addView(lblUsername);

        etUserName = new EditText(context);
        etUserName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        etUserName.setInputType(InputType.TYPE_CLASS_TEXT);
        etUserName.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        this.addView(etUserName);

        TextView lblPassword = new TextView(context);
        lblPassword.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lblPassword.setText("Password");
        lblPassword.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
        this.addView(lblPassword);

        etPassword = new EditText(context);
        etPassword.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPassword.setImeOptions(EditorInfo.IME_ACTION_GO);
        this.addView(etPassword);

        LinearLayout submitLine = new LinearLayout(context);
        submitLine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        submitLine.setOrientation(HORIZONTAL);
        submitLine.setGravity(Gravity.CENTER);
            Button submit = new Button(context);
            submit.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            submit.setText("Login");
            submit.setCompoundDrawablesWithIntrinsicBounds(DrawableUtil.resizeIcon(context, context.getResources().getDrawable(R.drawable.key), 25, 25), null, null, null);
            submit.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionAPI.Login(getUserName(), getPassword(), response);
                }
            });
        submitLine.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
        submitLine.addView(submit);

        this.addView(submitLine);
    }

    public String getUserName() {
        return etUserName.getText().toString();
    }

    public String getPassword() {
        return etPassword.getText().toString();
    }
}