package com.example.root.qtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/*
This screen notifies the user that Quiet Time is in progress and 
shows a little loading circle to visualize this. Also has a Stop 
Button that will cancel the timer and go back to the home screen.
*/
public class QT extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qt);
    }
    public void onStopTap(View v) {
        Toast stopped = Toast.makeText(getApplicationContext(), "Timer Stopped", Toast.LENGTH_LONG);
        stopped.show();
        Intent home = new Intent(QT.this, MainActivity.class);
        startActivity(home);

    }
}
