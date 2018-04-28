package xifuyin.tumour.com.a51ehealth.fakevideoview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import xifuyin.tumour.com.a51ehealth.xvideoview.QQBrowserController;
import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoView;
import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoViewManager;

/**
 * Created by Administrator on 2018/4/28.
 */

public class SingleFragment extends Fragment {

    String videoUrl = "http://video.699pic.com/videos/65/29/06/DnpVuG3auxjP1519652906.mp4";//播放地址
    private String url1 = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single, container, false);
        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final XVideoView xvideo_view = view.findViewById(R.id.xvideo_view);
        Button btn1 = view.findViewById(R.id.btn1);


        QQBrowserController controller = new QQBrowserController(getActivity());//初始化了一个布局，设置了点击事件和拖动事件
        controller.setTitle("视频1");//设置视频名字，全屏的时候显示
        xvideo_view.setController(controller);//让自定义播放器持有控制器对象，同时让控制器持有播放器对象，同时把控制器添加到自定义控件中
        xvideo_view.setUrl(videoUrl);
        xvideo_view.start();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xvideo_view.setUrl(url1);
                xvideo_view.start();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        XVideoViewManager.getInstance().onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        XVideoViewManager.getInstance().onDestroy();
    }
}
