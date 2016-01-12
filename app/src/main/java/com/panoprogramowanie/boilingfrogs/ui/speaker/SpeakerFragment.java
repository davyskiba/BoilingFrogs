package com.panoprogramowanie.boilingfrogs.ui.speaker;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 12.01.2016.
 */
public class SpeakerFragment extends BoilingFrogsFragment {

    private static String SPEAKER_ARG_KEY ="speaker_arg";

    public static SpeakerFragment createInstance(Speaker speaker){
        Bundle args=new Bundle();
        args.putParcelable(SPEAKER_ARG_KEY, speaker);

        SpeakerFragment result=new SpeakerFragment();
        result.setArguments(args);

        return result;
    }

    @Override
    public String getToolbarTitle(Context context) {
        return context.getString(R.string.toolbar_title_speaker);
    }

    @Bind(R.id.speaker_avatar)
    ImageView avatar;

    @Bind(R.id.speaker_name)
    TextView speakerName;

    @Bind(R.id.speaker_occupation)
    TextView speakerOccupation;

    @Bind(R.id.speaker_about)
    TextView speakerAbout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result= inflater.inflate(R.layout.fragment_speaker,null);
        ButterKnife.bind(this,result);

        Speaker speaker=getArguments().getParcelable(SPEAKER_ARG_KEY);

        AvatarLoaderUtil.loadAvatar(getActivity(),speaker.getPhotoUrl(),avatar,R.drawable.avatar_placeholder);
        speakerName.setText(speaker.getName().toUpperCase());
        speakerAbout.setText(speaker.getDescription().replace("\\n","\n"));
        speakerOccupation.setText(speaker.getOccupation().toUpperCase());

        return result;
    }
}
