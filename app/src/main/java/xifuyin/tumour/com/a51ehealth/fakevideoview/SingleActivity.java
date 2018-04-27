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
 *
 */

public class SingleActivity extends AppCompatActivity {

    private XVideoView xvideo_view;
    private String url1 = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    String videoUrl = "http://video.699pic.com/videos/65/29/06/DnpVuG3auxjP1519652906.mp4";//播放地址
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
        XVideoViewManager.getInstance().onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XVideoViewManager.getInstance().onDestroy();
    }
}
