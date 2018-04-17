package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private XVideoView xvideo_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xvideo_view = findViewById(R.id.xvideo_view);
        xvideo_view.setUrl("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4");
        QQBrowserController controller = new QQBrowserController(this);
        xvideo_view.setController(controller);
    }
}
