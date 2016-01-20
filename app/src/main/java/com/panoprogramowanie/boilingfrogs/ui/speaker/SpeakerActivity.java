package com.panoprogramowanie.boilingfrogs.ui.speaker;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;
import com.panoprogramowanie.boilingfrogs.ui.schedule.ScheduleFragment;
import com.panoprogramowanie.boilingfrogs.ui.speakers.SpeakersFragment;
import com.panoprogramowanie.boilingfrogs.ui.view.SocialView;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 12.01.2016.
 */
public class SpeakerActivity extends AppCompatActivity implements MvpView{

    //region Navigation

    private final static String SPEAKER_ARG_KEY ="speaker_arg";

    public static void startForSpeaker(Speaker speaker, Activity activity)
    {
        Intent intent=new Intent(activity,SpeakerActivity.class);
        intent.putExtra(SPEAKER_ARG_KEY, speaker);
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

    SpeakerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker_activity);

        ButterKnife.bind(this);

        Speaker speaker=getIntent().getParcelableExtra(SPEAKER_ARG_KEY);

        presenter=new SpeakerPresenter();
        presenter.takeView(this);
        presenter.setSpeaker(speaker);


        setupDrawerAndToolbar();
    }

    public void displaySpeakerData(Speaker speaker){
        speakerName.setText(speaker.getName());
        speakerOccupation.setText(speaker.getOccupation());
        speakerAbout.setText(speaker.getDescription().replace("\\n", "\n").replace("\n\n","\n").replace("\n", "\n\n"));

        socialView.setupForSpeaker(speaker);
        AvatarLoaderUtil.loadAvatar(this, speaker.getPhotoUrl(), avatar, R.drawable.avatar_placeholder);
    }

    private void setupDrawerAndToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
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
