package com.fresh.company.fresh.View.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.fresh.company.fresh.Model.UserInfo;
import com.fresh.company.fresh.Presenter.ILoginPresenter;
import com.fresh.company.fresh.Presenter.LoginPresenter;
import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.ILoginView;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.rey.material.widget.CheckBox;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private Button mSignInBtn;
    private Button mSignUpBtn;
    private EditText mUsrEt;
    private EditText mPsdEt;
    private CheckBox mPsdCb;
    private CircularProgressView mProgressView;
    private static final String ERROR_TEXT="Sign In Failed";
    private static final String SUCCESS_TEXT="Sign In Success";
    public static final String USR="usr";
    public static final String PSD="psd";
    private static int SUCCESS_FALG=0;
    private static int FAILED_FALG=1;
    private ILoginPresenter mLoginPresenter;
    private Handler mHandler;
    public LoginActivity() {
        mLoginPresenter=new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_login);
        initViews();
    }
    private void SetEditTextStatus(boolean b){
        mUsrEt.setEnabled(b);
        mPsdEt.setEnabled(b);
    }

    private void initViews() {
        Bundle bundle = this.getIntent().getExtras();
        String usr= bundle.getString(USR);
        mUsrEt=(EditText) findViewById(R.id.usrEt);
        mUsrEt.setText(usr);
        mPsdEt=(EditText) findViewById(R.id.psdEt);
        mSignInBtn=(Button) findViewById(R.id.signInBtn);
        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowLoginStatuss();
                SetEditTextStatus(false);

                Observable.create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        Boolean b=mLoginPresenter.CheckUserInfo();
                        subscriber.onNext(b);
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean){
                            mLoginPresenter.loginSuccess();
                        }else{
                            mLoginPresenter.loginFailed();
                        }
                        SetEditTextStatus(true);
                    }
                });
                //Message message=new Message();
                //异步Handler处理
//                mHandler=new Handler(){
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        switch (msg.what){
//                            case 0:
//                                mLoginPresenter.loginSuccess();
//                                break;
//                            case 1:
//                                mLoginPresenter.loginFailed();
//                                break;
//                        }
//                    }
//                };
//                new Thread(){
//                    @Override
//                    public void run() {
//                        Message message = new Message();
//                        if (mLoginPresenter.CheckUserInfo()){
//                            message.what = SUCCESS_FALG;
//                        }else{
//                            message.what = FAILED_FALG;
//                        }
//                        mHandler.sendMessage(message);
//                        }
//                }.start();
            }
        });
        mSignUpBtn=(Button) findViewById(R.id.signUpBtn);
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mProgressView = (CircularProgressView) findViewById(R.id.progress_view);
        mPsdCb=(CheckBox)findViewById(R.id.psdCb);
        mPsdCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true)
                {
                    mPsdEt.setText("1994");
                }
            }
        });
    }

    private void update()
    {
        if (mLoginPresenter.CheckUserInfo()) {
            mLoginPresenter.loginSuccess();
        } else {
            mLoginPresenter.loginFailed();
        }
    }
    @Override
    public void ShowLoginStatuss() {
        mProgressView.setVisibility(View.VISIBLE);
        mProgressView.startAnimation();
    }

    @Override
    public void ShowLoginSuccess() {
        //Snackbar.make(getCurrentFocus(), SUCCESS_TEXT, Snackbar.LENGTH_LONG)
         //       .setAction("Action", null).show();
        Toast.makeText(this,SUCCESS_TEXT, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowLoginFailed() {
        //Snackbar.make(getCurrentFocus(), ERROR_TEXT, Snackbar.LENGTH_LONG)
         //       .setAction("Action", null).show();
        Toast.makeText(this,ERROR_TEXT, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String GetUsr() {
        return mUsrEt.getText().toString();
    }

    @Override
    public String GetPsd() {
        return mPsdEt.getText().toString();
    }

    @Override
    public void clearUsr() {
        mUsrEt.setText("");
    }

    @Override
    public void clearPsd() {
        mPsdEt.setText("");
    }

    @Override
    public void HideLoginStatuss() {
        mProgressView.stopAnimation();
        mProgressView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toNextActivity(UserInfo m) {
        Bundle bundle = new Bundle();
        //保存输入的信息
        bundle.putString(USR, m.getmUserName());
        bundle.putString(PSD, m.getmPass());
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtras(bundle);
        //Observable.create(new )
        startActivity(intent);
        LoginActivity.this.finish();
    }
}
