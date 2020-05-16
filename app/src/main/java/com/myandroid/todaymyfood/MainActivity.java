package com.myandroid.todaymyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    TextView foodText;
    Button randomBtn, setBtn;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, getString(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // 광고가 제대로 로드 되는지 테스트 하기 위한 코드입니다.

        mAdView.setAdListener(new AdListener() {

            @Override

            public void onAdLoaded() {

                // Code to be executed when an ad finishes loading.

                // 광고가 문제 없이 로드시 출력됩니다.

                Log.d("@@@", "onAdLoaded");

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                // 광고 로드에 문제가 있을시 출력됩니다.
                Log.d("@@@", "onAdFailedToLoad " + errorCode);

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        foodText = (TextView)findViewById(R.id.foodText);
        randomBtn = (Button)findViewById(R.id.randomBtn);
        setBtn = (Button)findViewById(R.id.setBtn);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "FOODTABLE.db", null, 1);

        //랜덤 버튼
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    foodText.setText(dbHelper.getNum());
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "오류 발생", Toast.LENGTH_LONG).show();
                }
            }
        });

        //화면 전환
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

    }
}
