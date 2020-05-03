package com.example.traditionalwordsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    public static final String ANSWER_DESCRIPTION_KEY = "mAnswer_description_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        TextView textViewShowAnswerTitle = findViewById(R.id.text_view_show_correct_answer);

        String answerTitle = getIntent().getStringExtra("answer_share_title_key");
        if(answerTitle != null)
        textViewShowAnswerTitle.setText(answerTitle);


        TextView textViewAnswer = findViewById(R.id.text_view_answer);
        String answer = getIntent().getStringExtra(ANSWER_DESCRIPTION_KEY);
        if(answer != null)
            textViewAnswer.setText(answer);

    }

    public void onClickFinish(View view){
        finish();
    }
}
