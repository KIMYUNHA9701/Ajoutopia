package com.example.firebasetest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class QnaPostActivity extends AppCompatActivity {
    private static final String TAG = "QnaPostActivity";
    private FirebaseUser user;
    private ArrayList<String> pathList = new ArrayList<>();
    private LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_qna);
        setTitle("QnA 게시판 글 작성");
        parent = findViewById(R.id.contentsLayout);

        findViewById(R.id.checkQna).setOnClickListener(onClickListener);

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
                case R.id.checkQna:
                    qnaUpload();
                    break;

            }
        }
    };
    private void qnaUpload() {
        final String title = ((EditText)findViewById(R.id.titleEditTextQna)).getText().toString();
        final String contents = ((EditText)findViewById(R.id.contentsEditTextQna)).getText().toString();
        int points = 0;

        if(title.length() > 0 && contents.length() > 0 ){
            user = FirebaseAuth.getInstance().getCurrentUser();
            ArrayList<String> contentList = new ArrayList<>();
            int pathCount =0;

            QnaWriteInfo writeInfo = new QnaWriteInfo(title,contents,user.getUid(),points);
            qnaInfoUpload(writeInfo);

        }else{
            startToast("글 또는 제목을 입력하세요.");
        }
    }
    /*private void qnaInfoUpload(QnaWriteInfo writeInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("qnaposts").add(writeInfo)
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
    }*/

    private void qnaInfoUpload(QnaWriteInfo writeInfo){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("qnaposts").add(writeInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        startToast("글 작성 완료.\n3point가 지급되었습니다.");
                        final DocumentReference docRef = db.collection("users").document(user.getUid());

                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            private String point;
                            private int temp_point;

                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                        point = (String) document.get("point");
                                        temp_point = Integer.parseInt(point);
                                        temp_point = temp_point + 3;
                                        point = Integer.toString(temp_point);
                                        docRef
                                                .update("point", point)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error updating document", e);
                                                    }
                                                });

                                    } else {
                                        Log.d(TAG, "No such document");
                                    }
                                } else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
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
