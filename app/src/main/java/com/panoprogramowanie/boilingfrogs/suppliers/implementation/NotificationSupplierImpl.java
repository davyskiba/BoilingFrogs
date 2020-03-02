package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.panoprogramowanie.boilingfrogs.suppliers.NotificationSupplier;

/**
 * Created by Wojciech on 20.01.2016.
 */
public class NotificationSupplierImpl implements NotificationSupplier {
    @Override
    public void showShortLastingSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showShortLastingSnackbar(View view, int textResource) {
        Snackbar.make(view, textResource, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showLongLastingSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showLongLastingSnackbar(View view, int textResource) {
        Snackbar.make(view, textResource, Snackbar.LENGTH_LONG)
                .show();
    }
}
