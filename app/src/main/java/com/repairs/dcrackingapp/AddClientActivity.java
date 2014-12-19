package com.repairs.dcrackingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * This class is used to add a new client to the list and database.
 */
public class AddClientActivity extends Activity
{
    EditText client_name;
    EditText date_created;
    EditText client_address;
    EditText client_number;

    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_client);

        client_name = (EditText) findViewById( R.id.client_name );
        date_created = (EditText) findViewById( R.id.date_created );
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            date_created.setText(sdf.format(new Date()));
        client_address = (EditText) findViewById( R.id.client_address );
        client_number = (EditText) findViewById( R.id.client_number );
    }

    public void addNewClient(View view)
    {
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        queryValuesMap.put("client_name", client_name.getText().toString());
        queryValuesMap.put("date_created", date_created.getText().toString());
        queryValuesMap.put("client_address", client_address.getText().toString());
        queryValuesMap.put("client_number", client_number.getText().toString());

        dbTools.insertClient(queryValuesMap);

        this.callNewClientActivity(view);
    }

    public void callNewClientActivity(View view)
    {
        Intent theIntent = new Intent(getApplication(), NewClientActivity.class);

        startActivity(theIntent);
    }
}
