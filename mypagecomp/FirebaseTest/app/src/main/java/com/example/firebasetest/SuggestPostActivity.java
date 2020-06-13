package com.example.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SuggestPostActivity extends AppCompatActivity {
    private static final String TAG = "QnaPostActivity";
    private FirebaseUser user;
    private ArrayList<String> pathList = new ArrayList<>();
    private LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_post);
        setTitle("건의사항 작성");
        parent = findViewById(R.id.contentsLayout);
        
        findViewById(R.id.checkSug).setOnClickListener(onClickListener);
        
    }


   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0 :
                if(requestCode == Activity.RESULT_OK){
                    String profilepath = data.getStringExtra("profilepath");
                    pathList.add(profilepath);
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    ImageView imageView = new ImageView(QnaPostActivity.this);
                    imageView.setLayoutParams(layoutParams);

                    Glide.with(this).load(profilepath).centerCrop().override(1000).into(imageView);
                    parent.addView(imageView);

                    EditText editText = new EditText(QnaPostActivity.this);
                    editText.setLayoutParams(layoutParams);
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);
                    parent.addView(editText);


                }
                break;
        }
    }*/


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.checkSug:
                    sugUpload();
                    break;
            }
        }
    };
    private void sugUpload() {
        final String title = ((EditText)findViewById(R.id.titleEditTextSug)).getText().toString();
        final String contents = ((EditText)findViewById(R.id.contentsEditTextSug)).getText().toString();

        if(title.length() > 0 && contents.length() > 0 ){
            user = FirebaseAuth.getInstance().getCurrentUser();
            ArrayList<String> contentList = new ArrayList<>();
            int pathCount =0;

            SuggestWriteInfo swriteInfo = new SuggestWriteInfo(title,contents,user.getUid());
            sugInfoUpload(swriteInfo);

        }else{
            startToast("글 또는 제목을 입력하세요.");
        }
    }
    private void sugInfoUpload(SuggestWriteInfo swriteInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sugposts").add(swriteInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        startToast("글 작성 완료");
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }



    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /*private void myStartActivity(Class c, String media){
        Intent intent=new Intent(this,c);
        intent.putExtra("media",media);
        startActivity(intent);
    }*/
    private void myStartActivity(Class c){
        Intent intent=new Intent(this,c);
        startActivity(intent);
    }

}
