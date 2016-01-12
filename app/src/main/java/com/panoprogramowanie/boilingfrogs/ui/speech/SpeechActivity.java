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

    @Bind(R.id.speech_title)
    TextView speechTitle;

    @Bind(R.id.speaker_name)
    TextView speakerName;

    @Bind(R.id.speaker_occupation)
    TextView speakerOccupation;

    @Bind(R.id.speech_description)
    TextView speechDescription;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

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
        speechDescription.setText(speech.getDescription().replace("\\n", "\n"));

        setupDrawerAndToolbar(speech.getTimeString());

        AvatarLoaderUtil.loadAvatar(this, speech.getSpeaker().getPhotoUrl(), avatar, R.drawable.avatar_placeholder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String socialUrl;

        Speaker speaker=speech.getSpeaker();

        socialUrl=speaker.getFacebook();
        if(socialUrl!=null) {
            menu.add(0, 0, 0, "Item").setIcon(R.drawable.facebook_icon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        socialUrl=speaker.getTwitter();
        if(socialUrl!=null) {
            menu.add(0, 1, 1, "Item").setIcon(R.drawable.twitter_icon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        socialUrl=speaker.getLinkedIn();
        if(socialUrl!=null) {
            menu.add(0, 2, 2, "Item").setIcon(R.drawable.linkedin_icon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Speaker speaker=speech.getSpeaker();
        switch (item.getItemId())
        {
            case MENU_ITEM_ID_FACEBOOK:
                launchBrowser(speaker.getFacebook());
                break;
            case MENU_ITEM_ID_TWITTER:
                launchBrowser(speaker.getTwitter());
                break;
            case MENU_ITEM_ID_LINKEDIN:
                launchBrowser(speaker.getLinkedIn());
                break;
        }

        return true;
    }

    private void launchBrowser(String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void setupDrawerAndToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.BoilingFrogsTheme_CollapsingToolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
