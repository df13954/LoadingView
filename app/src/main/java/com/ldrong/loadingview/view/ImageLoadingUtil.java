package com.ldrong.loadingview.view;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ldrong.library.DhomeLoadingView;
import com.ldrong.library.LoadingView;
import com.ldrong.loadingview.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.math.BigDecimal;


/**
 * 显示图片，先显示缩略图，然后加载大图片。有圆饼进度条
 * Created by ldr on 2016/8/15.
 */
public class ImageLoadingUtil {

    public static final String FEED_DEAL_SIZE = "230x230,350x350,750x1050,360x160";

    private static final String TAG = "ImageLoadingUtil";

    private static final String PIC_SIZE = "_230_230_cut";//配置显示的小图
//    private static final String PIC_SIZE2 = "_750_1050_cut";

    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            //.showImageOnLoading(R.drawable.pic_downloadfailed_230)// 设置图片在下载期间显示的图片,如果增加了这个，小图就不能显示
            .showImageForEmptyUri(R.drawable.ic_dialog_images_2x)// 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.drawable.ic_dialog_images_2x)// 设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)// 是否緩存都內存中
            .cacheOnDisk(true)// 是否緩存到sd卡上
            //.displayer(new RoundedBitmapDisplayer(10))//图片圆角
            .build();

    public void show(final String uri, DhomeLoadingView dhomeLoadingView) {
        final ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        final ImageView imageView = dhomeLoadingView.getBigImage();
        final LoadingView loadingview = dhomeLoadingView.getLoadingview();
        loadingview.setVisibility(View.GONE);
        imageLoader.displayImage(getPicUrl(uri), imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Log.e(TAG, "小图已经加载完成");
                imageView.setImageBitmap(loadedImage);
                loadingview.setVisibility(View.VISIBLE);
                //小图完成之后,加载大图
                imageLoader.displayImage(uri, imageView, options, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        loadingview.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                        if (0 == total) {
                            //总数据是0
                            Log.i(TAG, "onProgressUpdate: 总大小是0");
                            return;
                        }
                        BigDecimal bigDecimalc = new BigDecimal(current);
                        BigDecimal bigDecimalt = new BigDecimal(total);
                        BigDecimal percent = bigDecimalc.divide(bigDecimalt, 2, BigDecimal.ROUND_HALF_EVEN);
                        int percentValue = (int) (percent.doubleValue() * 100);
                        if (100 == percentValue) {
                            loadingview.setVisibility(View.GONE);
                            Log.e(TAG, "大图加载完成");
                        } else {
                            loadingview.setProgress(percentValue);
                        }
                    }
                });
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    public String getPicUrl(String pic) {
        if (!pic.isEmpty()) {
            int index = pic.indexOf(".jpg");
//			//有可能没有这个jpg
            if (index == -1) return "";
            return pic.substring(0, index) + PIC_SIZE + pic.substring(index, pic.length());
        } else {
            return "";
        }

    }
}
