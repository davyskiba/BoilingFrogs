package com.panoprogramowanie.boilingfrogs.ui.main;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.panoprogramowanie.boilingfrogs.BoilingFrogs;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.GreenDaoScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.util.BrowserLaunchingUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Wojciech on 30.12.2015.
 */
public class MainActivity extends BoilingFrogsFragmentActivity {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Inject
    NavigationSupplier navigationSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        BoilingFrogs.getMainComponent(this).inject(this);

        GreenDaoScheduleSupplier greenDaoScheduleSupplier=new GreenDaoScheduleSupplier(this);

        setupDrawerAndToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationSupplier.registerFragmentActivity(this);

        if (navigationSupplier.isContainterEmpty()) {
            navigationSupplier.navigateToSchedule();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigationSupplier.unregisterFragmentActivity(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        if (navigationSupplier.onBackPressed()) {
            super.onBackPressed();
        }
    }

    //region Drawer&Toolbar

    private void setupDrawerAndToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return drawerOptionSelected(item);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    onBackPressed();
                }
            }
        });
    }

    private boolean drawerOptionSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_schedule:
                navigationSupplier.navigateToSchedule();
                break;
            case R.id.nav_speakers:
                navigationSupplier.navigateToSpeakersList();
                break;
            case R.id.nav_my_schedule:
                navigationSupplier.navigateToMySchedule();
                break;
            case R.id.nav_navigation:
                browseTo(R.string.navigation_url);
                break;
            case R.id.nav_twitter:
                browseTo(R.string.twitter_feed_url);
                break;
            case R.id.nav_survey:
                browseTo(R.string.survey_url);
                break;
            default:
                return false;
        }
        closeDrawers();
        return true;
    }

    private void browseTo(int urlId) {
        BrowserLaunchingUtil.launchBrowser(this, urlId);
    }

    public void animateToCloseDrawer() {
        animateDrawer(1, 0);
    }

    public void animateToOpenDrawer() {
        animateDrawer(0, 1);
    }

    private void animateDrawer(int start, int end) {
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                toggle.onDrawerSlide(drawer, slideOffset);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(500);
        anim.start();
    }

    private void closeDrawers() {
        drawer.closeDrawers();
    }
    //endregion

}
