package com.fresh.company.fresh.CommonUtil;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.GoodsType;
import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.activity.MainActivity;
import com.fresh.company.fresh.View.fragment.ListAllGoodsFragment;
import com.rey.material.app.Dialog;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.TimeZone;

/**
 * Created by CJH on 2016/8/20.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>
        implements onMoveAndSwipedListener
{
    private final onStartDragListener mStartDragListener;
    private OnSingleItemClickListener clickListener=null;
    private Context context;
    private final Calendar current;
    private ArrayList<GoodsInfo> mItems=new ArrayList<>();
    //private Date now;
    public RecyclerViewAdapter(Context context , onStartDragListener startDragListener,ArrayList<GoodsInfo> mItems){
        this.context=context;
        //初始化数据
        this.mItems=mItems;
        mStartDragListener = startDragListener;
        current=Calendar.getInstance();
    }

    public void setOnItemCilckLisener(OnSingleItemClickListener lisener) {
        this.clickListener = lisener;
    }
    /**在这里反射出我们的item的布局*/
    @Override
    public RecyclerViewAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //利用反射将item的布局加载出来
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,null);
        //new一个我们的ViewHolder，findViewById操作都在ItemViewHolder的构造方法中进行了
        return new ItemViewHolder(view);
    }
    /**在这里为布局中的控件设置数据*/
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final ItemViewHolder t=holder;
        final int pos=position;
        holder.text.setText(mItems.get(position).getmGoodsName());//文字
        GoodsType s=mItems.get(position).getmGoodsType();
        int id=TypeToMipmap.Type2Mipmap(s);
        holder.handle.setImageResource(id);
        holder.hint_text.setText(CalcDate(mItems.get(position).getmGoodsName()
                                    ,mItems.get(position).getmProductionDate()
                                    ,mItems.get(position).getmDurabilityPeriod()
                                    ,mItems.get(position).getmManualPeriod()));

        if (clickListener != null) {
            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v,pos);
                }
            });
            holder.handle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v,pos);
                }
            });
            holder.hint_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(view,pos);
                }
            });
        }
        //handle是我们拖动item时候要用的，目前先空着
//        holder.handle.setOnTouchListener(new View.OnTouchListener() {//图片
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //如果按下
//                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_BUTTON_PRESS){
//                    //回调RecyclerListFragment中的startDrag方法
//                    //让mItemTouchHelper执行拖拽操作
//                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT);
//                   // mStartDragListener.startDrag(t);
//                }
//                return false;
//            }
//        });
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

    public void update(int position) {
        //mItems.remove(position);
        notifyItemChanged(position);
    }

    public void add(GoodsInfo goodsInfo, int position) {
        mItems.add(position,goodsInfo);
        notifyItemInserted(position);
    }
    public void remove(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onItemDismiss(int position) {
        String t=mItems.get(position).getmGoodsName();
        final int index=position;
        TextView text = new TextView(context);
        text.setText("Do you wan to delete :"+t+"?");
        final Dialog dialog=new Dialog(context);
        dialog.contentMargin(20,10,0,0);
        dialog.title("Delete").contentView(text).positiveAction("OK")
                .negativeAction("CANCEL").positiveActionClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //dialog.show();
                        if(context instanceof MainActivity){
                            ((MainActivity) context).NotifyToFragement(mItems.get(index));
                        }
                        //删除mItems数据
                        mItems.remove(index);
                        //删除RecyclerView列表对应item
                        notifyItemRemoved(index);
                        dialog.hide();
                    }
                }
        ).negativeActionClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notifyItemChanged(index);
                        dialog.hide();
                       // flag=true;
                    }
                }
        ).show();
    }

    private String CalcDate(final String goods_name,String date,String DurabilityPeriod,String ManualPeriod){
        String [] d=date.split("-");
        int year=Integer.parseInt(d[0]);
        int month=Integer.parseInt(d[1]);
        int day=Integer.parseInt(d[2]);
        String dateStr="";
        //java月份是从0-11,月份设置时要减1.
        //Calendar calendar=new GregorianCalendar(year,month-1,day,0,0,0);
        final Calendar c=CalcPeriod(year,month,day,DurabilityPeriod,ManualPeriod);
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DATE);
        if (c!=null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int xx=c.get(Calendar.DATE);
                    int yy=current.get(Calendar.DATE);
                    if (c.get(Calendar.DATE)-current.get(Calendar.DATE)==1) {
                        ((MainActivity) context).NotifyToFragement(goods_name);
                    }
                }
            },1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateStr = sdf.format(c.getTime())+"过期";
        }
//        if (current.get(Calendar.YEAR)-year>0){
//            return String.valueOf(current.get(Calendar.YEAR)-year)+"年前";
//        }else if (current.get(Calendar.MONTH)+1-month>0){
//            return String.valueOf(current.get(Calendar.MONTH)+1-month)+"月前";
//        }else  if (current.get(Calendar.DAY_OF_MONTH)-day>0){
//            return String.valueOf(current.get(Calendar.DAY_OF_MONTH)-day)+"日前";
//        }
        return dateStr;
        //now = new Date();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式

        //String nowStr=dateFormat.format(now);
        //Date d1=dateFormat.parse(date,new ParsePosition(0));
        //Date d2 = dateFormat.parse(nowStr,new ParsePosition(0));
//        long diff = d2.getTime()-d1.getTime();
//        long days = diff/((1000 * 60 * 60 * 24));
//        if (days<30)
//            return String.valueOf(days)+"天前";
//        else if (days>30 && days<365)
//            return String.valueOf(days/30)+"月前";
//        else if(days>365)
//            return String.valueOf(days/365)+"年前";

//        return "";
    }

    private Calendar CalcPeriod(int year,int month,int day,String DurabilityPeriod, String ManualPeriod) {
        Calendar c=null;
        int dur;
        String date;

        if (!ManualPeriod.trim().isEmpty()) {
            date=ManualPeriod;
        }else{
            date=DurabilityPeriod;
        }
        int length = date.length();
        if (date.contains("个")) {
            date = date.replaceAll("个", "");
        }
        if (date.contains("天")) {
            date=date.substring(0,length-1);
            dur = Integer.parseInt(date);
            c=new GregorianCalendar(year,month-1,day,0,0,0);
            c.add(Calendar.DATE,dur);
        } else {
            if (date.contains("月")) {
                dur = Integer.parseInt(date.substring(0, length - 1));
                c=new GregorianCalendar(year,month-1,day,0,0,0);
                c.add(Calendar.MONTH,dur);
            }
        }
        return c;
    }

    /**相当于ListView中的ViewHolder，即每一条的Item*/
    public static class ItemViewHolder extends RecyclerView.ViewHolder
            implements SimpleItemTouchHelperCallback.onStateChangedListener
    {
        private TextView text;
        private ImageView handle;
        private TextView hint_text;
        //private Drawable drawable;
        public ItemViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            handle = (ImageView) itemView.findViewById(R.id.handle);
            hint_text=(TextView) itemView.findViewById(R.id.hint_text);
            //drawable=@color/bigColorContent
        }

        @Override
        public void onItemSelected() {
            //drawable=itemView.getBackground();
            //设置item的背景颜色为浅灰色
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            //if (RecyclerViewAdapter.flag)
               // itemView.setBackground(drawable);
             //恢复item的背景颜色
            itemView.setBackgroundResource(R.color.bigColorContent);
        }
    }

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }
}
