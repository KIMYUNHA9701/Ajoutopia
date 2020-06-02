package com.example.crux16_ajou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class mypageMainActivity extends AppCompatActivity {


    private Button home;
    private Button qna;
    private Button study;
    private Button mypage;
    private Button tradecoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_main);

        home = (Button) findViewById(R.id.home);
        qna = (Button) findViewById(R.id.qna);
        study = (Button) findViewById(R.id.study);
        mypage = (Button) findViewById(R.id.mypage);
        tradecoupon=(Button)findViewById(R.id.couponTrade);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypageMainActivity.this, FirstpageActivity.class);
                startActivity(intent);
            }
        });

        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypageMainActivity.this, qnaMainActivity.class);
                startActivity(intent);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypageMainActivity.this, studyMainActivity.class);
                startActivity(intent);
            }
        });

        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypageMainActivity.this, mypageMainActivity.class);
                startActivity(intent);
            }
        });

        tradecoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypageMainActivity.this, tradeCouponActivity.class);
                startActivity(intent);
            }
        });

    }
}