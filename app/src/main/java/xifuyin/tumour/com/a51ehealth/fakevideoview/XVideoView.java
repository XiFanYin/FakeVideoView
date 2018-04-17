package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2018/4/17.
 */

public class XVideoView extends FrameLayout implements IXVideoView, TextureView.SurfaceTextureListener {

    private Context mContext;//上下文
    private FrameLayout mContainer;//容器
    private BaseController mController;//控制器布局
    private String url;//播放视频的url
    private int mCurrentState = Constants.STATE_IDLE;//记录播放器状态
    private IjkMediaPlayer mediaPlayer;
    private SurfaceTexture mSurfaceTexture;
    private XTextureView mTextureView;

    public XVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {//小容器添加到大布局中去，这里设置的是MATCH_PARENT，父布局会给父布局最大的布局范围
        mContainer = new FrameLayout(mContext);
        mContainer.setBackgroundColor(Color.BLACK);//设置为黑色背景，
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(mContainer, params);
    }

    /**
     * 对外暴漏设置控制器的方法，其实就是向容器中添加一个布局
     *
     * @param mController
     */
    public void setController(BaseController mController) {
        mContainer.removeView(mController);//先移除之前添加的控制器，如果第一次添加也不会报错，因为系统已经做了判断
        this.mController = mController;//这里就持有控制器的对象
        mController.setXVideoView(this);//这里设置播放器对象
        //更改当前UI状态为正在准备中，同时调用控制器中更新Ui的方法
        mCurrentState = Constants.STATE_IDLE;
        mController.onPlayStateChanged(mCurrentState);//更新控制器为正在准备状态
        //把控制器布局添加到播放器中
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(mController, params);
    }

    /**
     * 设置视频的url
     *
     * @param url
     */
    @Override
    public void setUrl(String url) {
        this.url = url;
    }


    //用户点击控制器播放按钮时候调用的方法
    @Override
    public void start() {
        //初始化播放器
        initMediaPlayer();
        //初始化TextTureView控件
        initTextureView();
        //添加TextureView到容器中
        addTextureView();
    }

    //初始化播放器
    private void initMediaPlayer() {

        if (mediaPlayer == null) {
            mediaPlayer = new IjkMediaPlayer();
        }
    }

    //初始化TextureView，并设置创建SurfaceTexture完成监听
    private void initTextureView() {

        if (mTextureView == null) {
            mTextureView = new XTextureView(mContext);
            mTextureView.setSurfaceTextureListener(this);
        }

    }

    /**
     * 添加TextureView到容器中
     */
    private void addTextureView() {
        mContainer.removeView(mTextureView);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        mContainer.addView(mTextureView, 0, params);
    }


    //===============================texttureView.setSurfaceTextureListener(this)回调======================================================
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        if (mSurfaceTexture == null) {
            mSurfaceTexture = surfaceTexture;//把mSurfaceTexture变成成员变量
            openMediaPlayer();
        } else {
            mTextureView.setSurfaceTexture(mSurfaceTexture);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    //播放视频的一系列准备工作
    private void openMediaPlayer() {
        //设置监听
        mediaPlayer.setOnPreparedListener(onPrepared());//设置准备完成监听
        mediaPlayer.setOnVideoSizeChangedListener(onVideoSizeChanged());//当视频大小改变的时候
        mediaPlayer.setOnInfoListener(onInfo());//视频加载信息监听回调
        //创建Surface对象，让mediaPlayer通过Surface 和mSurfaceTexture与TextureView关联起来
        Surface surface = new Surface(mSurfaceTexture);
        mediaPlayer.setSurface(surface);//设置视频流
        try {
            mediaPlayer.setDataSource(mContext.getApplicationContext(), Uri.parse(url));//设置视频播放地址
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();//异步准备播放视频
        //更改当前UI状态为正在准备中，同时调用控制器中更新Ui的方法
        mCurrentState = Constants.STATE_PREPARING;
        mController.onPlayStateChanged(mCurrentState);//更新控制器为正在准备状态

    }


    //==========================MediaPlayer的监听回调====================================================


    @NonNull//视频准备完成之后调用
    private IMediaPlayer.OnPreparedListener onPrepared() {
        return new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                iMediaPlayer.start();//开始播放视频
                mCurrentState = Constants.STATE_PREPARED;//播放准备就绪
                mController.onPlayStateChanged(mCurrentState);//更新控制器为正在准备状态
            }
        };
    }


    @NonNull//让视频不变形
    private IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChanged() {
        return new IMediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
                mTextureView.adaptVideoSize(iMediaPlayer.getVideoWidth(), iMediaPlayer.getVideoHeight());
            }
        };
    }


    @NonNull
    private IMediaPlayer.OnInfoListener onInfo() {
        return new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int extra) {
                switch (what) {
                    //The player just pushed the very first video frame for rendering,播放第一帧回调
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:

                        mCurrentState = Constants.STATE_PLAYING;//正在播放
                        mController.onPlayStateChanged(mCurrentState);//更新控制器为正在准备状态

                        break;
                }


                return true;
            }
        };
    }

    //===========================================播放器状态判断==============================================================
    //播放器是否处于默认状态，第一次播放状态
    @Override
    public boolean isIdle() {
        return mCurrentState == Constants.STATE_IDLE;
    }

    //播放器正在做播放的准备工作
    @Override
    public boolean isPreparing() {
        return mCurrentState == Constants.STATE_PREPARING;
    }
    //播放器正在播放中
    @Override
    public boolean isPlaying() {
        return mCurrentState == Constants.STATE_PLAYING;
    }


}
