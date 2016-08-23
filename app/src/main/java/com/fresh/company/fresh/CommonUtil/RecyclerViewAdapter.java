package com.fresh.company.fresh.CommonUtil;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fresh.company.fresh.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by CJH on 2016/8/20.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>
        implements onMoveAndSwipedListener
{
    private final onStartDragListener mStartDragListener;
    private ArrayList<String> mItems=new ArrayList<>();
    public RecyclerViewAdapter(Context context , onStartDragListener startDragListener){
        //初始化数据
        mItems.addAll(Arrays.asList(context.getResources().getStringArray(R.array.dummy_items)));
        mStartDragListener = startDragListener;
    }
    /**在这里反射出我们的item的布局*/
    @Override
    public RecyclerViewAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //利用反射将item的布局加载出来
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
        //new一个我们的ViewHolder，findViewById操作都在ItemViewHolder的构造方法中进行了
        return new ItemViewHolder(view);
    }
    /**在这里为布局中的控件设置数据*/
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final ItemViewHolder t=holder;
        holder.text.setText(mItems.get(position));//文字
        //handle是我们拖动item时候要用的，目前先空着
        holder.handle.setOnTouchListener(new View.OnTouchListener() {//图片
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //如果按下
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    //回调RecyclerListFragment中的startDrag方法
                    //让mItemTouchHelper执行拖拽操作
                    mStartDragListener.startDrag(t);
                }
                return false;
            }
        });
    }
    /**返回数据个数*/
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(mItems,fromPosition,toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        //删除mItems数据
        mItems.remove(position);
        //删除RecyclerView列表对应item
        notifyItemRemoved(position);
    }

    /**相当于ListView中的ViewHolder，即每一条的Item*/
    public static class ItemViewHolder extends RecyclerView.ViewHolder
            implements SimpleItemTouchHelperCallback.onStateChangedListener
    {
        private TextView text;
        private ImageView handle;
        public ItemViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            handle = (ImageView) itemView.findViewById(R.id.handle);
        }

        @Override
        public void onItemSelected() {
            //设置item的背景颜色为浅灰色
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            //恢复item的背景颜色
            itemView.setBackgroundColor(0);
        }
    }
}
