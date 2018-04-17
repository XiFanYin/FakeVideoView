package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2018/4/17.
 * 这个类主要是后期处理手势触摸的基础类,同时还有规范方法名字的作用
 */

public abstract class BaseController extends FrameLayout implements View.OnTouchListener {

    public Context mContext;//上下文对象
    public IXVideoView xVideoView;//播放器对象

    public BaseController(@NonNull Context context) {
        super(context);
        this.mContext = context;
        this.setOnTouchListener(this);//注册当前控制器的触摸事件
    }

    /**
     * @param v
     * @param event
     * @return 当返回值为true时当前点击事件被onTouch消耗掉，否则当前点击事件没有被onTouch消耗掉
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    /**
     * 对外提供设置播放器类对象的方法，让本类持有播放器类的对象，好调用播放器类的方法
     */
    public void setXVideoView(IXVideoView xVideoView) {
        this.xVideoView = xVideoView;

    }

    //根据播放器的状态去更新控制器Ui的显示
    public abstract void onPlayStateChanged(int state);
}
