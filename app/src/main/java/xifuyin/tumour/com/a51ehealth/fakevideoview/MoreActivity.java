package xifuyin.tumour.com.a51ehealth.fakevideoview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import xifuyin.tumour.com.a51ehealth.xvideoview.XVideoViewManager;

/**
 * Created by Administrator on 2018/4/28.
 */

public class MoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String url1 = "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4";
    private String url2 = "http://jzvd.nathen.cn/63f3f73712544394be981d9e4f56b612/69c5767bb9e54156b5b60a1b6edeb3b5-5287d2089db37e62345123a1be272f8b.mp4";
    private String url3 = "http://jzvd.nathen.cn/b201be3093814908bf987320361c5a73/2f6d913ea25941ffa78cc53a59025383-5287d2089db37e62345123a1be272f8b.mp4";
    private String url4 = "http://jzvd.nathen.cn/d2438fd1c37c4618a704513ad38d68c5/68626a9d53ca421c896ac8010f172b68-5287d2089db37e62345123a1be272f8b.mp4";
    private String url5 = "http://jzvd.nathen.cn/25a8d119cfa94b49a7a4117257d8ebd7/f733e65a22394abeab963908f3c336db-5287d2089db37e62345123a1be272f8b.mp4";
    private String url6 = "http://jzvd.nathen.cn/7512edd1ad834d40bb5b978402274b1a/9691c7f2d7b74b5e811965350a0e5772-5287d2089db37e62345123a1be272f8b.mp4";
    private String url7 = "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<String> urls = new ArrayList<>();
        urls.add(url1);
        urls.add(url2);
        urls.add(url3);
        urls.add(url4);
        urls.add(url5);
        urls.add(url6);
        urls.add(url7);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MoreAdapter adapter = new MoreAdapter(R.layout.item_move, urls, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        XVideoViewManager.getInstance().onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XVideoViewManager.getInstance().onDestroy();
    }
}
