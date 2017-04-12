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
 * Created by Roman on 02/03/2017.
 */

public class EditContact extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
       contact = getIntent().getParcelableExtra("contact");

        Button save_bt = (Button)findViewById(R.id.save_button_edit);
        Button cancel_bt = (Button)findViewById(R.id.cancel_button_edit);

        et_edit_first_name = (EditText)findViewById(R.id.et_edit_first_name);
        et_edit_last_name = (EditText)findViewById(R.id.et_edit_last_name);
        et_edit_home_phone = (EditText)findViewById(R.id.et_edit_home_phone);
        et_edit_mobile_phone = (EditText)findViewById(R.id.et_edit_mobile_phone);
        et_edit_mail = (EditText)findViewById(R.id.et_edit_mail);

        et_edit_first_name.setText(contact.first_name);
        et_edit_last_name.setText(contact.last_name);
        et_edit_home_phone.setText(contact.home_phone);
        et_edit_mobile_phone.setText(contact.mobile_phone);
        et_edit_mail.setText(contact.mail);
        context = this;

       save_bt.setOnClickListener(new View.OnClickListener()
       {
            @Override
            public void onClick(View v) {
                if (et_edit_first_name.getText().toString().isEmpty() && et_edit_last_name.getText().toString().isEmpty())
                {
                    Toast.makeText(context, "You must provide a first or a last name", Toast.LENGTH_LONG).show();
                    return;
                }
                Contact new_contact = new Contact(
                        contact.id,
                        et_edit_first_name.getText().toString(),
                        et_edit_last_name.getText().toString(),
                        et_edit_home_phone.getText().toString(),
                        et_edit_mobile_phone.getText().toString(),
                        et_edit_mail.getText().toString());
                Intent result = new Intent(Intent.ACTION_VIEW);
                result.putExtra("updated_contact", new_contact);
                setResult(RESULT_OK, result);
                finish();
            }
        });

        cancel_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    Context context;
    Contact contact;
    EditText et_edit_first_name;
    EditText et_edit_last_name;
    EditText et_edit_home_phone;
    EditText et_edit_mobile_phone;
    EditText et_edit_mail;

}
