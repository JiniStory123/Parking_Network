package kr.ac.mokwon.parkingnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
{
    public static Context context_main;

    // 바텀 네비게이션 제어 변수
    BottomNavigationView bottomNavigationView;

    // 프래그먼트 제어 변수
    MainMapFragment mainMapFragment;
    BookMarkerFragment bookMarkerFragment;
    ParkingLocationFragment parkingLocationFragment;
    MypageActivity mypageActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        // 프래그먼트 설정
        mainMapFragment = new MainMapFragment();
        bookMarkerFragment = new BookMarkerFragment();
        parkingLocationFragment = new ParkingLocationFragment();
        mypageActivity = new MypageActivity();

        // 초기 프래그먼트 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mainMapFragment).commit();

        // 바텀 네비게이션 설정
        bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.item1:
                        getSupportActionBar().hide();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mainMapFragment).commit();
                        return true;
                    case R.id.item2:
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                        getSupportActionBar().setCustomView(R.layout.title_bookmarker);
                        getSupportActionBar().show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, bookMarkerFragment).commit();
                        return true;

                    case R.id.item3:
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                        getSupportActionBar().setCustomView(R.layout.title_location);
                        getSupportActionBar().show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, parkingLocationFragment).commit();
                        return true;

                    case R.id.item4:
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                        getSupportActionBar().setCustomView(R.layout.title_mypage);
                        getSupportActionBar().show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mypageActivity).commit();
                        return true;
                }
                return false;
            }
        });
    }
}