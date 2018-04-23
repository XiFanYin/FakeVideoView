package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/4/17.
 * 这个类主要是后期处理手势触摸的基础类,同时还有规范方法名字的作用
 */

public abstract class BaseController extends FrameLayout implements View.OnTouchListener {

    public Context mContext;//上下文对象
    public IXVideoView xVideoView;//播放器对象
    private Timer mUpdateProgressTimer;
    private TimerTask mUpdateProgressTimerTask;
    private CountDownTimer mDismissTopBottomCountDownTimer;
    public boolean topBottomVisible = false;//顶部和底部默认不显示
    public boolean LockVisible = true;//锁是否显示和隐藏标记
    public String url;
    private CountDownTimer mDismissLockTimer;

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


    //根据播放器的显示模式去更新控制器Ui的显示
    public abstract void onPlayModeChanged(int Mode);

//===============================================和进度有关的逻辑======================================================

    /**
     * 开启更新进度的计时器。
     */
    protected void startUpdateProgressTimer() {
        if (mUpdateProgressTimer == null) {
            mUpdateProgressTimer = new Timer();//创建Timer对象
        }
        if (mUpdateProgressTimerTask == null) {
            mUpdateProgressTimerTask = new TimerTask() {//创建TimerTask对象
                @Override
                public void run() {
                    BaseController.this.post(new Runnable() {
                        @Override
                        public void run() {
                            updateProgress();//这里每隔一秒调用一次更新进度的方法，
                        }
                    });
                }
            };
        }
        mUpdateProgressTimer.schedule(mUpdateProgressTimerTask, 0, 1000);//把任务添加到定时器中，每隔一秒执行一次Runnable中代码
    }

    /**
     * 取消更新进度的计时器。
     */
    protected void cancelUpdateProgressTimer() {
        if (mUpdateProgressTimer != null) {
            mUpdateProgressTimer.cancel();
            mUpdateProgressTimer = null;
        }
        if (mUpdateProgressTimerTask != null) {
            mUpdateProgressTimerTask.cancel();
            mUpdateProgressTimerTask = null;
        }
    }

    /**
     * 这里更新进度的抽象方法，之所以抽象1，父类中可以调用，2，规范方法名字
     */
    protected abstract void updateProgress();


//============================================和控制器的显示隐藏有关的定时器===============================================

    /**
     * 开启top、bottom自动消失的timer
     */
    protected void startDismissTopBottomTimer() {
        cancelDismissTopBottomTimer();
        if (mDismissTopBottomCountDownTimer == null) {
            mDismissTopBottomCountDownTimer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    setTopBottomVisible(topBottomVisible);//设置顶部和底部显示和隐藏
                    setLockImageViesible(topBottomVisible);//设置锁的显示和隐藏
                    if (xVideoView.isPlaying()) {
                        setCenterImageViesible(topBottomVisible);//设置中间播放按钮是否显示和隐藏
                    }
                    topBottomVisible = !topBottomVisible;
                }
            };
        }
        mDismissTopBottomCountDownTimer.start();
    }

    /**
     * 取消top、bottom自动消失的timer
     */
    protected void cancelDismissTopBottomTimer() {
        if (mDismissTopBottomCountDownTimer != null) {
            mDismissTopBottomCountDownTimer.cancel();
        }
    }

    /**
     * 顶部和底部控制器显示和隐藏抽象方法
     *
     * @param visible
     */
    abstract void setTopBottomVisible(boolean visible);


    /**
     * 中间开始和暂停显示和隐藏
     */
    abstract void setCenterImageViesible(boolean visible);

    /**
     * 锁屏按钮显示和隐藏
     */
    abstract void setLockImageViesible(boolean visible);


    //===================================和url有关的=============================================

    /**
     * 设置视频播放的url
     *
     * @param url
     */
    public void setUrl(String url) {

        this.url = url;
    }

    ;

    /**
     * 获取视频播放的url
     */
    public String getUrl() {

        return url;
    }


    //==============================================和锁显示隐藏有关的定时器=======================================
    ;

    /**
     * 开启锁自动消失的timer
     */
    protected void startDismissLockTimer() {
        cancelDismissLockTimer();
        if (mDismissLockTimer == null) {
            mDismissLockTimer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    setLockImageViesible(LockVisible);
                    LockVisible = !LockVisible;
                }
            };
        }
        mDismissLockTimer.start();
    }

    /**
     * 取消锁自动消失的timer
     */
    protected void cancelDismissLockTimer() {
        if (mDismissLockTimer != null) {
            mDismissLockTimer.cancel();
        }
    }

}
