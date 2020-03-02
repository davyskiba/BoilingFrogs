package com.panoprogramowanie.boilingfrogs.ui.speech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.panoprogramowanie.boilingfrogs.BoilingFrogs;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.databinding.SpeechActivityBinding;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wojciech on 12.01.2016.
 */
public class SpeechActivity extends AppCompatActivity implements MvpView {

    //region Navigation

    private final static String SPEECH_ID_ARG = "speech_id_arg";

    public static void startForSpeech(long speechId, Activity activity) {
        Intent intent = new Intent(activity, SpeechActivity.class);
        intent.putExtra(SPEECH_ID_ARG, speechId);
        activity.startActivity(intent);
    }

    //endregion

    //region Views

    @BindView(R.id.contentScroll)
    NestedScrollView contentScroll;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    //endregion

    private SpeechActivityBinding binding;
    private ColorStateList floatingButtonDefaultTint;

    @Inject
    SpeechPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.speech_activity);

        ButterKnife.bind(this);
        floatingButtonDefaultTint = floatingActionButton.getBackgroundTintList();
        setupDrawerAndToolbar();

        long speechId = getIntent().getLongExtra(SPEECH_ID_ARG, 0);

        BoilingFrogs.getMainComponent(this).inject(this);
        presenter.takeView(this);
        presenter.setSpeech(speechId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(presenter.hasVideo()) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.speech_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.speech_play_video:
                presenter.playVideoSelected();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerAndToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.BoilingFrogsTheme_CollapsingToolbar);

        toolbar.setNavigationOnClickListener((v)->onBackPressed());
    }

    //region DataDisplay

    public void displaySpeech(Speech speech) {
        binding.setSpeech(speech);
    }

    public void displaySpeakerData(Speaker speaker) {
        binding.setSpeaker(speaker);
    }

    public void displayFavorite(boolean isFavorite) {
        if (isFavorite) {
            setSelectedFab();
        } else {
            setUnSelectedFab();
        }
    }

    private void setSelectedFab() {
        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.star_full));
        floatingActionButton.setBackgroundTintList(floatingButtonDefaultTint);
    }

    private void setUnSelectedFab() {
        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
    }

    //endregion

    @OnClick(R.id.fab)
    public void onFavoriteClick() {
        presenter.favoriteClicked();
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
