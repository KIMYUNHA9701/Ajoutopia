package com.example.firebasetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebasetest.adapters.SugPostAdapter;
import com.example.firebasetest.adapters.SugPostAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SuggestActivity extends AppCompatActivity {
    private RecyclerView mPostRecyclerView;
    private SugPostAdapter mAdapter;
    private List<SuggestWriteInfo> mDatas;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        setTitle("건의사항");
        mPostRecyclerView = findViewById(R.id.sugBoardRv);

        mDatas = new ArrayList<>();
        mAdapter = new SugPostAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdapter);

        db.collection("sugposts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
                    String publisher = (String) dc.getDocument().getData().get("publisher");
                    String title = (String) dc.getDocument().getData().get("title");
                    String contents = (String) dc.getDocument().getData().get("contents");
                    SuggestWriteInfo data = new SuggestWriteInfo(title,contents,publisher);
                    mDatas.add(data);
                }
                mAdapter = new SugPostAdapter(mDatas);
                mPostRecyclerView.setAdapter(mAdapter);
            }
        });
        
        findViewById(R.id.sug_post_edit).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.sug_post_edit:
                    myStartActivity(SuggestPostActivity.class);
                    break;
            }
        }
    };
    
    
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void myStartActivity(Class c){
        Intent intent=new Intent(this,c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
