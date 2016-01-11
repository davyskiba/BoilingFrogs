package com.panoprogramowanie.boilingfrogs.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class SpeechListItem extends RelativeLayout {

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.speaker_avatar)
    ImageView avatar;

    @Bind(R.id.subtitle)
    TextView subtitle;

    private Speech speech;

    public SpeechListItem(Context context) {
        super(context);
    }

    public SpeechListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeechListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void takeSpeech(Speech speech) {
        this.speech = speech;

        title.setText(speech.getTitle());

        Speaker speaker=speech.getSpeaker();
        if(speaker==null)
        {
            title.setGravity(Gravity.CENTER);
            subtitle.setText("");

            avatar.setVisibility(View.GONE);
        }
        else
        {
            title.setGravity(Gravity.LEFT);
            subtitle.setText(speaker.getName());

            avatar.setVisibility(View.VISIBLE);
            AvatarLoaderUtil.loadAvatar(getContext(), speaker.getPhotoUrl(), avatar, R.drawable.avatar_placeholder);
        }
    }

    public Speech getSpeech() {
        return speech;
    }
}
