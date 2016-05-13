package com.panoprogramowanie.boilingfrogs.ui.splash;

import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.UpdateSupplier;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

import javax.inject.Inject;

public class SplashPresenter extends Presenter<SplashActivity> {

    private final UpdateSupplier updateSupplier;
    private final NavigationSupplier navigationSupplier;

    @Inject
    public SplashPresenter(UpdateSupplier updateSupplier, NavigationSupplier navigationSupplier) {
        this.updateSupplier = updateSupplier;
        this.navigationSupplier = navigationSupplier;
    }

    @Override
    public void takeView(SplashActivity view) {
        super.takeView(view);

//        updateSupplier.performUpdate();
    }

    public void navigateToMainActivity(){
        navigationSupplier.navigateToMain(getContext());
        getView().close();
    }
}
