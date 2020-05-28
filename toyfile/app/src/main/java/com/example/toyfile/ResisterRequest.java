package com.example.toyfile;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ResisterRequest extends StringRequest {

    final static private String URL = "http://esangbaek.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public ResisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listner){
        super(Method.POST, URL, listner,null);

        map=new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userName",userName);
        map.put("userAge",userAge+ "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
