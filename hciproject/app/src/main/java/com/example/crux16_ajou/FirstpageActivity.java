package com.example.crux16_ajou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstpageActivity extends AppCompatActivity {

    private Button subject;
    //private Button study;
    //private Button posting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        subject = (Button) findViewById(R.id.subject1);

        //study = (Button) findViewById(R.id.study);
        //posting = (Button) findViewById(R.id.posting);

        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstpageActivity.this, qnaMainActivity.class);
                startActivity(intent);
            }
        });

    }
}
