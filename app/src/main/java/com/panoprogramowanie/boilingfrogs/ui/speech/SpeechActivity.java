package com.panoprogramowanie.boilingfrogs.ui.speech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.panoprogramowanie.boilingfrogs.BR;
import com.panoprogramowanie.boilingfrogs.BoilingFrogs;
import com.panoprogramowanie.boilingfrogs.R;
//import com.panoprogramowanie.boilingfrogs.databinding.SpeechActivityBinding;
import com.panoprogramowanie.boilingfrogs.databinding.SpeechActivityBinding;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.NotificationSupplierImpl;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;
import com.panoprogramowanie.boilingfrogs.ui.view.SocialView;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wojciech on 12.01.2016.
 */
public class SpeechActivity extends AppCompatActivity implements MvpView {

    //region Navigation

    private final static String SPEECH_ID_ARG = "speech_id_arg";

    public static void startForSpeech(long speechId, Activity activity) {
        Intent intent = new Intent(activity, SpeechActivity.class);
        intent.putExtra(SPEECH_ID_ARG, speechId);
        activity.startActivity(intent);
    }

    //endregion

    //region Views

    @Bind(R.id.contentScroll)
    NestedScrollView contentScroll;

    @Bind(R.id.header)
    ImageView avatar;

    @Bind(R.id.speaker_name)
    TextView speakerName;

    @Bind(R.id.speaker_occupation)
    TextView speakerOccupation;

    @Bind(R.id.speech_title)
    TextView speechTitle;

    @Bind(R.id.speech_time)
    TextView speechTime;

    @Bind(R.id.speech_description)
    TextView speechDescription;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.speaker_social)
    SocialView socialView;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    //endregion

    private SpeechActivityBinding binding;
    private ColorStateList floatingButtonDefaultTint;

    @Inject
    SpeechPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.speech_activity);

        ButterKnife.bind(this);
        floatingButtonDefaultTint = floatingActionButton.getBackgroundTintList();
        setupDrawerAndToolbar();

        long speechId = getIntent().getLongExtra(SPEECH_ID_ARG, 0);

        BoilingFrogs.getMainComponent(this).inject(this);
        presenter.takeView(this);
        presenter.setSpeech(speechId);
    }

    private void setupDrawerAndToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.BoilingFrogsTheme_CollapsingToolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //region DataDisplay

    public void displaySpeech(Speech speech) {
        binding.setSpeech(speech);
    }

    public void displaySpeakerData(Speaker speaker) {
        binding.setSpeaker(speaker);

        socialView.setupForSpeaker(speaker);

        AvatarLoaderUtil.loadAvatar(this, speaker.getPhotoUrl(), avatar, R.drawable.avatar_placeholder);
    }

    public void displayFavorite(boolean isFavorite) {
        if (isFavorite) {
            setSelectedFab();
        } else {
            setUnSelectedFab();
        }
    }

    private void setSelectedFab() {
        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.star_full));
        floatingActionButton.setBackgroundTintList(floatingButtonDefaultTint);
    }

    private void setUnSelectedFab() {
        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
    }

    //endregion

    @OnClick(R.id.fab)
    public void onFavoriteClick() {
        presenter.favoriteClicked();
    }

    //region MvpView

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public View getContainerView() {
        return contentScroll;
    }

    //endregion
}
