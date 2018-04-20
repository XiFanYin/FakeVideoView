package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private XVideoView xvideo_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xvideo_view = findViewById(R.id.xvideo_view);
        QQBrowserController controller = new QQBrowserController(this);
        controller.setUrl("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4");
        xvideo_view.setController(controller);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FloatWindow.getInstance(getApplicationContext()).dismass();
    }
}
