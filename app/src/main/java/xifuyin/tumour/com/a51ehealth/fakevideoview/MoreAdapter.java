package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xifuyin.tumour.com.a51ehealth.xvideoview.QQBrowserController;
import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoView;

/**
 * Created by Administrator on 2018/4/28.
 */

public class MoreAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Activity act;
    private XVideoView xvideo_view;
    private Button btn1;
    private String url1 = "rtmp://live.hkstv.hk.lxdns.com/live/hks";

    public MoreAdapter(int layoutResId, @Nullable List<String> data, Activity act) {
        super(layoutResId, data);
        this.act = act;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        xvideo_view = helper.getView(R.id.xvideo_view);
        QQBrowserController controller = new QQBrowserController(act);//初始化了一个布局，设置了点击事件和拖动事件
        controller.setTitle("视频" + helper.getAdapterPosition());//设置视频名字，全屏的时候显示
        xvideo_view.setController(controller);//让自定义播放器持有控制器对象，同时让控制器持有播放器对象，同时把控制器添加到自定义控件中
        xvideo_view.setUrl(item);

    }
}
