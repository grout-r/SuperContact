package com.example.roman.supercontact;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        contactCtrl = new ContactDBController(this);
        contact_lv = (ListView) findViewById(R.id.contact_lv);
        allContacts = contactCtrl.getAllContacts();
        caa = new ContactArrayAdapter(this, allContacts);
        add_button = (Button) findViewById(R.id.add_contact_button);


        contact_lv.setAdapter(caa);

        contact_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterview, View view, int pos, long id)
            {
                final Contact clicked = allContacts.get(pos);
                Intent intent = new Intent(ContactList.this, ContactDetails.class);
                intent.putExtra("contact", clicked);
                startActivity(intent);
            }
        });

        contact_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView<?> adapterview, View view, int pos, long id)
            {
                final Contact clicked = allContacts.get(pos);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(clicked.first_name + " " + clicked.last_name);
                builder.setItems(R.array.contact_long_press, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(which == 0)
                        {
                            contactCtrl.deleteContact(clicked);
                            refeshList();
                        }
                        else if(which == 1)
                        {
                            Intent intent = new Intent(ContactList.this, EditContact.class);
                            intent.putExtra("contact", clicked);
                            startActivityForResult(intent, 2);
                        }
                    }
                });
                // create the dialog and display it
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactList.this, NewContact.class);
                startActivityForResult(intent, 1);
            }
        });
    }


    protected void onActivityResult(int request, int result, Intent data)
    {
        if (request == 1)
        {
            if (result == RESULT_OK)
            {
                Contact new_contact = data.getParcelableExtra("new_contact");
                if (!contactCtrl.addContact(new_contact))
                {
                    Toast.makeText(this, "The contact already exist. Try to edit it.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    refeshList();
                }
            }
        }
        if (request == 2)
        {
            if (result == RESULT_OK)
            {
                Contact updated = data.getParcelableExtra("updated_contact");
                if (!contactCtrl.updateContact(updated))
                {
                    Toast.makeText(this, "The contact already exist. Try to edit it.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    refeshList();
                }
            }
        }
    }

    private void refeshList ()
    {
        allContacts = contactCtrl.getAllContacts();
        caa = new ContactArrayAdapter(this, allContacts);
        contact_lv.setAdapter(caa);
    }

    private Button add_button;
    private ListView contact_lv;
    private ContactArrayAdapter caa;
    private Context context = this;
    ArrayList<Contact> allContacts;
    ContactDBController contactCtrl;
}
