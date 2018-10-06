package com.fresh.company.fresh.View.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dtr.zxing.activity.CaptureActivity;
import com.fresh.company.fresh.CommonUtil.OnSingleItemClickListener;
import com.fresh.company.fresh.CommonUtil.RecyclerViewAdapter;
import com.fresh.company.fresh.CommonUtil.SimpleItemTouchHelperCallback;
import com.fresh.company.fresh.CommonUtil.onStartDragListener;
import com.fresh.company.fresh.Component.AlarmReceiver;
import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.GoodsInfoFactory;
import com.fresh.company.fresh.Presenter.AllGoodsPresenter;
import com.fresh.company.fresh.Presenter.IAllGoodsPresenter;
import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.IAllGoodsView;
import com.fresh.company.fresh.View.activity.BaseActivity;
import com.fresh.company.fresh.View.activity.GoodsActivity;
import com.fresh.company.fresh.View.activity.ScanActivity;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListAllGoodsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListAllGoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListAllGoodsFragment extends Fragment implements onStartDragListener,IAllGoodsView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String HELLO="cjh";
    public static final int SCAN_CODE = 1;
    public static final String ADD_GOODS="add_goods";
    public static final String UPDATE_GOODS="update_goods";
    public static final String SEND_GOODS="send_goods";
    //public static final String SEND_FALG="send_falg";
    public static final String SEND_NOTIFY="com.fresh.sendNotify";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mName;
    private TextView mTextView;
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton mFloatingActionButton;
    private ItemTouchHelper mItemTouchHelper;
    private RecyclerViewAdapter adapter;
    private RecyclerView mRecyclerView;
    private ArrayList<GoodsInfo> mGoodsInfos;
    private IAllGoodsPresenter mIAllGoodsPresenter;
    private AlarmReceiver mAlarmReceiver;

    public IAllGoodsPresenter getIAllGoodsPresenter(){
        return mIAllGoodsPresenter;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListAllGoodsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListAllGoodsFragment newInstance(String param1, String param2,String name) {
        ListAllGoodsFragment fragment = new ListAllGoodsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(HELLO,name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mName=getArguments().getString(HELLO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mIAllGoodsPresenter=new AllGoodsPresenter(this,new GoodsInfoFactory(this.getActivity()));
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_list_all_goods, container, false);
        mTextView=(TextView)v.findViewById(R.id.tv);
        mTextView.setText("");

        //get all data
        mGoodsInfos=mIAllGoodsPresenter.GetAllGoodsInfo();
        //list
        adapter = new RecyclerViewAdapter(getActivity(),this,mGoodsInfos);
        adapter.setOnItemCilckLisener(new OnSingleItemClickListener() {
            @Override
            public void onClick(View v, int pos) {
                Intent i=new Intent(ListAllGoodsFragment.this.getActivity(),GoodsActivity.class);
                Bundle b=new Bundle();
                b.putParcelable(SEND_GOODS,mGoodsInfos.get(pos));
                i.putExtras(b);
                startActivity(i);
            }
        });
        //参数view即为我们在onCreateView中return的view
        mRecyclerView = (RecyclerView)v.findViewById(R.id.goodsRv);
        //固定recyclerview大小,提升性能
        mRecyclerView.setHasFixedSize(true);
        //设置adapter
        mRecyclerView.setAdapter(adapter);
        //设置布局类型为LinearLayoutManager，相当于ListView的样式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //关联ItemTouchHelper和RecyclerView
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);


        //fab
        mFloatingActionButton=(FloatingActionButton)v.findViewById(R.id.fab);
        mFloatingActionButton.attachToRecyclerView(mRecyclerView);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //扫描操作
                Intent intent= new Intent(getActivity(),CaptureActivity.class);
                //startActivityForResult(intent, SCAN_CODE);
                startActivity(intent);
            }
        });
        mFloatingActionButton.attachToRecyclerView(mRecyclerView);

        mAlarmReceiver=new AlarmReceiver(getActivity());
        mAlarmReceiver.registerAction(SEND_NOTIFY);
        return v;
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCAN_CODE:
//                if (resultCode == CaptureActivity.RESULT_OK) {
//                    Bundle b=data.getExtras(); //data为B中回传的Intent
//                    String str=b.getString(CaptureActivity.RESULT);//str即为回传的值
//                    mTextView.setText(str);
//                } else if (resultCode == RESULT_CANCELED) {
//                    mTextView.setText("没有扫描出结果");
//                }
                break;
            default:
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroy() {
        mIAllGoodsPresenter.Dispose();
        getActivity().unregisterReceiver(mAlarmReceiver);
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        //mItemTouchHelper.startDrag(viewHolder);
        mGoodsInfos.get(viewHolder.getAdapterPosition());
//        Intent i=new Intent(this.getActivity(), GoodsActivity.class);
//        Bundle bundle=new Bundle();
//        bundle.putBundle();
//        startActivity(i);
    }

    @Override
    public void GoodsAdd(GoodsInfo goodsInfo) {
//        mGoodsInfos.add(0,goodsInfo);
//        adapter.notifyItemInserted(0);
        adapter.add(goodsInfo,0);
        //如果在第一项添加模拟数据需要调用 scrollToPosition（0）把列表移动到顶端（可选）
        mRecyclerView.scrollToPosition(0);
        //adapter.notifyItemInserted(mGoodsInfos.size()-1);
    }

    @Override
    public void GoodsChange(int i) {
        adapter.update(i);
        //adapter.notifyItemChanged(c);
        //adapter.notifyItemChanged(c);
    }

    @Override
    public void NotifyToast(String text) {
        Toast.makeText(this.getActivity(),text,Toast.LENGTH_SHORT);
    }

    @Override
    public ArrayList<GoodsInfo> GetAllGoodsInfo() {
        return mGoodsInfos;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
