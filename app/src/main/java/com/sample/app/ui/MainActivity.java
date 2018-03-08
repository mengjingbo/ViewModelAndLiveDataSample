package com.sample.app.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sample.app.R;
import com.sample.app.bean.AccountBean;
import com.sample.app.model.AccountModel;

/**
 * 作者：秦川小将
 * 时间：2018/3/7
 * 描述：
 */
public class MainActivity extends AppCompatActivity {

    private AccountModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView mText = findViewById(R.id.textView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_1, new TopFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_2, new BottomFragment()).commit();
        mModel = ViewModelProviders.of(this).get(AccountModel.class);
        findViewById(R.id.main_set_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.setAccount("秦川小将", "182*****008", "http://blog.csdn.net/mjb00000");
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
