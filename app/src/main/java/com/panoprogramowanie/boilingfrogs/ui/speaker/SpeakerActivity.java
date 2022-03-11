package com.panoprogramowanie.boilingfrogs.ui.speaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.databinding.SpeakerActivityBinding;
import com.panoprogramowanie.boilingfrogs.koin.KoinModule;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 12.01.2016.
 */
public class SpeakerActivity extends AppCompatActivity implements MvpView {

    //region Navigation

    private final static String SPEAKER_ID_ARG_KEY = "speaker_id_arg";

    public static void startForSpeaker(Speaker speaker, Activity activity) {
        Intent intent = new Intent(activity, SpeakerActivity.class);
        intent.putExtra(SPEAKER_ID_ARG_KEY, speaker.getId());
        activity.startActivity(intent);
    }

    //endregion

    //region Views

    @BindView(R.id.contentScroll)
    NestedScrollView contentScroll;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    //endregion

    SpeakerActivityBinding binding;

    SpeakerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.speaker_activity);

        ButterKnife.bind(this);

        long speakerId = getIntent().getLongExtra(SPEAKER_ID_ARG_KEY, 0);

        presenter = KoinModule.getSpeakerPresenter();
        presenter.takeView(this);
        presenter.setSpeakerId(speakerId);


        setupDrawerAndToolbar();
    }

    public void displaySpeakerData(Speaker speaker) {
        binding.setSpeaker(speaker);
    }

    private void setupDrawerAndToolbar() {
        Toolbar toolbar = findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.BoilingFrogsTheme_CollapsingToolbar);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
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
