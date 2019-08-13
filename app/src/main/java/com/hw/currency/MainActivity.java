package com.hw.currency;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edInput;
    private TextView tvJPrate;
    private TextView tvUSrate;
    private Button btGO;
    private CurrencyExchanger currencyEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyEx = new CurrencyExchanger();
        findViews();
        setViews();
    }

    void findViews(){
        edInput = findViewById(R.id.etInput);
        btGO = findViewById(R.id.btGO);
        tvJPrate = findViewById(R.id.tvJPrate);
        tvUSrate = findViewById(R.id.tvUSDrate);
    }

    void setViews(){
        btGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edInput.getText().length() < 1) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.HI)
                            .setMessage(R.string.PLZ_ENTER)
                            .setNegativeButton(R.string.OK,null)
                            .show();
                } else {
                    float input =  Float.parseFloat(edInput.getText().toString());
                    float JPrate = currencyEx.getExRate(CurrencyExchanger.CURRENCY.JPY);
                    float USrate = currencyEx.getExRate(CurrencyExchanger.CURRENCY.USD);
                    if(JPrate < 0){
                        Toast.makeText(MainActivity.this, R.string.TYR_LATER,Toast.LENGTH_LONG).show();
                    }else{
                        tvJPrate.setText(input / JPrate + "");
                        tvUSrate.setText(input / USrate + "");
                    }

                }

            }
        });
    }
}

