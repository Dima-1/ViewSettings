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

/**
 * Created by Dim on 24.11.2017.
 * Class for list of different sizes of the font
 */

public class FontSizeListPreference extends ListPreference {

    private int mClickedDialogEntryIndex;


    public FontSizeListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(),
                R.layout.list_font_size, getEntries()) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                float fontSize;
                CheckedTextView view = (CheckedTextView) convertView;
                if (view == null) {
                    view = (CheckedTextView) View.inflate(getContext(),
                            R.layout.list_font_size, null);
                }
                switch (position) {
                    case 0:
                        fontSize = 10;
                        break;
                    case 1:
                        fontSize = 12;
                        break;
                    case 2:
                        fontSize = 14;
                        break;
                    case 3:
                        fontSize = 16;
                        break;
                    case 4:
                        fontSize = 19;
                        break;
                    default:
                        fontSize = 14;
                }
                view.setText(getEntries()[position]);
                view.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
                return view;
            }
        };
        mClickedDialogEntryIndex = findIndexOfValue(getValue());
        builder.setSingleChoiceItems(adapter, mClickedDialogEntryIndex,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mClickedDialogEntryIndex = which;
                        FontSizeListPreference.this.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        dialog.dismiss();
                    }
                });
        builder.setPositiveButton(null, null);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult && mClickedDialogEntryIndex >= 0 && getEntryValues() != null) {
            String val = getEntryValues()[mClickedDialogEntryIndex].toString();
            if (callChangeListener(val)) {
                setValue(val);
            }
        }
    }
}
