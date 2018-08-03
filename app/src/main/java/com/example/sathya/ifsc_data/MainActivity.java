package com.example.sathya.ifsc_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    EditText ifsc;
    Button submit;
    TextView bank, state, city, district, branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ifsc = findViewById(R.id.ifsc);
        submit = findViewById(R.id.submit);
        bank = findViewById(R.id.bank);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        district = findViewById(R.id.district);
        branch = findViewById(R.id.branch);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ifscCode = ifsc.getText().toString();
                callRetro(ifscCode);
            }
        });
    }

    void callRetro(String ifscCode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ifsc.razorpay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IFSCService ifscService = retrofit.create(IFSCService.class);
        ifscService.getIFSC(ifscCode).enqueue(new Callback<IFSCData>() {
            @Override
            public void onResponse(Call<IFSCData> call, Response<IFSCData> response) {
                IFSCData ifscData = response.body();
                branch.setText(ifscData.BRANCH);
                state.setText(ifscData.STATE);
                district.setText(ifscData.DISTRICT);
                city.setText(ifscData.CITY);
                bank.setText(ifscData.BANK);

            }

            @Override
            public void onFailure(Call<IFSCData> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}
