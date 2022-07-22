package com.assignment.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IpAddressActivity extends AppCompatActivity {

    //Edit Text
    EditText editText;
    //Two Buttons
    Button btn_png,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_address);

        //Initilzation
        editText = findViewById(R.id.edt_ip);
        btn_png = findViewById(R.id.ping_btn);
        btnback = findViewById(R.id.back_btn);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IpAddressActivity.this,MainActivity.class));
                finish();
            }
        });

        btn_png.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipAdress = editText.getText().toString();
                if(ipAdress.isEmpty()){
                    editText.setError("Enter Ip Address");
                }
                else{

                    String res = executeCmd("ping -c 1 -w 1 "+ipAdress, false);
                    Intent i = new Intent(IpAddressActivity.this,ResultActivity.class);
                    i.putExtra("result",res);
                    startActivity(i);
                    finish();
                    //Toast.makeText(IpAddressActivity.this, ""+res, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static String executeCmd(String cmd, boolean sudo){
        try {

            Process p;
            if(!sudo)
                p= Runtime.getRuntime().exec(cmd);
            else{
                p= Runtime.getRuntime().exec(new String[]{"su", "-c", cmd});
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s;
            String res = "";
            while ((s = stdInput.readLine()) != null) {
                res += s + "\n";
            }
            p.destroy();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}