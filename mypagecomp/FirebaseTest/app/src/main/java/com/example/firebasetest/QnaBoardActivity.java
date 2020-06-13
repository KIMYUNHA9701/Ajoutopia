package com.example.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasetest.adapters.QnaPostAdapter;
import com.example.firebasetest.adapters.StudyPostAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QnaBoardActivity extends AppCompatActivity{
    private RecyclerView mPostRecyclerView;
    private QnaPostAdapter mAdapter;
    private List<QnaWriteInfo> mDatas;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_board);
        setTitle("QnA 게시판");
        mPostRecyclerView = findViewById(R.id.qnaBoardRv);

        mDatas = new ArrayList<>();
        mAdapter = new QnaPostAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdapter);

        db.collection("qnaposts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
                    String publisher = (String) dc.getDocument().getData().get("publisher");
                    String title = (String) dc.getDocument().getData().get("title");
                    String contents = (String) dc.getDocument().getData().get("contents");
                    QnaWriteInfo data = new QnaWriteInfo(title,contents,publisher);
                    mDatas.add(data);
                }
                mAdapter = new QnaPostAdapter(mDatas);
                mPostRecyclerView.setAdapter(mAdapter);
            }
        });

        /*db.collection("posts").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String publisher = (String) document.getData().get("publisher");
                                String title = (String) document.getData().get("title");
                                String contents = (String) document.getData().get("contents");
                                StudyWriteInfo data = new StudyWriteInfo(title,contents,publisher);
                                mDatas.add(data);
                            }
                            mAdapter = new StudyPostAdapter(mDatas);
                            mPostRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });*/

        findViewById(R.id.qna_post_edit).setOnClickListener(onClickListener);
        findViewById(R.id.homeBtn).setOnClickListener(onClickListener);
        findViewById(R.id.qnaBtn).setOnClickListener(onClickListener);
        findViewById(R.id.studyBtn).setOnClickListener(onClickListener);
}
    final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.qna_post_edit:
                    myStartActivity(QnaPostActivity.class);
                    break;
                case R.id.homeBtn:
                    myStartActivity(MainActivity.class);
                    break;
                case R.id.qnaBtn:
                    startToast("이미 QnA게시판입니다.");
                    break;
                case R.id.studyBtn:
                    myStartActivity(StudyBoardActivity.class);
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            moveTaskToBack(true);						// 태스크를 백그라운드로 이동
            finishAndRemoveTask();						// 액티비티 종료 + 태스크 리스트에서 지우기
            android.os.Process.killProcess(android.os.Process.myPid());
            toast.cancel();
        }
    }
    private void startToast(String msg){
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }
    private void myStartActivity(Class c){
        Intent intent=new Intent(this,c);
        startActivity(intent);
    }

}
