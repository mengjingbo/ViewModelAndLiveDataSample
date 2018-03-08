package com.sample.app.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sample.app.bean.AccountBean;

/**
 * 作者：秦川小将
 * 时间：2018/3/7
 * 描述：
 */
public class AccountModel extends AndroidViewModel{

    private MutableLiveData<AccountBean> mAccount = new MutableLiveData<>();

    public AccountModel(@NonNull Application application) {
        super(application);
    }

    public void setAccount(String name, String phone, String blog){
        mAccount.setValue(new AccountBean(name, phone, blog));
    }

    public MutableLiveData<AccountBean> getAccount(){
        return mAccount;
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
    @Override
    protected void onCleared() {
        Log.e("AccountModel", "==========onCleared()==========");
        super.onCleared();
    }

    public static String getFormatContent(String name, String phone, String blog) {
        StringBuilder mBuilder = new StringBuilder();
        mBuilder.append("昵称:");
        mBuilder.append(name);
        mBuilder.append("\n手机:");
        mBuilder.append(phone);
        mBuilder.append("\n博客:");
        mBuilder.append(blog);
        return mBuilder.toString();
    }
}
