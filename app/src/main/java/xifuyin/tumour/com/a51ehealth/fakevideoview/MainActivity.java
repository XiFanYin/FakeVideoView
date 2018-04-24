package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private XVideoView xvideo_view;
    private XVideoView xvideo_view2;
    private String url1 = "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4";
    private String url2 = "http://jzvd.nathen.cn/d2e969f2ec734520b46ab0965d2b68bd/f124edfef6c24be8b1a7b7f996ccc5e0-5287d2089db37e62345123a1be272f8b.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xvideo_view = findViewById(R.id.xvideo_view);
        QQBrowserController controller = new QQBrowserController(this);
        controller.setUrl(url1);
        controller.setTitle("视频1");
        controller.getCover().setImageBitmap(Utils.getVideoThumbnail(url1));
        xvideo_view.setController(controller);


        xvideo_view2 = findViewById(R.id.xvideo_view2);
        QQBrowserController controller2 = new QQBrowserController(this);
        controller2.setUrl(url2);
        controller2.setTitle("视频2");
        controller2.getCover().setImageBitmap(Utils.getVideoThumbnail(url2));
        xvideo_view2.setController(controller2);
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
