package com.panoprogramowanie.boilingfrogs.ui.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class SocialView extends LinearLayout {

    @BindView(R.id.facebook)
    ImageView facebook;

    @BindView(R.id.twitter)
    ImageView twitter;

    @BindView(R.id.linkedin)
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

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;

        facebook.setVisibility(GONE);
        twitter.setVisibility(GONE);
        linkedin.setVisibility(GONE);

        if (speaker == null) {
            return;
        }

        if (speaker.getFacebook() != null) {
            facebook.setVisibility(VISIBLE);
        }


        if (speaker.getTwitter() != null) {
            twitter.setVisibility(VISIBLE);
        }


        if (speaker.getLinkedin() != null) {
            linkedin.setVisibility(VISIBLE);
        }
    }

    @OnClick(R.id.facebook)
    public void facebookClicked() {
        launchBrowser(speaker.getFacebook());
    }

    @OnClick(R.id.twitter)
    public void twitterClicked() {
        launchBrowser(speaker.getTwitter());
    }

    @OnClick(R.id.linkedin)
    public void linkedinClicked() {
        launchBrowser(speaker.getLinkedin());
    }

    private void launchBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        getContext().startActivity(browserIntent);
    }

    @BindingAdapter("bind:speaker")
    public static void setSpeaker(SocialView view, Speaker speaker){
        view.setSpeaker(speaker);
    }
}
