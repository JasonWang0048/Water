package com.fju.water;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        float fee = intent.getFloatExtra("FEE", -1);
        Log.d("ResultActivity", fee + "");
        TextView feeText = findViewById(R.id.fee);
//        將水費費用自小數點後四捨五入的動作
        int x = (int)(fee + 0.5f);
        feeText.setText(x + " 元");
    }
}
