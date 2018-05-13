package com.sriyanksiddhartha.speechtotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstMain extends AppCompatActivity {

    Button itemmenu1,itemmenu2,itemmenu3,itemmenu4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstmain);

        itemmenu1=(Button) findViewById(R.id.itemmenu1);
        itemmenu2=(Button) findViewById(R.id.itemmenu2);
        itemmenu3=(Button) findViewById(R.id.itemmenu3);
        itemmenu4=(Button) findViewById(R.id.itemmenu4);

        itemmenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstMain.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
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
