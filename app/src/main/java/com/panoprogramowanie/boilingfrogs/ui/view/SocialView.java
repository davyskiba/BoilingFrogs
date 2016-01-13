package com.panoprogramowanie.boilingfrogs.ui.view;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class SocialView extends LinearLayout {

    @Bind(R.id.facebook)
    ImageView facebook;

    @Bind(R.id.twitter)
    ImageView twitter;

    @Bind(R.id.linkedin)
    ImageView linkedin;

    private Speaker speaker;

    public SocialView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setupForSpeaker(Speaker speaker)
    {
        this.speaker=speaker;

        facebook.setVisibility(GONE);
        twitter.setVisibility(GONE);
        linkedin.setVisibility(GONE);

        if(speaker==null) {
            return;
        }

        if(speaker.getFacebook()!=null)
        {
            facebook.setVisibility(VISIBLE);
        }


        if(speaker.getTwitter()!=null)
        {
            twitter.setVisibility(VISIBLE);
        }


        if(speaker.getLinkedIn()!=null)
        {
            linkedin.setVisibility(VISIBLE);
        }
    }

    @OnClick(R.id.facebook)
    public void facebookClicked()
    {
        launchBrowser(speaker.getFacebook());
    }

    @OnClick(R.id.twitter)
    public void twitterClicked()
    {
        launchBrowser(speaker.getTwitter());
    }

    @OnClick(R.id.linkedin)
    public void linkedinClicked()
    {
        launchBrowser(speaker.getLinkedIn());
    }


    private void launchBrowser(String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        getContext().startActivity(browserIntent);
    }
}
