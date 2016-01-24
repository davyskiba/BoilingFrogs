package com.panoprogramowanie.boilingfrogs.ui.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Wojciech on 20.01.2016.
 */
public interface MvpView {
    Context getContext();

    View getContainerView();
}
