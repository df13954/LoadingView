package com.ldrong.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by ldr on 2016/8/17.
 */
public class DhomeLoadingView extends RelativeLayout {
    private static final String TAG = "LoadingView";
    private LoadingView loadingview;
    private PhotoView bigImage;
    private View rootView;

    public DhomeLoadingView(Context context) {
        super(context, null);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.imageview_dhome, this);
        bigImage = (PhotoView) findViewById(R.id.iv_big);
        loadingview = (LoadingView) findViewById(R.id.loadingview);
    }


    public DhomeLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.imageview_dhome, this);
        bigImage = (PhotoView) findViewById(R.id.iv_big);
        loadingview = (LoadingView) findViewById(R.id.loadingview);
    }




    public ImageView getBigImage() {
        return bigImage;
    }

    public LoadingView getLoadingview() {
        return loadingview;
    }
}
