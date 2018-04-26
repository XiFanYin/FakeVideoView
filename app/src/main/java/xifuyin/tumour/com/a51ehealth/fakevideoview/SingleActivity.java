package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xifuyin.tumour.com.a51ehealth.xvideoview.QQBrowserController;
import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoView;
import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoViewManager;

/**
 * Created by Administrator on 2018/4/25.
 * <p>
 * 要实现重力感应，1怎么监听到屏幕被用户旋转到哪个方向了  2，根据方向，来确定大小屏幕以及方向的设置
 */

public class SingleActivity extends AppCompatActivity {

    private XVideoView xvideo_view;
    private String url1 = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    private String url2 = "http://play.g3proxy.lecloud.com/vod/v2/MjQ5LzM3LzIwL2xldHYtdXRzLzE0L3Zlcl8wMF8yMi0xMTA3NjQxMzkwLWF2Yy00MTk4MTAtYWFjLTQ4MDAwLTUyNjExMC0zMTU1NTY1Mi00ZmJjYzFkNzA1NWMyNDc4MDc5OTYxODg1N2RjNzEwMi0xNDk4NTU3OTYxNzQ4Lm1wNA==?b=479&mmsid=65565355&tm=1499247143&key=98c7e781f1145aba07cb0d6ec06f6c12&platid=3&splatid=345&playid=0&tss=no&vtype=13&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        xvideo_view = findViewById(R.id.xvideo_view);
        QQBrowserController controller = new QQBrowserController(this);
        controller.setTitle("视频1");
        xvideo_view.setController(controller);
        xvideo_view.setUrl(url1);
        xvideo_view.start();


    }


    @Override
    protected void onResume() {
        super.onResume();
        XVideoViewManager.getInstance().onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XVideoViewManager.getInstance().onDestroy();
    }
}
