package com.fresh.company.fresh.Presenter;

import com.fresh.company.fresh.CommonUtil.DBManager;
import com.fresh.company.fresh.Model.UserInfo;
import com.fresh.company.fresh.View.ILoginView;
import com.fresh.company.fresh.View.activity.BaseActivity;


/**
 * Created by CJH on 2016/7/31.
 */
public class LoginPresenter implements ILoginPresenter {
    private ILoginView mLoginView;
    private UserInfo mUserInfo;
    private DBManager mDBManager;
   // private ExecutorService executor;
    //private CompletionService<Boolean> completionService;
    public LoginPresenter(ILoginView mLoginView,DBManager dbManager) {
        this.mLoginView = mLoginView;
        mDBManager=dbManager;
        //mDBManager.addUserInfo(new UserInfo("CJH","1994"));
        //mDBManager.updateUserInfoPassword("1994");
       // executor = Executors.newCachedThreadPool();
       // completionService = new ExecutorCompletionService<Boolean>(
//                executor);
    }

    @Override
    public void loginSuccess() {
        mLoginView.ShowLoginSuccess();
        mLoginView.HideLoginStatuss();
       // executor.shutdown();
        mLoginView.toNextActivity(mUserInfo);
    }

    @Override
    public void loginFailed() {
        mLoginView.ShowLoginFailed();
        mLoginView.HideLoginStatuss();
        mLoginView.clearPsd();
    }

    @Override
    public boolean CheckUserInfo() {
        //change in the future.
        mUserInfo=new UserInfo(mLoginView.GetUsr(),mLoginView.GetPsd());
        String psd=mDBManager.queryUserInfo(mUserInfo.getmUserName());
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //模拟登录成功
        //if (mUserInfo.getmUserName().equals("CJH") && mUserInfo.getmPass().equals("1994"))
        if(psd.equals(mUserInfo.getmPass()))
        {
            return true;
        }
        return false;
//        completionService.submit(
//                new Callable<Boolean>()
//                {
//                    @Override
//                    public Boolean call() throws Exception {
//
//                    }
//                }
//        );
//        try {
//            return completionService.take().get().booleanValue();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        if (mUserInfo.getmUserName().equals("CJH") && mUserInfo.getmPass().equals("1994"))
//        {
//            return true;
//        }
//        return false;
    }

    @Override
    public void TransportDataToView() {

    }

    @Override
    public void UpdateDataToView() {

    }
}
