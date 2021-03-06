package com.fresh.company.fresh.View.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.fresh.company.fresh.CommonUtil.OnSwithPageListener;
import com.fresh.company.fresh.Component.ExpandableLayout;
import com.fresh.company.fresh.Model.DietPlanInfoFactory;
import com.fresh.company.fresh.Presenter.DietPlanPresenter;
import com.fresh.company.fresh.Presenter.IDietPlanPresenter;
import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.IDietPlanView;
import com.fresh.company.fresh.View.activity.MainActivity;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DietPlanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DietPlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DietPlanFragment extends Fragment implements ObservableScrollViewCallbacks,IDietPlanView,OnSwithPageListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ObservableScrollView mObservableScrollView;
    private ExpandableLayout mExpandableLayout;
    private OnFragmentInteractionListener mListener;
    private EditText morningPlan,afaternoonPlan,eveningPlan;
    private IDietPlanPresenter mIDietPlanPresenter;
    public DietPlanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DietPlanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DietPlanFragment newInstance(String param1, String param2) {
        DietPlanFragment fragment = new DietPlanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_diet_plan, container, false);
        mIDietPlanPresenter=new DietPlanPresenter(this,new DietPlanInfoFactory(this.getActivity()));
        mExpandableLayout=(ExpandableLayout)v.findViewById(R.id.expandableLayout);
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("早餐");
        arrayList.add("午餐");
        arrayList.add("晚餐");
        mExpandableLayout.setHeader(arrayList);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height=dm.heightPixels;
        int statusBarHeight=((MainActivity)getActivity()).getStatusBarHeight();
        height=height-statusBarHeight-102;//102=56[actionBar] + 46[radioGroup]
        mExpandableLayout.setHeaderHeight(height/6);
        mExpandableLayout.setContentHeight(height/6*5);
        FrameLayout m=(FrameLayout)mExpandableLayout.findViewById(R.id.morning_content);
        FrameLayout a=(FrameLayout)mExpandableLayout.findViewById(R.id.afternoon_content);
        FrameLayout e=(FrameLayout)mExpandableLayout.findViewById(R.id.evening_content);
        morningPlan=(EditText) m.findViewById(R.id.content);
        afaternoonPlan=(EditText) a.findViewById(R.id.content);
        eveningPlan=(EditText) e.findViewById(R.id.content);
        return v;
    }

    @Override
    public void onStart() {
        final SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        final String date=sf.format(new Date());
        morningPlan.setText(mIDietPlanPresenter.GetMorningContent(date));
        afaternoonPlan.setText(mIDietPlanPresenter.GetAfternoonContent(date));
        eveningPlan.setText(mIDietPlanPresenter.GetEveningContent(date));
        super.onStart();
    }

    @Override
    public void onStop() {
        mIDietPlanPresenter.SetDietPlanInfoToFile();
        super.onStop();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onDestroy() {
        mIDietPlanPresenter.Dispose();
        mIDietPlanPresenter.DeleteDietPlanInfo();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    public void ShowDietPlanInfoStatus() {

    }

    @Override
    public void HideDietPlanInfoStatus() {

    }

    @Override
    public void ShowDietPlanInfo(String content) {

    }

    @Override
    public String GetMorningContent() {
        return morningPlan.getText().toString();
    }

    @Override
    public String GetAfternoonContent() {
        return afaternoonPlan.getText().toString();
    }

    @Override
    public String GetEveningContent() {
        return eveningPlan.getText().toString();
    }

    @Override
    public void onSwithPage() {
        mIDietPlanPresenter.SetDietPlanInfoToFile();
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
