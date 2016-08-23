package com.fresh.company.fresh.CommonUtil;

/**
 * Created by CJH on 2016/8/20.
 */
public interface onMoveAndSwipedListener {
    boolean onItemMove(int fromPosition , int toPosition);
    void onItemDismiss(int position);
}
