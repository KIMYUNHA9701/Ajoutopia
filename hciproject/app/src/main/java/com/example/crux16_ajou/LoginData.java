package com.example.crux16_ajou;

public class LoginData {

    private String id;
    private String pw;
    private String nickname;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getpw() {
        return pw;
    }

    public void setpw(String pw) {
        this.pw = pw;
    }

    public String getnickname() {
        return nickname;
    }

    public void setnickname(String name) {
        this.nickname = nickname;
    }

    public static void setInstance(LoginData instance) {
        LoginData.instance = instance;
    }

    private static LoginData instance = null;

    public static synchronized LoginData getInstance(){
        if(null==instance){
            instance = new LoginData();
        }
        return instance;
    }

}
