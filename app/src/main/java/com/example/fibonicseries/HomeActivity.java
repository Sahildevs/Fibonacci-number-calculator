package com.example.fibonicseries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private EditText etNum1, etNum2;
    private Button btnOk;
    private TextView tvSeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnOk = findViewById(R.id.btnOk);
        tvSeries = findViewById(R.id.tvSeries);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //tvSeries.setText(fibonacci(Integer.parseInt(String.valueOf(etNum1.getText()))));

                fibonacci();


            }
        });
    }

    private void fibonacci() {

        int num1 = Integer.parseInt(String.valueOf(etNum1.getText()));
        int num2 = Integer.parseInt(String.valueOf(etNum2.getText()));

        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(0, num1);
        arrayList.add(1, num2);

        for (int i=2; i<15; i++){

            int num3= num1 + num2;

            arrayList.add(i, num3);

            num1 = arrayList.get(i-1);
            num2 = arrayList.get(i);
        }


        for (int j = 0; j < arrayList.size(); j++){


            tvSeries.append(""+arrayList.get(j)+ ", ");
        }



    }


}