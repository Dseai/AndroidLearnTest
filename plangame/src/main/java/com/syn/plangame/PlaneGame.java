package com.syn.plangame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class PlaneGame extends AppCompatActivity {
    private int speed=12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //创建planeView组件
        final PlaneView planeView=new PlaneView(this);
        setContentView(planeView);
        //planeView.setBackgroundResource(R.drawable.black);
        //获得窗口管理器
        WindowManager windowManager=getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        //获得屏幕的宽和高
        int screenWidth=display.getWidth();
        int  screenHeight=display.getHeight();

        //设置飞机的起始位置
        planeView.currentX=screenWidth/2;
        planeView.currentY=screenHeight-40;
        //为draw组件键盘事件监听器
        planeView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()){
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        planeView.currentY+=speed;
                        break;
                    case KeyEvent.KEYCODE_DPAD_UP:
                        planeView.currentY-=speed;
                        break;
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        planeView.currentX-=speed;
                        break;
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        planeView.currentX+=speed;
                        break;

                }
                planeView.invalidate();
                return true;
            }
        });
    }
}
