package com.example.crux16_ajou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class writeQnaLecture extends AppCompatActivity {

    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_qna_lecture);

        finish=(Button)findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(writeQnaLecture.this, qnaMain2Activity.class);
                startActivity(intent);
            }
        });
    }
}