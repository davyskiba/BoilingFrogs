package com.panoprogramowanie.boilingfrogs.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.panoprogramowanie.boilingfrogs.BoilingFrogs;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragmentActivity;

import javax.inject.Inject;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class SplashActivity extends AppCompatActivity implements MvpView {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_acitivty);

        BoilingFrogs.getMainComponent(this).inject(this);

        presenter.takeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(()->navigateToMainActivity(), SPLASH_TIME_OUT);
    }

    private void navigateToMainActivity() {
        presenter.navigateToMainActivity();
    }

    public void close(){
        finish();
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
