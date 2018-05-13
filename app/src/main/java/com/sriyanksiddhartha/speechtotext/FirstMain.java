package com.sriyanksiddhartha.speechtotext;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstMain extends AppCompatActivity {

    Button itemmenu1,itemmenu2,itemmenu3,itemmenu4;
    int time_temp=12;
    public static final String TIME_RUN = "time_run";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstmain);

        itemmenu1=(Button) findViewById(R.id.itemmenu1);
        itemmenu2=(Button) findViewById(R.id.itemmenu2);
        itemmenu3=(Button) findViewById(R.id.itemmenu3);
        itemmenu4=(Button) findViewById(R.id.itemmenu4);

        itemmenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FirstMain.this);
                builder.setTitle("Rules");
                builder.setMessage("Hãy chọn cấp độ để luyện tập.");

                builder.setPositiveButton("Khó", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        time_temp=5;

                        Intent intent = new Intent(FirstMain.this, AvailableAnswerActivity.class);
                        intent.putExtra(TIME_RUN, time_temp+"");
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                    }
                });

                builder.setNegativeButton("Dễ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        time_temp=10;

                        Intent intent = new Intent(FirstMain.this, AvailableAnswerActivity.class);
                        intent.putExtra(TIME_RUN, time_temp+"");
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                    }
                });

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        itemmenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FirstMain.this);
                builder.setTitle("Rules");
                builder.setMessage("Hãy chọn cấp độ để luyện tập.");

                builder.setPositiveButton("Khó", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        time_temp=5;

                        Intent intent = new Intent(FirstMain.this, MainActivity.class);
                        intent.putExtra(TIME_RUN, time_temp+"");
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                    }
                });

                builder.setNegativeButton("Dễ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        time_temp=10;

                        Intent intent = new Intent(FirstMain.this, MainActivity.class);
                        intent.putExtra(TIME_RUN, time_temp+"");
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                    }
                });

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        itemmenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstMain.this, ViewContentActivity.class));
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });

        itemmenu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstMain.this, ViewDirections.class));
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });
    }

}
