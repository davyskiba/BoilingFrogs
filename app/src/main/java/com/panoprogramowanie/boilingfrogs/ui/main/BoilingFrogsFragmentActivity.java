package com.panoprogramowanie.boilingfrogs.ui.main;


import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by wdawi on 24.01.2016.
 */
public abstract class BoilingFrogsFragmentActivity extends AppCompatActivity {
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public abstract void animateToCloseDrawer();

    public abstract void animateToOpenDrawer();
}
