package com.panoprogramowanie.boilingfrogs.ui.main;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;

/**
 * Created by Wojciech on 07.01.2016.
 */
public abstract class BoilingFrogsFragment extends Fragment implements MvpView {

    private ViewGroup containterView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containterView=container;
        return onCreateFragmentView(inflater, container, savedInstanceState);
    }

    protected abstract View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public View getContainerView() {
        return containterView;
    }

    public abstract String getToolbarTitle(Context context);


}
