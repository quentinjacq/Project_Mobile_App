package com.example.project_mobile_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddAccountActivity extends AppCompatActivity {


    public static final String EXTRA_ACCOUNT =
            "com.example.project_mobile_app.EXTRA_ACCOUNT";
    public static final String EXTRA_IBAN =
            "com.example.project_mobile_app.EXTRA_IBAN";
    public static final String EXTRA_AMOUNT =
            "com.example.project_mobile_app.EXTRA_AMOUNT";
    public static final String EXTRA_CURRENCY =
            "com.example.project_mobile_app.EXTRA_CURRENCY";




    private EditText editAccountName;
    private EditText editIban;
    private EditText editAmount;
    private EditText editCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        editAccountName = findViewById(R.id.edit_account_name);
        editIban = findViewById(R.id.edit_iban);
        editAmount = findViewById(R.id.edit_amount);
        editCurrency = findViewById(R.id.edit_currency);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Account");

        FloatingActionButton buttonValidate = findViewById(R.id.button_validate);
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccount();
            }
        });
    }
    private void saveAccount() {
        String account = editAccountName.getText().toString();
        String iban = editIban.getText().toString();
        String amount = editAmount.getText().toString();
        if(amount.matches("")){
            amount = "0";
        }
        String currency = editCurrency.getText().toString();

        if (account.trim().isEmpty() || iban.trim().isEmpty()) {
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_ACCOUNT, account);
        data.putExtra(EXTRA_IBAN, iban);
        data.putExtra(EXTRA_AMOUNT, amount);
        data.putExtra(EXTRA_CURRENCY, currency);
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

}