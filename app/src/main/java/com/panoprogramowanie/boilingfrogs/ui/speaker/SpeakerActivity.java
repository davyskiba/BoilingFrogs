package com.panoprogramowanie.boilingfrogs.ui.speaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.BoilingFrogs;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;
import com.panoprogramowanie.boilingfrogs.ui.view.SocialView;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 12.01.2016.
 */
public class SpeakerActivity extends AppCompatActivity implements MvpView {

    //region Navigation

    private final static String SPEAKER_ID_ARG_KEY = "speaker_id_arg";

    public static void startForSpeaker(Speaker speaker, Activity activity) {
        Intent intent = new Intent(activity, SpeakerActivity.class);
        intent.putExtra(SPEAKER_ID_ARG_KEY,speaker.getId());
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

    @Bind(R.id.speaker_about)
    TextView speakerAbout;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.speaker_social)
    SocialView socialView;

    //endregion

    @Inject
    SpeakerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker_activity);

        ButterKnife.bind(this);

        long speakerId = getIntent().getLongExtra(SPEAKER_ID_ARG_KEY,0);

        BoilingFrogs.getMainComponent(this).inject(this);
        presenter.takeView(this);
        presenter.setSpeakerId(speakerId);


        setupDrawerAndToolbar();
    }

    public void displaySpeakerData(Speaker speaker) {
        speakerName.setText(speaker.getName());
        speakerOccupation.setText(speaker.getOccupation());
        speakerAbout.setText(speaker.getDescription().replace("\\n", "\n").replace("\n\n", "\n").replace("\n", "\n\n"));

        socialView.setSpeaker(speaker);
        AvatarLoaderUtil.loadAvatar(speaker.getPhotoUrl(), avatar, R.drawable.avatar_placeholder);
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
