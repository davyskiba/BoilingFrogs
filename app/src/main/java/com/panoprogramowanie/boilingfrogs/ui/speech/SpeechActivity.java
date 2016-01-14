package com.panoprogramowanie.boilingfrogs.ui.speech;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.BoilingFrogs;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.view.SocialView;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wojciech on 12.01.2016.
 */
public class SpeechActivity extends AppCompatActivity {

    private final static String SPEECH_SLOT_ARG ="speech_slot_arg";
    private final static String SPEECH_PATH_ARG ="speech_path_arg";

    public static void startForSpeech(int speechSlot,int speechPath, Activity activity)
    {
        Intent intent=new Intent(activity,SpeechActivity.class);
        intent.putExtra(SPEECH_SLOT_ARG, speechSlot);
        intent.putExtra(SPEECH_PATH_ARG, speechPath);
        activity.startActivity(intent);
    }

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

    private SpeechSlot speechSlot;
    private Speech speech;

    private ColorStateList floatingButtonDefaultTint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_activity);

        ButterKnife.bind(this);
        floatingButtonDefaultTint=floatingActionButton.getBackgroundTintList();

        int speechSlotPosition=getIntent().getIntExtra(SPEECH_SLOT_ARG,0);
        int speechPath=getIntent().getIntExtra(SPEECH_PATH_ARG,0);

        speechSlot=((BoilingFrogs)getApplicationContext()).getScheduleSupplier().getSpeechSlotForPosition(speechSlotPosition);
        speech=speechSlot.getSpeechForPath(speechPath);

        Speaker speaker=speech.getSpeaker();
        speakerName.setText(speaker.getName());
        speakerOccupation.setText(speaker.getOccupation());

        speechTitle.setText(speech.getTitle());
        speechTime.setText(speech.getTimeString());
        speechDescription.setText(speech.getDescription().replace("\\n", "\n").replace("\n\n", "\n").replace("\n", "\n\n"));

        socialView.setupForSpeaker(speech.getSpeaker());

        setupDrawerAndToolbar();

        AvatarLoaderUtil.loadAvatar(this, speech.getSpeaker().getPhotoUrl(), avatar, R.drawable.avatar_placeholder);

        if(isFavorite())
        {
            setSelectedFab();
        }
        else {
            setUnSelectedFab();
        }
    }

    private boolean isFavorite()
    {
        return speechSlot.getFavoriteSpeechPath()==speech.getPath();
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

    @OnClick(R.id.fab)
    public void onFabClick()
    {
        if(isFavorite())
        {
            speechSlot.setFavoriteSpeechPath(-1);

            setUnSelectedFab();
            showSnackbar(R.string.speech_removed_from_favorites);
        }
        else {
            speechSlot.setFavoriteSpeechPath(speech.getPath());

            setSelectedFab();
            showSnackbar(R.string.speech_added_to_favorites);
        }
    }

    private void setSelectedFab()
    {
        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.star_full));
        floatingActionButton.setBackgroundTintList(floatingButtonDefaultTint);
    }

    private void setUnSelectedFab()
    {
        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
    }

    private void showSnackbar(int textId)
    {
        Snackbar.make(findViewById(R.id.contentScroll), getString(textId), Snackbar.LENGTH_SHORT)
                .show();
    }
}
