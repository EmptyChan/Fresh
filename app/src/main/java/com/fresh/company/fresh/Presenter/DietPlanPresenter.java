package com.fresh.company.fresh.Presenter;

import com.fresh.company.fresh.Model.DietPlanInfo;
import com.fresh.company.fresh.Model.IDietPlanInfoFactory;
import com.fresh.company.fresh.View.IDietPlanView;
import com.fresh.company.fresh.View.fragment.DietPlanFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by CJH on 2016/8/7.
 */
public class DietPlanPresenter implements IDietPlanPresenter{
    private IDietPlanView mIDietPlanView;
    private String allContent="";
    private IDietPlanInfoFactory mIDietPlanInfoFactory;
    public DietPlanPresenter(IDietPlanView IDietPlanView,IDietPlanInfoFactory iDietPlanInfoFactory) {
        mIDietPlanView = IDietPlanView;
        mIDietPlanInfoFactory=iDietPlanInfoFactory;
    }

    @Override
    public void WriteToFile() {
        if (mIDietPlanView instanceof DietPlanFragment) {
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
            String f="dp"+sf.format(new Date())+".dat";
            StringBuilder content=new StringBuilder();
            content.append(mIDietPlanView.GetMorningContent()+"@");
            content.append(mIDietPlanView.GetAfternoonContent()+"@");
            content.append(mIDietPlanView.GetEveningContent());
            try {
                FileOutputStream fout =((DietPlanFragment)mIDietPlanView).getActivity().openFileOutput(f,MODE_PRIVATE);
                byte [] bytes =content.toString().getBytes();
                fout.write(bytes);
//                ((DietPlanFragment)mIDietPlanView).getActivity().getFilesDir()
                fout.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    private String ReadFromFile(){
        if (allContent.equals("")) {
            synchronized(DietPlanPresenter.class) {
                if (allContent.equals("")) {
                    if (mIDietPlanView instanceof DietPlanFragment) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                        String f = "dp" + sf.format(new Date()) + ".dat";
                        try {
                            String c;
                            FileInputStream fin = ((DietPlanFragment) mIDietPlanView).getActivity().openFileInput(f);
                            int length = fin.available();
                            byte[] buffer = new byte[length];
                            fin.read(buffer);
                            fin.close();
                            c = new String(buffer);
                            return c;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return allContent;
    }

    @Override
    public String GetMorningContent(String key) {
        DietPlanInfo t=mIDietPlanInfoFactory.QueryDietPlanDiet(key);
        if (t!=null)
            return t.getMorningPlan();
        else
            return "";
    }

    @Override
    public String GetAfternoonContent(String key) {
        DietPlanInfo t=mIDietPlanInfoFactory.QueryDietPlanDiet(key);
        if (t!=null)
            return t.getAfternoonPlan();
        else
            return "";
    }

    @Override
    public String GetEveningContent(String key) {
        DietPlanInfo t=mIDietPlanInfoFactory.QueryDietPlanDiet(key);
        if (t!=null)
            return t.getEveningPlan();
        else
            return "";
    }

    @Override
    public void SetDietPlanInfoToFile() {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        DietPlanInfo t=new DietPlanInfo();
        String date=sf.format(new Date());
        t.setDate(date);
        t.setMorningPlan(mIDietPlanView.GetMorningContent());
        t.setAfternoonPlan(mIDietPlanView.GetAfternoonContent());
        t.setEveningPlan(mIDietPlanView.GetEveningContent());
        if (mIDietPlanInfoFactory.QueryDietPlanDiet(date)!=null){
            mIDietPlanInfoFactory.UpdateDietPlan(t);
        }else
            mIDietPlanInfoFactory.AddDietPlan(t);
    }

    @Override
    public void DeleteDietPlanInfo() {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sf.format(new Date());
        DietPlanInfo temp=mIDietPlanInfoFactory.QueryDietPlanDiet(date);
        mIDietPlanInfoFactory.DeleteAllDietPlan();
        mIDietPlanInfoFactory.AddDietPlan(temp);
    }

    public boolean fileIsExists(String strFile)
    {
        try
        {
            File f=new File(strFile);
            if(!f.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    @Override
    public void Dispose() {
        mIDietPlanInfoFactory.CloseFactory();
    }
}
