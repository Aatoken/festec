package com.mk.latte.util.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class ToastUtils {
    private static Toast toast;
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
