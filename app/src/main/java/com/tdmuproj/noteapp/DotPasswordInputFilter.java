package com.tdmuproj.noteapp;

import android.text.InputFilter;
import android.text.Spanned;

public class DotPasswordInputFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        // Replace all characters with dots
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < end - start; i++) {
            builder.append("•"); // Use the dot character here
        }
        return builder.toString();
    }
}
