# LoadingView
类似微博加载图片过程显示

![image](https://github.com/rongdongliu/LoadingView/blob/master/img/sample.gif)
##增加依赖
```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

```


```
compile 'com.github.rongdongliu:LoadingView:2.0'
```


```
//如果不想加入xml中，可以直接使用
//然后 add.view(dhomeLoadingView);添加进去
DhomeLoadingView dhomeLoadingView = new DhomeLoadingView(this);
new ImageLoadingUtil().show(uri, dhomeLoadingView);


//由于服务器上传图片的时候，裁剪了多套图片，所有需要多套图片的支持
String uri = "http://dd-feed.digi123.cn/201608/b2009d69b8f86334FYY9B28X34N80720E7S4UB1WW.jpg";
new ImageLoadingUtil().show(uri, loadingView);
```


