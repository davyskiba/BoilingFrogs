package com.panoprogramowanie.boilingfrogs.ui.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by Wojciech on 20.01.2016.
 */
public class Presenter<T extends MvpView> {

    private WeakReference<T> view;

    public void takeView(T view) {
        this.view = new WeakReference<T>(view);
    }

    protected T getView() {
        return view.get();
    }

    protected Context getContext() {
        return getView().getContext();
    }
}
