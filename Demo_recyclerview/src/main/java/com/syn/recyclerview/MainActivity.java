package com.syn.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> data;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerView=(RecyclerView)findViewById(R.id.id_recyclerview);
        data=new ArrayList<String>();
        //初始化数据
        for(int i='a';i<'z';i++){
            data.add(""+(char)i);
        }
        myAdapter=new MyAdapter(this,data);
        //利用一行显示4列的gridlayout 并设置垂直分布
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL ));
        recyclerView.setAdapter(myAdapter);

        //为每个item设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置每个item的样式   间隔线
      //  recyclerView.addItemDecoration(new DividerItemDecoration(this,2));

        initEvent();
    }

    private void initEvent() {
        //完成回调
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"长按了"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
      getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.add:
                myAdapter.addData(1);
                break;
            case R.id.delete:
                myAdapter.remove(1);
                break;
            case R.id.gridview:
                recyclerView.setLayoutManager(new GridLayoutManager(this,4));
                break;
            case R.id.horizontal_gridview:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.listView:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_staggeredgridview:
                Intent intent=new Intent(MainActivity.this,StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}
