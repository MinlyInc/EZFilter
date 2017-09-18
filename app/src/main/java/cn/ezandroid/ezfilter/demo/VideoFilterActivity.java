package cn.ezandroid.ezfilter.demo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import cn.ezandroid.ezfilter.EZFilter;
import cn.ezandroid.ezfilter.core.RenderPipeline;
import cn.ezandroid.ezfilter.io.input.VideoInput;
import cn.ezandroid.ezfilter.view.SurfaceRenderView;

/**
 * VideoFilterActivity
 *
 * @author like
 * @date 2017-09-16
 */
public class VideoFilterActivity extends BaseActivity {

    private SurfaceRenderView mRenderView;
    private ImageView mPreviewImage;

    private Uri uri1;
    private Uri uri2;

    private Uri mCurrentUri;

    private RenderPipeline mRenderPipeline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_filter);
        mRenderView = $(R.id.render_view);
        mPreviewImage = $(R.id.preview_image);

        uri1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
        uri2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test2);

        mCurrentUri = uri1;

        mRenderPipeline = new EZFilter.VideoBuilder()
                .setVideo(mCurrentUri)
                .setVideoLoop(true)
                .setRotation(1)
                .into(mRenderView);

        $(R.id.change_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeVideo();
            }
        });
    }

    private void changeVideo() {
        if (mCurrentUri == uri1) {
            mCurrentUri = uri2;
        } else {
            mCurrentUri = uri1;
        }

        mRenderPipeline = new EZFilter.VideoBuilder()
                .setVideo(mCurrentUri)
                .setVideoLoop(true)
                .setRotation(1)
                .into(mRenderView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((VideoInput) mRenderPipeline.getStartPointRender()).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((VideoInput) mRenderPipeline.getStartPointRender()).pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}