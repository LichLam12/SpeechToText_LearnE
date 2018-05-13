package com.sriyanksiddhartha.speechtotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ViewDirections extends AppCompatActivity {

    ImageView imgViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        imgViewBack = (ImageView) findViewById(R.id.imgViewBack);

        //Tro ve trang chu, nut back
        imgViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewDirections.this, FirstMain.class));
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });
    }
}
