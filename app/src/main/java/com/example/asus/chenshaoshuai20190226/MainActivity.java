package com.example.asus.chenshaoshuai20190226;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.asus.chenshaoshuai20190226.adapter.RecyclerviewAdapter;
import com.example.asus.chenshaoshuai20190226.entity.HotMovieBean;
import com.example.asus.chenshaoshuai20190226.presenter.IPresent;
import com.example.asus.chenshaoshuai20190226.presenter.IPresentImpl;
import com.example.asus.chenshaoshuai20190226.view.IView;
import com.example.asus.chenshaoshuai20190226.view.NowActivity;

public class MainActivity extends AppCompatActivity implements IView {
    private IPresentImpl iPresent;
    private RecyclerviewAdapter adapter;
    private RecyclerView recyclerView;
    private Button btn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPresent = new IPresentImpl(this);
        recyclerView = findViewById(R.id.recycle);
        btn = findViewById(R.id.btn);

        adapter = new RecyclerviewAdapter(this);
        iPresent.getRequest(Apis.HOT_MOVIE,HotMovieBean.class);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,NowActivity.class));
               finish();
           }
       });
    }

    @Override
    public void onSuccess(Object data) {
        HotMovieBean bean = (HotMovieBean) data;
        adapter.setData(bean.getResult());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresent.onDetach();
    }
}
