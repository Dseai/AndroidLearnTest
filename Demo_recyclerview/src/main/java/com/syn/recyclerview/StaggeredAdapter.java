package com.syn.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙亚楠 on 2016/12/4.
 * 用RecyclerView打造瀑布流效果
 */

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {
    private List<String> data;
    private LayoutInflater layoutInflater;
    private List<Integer> tall; //设置高度


//构造方法
    public StaggeredAdapter(Context context,List<String> data){
        layoutInflater=LayoutInflater.from(context);
        this.data=data;
        tall=new ArrayList<Integer>();
        for (int i=0;i<data.size();i++){
            //得到随机item的高度
            tall.add((int)(100+Math.random()*300));
        }
    }
    //自定义一个接口  完成回掉
    public interface OnItemClickListener{
        //定义接口中的方法  点击和长点击
        void OnItemClick(View view,int position);
        void OnItemLongClick(View view,int position);

    }
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder=new MyViewHolder(layoutInflater.inflate(R.layout.stagger_item,viewGroup,false));
     return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
      //得到item的layoutParams的布局参数
       ViewGroup.LayoutParams layoutParams=myViewHolder.textView.getLayoutParams();
        //把随机的高度赋予item布局
        layoutParams.height=tall.get(i);
       //把params设置给item布局
        myViewHolder.textView.setLayoutParams(layoutParams);
        //为控件绑定数据
        myViewHolder.textView.setText(data.get(i));
        //如果设置了监听器那么他就不为空，然后回调相应的方法
        if(onItemClickListener!=null){
            myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=myViewHolder.getLayoutPosition();
                    //用到了父类Recycler.ViewHolder的itemView
                    onItemClickListener.OnItemClick(myViewHolder.itemView,position);
                }

            });
            myViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //得到当前点击的item的位置position
                    int position=myViewHolder.getLayoutPosition();
                    //把事件交给我们实现的接口那里处理
                    onItemClickListener.OnItemLongClick(myViewHolder.itemView,position);
                    remove(position);
                    return true;
                }
            });

        }


    }

    public void addData(int position)
    {
        data.add(position, "Insert One");
        tall.add( (int) (100 + Math.random() * 300));
        notifyItemInserted(position);
    }

    public void remove(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            //不要忘记初始化
           textView=(TextView)itemView.findViewById(R.id.id_num);
        }
    }
}
