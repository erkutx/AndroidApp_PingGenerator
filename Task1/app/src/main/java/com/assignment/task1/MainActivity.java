package com.assignment.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button and it's click  listener
        Button btnping = findViewById(R.id.ping_btn);
        btnping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent for moving from this activity to Ip Address activity
                Intent i = new Intent(MainActivity.this,IpAddressActivity.class);

                startActivity(i);
                finish();

            }
        });
    }
}