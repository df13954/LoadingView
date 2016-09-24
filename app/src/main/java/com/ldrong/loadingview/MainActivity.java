package com.ldrong.loadingview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.ldrong.loadingview.view.GalleryAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gv_list)
    GridView gvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //建议使用2g网络测试

        //如果不想加入xml中，可以直接使用
//        DhomeLoadingView dhomeLoadingView = new DhomeLoadingView(this);
//        new ImageLoadingUtil().show(uri, dhomeLoadingView.getBigImage(), dhomeLoadingView.getLoadingview());
        //然后 add.view(dhomeLoadingView);添加进去

        //由于服务器上传图片的时候，裁剪了多套图片，所有需要多套图片的支持
//        String uri = "http://dd-feed.digi123.cn/201608/b2009d69b8f86334FYY9B28X34N80720E7S4UB1WW.jpg";
//
//        new ImageLoadingUtil().show(uri, loadingView);


        String str = "http://dd-feed.digi123.cn/201608/453fec22adda86aa1DZ1P2M06OZ8F19DR9ZM5OY7F.jpg|" +
                "http://dd-feed.digi123.cn/201608/453fec22adda86aaPD6375VINEVEV9BB5I3Z50U76.jpg|" +
                "http://dd-feed.digi123.cn/201608/fd1f5b9304342b112FK3T407MV39078FPI0ER49IF.jpg|" +
                "http://dd-feed.digi123.cn/201608/b2009d69b8f86334FYY9B28X34N80720E7S4UB1WW.jpg|" +
                "http://dd-feed.digi123.cn/201608/1ac0276d0d86ea231NP6B24VGA7HO77TWB54NCF90.jpg|" +
                "http://dd-feed.digi123.cn/201608/cad0e3e735cd2de0BF8EVD5F2DO42DDV3EUH2SHDJ.jpg|" +
                "http://dd-feed.digi123.cn/201608/9fe4dd5b4334e31e0QV116962N6O968S3I1AM01C3.jpg";

        GalleryAdapter adapter = new GalleryAdapter(this, str);
        gvList.setAdapter(adapter);


    }
}
