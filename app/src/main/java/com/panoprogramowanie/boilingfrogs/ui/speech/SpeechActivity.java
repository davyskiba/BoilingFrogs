package com.panoprogramowanie.boilingfrogs.ui.speech;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.ui.view.SocialView;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 12.01.2016.
 */
public class SpeechActivity extends AppCompatActivity {

    private final static String SPEAKER_ARG_KEY ="speech_arg";

    private final static int MENU_ITEM_ID_FACEBOOK =0;
    private final static int MENU_ITEM_ID_TWITTER=1;
    private final static int MENU_ITEM_ID_LINKEDIN=2;

    public static void startForSpeech(Speech speech, Activity activity)
    {
        Intent intent=new Intent(activity,SpeechActivity.class);
        intent.putExtra(SPEAKER_ARG_KEY, speech);
        activity.startActivity(intent);
    }

    private Speech speech;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_activity);

        ButterKnife.bind(this);


        speech=getIntent().getParcelableExtra(SPEAKER_ARG_KEY);


        Speaker speaker=speech.getSpeaker();
        speakerName.setText(speaker.getName());
        speakerOccupation.setText(speaker.getOccupation().toUpperCase());

        speechTitle.setText(speech.getTitle());
        speechTime.setText(speech.getTimeString());
        speechDescription.setText(speech.getDescription().replace("\\n", "\n").replace("\n\n","\n").replace("\n","\n\n"));

        socialView.setupForSpeaker(speech.getSpeaker());

        setupDrawerAndToolbar();

        AvatarLoaderUtil.loadAvatar(this, speech.getSpeaker().getPhotoUrl(), avatar, R.drawable.avatar_placeholder);
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
}
