package com.fju.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edMonth;
    private EditText edNext;
    private float degree;
    private float fee = 0;
    private Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edMonth = findViewById(R.id.month);
        edNext = findViewById(R.id.next);
        butt = findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fee();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            reset();
        }
    };

    public void fee() {
        String monthString = edMonth.getText().toString();
        if (!TextUtils.isEmpty(monthString)) {
            degree = Float.parseFloat(monthString);
            if (degree >= 1 && degree <= 10) {
                fee = degree * 7.35f;
            } else if (degree >= 11 && degree <= 30) {
                fee = degree * 9.45f - 21;
            } else if (degree >= 31 && degree <= 50) {
                fee = degree * 11.55f - 84;
            } else if (degree >= 51) {
                fee = degree * 12.075f - 110.25f;
            }
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra(getString(R.string.extra_fee), fee);
            startActivity(intent);
//            new AlertDialog.Builder(MainActivity.this)
//                    .setTitle("每月抄表費用")
//                    .setMessage("費用: " + fee + " 元")
//                    .setPositiveButton("OK", listener)
//                    .show();
        } else {
            String nextString = edNext.getText().toString();
            if (!TextUtils.isEmpty(nextString)) {
                degree = Float.parseFloat(nextString);
                if (degree >= 1 && degree <= 20) {
                    fee = degree * 7.35f;
                } else if (degree >= 21 && degree <= 60) {
                    fee = degree * 9.45f - 42;
                } else if (degree >= 61 && degree <= 100) {
                    fee = degree * 11.55f - 168;
                } else if (degree > 101) {
                    fee = degree * 12.075f - 220.5f;
                }
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("隔月抄表費用")
                        .setMessage("費用: " + fee + " 元")
                        .setPositiveButton(getString(R.string.ok), listener)
                        .show();
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(getString(R.string.error))
                        .setMessage(getString(R.string.no_input))
                        .setPositiveButton(getString(R.string.ok), null)
                        .show();
            }
        }
    }
    public void reset() {
        fee = 0;
        edMonth.setText("");
        edNext.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
//      若另外設置button.OnClickListener, 可直接呼叫執行方法(則方法不需放(View view)), 或是將執行程式碼包入Override內(但this的部分需明確標明哪個Activity).