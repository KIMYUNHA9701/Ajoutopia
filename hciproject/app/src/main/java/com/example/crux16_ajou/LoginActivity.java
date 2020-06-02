package com.example.crux16_ajou;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginbtn;

    private EditText editTextID;
    private EditText editTextPW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbtn = (Button) findViewById(R.id.loginButton);

        editTextID = (EditText) findViewById(R.id.idText);
        editTextPW = (EditText) findViewById(R.id.passwordText);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObject logindata = new JsonObject();
                logindata.addProperty("id", editTextID.getText().toString());
                logindata.addProperty("pw", editTextPW.getText().toString());

                RetrofitCommunication retrofitCommunication = new RetrofitConnection().init();
                Call<JsonObject> login = retrofitCommunication.userLogin(logindata);

                login.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body().get("code").getAsInt() == 200) {

                            Log.d("Get", response.body().toString());

                            JsonArray jsonArray = response.body().getAsJsonArray("result");
                            LoginData.getInstance().setid(jsonArray.get(0).getAsJsonObject().get("id").getAsString());
                            LoginData.getInstance().setpw(jsonArray.get(0).getAsJsonObject().get("pw").getAsString());
                            LoginData.getInstance().setnickname(jsonArray.get(0).getAsJsonObject().get("nickname").getAsString());

                            Intent intent = new Intent(LoginActivity.this, FirstpageActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, response.body().get("result").getAsString(), Toast.LENGTH_SHORT)
                            .show();
                        }

                    }


                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                                .show();
                        Log.e("TAG", "onFailure: " + t.getMessage() );
                    }
                });


            }
        });
    }
}
