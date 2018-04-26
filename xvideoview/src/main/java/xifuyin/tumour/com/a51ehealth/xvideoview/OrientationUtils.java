package xifuyin.tumour.com.a51ehealth.xvideoview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.util.Log;
import android.view.OrientationEventListener;

/**
 * Created by Administrator on 2018/4/26.
 */

public class OrientationUtils {


    private MyOrientationDetector orientationDetector;

    public OrientationUtils(Context context, IXVideoView mVideoPlayer) {

        boolean autoRotateOn = (Settings.System.getInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 1);

        orientationDetector = new MyOrientationDetector(context, mVideoPlayer);

    }

    /**
     * 开启旋转监听
     */
    public void enable() {
        orientationDetector.enable();

    }

    /**
     * 关闭旋转监听
     */
    public void disable() {
        orientationDetector.disable();
    }


    public class MyOrientationDetector extends OrientationEventListener {

        private Context context;
        private IXVideoView mVideoPlayer;

        public MyOrientationDetector(Context context, IXVideoView mVideoPlayer) {
            super(context);
            this.mVideoPlayer = mVideoPlayer;
            this.context = context;
        }

        @Override
        public void onOrientationChanged(int orientation) {
            boolean autoRotateOn = (Settings.System.getInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 1);
            boolean isLock = mVideoPlayer.isLock();
            if (autoRotateOn&&!isLock) {
                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {

                    if (mVideoPlayer.isFullScreen()) {
                        mVideoPlayer.exitFullScreen();
                    }
                }
                //只检测是否有四个角度的改变
                if (orientation > 350 || orientation < 10) {

                    if (mVideoPlayer.isFullScreen()) {
                        mVideoPlayer.exitFullScreen();
                    }

                } else if (orientation > 80 && orientation < 100) {

                    if (mVideoPlayer.isNormal()) {
                        mVideoPlayer.enterFullScreen(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    }
                } else if (orientation > 170 && orientation < 190) { //180度

                    if (mVideoPlayer.isFullScreen()) {
                        mVideoPlayer.exitFullScreen();
                    }

                } else if (orientation > 260 && orientation < 280) {

                    if (mVideoPlayer.isNormal()) {
                        mVideoPlayer.enterFullScreen(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }

                } else {
                    return;
                }

            }
        }
    }

}
