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
 * 明天任务，写一下注释，
 * 实现Button的几种需求
 *
 */

public class SingleActivity extends AppCompatActivity {

    private XVideoView xvideo_view;
    private String url1 = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    String videoUrl = "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4";//播放地址
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        xvideo_view = findViewById(R.id.xvideo_view);//初始化了一个容器，把容器添加到控件
        QQBrowserController controller = new QQBrowserController(this);//初始化了一个布局，设置了点击事件和拖动事件
        controller.setTitle("视频1");//设置视频名字，全屏的时候显示
        xvideo_view.setController(controller);//让自定义播放器持有控制器对象，同时让控制器持有播放器对象，同时把控制器添加到自定义控件中
        xvideo_view.setUrl(url1);
        xvideo_view.start();


        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xvideo_view.setUrl(videoUrl);
                xvideo_view.start();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        XVideoViewManager.getInstance().onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XVideoViewManager.getInstance().onDestroy();
    }
}
