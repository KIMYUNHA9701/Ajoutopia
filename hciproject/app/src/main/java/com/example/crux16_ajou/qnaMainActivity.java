package com.example.crux16_ajou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class qnaMainActivity extends AppCompatActivity {
    private Button home;
    private Button qna;
    private Button study;
    private Button mypage;
    private Button lecture;
    private Button write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_task);

        home = (Button) findViewById(R.id.home);
        qna = (Button) findViewById(R.id.qna);
        study = (Button) findViewById(R.id.study);
        mypage = (Button) findViewById(R.id.mypage);
        lecture=(Button)findViewById(R.id.lecture);
        write=(Button)findViewById(R.id.writetext);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMainActivity.this, FirstpageActivity.class);
                startActivity(intent);
            }
        });

        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMainActivity.this, qnaMainActivity.class);
                startActivity(intent);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMainActivity.this, studyMainActivity.class);
                startActivity(intent);
            }
        });

        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMainActivity.this, mypageMainActivity.class);
                startActivity(intent);
            }
        });

        lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMainActivity.this, qnaMain2Activity.class);
                startActivity(intent);
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMainActivity.this, writeQnaTask.class);
                startActivity(intent);
            }
        });
    }


}