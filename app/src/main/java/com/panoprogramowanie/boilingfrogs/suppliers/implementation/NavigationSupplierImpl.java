package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragmentActivity;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.MyScheduleFragment;
import com.panoprogramowanie.boilingfrogs.ui.schedule.ScheduleFragment;
import com.panoprogramowanie.boilingfrogs.ui.speaker.SpeakerActivity;
import com.panoprogramowanie.boilingfrogs.ui.speakers.SpeakersListFragment;
import com.panoprogramowanie.boilingfrogs.ui.speech.SpeechActivity;
import com.panoprogramowanie.boilingfrogs.ui.speechslot.SpeechSlotFragment;

/**
 * Created by wdawi on 24.01.2016.
 */
public class NavigationSupplierImpl implements NavigationSupplier {

    private static String FRAGMENT_TAG = "fTag";

    private FragmentManager.OnBackStackChangedListener onBackStackChangedListener;
    BoilingFrogsFragmentActivity currentFragmentActivity;

    private final ScheduleSupplier scheduleSupplier;

    public NavigationSupplierImpl(ScheduleSupplier scheduleSupplier) {
        this.scheduleSupplier = scheduleSupplier;

        onBackStackChangedListener = new FragmentBackstackChangeListener();
    }

    public void registerFragmentActivity(BoilingFrogsFragmentActivity activity) {
        if (currentFragmentActivity != null) {
            throw new IllegalArgumentException("Can't register new activity before unregistering old");
        }
        currentFragmentActivity = activity;
        currentFragmentActivity.getFragmentManager().addOnBackStackChangedListener(onBackStackChangedListener);
    }

    public void unregisterFragmentActivity(BoilingFrogsFragmentActivity activity) {
        if (currentFragmentActivity != activity) {
            throw new IllegalArgumentException("Can't unregister not registeredActivity");
        }

        currentFragmentActivity.getFragmentManager().removeOnBackStackChangedListener(onBackStackChangedListener);
        currentFragmentActivity = null;
    }

    @Override
    public void navigateToSpeech(long speechId) {
        SpeechActivity.startForSpeech(speechId, currentFragmentActivity);
    }

    @Override
    public void navigateToSpeaker(Speaker speaker) {
        SpeakerActivity.startForSpeaker(speaker, currentFragmentActivity);
    }

    @Override
    public void navigateToSlotDetail(int speechSlotPosition) {
        SpeechSlot destinationSpeechSlot = scheduleSupplier.getSpeechSlotForPosition(speechSlotPosition);
        SpeechSlotFragment fragment = SpeechSlotFragment.createInstance(destinationSpeechSlot, speechSlotPosition);
        replaceFragment(fragment, true);
    }

    @Override
    public void navigateToSchedule() {
        replaceFragment(new ScheduleFragment(), false);
    }

    @Override
    public void navigateToSpeakersList() {
        replaceFragment(new SpeakersListFragment(), false);
    }

    @Override
    public void navigateToMySchedule() {
        replaceFragment(new MyScheduleFragment(), false);
    }

    private void replaceFragment(BoilingFrogsFragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction = currentFragmentActivity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, FRAGMENT_TAG);

        if (addToBackstack) {
            transaction.addToBackStack("stack");
        }

        transaction.commit();

        String actionBarTitle = fragment.getActionBarTitle(currentFragmentActivity);
        currentFragmentActivity.setTitle(actionBarTitle);
    }

    @Override
    public boolean isContainterEmpty() {
        Fragment currentFragment = currentFragmentActivity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        return currentFragment == null;
    }

    @Override
    public boolean onBackPressed() {

        FragmentManager fragmentManager = currentFragmentActivity.getFragmentManager();
        fragmentManager.popBackStack();

        return (fragmentManager.getBackStackEntryCount() == 0);
    }

    private class FragmentBackstackChangeListener implements FragmentManager.OnBackStackChangedListener {
        int previousBackStackCount = 0;

        @Override
        public void onBackStackChanged() {
            int currentBackStackCount = currentFragmentActivity.getFragmentManager().getBackStackEntryCount();
            if (previousBackStackCount != currentBackStackCount) {
                if (currentBackStackCount == 0) {
                    currentFragmentActivity.animateToCloseDrawer();
                } else {
                    currentFragmentActivity.animateToOpenDrawer();
                }
            }

            BoilingFrogsFragment currentFragment = (BoilingFrogsFragment) currentFragmentActivity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
            String currentFragmentTitle = currentFragment.getActionBarTitle(currentFragmentActivity);
            currentFragmentActivity.setTitle(currentFragmentTitle);

            previousBackStackCount = currentBackStackCount;
        }
    }
}
