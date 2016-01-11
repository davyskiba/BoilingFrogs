package com.panoprogramowanie.boilingfrogs.ui.main;

import android.animation.ValueAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.ScheduleSupplierImpl;
import com.panoprogramowanie.boilingfrogs.ui.schedule.ScheduleFragment;
import com.panoprogramowanie.boilingfrogs.ui.speakers.SpeakersFragment;
import com.panoprogramowanie.boilingfrogs.ui.speech.SpeechFragment;

import butterknife.ButterKnife;

/**
 * Created by Wojciech on 30.12.2015.
 */
public class MainActivity extends AppCompatActivity implements NavigationSupplier, SuppliersProvider {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private ScheduleSupplier scheduleSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        setupDrawerAndToolbar();
        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            int previousBackStackCount = 0;

            @Override
            public void onBackStackChanged() {
                int currentBackStackCount = getFragmentManager().getBackStackEntryCount();
                if (previousBackStackCount != currentBackStackCount) {
                    if (currentBackStackCount == 0) {
                        animateToCloseDrawer();
                    } else {
                        animateToOpenDrawer();
                    }
                }

                previousBackStackCount = currentBackStackCount;
            }
        });

        scheduleSupplier=new ScheduleSupplierImpl();
        scheduleSupplier.loadSchedule(this);

        if (savedInstanceState == null) {
            replaceFragment(new ScheduleFragment(), false);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();

        if (fragmentManager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
    }

    //region SupplierProvider

    @Override
    public NavigationSupplier provideNavigator() {
        return this;
    }

    @Override
    public ScheduleSupplier provideScheduleSupplier() {
        return scheduleSupplier;
    }

    //ednregion

    //region NavigationSupplier

    @Override
    public void navigateToSpeech(Speech speech) {
        replaceFragment(SpeechFragment.createInstance(speech), true);
    }

    private void replaceFragment(BoilingFrogsFragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack("stack");
        }

        transaction.commit();

        getSupportActionBar().setTitle(fragment.getToolbarTitle());
    }
    //endregion

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
                replaceFragment(new ScheduleFragment(), false);
                break;
            case R.id.nav_speakers:
                replaceFragment(new SpeakersFragment(), false);
                break;
            default:return false;
        }
        closeDrawers();
        return true;
    }

    private void animateToCloseDrawer()
    {
        animateDrawer(1, 0);
    }

    private void animateToOpenDrawer()
    {
        animateDrawer(0, 1);
    }

    private void animateDrawer(int start, int end){
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
