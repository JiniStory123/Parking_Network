package kr.ac.mokwon.parkingnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        getSupportActionBar().hide();

        Button bt_join = findViewById(R.id.bt_join);

        bt_join.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(JoinActivity.this, "가입 끄읏!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}