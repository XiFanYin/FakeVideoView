package xifuyin.tumour.com.a51ehealth.fakevideoview;

/**
 * Created by Administrator on 2018/4/17.
 */

public interface IXVideoView {

    /**
     * 开始播放的方法
     */
    void start();

    /**
     * 设置视频播放的url
     *
     * @param url
     */
    void setUrl(String url);


    //===============================播放器状态=================================

    boolean isIdle();

    boolean isPreparing();

    boolean isPlaying();
}
