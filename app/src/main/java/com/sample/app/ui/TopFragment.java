package com.sample.app.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sample.app.R;
import com.sample.app.bean.AccountBean;
import com.sample.app.model.AccountModel;

/**
 * 作者：蒙景博
 * 时间：2018/3/7
 * 描述：
 */
public class TopFragment extends Fragment {

    private AccountModel mModel;
    private TextView mText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mText = view.findViewById(R.id.fragment_text_view);
        mModel = ViewModelProviders.of(getActivity()).get(AccountModel.class);
        view.findViewById(R.id.fragment_set_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.getAccount().postValue(new AccountBean("秦川小将", "182*****008", "这段数据是从Fragment中Post出来的"));
            }
        });
        mModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                mText.setText(AccountModel.getFormatContent(accountBean.getName(), accountBean.getPhone(), accountBean.getBlog()));
            }
        });
    }
}
