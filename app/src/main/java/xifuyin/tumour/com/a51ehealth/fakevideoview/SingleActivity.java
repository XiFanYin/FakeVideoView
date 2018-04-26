package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.OrientationEventListener;

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
    private String url1 = "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        xvideo_view = findViewById(R.id.xvideo_view);
        QQBrowserController controller = new QQBrowserController(this);
        controller.setUrl(url1);
        controller.setTitle("视频1");
//        controller.getCover().setImageBitmap(Utils.getVideoThumbnail(url1));//这里会阻塞线程，需要处理
        xvideo_view.setController(controller);
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
