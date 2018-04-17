package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/4/17.
 */

public class QQBrowserController extends BaseController implements View.OnClickListener {
    //控件对象
    public ImageView cover;
    public ImageView back;
    public TextView title;
    public ImageView share;
    public ImageView tiny_window;
    public ImageView menu;
    public LinearLayout top;
    public ImageView lock;
    public ImageView restart_or_pause;
    public TextView position;
    public SeekBar seek;
    public TextView duration;
    public ImageView full_screen;
    public LinearLayout bottom;
    public ImageView center_start;
    private ProgressBar progress_bar;

    public QQBrowserController(@NonNull Context context) {
        super(context);
        init();
    }


    //打入布局，布局是仿照QQ浏览器布局书写的
    private void init() {
        View qq_controller = LayoutInflater.from(mContext).inflate(R.layout.qq_controller, this, true);
        this.cover = qq_controller.findViewById(R.id.cover);
        this.back = qq_controller.findViewById(R.id.back);
        this.title = qq_controller.findViewById(R.id.title);
        this.share = qq_controller.findViewById(R.id.share);
        this.tiny_window = qq_controller.findViewById(R.id.tiny_window);
        this.menu = qq_controller.findViewById(R.id.menu);
        this.top = qq_controller.findViewById(R.id.top);
        this.lock = qq_controller.findViewById(R.id.lock);
        this.restart_or_pause = qq_controller.findViewById(R.id.restart_or_pause);
        this.position = qq_controller.findViewById(R.id.position);
        this.seek = qq_controller.findViewById(R.id.seek);
        this.duration = qq_controller.findViewById(R.id.duration);
        this.full_screen = qq_controller.findViewById(R.id.full_screen);
        this.bottom = qq_controller.findViewById(R.id.bottom);
        this.center_start = qq_controller.findViewById(R.id.center_start);
        this.progress_bar = qq_controller.findViewById(R.id.progress_bar);

        //中心按钮的点击事件
        center_start.setOnClickListener(this);

        //整个布局的点击事件
        this.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == center_start) {//播放按钮被点击

            if (xVideoView.isIdle()) { //判断是否是播放未开始
                xVideoView.start();//调用自定义播放器的开始方法
            }
        } else if (v == this) {


        }


    }


    @Override//根据标记的播放状态去更新对应的UI
    public void onPlayStateChanged(int state) {
        switch (state) {

            case Constants.STATE_IDLE://默认状态
                cover.setVisibility(VISIBLE);
                center_start.setVisibility(VISIBLE);

                break;
            case Constants.STATE_PREPARING://正在准备播放状态，对应去更新UI
                cover.setVisibility(GONE);
                center_start.setVisibility(GONE);
                progress_bar.setVisibility(VISIBLE);
                break;

            case Constants.STATE_PREPARED://播放器准备就绪，准备播放
                progress_bar.setVisibility(GONE);
                break;

            case Constants.STATE_PLAYING://播放器正在播放
                top.setVisibility(VISIBLE);
                bottom.setVisibility(VISIBLE);
                lock.setVisibility(VISIBLE);

                break;


        }

    }
}
