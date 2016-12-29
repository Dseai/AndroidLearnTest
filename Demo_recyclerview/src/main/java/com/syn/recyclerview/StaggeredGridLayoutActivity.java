package com.syn.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙亚楠 on 2016/12/4.
 */

public class StaggeredGridLayoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> data;
    private StaggeredAdapter staggeredAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.id_recyclerview);
        //初始化数据 一点要先初始化数据再 传入adapter 否则会传入空值
        data=new ArrayList<String>();
        for(int i='a';i<'z';i++){
            data.add(""+(char)i);
        }
        staggeredAdapter=new StaggeredAdapter(this,data);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(staggeredAdapter);
        initEvent();

    }

    private void initEvent() {
        staggeredAdapter.setOnItemClickListener(new StaggeredAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.staggered_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.add1:
                staggeredAdapter.addData(1);
                break;
            case R.id.delete1:
                staggeredAdapter.remove(1);
                break;
        }
        return true;
    }
}
