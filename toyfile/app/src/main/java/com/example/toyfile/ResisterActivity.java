package com.example.toyfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ResisterActivity extends AppCompatActivity {


    private EditText et_id, et_pass, et_name, et_age;
    private Button btn_resister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister);

        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);

        btn_resister=findViewById(R.id.btn_resister);
        btn_resister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID=et_id.getText().toString();
                String userPass=et_pass.getText().toString();
                String userName=et_name.getText().toString();
                int userAge=Integer.parseInt(et_age.getText().toString());

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ResisterRequest resisterRequest=new ResisterRequest(userID,userPass,userName,userAge,responseListener);
                RequestQueue queue= Volley.newRequestQueue(ResisterActivity.this);
                queue.add(resisterRequest);
            }
        });
    }
}
