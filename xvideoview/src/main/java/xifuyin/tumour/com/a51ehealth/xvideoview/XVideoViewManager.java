package xifuyin.tumour.com.a51ehealth.xvideoview;

/**
 * Created by Administrator on 2018/4/24.
 * <p>
 * 如果视频在列表中去播放，会导致多个视频同事播放，这里记录一下创建的播放器的多少，这里只允许一个播放器工作，其他播放器释放掉
 */

public class XVideoViewManager {

    private static XVideoViewManager mInstance;
    //播放器成员变量
    private IXVideoView mVideoPlayer;

    private boolean isAuto = false;//让视频暂停是来自用户还是来返回到后台自动暂停的标记

    //构造方法
    private XVideoViewManager() {
    }

    /**
     * 单例模式，生成该类对象
     *
     * @return
     */
    public static XVideoViewManager getInstance() {

        if (mInstance == null) {
            synchronized (FloatWindow.class) {
                mInstance = new XVideoViewManager();
            }
        }
        return mInstance;
    }

    //创建好的播放器设置到当前管理类中，记录下来，
    public void setCurrentNiceVideoPlayer(IXVideoView videoPlayer) {
        if (mVideoPlayer != videoPlayer) {
            // 如果播放器不等于之前的播放器，直接释放掉之前的播放器
            releaseXVideoPlayer();
            //同时，让新传入的播放器，记录下来
            mVideoPlayer = videoPlayer;
        }
    }


    /**
     * 释放掉上一个播放器对象
     */
    public void releaseXVideoPlayer() {
        if (mVideoPlayer != null) {
            mVideoPlayer.release();
            mVideoPlayer = null;
        }
    }

    /**
     * 当用户点击了Home键
     */
    public void onBackground() {

        if (mVideoPlayer != null && mVideoPlayer.isPlaying()) {
            mVideoPlayer.Pause();
        }

        if (mVideoPlayer != null && mVideoPlayer.isTinyWindow()) {
            mVideoPlayer.exitTinyWindow();
        }

    }

    /**
     * 当前界面从新获取焦点
     */
    public void onResume() {
        if (mVideoPlayer != null && mVideoPlayer.isPaused()) {
            mVideoPlayer.restart();
        }

    }

    /**
     * 如果只是退出播放器，而不是退出app的时候，只有再不是小屏的时候才释放掉播放器
     */
    public void onDestroy() {
        if (mVideoPlayer != null && !mVideoPlayer.isTinyWindow()) {
            mVideoPlayer.release();
        }

    }

    /**
     * App退出之后，直接释放掉播放器，不管是什么状态
     */
    public void onExitApp() {
        if (mVideoPlayer != null) {
            if (mVideoPlayer.isTinyWindow()) {
                mVideoPlayer.exitTinyWindow();
            }
            mVideoPlayer.release();
        }

    }


}
