package com.kelompok1.uas.MyPolis;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    final int welcomeScreenDisplay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread welcomeThread = new Thread() {

            int wait = 0;

            @Override
            public void run() {
                try {
                    super.run();

                    while (wait < welcomeScreenDisplay) {
                        sleep(1000);
                        wait += 1000;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);
                } finally {

                    // Start other Activity
                    startActivity(new Intent(SplashScreen.this,
                            login.class));
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
