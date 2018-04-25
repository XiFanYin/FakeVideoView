package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import xifuyin.tumour.com.a51ehealth.xvideoview.QQBrowserController;
import xifuyin.tumour.com.a51ehealth.xvideoview.Utils;
import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoView;
import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoViewManager;

/**
 * Created by Administrator on 2018/4/25.
 */

public class SingleActivity  extends AppCompatActivity {

    private XVideoView xvideo_view;
    private String url1 = "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4";
    private String url2 = "http://jzvd.nathen.cn/d2e969f2ec734520b46ab0965d2b68bd/f124edfef6c24be8b1a7b7f996ccc5e0-5287d2089db37e62345123a1be272f8b.mp4";
    private String url3 = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        xvideo_view = findViewById(R.id.xvideo_view);
        QQBrowserController controller = new QQBrowserController(this);
        controller.setUrl(url1);
        controller.setTitle("视频1");
        controller.getCover().setImageBitmap(Utils.getVideoThumbnail(url1));
        xvideo_view.setController(controller);

    }

    @Override
    protected void onResume() {
        super.onResume();
        XVideoViewManager.getInstance().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        XVideoViewManager.getInstance().onBackground();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        XVideoViewManager.getInstance().onDestroy();
    }
}
