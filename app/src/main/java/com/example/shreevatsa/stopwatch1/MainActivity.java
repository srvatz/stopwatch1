package com.example.shreevatsa.stopwatch1;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    ImageView start,Reset;
    TextView number;
    int second = 0;
    boolean isplay = false;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        number = findViewById(R.id.sample_text);
        start = findViewById(R.id.stopwatch);
        Reset = findViewById(R.id.Reset);

        handler = new Handler();
        final Runnable thread  = new Runnable() {
            @Override
            public void run() {
                            if(isplay){
                                handler.postDelayed(this,1000);
                                second++;
                                int m,s;
                                m = second/60;
                                s = second%60;

                                if(m < 10 && s >= 10) {
                                    number.setText("0"+m+":"+s);
                                }else if(m >= 10 && s < 10){
                                    number.setText(""+m+":0"+s);
                                }if(m >= 10 && s >= 10){
                                    number.setText(""+m+":"+s);
                                }else if(m < 10 && s < 10){
                                    number.setText("0"+m+":0"+s);
                                }
                            }
                            else{

                            }
            }
        };


        start.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.play));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isplay){
                    isplay = true;
                    handler.postDelayed(thread,0);
                    start.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.pause));
                }
                else{
                    isplay = false;
                    start.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.play));
                }

            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isplay = false;
                second = 0;
                number.setText("00:00");
            }
        });
        count();


    }


    public void count(){

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
