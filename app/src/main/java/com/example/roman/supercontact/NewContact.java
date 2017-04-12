package com.example.roman.supercontact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Roman on 01/03/2017.
 */

public class NewContact extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        context = this;

        cancel_bt = (Button)findViewById(R.id.cancel_button_new);
        save_bt = (Button)findViewById(R.id.save_button_new);
        first_name_et = (EditText)findViewById(R.id.et_first_name);
        last_name_et = (EditText)findViewById(R.id.et_last_name);
        home_phone_et = (EditText)findViewById(R.id.et_home_phone);
        mobile_phone_et = (EditText)findViewById(R.id.et_mobile_phone);
        mail_et = (EditText)findViewById(R.id.et_mail);

        cancel_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first_name_et.getText().toString().isEmpty() && last_name_et.getText().toString().isEmpty())
                {
                    Toast.makeText(context, "You must provide a first or a last name", Toast.LENGTH_LONG).show();
                    return;
                }
                Contact new_contact = new Contact(
                        first_name_et.getText().toString(),
                        last_name_et.getText().toString(),
                        home_phone_et.getText().toString(),
                        mobile_phone_et.getText().toString(),
                        mail_et.getText().toString());
                Intent result = new Intent(Intent.ACTION_VIEW);
                result.putExtra("new_contact", new_contact);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

    private Button cancel_bt;
    private Button save_bt;
    private Context context;

    private EditText first_name_et;
    private EditText last_name_et;
    private EditText home_phone_et;
    private EditText mobile_phone_et;
    private EditText mail_et;
}
