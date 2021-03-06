package com.sriyanksiddhartha.speechtotext;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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
	TextToSpeech textToSpeech;
	int result;
	ImageView imgViewReload;
	public static final int a=0;
	private QuestionAdapter adapter;
	public ArrayList<Question> arrayQuestion;
	public ArrayList<Question> arrayQues_temp;
	TextView txt_Timer;

	String time_run_temp;
	int time_run;

	ImageView imgViewBack; //nut back tro lai trang chu

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imgViewBack = (ImageView) findViewById(R.id.imgViewBack);
		txt_Timer = (TextView) findViewById(R.id.txt_Timer);
		imgViewReload = (ImageView) findViewById(R.id.imgViewReload);
		lstViewConversation = (ListView) findViewById(R.id.lstViewConversation);

		Intent intent = getIntent();
		time_run_temp=intent.getStringExtra(FirstMain.TIME_RUN);
		if(Integer.parseInt(time_run_temp) == 5){
			time_run=5;
		}
		else if(Integer.parseInt(time_run_temp) == 10){
			time_run=10;
		}

		arrayQuestion = new ArrayList<Question>();
		arrayQues_temp = new ArrayList<Question>();

		arrayQues_temp.add(new Question("Hello! Nice to meet you!",R.drawable.student48));
		arrayQues_temp.add(new Question("What's your name?",R.drawable.hulagirl48));
		arrayQues_temp.add(new Question("How old are you?",R.drawable.hulagirl48));
		arrayQues_temp.add(new Question("Are you student or worker?",R.drawable.hulagirl48));
		arrayQues_temp.add(new Question("What's your major?",R.drawable.hulagirl48));
		arrayQues_temp.add(new Question("Where do you study/work?",R.drawable.hulagirl48));
		arrayQues_temp.add(new Question("What's your hobbies?",R.drawable.hulagirl48));
		arrayQues_temp.add(new Question("What's your dream?",R.drawable.hulagirl48));
		arrayQues_temp.add(new Question("How many people are there in your family?",R.drawable.hulagirl48));

		adapter =new QuestionAdapter(
				MainActivity.this,
				R.layout.row_question,
				arrayQuestion
		);

		lstViewConversation.setAdapter(adapter);
		Set_AutoTime();
		if(count <= arrayQues_temp.size())
		{
			StartCountTime();
		}
		else{
			StopCountTime();
		}

		//Tro ve trang chu, nut back
		imgViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, FirstMain.class));
				overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
			}
		});
	}

	private void StartCountTime(){
        if(isRun)
            return;
        isRun = true;
        lStartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    private void StopCountTime(){
        if(!isRun)
            return;
        isRun = false;
        lPauseTime = 0;
        handler.removeCallbacks(runnable);
    }

    private void PauseCountTime(){
        if(!isRun)
            return;
        isRun = false;
        lPauseTime += lSystemTime;
        handler.removeCallbacks(runnable);
    }

	//Diem nguoc thoi gian
	long lStartTime, lPauseTime, lSystemTime = 0L;
	Handler handler = new Handler();
    boolean isRun;
	Runnable runnable = new Runnable() {
	@Override
	public void run() {
		lSystemTime = SystemClock.uptimeMillis() - lStartTime;
		long lUpdateTime = lPauseTime + lSystemTime;
		long secs = (long)(lUpdateTime/1000);
		secs = secs %60;
		long milliseconds = (long)(lUpdateTime%1000);
		if(secs==time_run)
		{
			isRun = false;
			lPauseTime = 0;
			handler.removeCallbacks(runnable);
			return;
		}
		txt_Timer.setText(String.format("%02d",secs)+":"+ String.format("%02d",milliseconds));

		//txt_Timer.setText(String.format("%02d",secs) + ":" + String.format("%02d",milliseconds));
		handler.postDelayed(this,0);
	}};

	int count=1;
	private void ShowNewQues(){
		if(count < arrayQues_temp.size()){

			textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
				@Override
				public void onInit(int i) {
					if(i==TextToSpeech.SUCCESS){
						result=textToSpeech.setLanguage(Locale.UK);
						textToSpeech.speak(arrayQues_temp.get(count-1).quesContent,TextToSpeech.QUEUE_FLUSH,null);
					}
					else{
						Toast.makeText(getApplicationContext(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
					}
				}
			});

			arrayQuestion.add(arrayQues_temp.get(count));
			adapter.notifyDataSetChanged();
			StartCountTime();
			count++;
		}
	}

	//Cach 12s goi ShowNewQues() de them question moi 1 lan
	private void Set_AutoTime(){
		final Handler handler = new Handler() {
			@Override    public void handleMessage(Message msg) {
				// Chèn method có chứa các đối tượng UI vào đây
				ShowNewQues();
				getSpeechInput();
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
		}, 0, time_run+1, TimeUnit.SECONDS);
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

	public void getSpeechInput() {

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
					//txvResult.setText(result.get(0));

					if(count <= arrayQues_temp.size()){
						arrayQuestion.add(new Question(result.get(0),R.drawable.student48));
						adapter.notifyDataSetChanged();
						//StartCountTime();
						//count++;
					}else {
						StopCountTime();
					}

				}
				break;
		}
	}

	public void reload(){
		arrayQuestion.clear();
		count=1;
		adapter.notifyDataSetChanged();
	}
}
