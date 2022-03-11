package com.panoprogramowanie.boilingfrogs.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.koin.KoinModule;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class SplashActivity extends AppCompatActivity implements MvpView {

    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_acitivty);

        presenter = KoinModule.getSplashPresenter();

        presenter.takeView(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public View getContainerView() {
        return getContainerView();
    }
}
