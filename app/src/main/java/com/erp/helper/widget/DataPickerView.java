package com.erp.helper.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.erp.helper.utils.DateTimeUtil;
import com.erp.helper.utils.GeneratorUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataPickerView extends RelativeLayout {

    private Context mContext;
    private EditText mEditText;
    private Button mButton;

    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;

    private String mDateFormat = "yyyy-MM-dd";
    private String mDisplayDateFormat= "dd-MM-yyyy";

    /* ----------------- */
    /* -- Constructor -- */
    /* ----------------- */

    public DataPickerView(Context context) {
        this(context, 0, -1, 0);
    }

    public DataPickerView(Context context, int defaultYear, int defaultMonthOfYear, int defaultDayOfMonth) {
        super(context);
        mContext = context;

        // Set default format setting

        setFormat(DateTimeUtil.DATE_FORMAT);
        setFormatDisplay(DateTimeUtil.DATE_DISPLAY_FORMAT);


        // Build display

        mEditText = new EditText(mContext);
        mEditText.setSingleLine(true);
        mEditText.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        mButton = new Button(mContext);
        mButton.setText("...");
        mButton.setId(GeneratorUtil.generateViewId());

        RelativeLayout.LayoutParams editTextLayout = new RelativeLayout.LayoutParams(0, RelativeLayout.LayoutParams.WRAP_CONTENT);
        editTextLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        editTextLayout.addRule(RelativeLayout.LEFT_OF, mButton.getId());

        RelativeLayout.LayoutParams buttonLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        mEditText.setLayoutParams(editTextLayout);
        mButton.setLayoutParams(buttonLayout);

        this.addView(mEditText);
        this.addView(mButton);

        if (!(defaultYear == 0 && defaultMonthOfYear == -1 && defaultDayOfMonth == 0)) {
            setDate(defaultYear, defaultMonthOfYear, defaultDayOfMonth);
        }

        Calendar defaultCalendar = Calendar.getInstance();
        if (defaultYear == 0)
            defaultYear = defaultCalendar.get(Calendar.YEAR);
        if (defaultMonthOfYear == -1)
            defaultMonthOfYear = defaultCalendar.get(Calendar.MONTH);
        if (defaultDayOfMonth == 0)
            defaultDayOfMonth = defaultCalendar.get(Calendar.DAY_OF_MONTH);

        mDatePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setDate(year, monthOfYear, dayOfMonth);
            }
        }, defaultYear, defaultMonthOfYear, defaultDayOfMonth);

        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false)
                    setDateFromDisplay(mEditText.getText().toString());
            }
        });
    }

    public DataPickerView(Context context, Calendar calendar) {
        this(context, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    /* ------------ */
    /* -- Helper -- */
    /* ------------ */

    public Calendar getCalendar() {
        return mCalendar;
    }

    public EditText getEditText() {
        return mEditText;
    }

    /* -------------- */
    /* -- Set Date -- */
    /* -------------- */

    public void setDate(Calendar calendar) {
        mCalendar = calendar;
        if (mCalendar != null)
        {
            try {
                mDatePickerDialog.updateDate(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
            } catch (Exception ex) {
                mCalendar = null;
            }
        }

        setDateDisplay();
    }

    public void setDate(String text) {
        setDate(DateTimeUtil.FromString(text, mDateFormat));
    }

    public void setDate(int year, int monthOfYear, int dayOfMonth) {
        setDate(DateTimeUtil.FromDate(year, monthOfYear, dayOfMonth));
    }

    public void setDateFromDisplay(String text) {
        setDate(DateTimeUtil.FromString(text, mDisplayDateFormat));
    }

    private void setDateDisplay() {
        mEditText.setText(getDateDisplayString());
    }

    /* -------------- */
    /* -- Get Date -- */
    /* -------------- */

    public String getDateString() {
        return DateTimeUtil.ToStringFormat(mCalendar, mDateFormat);
    }

    public String getDateDisplayString() {
        return DateTimeUtil.ToStringFormat(mCalendar, mDisplayDateFormat);
    }

    /* ------------- */
    /* -- Setting -- */
    /* ------------- */

    public void setFormat(String format) {
        mDateFormat = format;
    }

    public String getFormat() {
        return mDateFormat;
    }

    public void setFormatDisplay(String format) {
        mDisplayDateFormat = format;
    }

    public String getFormatDisplay() {
        return mDisplayDateFormat;
    }
}
