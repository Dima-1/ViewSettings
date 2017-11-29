package com.example.user.viewsettings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.ListPreference;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

/**
 * Created by Dim on 24.11.2017.
 * Class for list of different sizes of the font
 */

public class FontSizeListPreference extends ListPreference {

    private int clickedDialogEntryIndex;


    public FontSizeListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float getTextSizeByIndex(int index) {
        return 10 + index * 2;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView summary = view.findViewById(android.R.id.summary);
        summary.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                getTextSizeByIndex(findIndexOfValue(getValue())));
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(),
                R.layout.item_font_size_dialog, getEntries()) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                CheckedTextView view = (CheckedTextView) convertView;
                if (view == null) {
                    view = (CheckedTextView) View.inflate(getContext(),
                            R.layout.item_font_size_dialog, null);
                }

                view.setText(getEntries()[position]);
                view.setTextSize(TypedValue.COMPLEX_UNIT_SP, getTextSizeByIndex(position));
                return view;
            }
        };
        clickedDialogEntryIndex = findIndexOfValue(getValue());
        builder.setSingleChoiceItems(adapter, clickedDialogEntryIndex,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clickedDialogEntryIndex = which;
                        FontSizeListPreference.this.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton(null, null);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult && clickedDialogEntryIndex >= 0 && getEntryValues() != null) {
            String val = getEntryValues()[clickedDialogEntryIndex].toString();
            if (callChangeListener(val)) {
                setValue(val);
            }
        }
    }
}
