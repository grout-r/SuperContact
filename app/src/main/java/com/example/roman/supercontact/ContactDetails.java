package com.example.roman.supercontact;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Roman on 02/03/2017.
 */

public class ContactDetails extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Contact contact = getIntent().getParcelableExtra("contact");
        contact.replaceEmptyByNA();
        ((TextView)findViewById(R.id.tv_details_full_name)).setText(contact.first_name + " " + contact.last_name);
        ((TextView)findViewById(R.id.tv_details_home_phone)).setText(contact.home_phone);
        ((TextView)findViewById(R.id.tv_details_mobile_phone)).setText(contact.mobile_phone);
        ((TextView)findViewById(R.id.tv_details_mail)).setText(contact.mail);

        (findViewById(R.id.back_button_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
