package com.ldrong.loadingview.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ldrong.loadingview.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ldr on 2016/8/18.
 */
public class GalleryAdapter extends BaseAdapter {
    private static final String PIC_SIZE = "_350_350_cut";//配置显示的小图
//    private static final String PIC_SIZE2 = "_750_1050_cut";


    private final LayoutInflater inf;
    private Context mContext;
    private List<String> mList;
    private String imgs;

    public GalleryAdapter(Context mContext, String string) {
        this.mContext = mContext;
        this.mList = Arrays.asList(string.split("\\|"));
        this.imgs = string;
        inf = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (null == convertView) {
            convertView = inf.inflate(R.layout.image_grid_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //显示图片
        ImageLoader.getInstance().displayImage(getPicUrl(mList.get(position)), holder.idItemImage);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, IndexGalleryActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("imgs", imgs.split("\\|"));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.id_item_image)
        ImageView idItemImage;
        @BindView(R.id.id_item_select)
        ImageButton idItemSelect;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
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
