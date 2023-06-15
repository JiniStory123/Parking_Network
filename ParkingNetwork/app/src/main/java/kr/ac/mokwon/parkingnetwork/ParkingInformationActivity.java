package kr.ac.mokwon.parkingnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ParkingInformationActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_information);

        getSupportActionBar().hide();

        Button bt_review = findViewById(R.id.bt_review);

        bt_review.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent (getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
            }
        });
    }
}
