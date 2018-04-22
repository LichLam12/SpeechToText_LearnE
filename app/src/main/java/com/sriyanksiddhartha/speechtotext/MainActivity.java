package com.sriyanksiddhartha.speechtotext;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

	private TextView txvResult;
	private ListView lstViewConversation;
	private QuestionAdapter adapter;
	ArrayList<Question> arrayQuestion;
	ArrayList<Question> arrayQues_temp;
	TextView txt_Timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt_Timer = (TextView) findViewById(R.id.txt_Timer);
		lstViewConversation = (ListView) findViewById(R.id.lstViewConversation);

		arrayQuestion = new ArrayList<Question>();
		arrayQues_temp = new ArrayList<Question>();

		arrayQues_temp.add(new Question("Hello! Nice to meet you!"));
		arrayQues_temp.add(new Question("What's your name?"));
		arrayQues_temp.add(new Question("How old are you?"));
		arrayQues_temp.add(new Question("Are you student or worker?"));
		arrayQues_temp.add(new Question("What's your major?"));
		arrayQues_temp.add(new Question("Where do you study/work?"));
		arrayQues_temp.add(new Question("What's your hobbies?"));
		arrayQues_temp.add(new Question("What's your dream?"));
		arrayQues_temp.add(new Question("How many people are there in your family?"));

		adapter =new QuestionAdapter(
				MainActivity.this,
				R.layout.row_question,
				arrayQuestion
		);

		lstViewConversation.setAdapter(adapter);
		Set_AutoTime();

		lStartTime = SystemClock.uptimeMillis();
		handler.postDelayed(runnable, 0);

	}

	//Diem nguoc thoi gian
	long lStartTime, lPauseTime, lSystemTime = 0L;
	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
	@Override
	public void run() {
		lSystemTime = SystemClock.uptimeMillis() - lStartTime;
		long lUpdateTime = lPauseTime + lSystemTime;
		long secs = (long)(lUpdateTime/1000);
		secs = secs %60;
		long milliseconds = (long)(lUpdateTime%1000);
		txt_Timer.setText(String.format("%02d",secs) + ":" + String.format("%03d",milliseconds));
		handler.postDelayed(this,0);
	}};

	int count=1;
	private void ShowNewQues(){
		if(count < arrayQues_temp.size()){
			arrayQuestion.add(arrayQues_temp.get(count));
			adapter.notifyDataSetChanged();
		}
	}

	//Cach 10s goi ShowNewQues() de them question moi 1 lan
	private void Set_AutoTime(){
		final Handler handler = new Handler() {
			@Override    public void handleMessage(Message msg) {
				// Chèn method có chứa các đối tượng UI vào đây
				ShowNewQues();
				count++;
			}
		};

        //Ham nay chi thuc thi 1 lan sau 8s, chu k lap lai
        /*new Timer().schedule(new TimerTask() {
            @Override    public void run() {
                // this code will be executed after 10 minutes
                handler.sendEmptyMessage(0);
            }
        }, 1000 * 8);*/

		//Lap lai sau 6s
		final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleWithFixedDelay(new Runnable()
		{
			@Override    public void run()
			{
				handler.sendEmptyMessage(0);
			}
		}, 0, 10, TimeUnit.SECONDS);
	}

	public void getSpeechInput(View view) {

		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(intent, 10);
		} else {
			Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case 10:
				if (resultCode == RESULT_OK && data != null) {
					ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
					txvResult.setText(result.get(0));
				}
				break;
		}
	}
}
