package com.sriyanksiddhartha.speechtotext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewContentActivity extends AppCompatActivity {

    private ListView lstViewContent;
    private QuestionAdapter adapter;
    public ArrayList<Question> arrayQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontent);
        lstViewContent = (ListView) findViewById(R.id.lstViewContent);


        arrayQuestion = new ArrayList<Question>();

        //arrayQuestion.add(new Question("Hello! Nice to meet you!",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("What's your name?",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("I'm Ariana Grander",R.drawable.student48));
        arrayQuestion.add(new Question("How old are you?",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("I'm 20 years old",R.drawable.student48));
        arrayQuestion.add(new Question("Are you student or worker?",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("I'm student",R.drawable.student48));
        arrayQuestion.add(new Question("What's your major?",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("My major is Infomation Teachnology",R.drawable.student48));
        arrayQuestion.add(new Question("Where do you study/work?",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("I live and work in Ho Chi Minh City",R.drawable.student48));
        arrayQuestion.add(new Question("What's your hobbies?",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("I love listening to Chinese music",R.drawable.student48));
        arrayQuestion.add(new Question("What's your dream?",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("I want to become famous actor",R.drawable.student48));
        arrayQuestion.add(new Question("How many people are there in your family?",R.drawable.hulagirl48));
        arrayQuestion.add(new Question("There are 4 people in my family",R.drawable.student48));

        adapter =new QuestionAdapter(
                ViewContentActivity.this,
                R.layout.row_question,
                arrayQuestion
        );

        lstViewContent.setAdapter(adapter);

    }
}
