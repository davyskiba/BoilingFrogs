package com.panoprogramowanie.boilingfrogs.ui.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class ListItemModelView extends RelativeLayout {
    @Bind(R.id.photo)
    ImageView photo;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.subtitle)
    TextView subtitle;

    ListItemModel model;

    public ListItemModelView(Context context) {
        super(context);
    }

    public ListItemModelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListItemModelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void takeModel(ListItemModel model) {

        this.model = model;

        title.setText(model.getTitle());
        subtitle.setText(model.getSubtitle());
        String photoUrl = model.getPhotoUrl();

        if (photoUrl == null) {
            setTextGravity(Gravity.CENTER);
            setPhotoVisibiliy(View.GONE);
        } else {
            setTextGravity(Gravity.LEFT);
            setPhotoVisibiliy(View.VISIBLE);
            AvatarLoaderUtil.loadAvatar(getContext(), photoUrl, photo, R.drawable.avatar_placeholder);
        }
    }

    private void setTextGravity(int center) {
        title.setGravity(center);
        subtitle.setGravity(center);
    }

    private void setPhotoVisibiliy(int visibiliy) {
        photo.setVisibility(visibiliy);
    }

    public ListItemModel getModel() {
        return model;
    }
}
