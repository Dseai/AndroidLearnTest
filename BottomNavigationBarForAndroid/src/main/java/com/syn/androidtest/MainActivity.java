package com.syn.androidtest;

import android.animation.FloatArrayEvaluator;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.syn.androidtest.fragment.NavigationFragment;
import com.syn.androidtest.fragment.RadioFragment;
import com.syn.androidtest.fragment.TabLayoutFragment;
import com.syn.androidtest.fragment.TabLayoutFragment2;
import com.syn.androidtest.fragment.TextTabFragment;

/**
 * activity+fragment实现  底部导航样式实现
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationFragment navigationFragment;
    private RadioFragment radioFragment;
    private TextTabFragment textTabFragment;
    private TabLayoutFragment tabLayoutFragment;
    private TabLayoutFragment2 tabLayoutFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        navigationView=(NavigationView)findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        //给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         mDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_layout_open,R.string.drawer_layout_close);
        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(true);//true  改回侧选单按钮，false 改回返回按钮
        //设置状态栏背景颜色
        drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        View headerView=navigationView.getHeaderView(0);//获取头部view
        headerView.setOnClickListener(this);//给头部view设置监听器
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        //设置menuItem的字体颜色 设置菜单title的颜色
        navigationView.setItemTextColor(ContextCompat.getColorStateList(this,R.color.bg_drawer_navigation));
        //设置菜单icon的颜色，
        navigationView.setItemIconTintList(ContextCompat.getColorStateList(this,R.color.bg_drawer_navigation));
//设置导航视图检查
        setNavigationViewChecked(0);
        setCurrrentFragment();
    }

    private void setCurrrentFragment() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        navigationFragment=NavigationFragment.newInstance("NavigationBar");
        transaction.replace(R.id.fram_content,navigationFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.menu_bottom_navigation_bar:
                if(navigationFragment==null){
                    navigationFragment=NavigationFragment.newInstance("NavigationBar");
                }
                transaction.replace(R.id.fram_content,navigationFragment);
                //Snackbar.make(drawerLayout,"NavigationBar",Snackbar.LENGTH_SHORT).show();
                setNavigationViewChecked(0);
                break;
            case R.id.menu_radio_group:
                if (radioFragment==null){
                    radioFragment=RadioFragment.newInstance("RadioGroup");
                }
                transaction.replace(R.id.fram_content,radioFragment);
                //Snackbar.make(drawerLayout,"RadioGroup",Snackbar.LENGTH_SHORT).show();
                setNavigationViewChecked(1);
                break;
            case R.id.text_view:
                if(textTabFragment==null){
                    textTabFragment=TextTabFragment.newInstance("TextView+LinearLayout");
                }
                transaction.replace(R.id.fram_content,textTabFragment);
                setNavigationViewChecked(2);
                //Snackbar.make(drawerLayout,"Tablayout+ViewPager",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_tab_Layout:
                if(tabLayoutFragment==null){
                    tabLayoutFragment=TabLayoutFragment.newInstance("Tablayout+ViewPager2");

                }
                transaction.replace(R.id.fram_content,tabLayoutFragment);
                setNavigationViewChecked(3);
                //Snackbar.make(drawerLayout,"TabLaout+ViewPager2",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_tab_layout2:
                  if (tabLayoutFragment2==null){
                      tabLayoutFragment2=TabLayoutFragment2.newInstance("tabLayout+ViewPager");
                  }transaction.replace(R.id.fram_content,tabLayoutFragment2);
                setNavigationViewChecked(4);
                break;
        }
        drawerLayout.closeDrawers();
        transaction.commit();
        return true;
    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.settings:
                Snackbar.make(drawerLayout,"settings",Snackbar.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void setNavigationViewChecked(int position) {
        navigationView.getMenu().getItem(position).setChecked(true);
        for(int i=0;i<navigationView.getMenu().size();i++){
            if(i!=position){
                navigationView.getMenu().getItem(i).setChecked(false);
            }
        }
    }
}
