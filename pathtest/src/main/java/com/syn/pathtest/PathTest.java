package com.syn.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.security.PublicKey;

public class PathTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
    class MyView extends View{
        float phase;
        PathEffect[] effects=new PathEffect[7];
        int[] colors;
        private Paint paint;
        Path path;
        public MyView(Context context){
            super(context);
            paint=new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
            path=new Path();
            path.moveTo(0,0);
            for(int i=1;i<15;i++){
                //生成15个点，随机产生它们的Y坐标，并将他们连成一条线Path
                path.lineTo(i*20,(float)Math.random()*60);
            }
            //初始化7个颜色
            colors=new int[]{ Color.BLUE,Color.BLACK,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.RED,Color.YELLOW};

        }
        @Override
        protected void onDraw(Canvas canvas){
            //背景填充为白色
            canvas.drawColor(Color.WHITE);
            //下面初始化7中路径效果
            //不适用路径效果
            effects[0]=null;
            //使用CornerPathEffect路径效果
            effects[1]=new CornerPathEffect(10);
            //使用DisCretePathEffect
            effects[2]=new DiscretePathEffect(3.0f,5.0f);
            //初始化DashPathEffect
            effects[3]=new DashPathEffect(new float[]{20,10,5,10},phase);
            //初始化PathDashPathEffect
            Path p=new Path();
            p.addRect(0,0,8,8,Path.Direction.CCW);
            effects[4]=new PathDashPathEffect(p,12,phase,PathDashPathEffect.Style.ROTATE);
            //初始化PathDashPathEffect
            effects[5]=new ComposePathEffect(effects[2],effects[4]);
            effects[6]=new SumPathEffect(effects[4],effects[3]);
            //将画布移动到8，8处开始绘制
            canvas.translate(8,8);
            //依次使用7中不同的效果，7种不同的颜色来绘制路径
            for (int i=0;i<effects.length;i++){
                paint.setPathEffect(effects[i]);
                paint.setColor(colors[i]);
                canvas.drawPath(path,paint);
                canvas.translate(0,60);
            }
            phase+=1;
            invalidate();


        }
    }
}
