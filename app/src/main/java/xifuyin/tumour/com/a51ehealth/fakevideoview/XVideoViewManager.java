package xifuyin.tumour.com.a51ehealth.fakevideoview;

/**
 * Created by Administrator on 2018/4/24.
 *
 * 如果视频在列表中去播放，会导致多个视频同事播放，这里记录一下创建的播放器的多少，这里只允许一个播放器工作，其他播放器释放掉
 */

public class XVideoViewManager {

    private static XVideoViewManager mInstance;
    //播放器成员变量
    private IXVideoView mVideoPlayer;

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



    public void setCurrentNiceVideoPlayer(IXVideoView videoPlayer) {
        //创建好的播放器设置到当前管理类中，记录下来，
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
}
