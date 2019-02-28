package com.example.asus.chenshaoshuai20190226.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.asus.chenshaoshuai20190226.Apis;
import com.example.asus.chenshaoshuai20190226.MainActivity;
import com.example.asus.chenshaoshuai20190226.R;
import com.example.asus.chenshaoshuai20190226.adapter.RecuclenowAdapter;
import com.example.asus.chenshaoshuai20190226.adapter.RecyclerviewAdapter;
import com.example.asus.chenshaoshuai20190226.entity.HotMovieBean;
import com.example.asus.chenshaoshuai20190226.entity.NowMovieBean;
import com.example.asus.chenshaoshuai20190226.presenter.IPresentImpl;

public class NowActivity extends AppCompatActivity implements IView{
    private IPresentImpl iPresent;
    private RecuclenowAdapter adapter;
    private RecyclerView recyclerView;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now);

        iPresent = new IPresentImpl(this);
        recyclerView = findViewById(R.id.recycle_now);
        btn = findViewById(R.id.btn_now);
        adapter = new RecuclenowAdapter(this);
        iPresent.getRequest(Apis.NOW_MOIVE,NowMovieBean.class);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NowActivity.this,MainActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onSuccess(Object data) {
       NowMovieBean bean = (NowMovieBean) data;
       adapter.setData(bean.getResult());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresent.onDetach();
    }
}
