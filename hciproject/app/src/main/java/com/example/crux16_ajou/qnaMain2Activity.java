package com.example.crux16_ajou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class qnaMain2Activity extends AppCompatActivity {

    private Button home;
    private Button qna;
    private Button study;
    private Button mypage;
    private Button task;
    private Button write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_lecture);

        home = (Button) findViewById(R.id.home);
        qna = (Button) findViewById(R.id.qna);
        study = (Button) findViewById(R.id.study);
        mypage = (Button) findViewById(R.id.mypage);
        task = (Button) findViewById(R.id.task);
        write=(Button)findViewById(R.id.writetext);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMain2Activity.this, FirstpageActivity.class);
                startActivity(intent);
            }
        });

        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMain2Activity.this, qnaMain2Activity.class);
                startActivity(intent);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMain2Activity.this, studyMainActivity.class);
                startActivity(intent);
            }
        });

        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMain2Activity.this, mypageMainActivity.class);
                startActivity(intent);
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMain2Activity.this, qnaMainActivity.class);
                startActivity(intent);
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qnaMain2Activity.this, writeQnaLecture.class);
                startActivity(intent);
            }
        });



    }
}