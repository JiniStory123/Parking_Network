package kr.ac.mokwon.parkingnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        getSupportActionBar().hide();

        Button bt_writing = findViewById(R.id.bt_writing);

        bt_writing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent (getApplicationContext(), ReviewEditActivity.class);
                startActivity(intent);
            }
        });
    }
}
