package com.ldrong.loadingview.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ldrong.library.DhomeLoadingView;
import com.ldrong.loadingview.R;
import com.ldrong.loadingview.zo.ViewPagerFixed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexGalleryActivity extends Activity {
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_context)
    TextView tvContext;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.rl_glob)
    RelativeLayout rlGlob;
    @BindView(R.id.vp_pager)
    ViewPagerFixed vpPager;
    // 当前的位置
    private int location = 0;


    private MyPageAdapter adapter;
    private int maxnum;
    private int id;// 刚进来的时候，要显示点击的那张，并像是*/* 有3个地方改变；1、监听器滑动;2、del；3、开始
    private String[] imgs;
    private IndexGalleryActivity mContext;
    private String contextText;
    private String[] postIdList;
    private int[] postTypeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indexgallery);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        imgs = intent.getStringArrayExtra("imgs");
        id = intent.getIntExtra("position", 0);
        contextText = intent.getStringExtra("contextText");

        maxnum = imgs.length;
        vpPager.addOnPageChangeListener(pageChangeListener);
        adapter = new MyPageAdapter(imgs);
        vpPager.setAdapter(adapter);
        int margin = getResources().getDimensionPixelOffset(R.dimen.album_margin);
        vpPager.setPageMargin(margin);
        if (id == 0) {
            tvNumber.setText(id + 1 + "/" + maxnum);
        } else if (id == -1) {
            tvNumber.setVisibility(View.GONE);
        }
        //如果是1个，就不显示1/1
        List imgList = Arrays.asList(imgs);
        if (imgList.size() == 1) {
            tvNumber.setVisibility(View.GONE);
        }

        vpPager.setCurrentItem(id);
    }


    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            location = position;
            tvNumber.setText(location + 1 + "/" + maxnum);
            id = position;
        }

        @Override
        public void onPageScrolled(int i, float f, int j) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    class MyPageAdapter extends PagerAdapter {
        private int size;
        private String[] imgs;
        private HashMap<Integer, View> hashMap = new HashMap<Integer, View>();

        public MyPageAdapter(String[] imgs) {
            this.imgs = imgs;
            size = imgs == null ? 0 : imgs.length;
        }

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(View view, int i, Object object) {
            ((ViewPagerFixed) view).removeView((View) object);
            hashMap.remove(i);
        }

        @Override
        public void finishUpdate(View view) {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            DhomeLoadingView lv = new DhomeLoadingView(mContext);
            try {
                new ImageLoadingUtil().show(imgs[position], lv);

            } catch (Exception e) {
                e.printStackTrace();
            }
            ((ViewPager) container).addView(lv);
            hashMap.put(position, lv);
            return lv;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
    private static final String PIC_SIZE = "_230_230_cut";//配置显示的小图
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
