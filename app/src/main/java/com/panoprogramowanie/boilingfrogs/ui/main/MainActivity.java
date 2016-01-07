package com.panoprogramowanie.boilingfrogs.ui.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.navigation.Navigator;
import com.panoprogramowanie.boilingfrogs.ui.schedule.ScheduleFragment;
import com.panoprogramowanie.boilingfrogs.ui.speakers.SpeakersFragment;
import com.panoprogramowanie.boilingfrogs.ui.speech.SpeechFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 30.12.2015.
 */
public class MainActivity extends AppCompatActivity implements Navigator {
    @Bind(R.id.drawer_list)
    ListView drawerListView;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private DrawerAdapter drawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        setupDrawerAndToolbar();

        if (savedInstanceState == null) {
            replaceFragment(new ScheduleFragment(), false);
        }
    }

    private void setupDrawerAndToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawerAdapter = new DrawerAdapter(this, NavigationDrawerListEntries.values());
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                closeDrawers();
                setDrawerItemSelected(position);
                drawerOptionSelected((NavigationDrawerListEntries) drawerAdapter.getItem(position));
            }
        });

        drawerAdapter.setCurrentlySelected(0);
        drawerListView.setAdapter(drawerAdapter);
        drawerListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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

    public void setDrawerItemSelected(int position) {
        drawerListView.setItemChecked(position, true);
        drawerAdapter.setCurrentlySelected(position);
    }

    private void drawerOptionSelected(NavigationDrawerListEntries item) {
        switch (item) {
            case SCHEDULE:
                replaceFragment(new ScheduleFragment(), false);
                break;
            case SPEAKERS:
                replaceFragment(new SpeakersFragment(), false);
                break;
        }
    }

    private void replaceFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack("stack");
        }

        transaction.commit();
    }

    private void closeDrawers() {
        drawer.closeDrawers();
    }

    @Override
    public void navigateToSpeech() {
        replaceFragment(new SpeechFragment(),true);
    }

    //region DrawerData
    public enum NavigationDrawerListEntries implements DrawerAdapter.NavigationDrawerListEntry {
        SCHEDULE(R.string.drawer_item_schedule),
        SPEAKERS(R.string.drawer_item_speakers);

        private int labedId;

        NavigationDrawerListEntries(int labelId) {
            this.labedId = labelId;
        }

        @Override
        public int getLabelId() {
            return labedId;
        }

    }
    //endregion

}
