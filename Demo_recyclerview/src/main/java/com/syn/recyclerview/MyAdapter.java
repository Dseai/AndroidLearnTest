package com.syn.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 孙亚楠 on 2016/12/4.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder > {
    private List<String> data;
    private LayoutInflater layoutInflater;


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
    public MyAdapter(Context context,List<String> data){
        layoutInflater=LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //构造并实例化自己创建的viewHolder  注意  要使用三个参数的  并将最后一个参数设置为false
        //否则在删除某一个item时 会报错提示没有删除parent 就先删除了子view
        MyViewHolder myViewHolder=new MyViewHolder(layoutInflater.inflate(R.layout.myadapter_listitem,viewGroup,false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.textView.setText(data.get(i));
        //如果有回调的话
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
                    int position=myViewHolder.getLayoutPosition();
                    onItemClickListener.OnItemLongClick(myViewHolder.itemView,position);
                    remove(position);
                    return true;
                }
            });

        }

    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
    public void  addData(int position){
        data.add(position,"新数据");
        notifyItemInserted(position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.item_TextView);
        }
    }
}

