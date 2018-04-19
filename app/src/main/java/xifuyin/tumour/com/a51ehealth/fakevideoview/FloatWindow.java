package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/4/19.
 * 悬浮窗管理类
 */

public class FloatWindow {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private static FloatWindow mInstance;
    private XTextureView View;


    /**
     * 单例模式，生成该类对象
     *
     * @return
     */
    public static FloatWindow getInstance(Context applicationContext) {

        if (mInstance == null) {
            synchronized (FloatWindow.class) {
                mInstance = new FloatWindow(applicationContext);
            }
        }
        return mInstance;
    }

    //构造方法
    private FloatWindow(Context applicationContext) {
        // 获取系统窗体管理类
        mWindowManager = (WindowManager) applicationContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        // 获取描述窗口的管理策略类
        mLayoutParams = new WindowManager.LayoutParams();

    }

    /**
     * 设置显示类型
     *
     * @param layoutParamsType
     * @return
     */
    public FloatWindow setType(int layoutParamsType) {
        // 设置窗体显示类型——TYPE_SYSTEM_ALERT(系统提示)
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        return mInstance;
    }

    /**
     * 设置显示标记
     *
     * @param layoutParamsFlags
     * @return
     */
    public FloatWindow setFlags(int layoutParamsFlags) {
        mLayoutParams.flags = layoutParamsFlags;
        return mInstance;
    }

    /**
     * 设置显示模式
     *
     * @param layoutParamsFormat
     * @return
     */

    public FloatWindow setFormat(int layoutParamsFormat) {

        mLayoutParams.format = layoutParamsFormat;

        return mInstance;
    }

    /**
     * 设置显示位置
     *
     * @param layoutParamsGravity
     * @return
     */

    public FloatWindow setGravity(int layoutParamsGravity) {

        mLayoutParams.gravity = layoutParamsGravity;
        return mInstance;
    }


    public FloatWindow setWidth(int layoutParamsWidth) {

        mLayoutParams.width = layoutParamsWidth;

        return mInstance;
    }


    public FloatWindow setHeight(int layoutParamsHeight) {

        mLayoutParams.height = layoutParamsHeight;
        return mInstance;
    }

    public FloatWindow setX(int x) {
        mLayoutParams.x = x;
        return mInstance;
    }

    public FloatWindow setY(int y) {
        mLayoutParams.y = y;
        return mInstance;
    }

    public FloatWindow addView(XTextureView mTextureView) {
        this.View = mTextureView;

        ViewGroup View = (ViewGroup) mTextureView.getParent();
        if (View != null) {
            View.removeAllViews();
        }

        addOnTouch();

        return mInstance;
    }

    private void addOnTouch() {

        View.setOnTouchListener(new View.OnTouchListener() {
            float downX = 0;
            float downY = 0;
            int oddOffsetX = 0;
            int oddOffsetY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downX =  event.getX();
                        downY =  event.getY();
                        oddOffsetX = mLayoutParams.x;
                        oddOffsetY = mLayoutParams.y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getX();
                        float moveY =  event.getY();
                        //不除以3，拖动的view抖动的有点厉害
                        mLayoutParams.x += (moveX - downX)/3;
                        mLayoutParams.y += (moveY - downY)/3;
                        if(View != null){
                            mWindowManager.updateViewLayout(View,mLayoutParams);
                        }

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return true;
            }

        });
    }


    public void show() {

        mWindowManager.addView(View, mLayoutParams);
    }
}
