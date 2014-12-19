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
    EditText clientName;
    EditText date;
    EditText address;
    EditText clientNumber;

    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_client);

        clientName = (EditText) findViewById( R.id.clientName );
        date = (EditText) findViewById( R.id.date );
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            date.setText(sdf.format(new Date()));
        address = (EditText) findViewById( R.id.address );
        clientNumber = (EditText) findViewById( R.id.clientNumber );
    }

    public void addNewClient(View view)
    {
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        queryValuesMap.put("clientName", clientName.getText().toString());
        queryValuesMap.put("date", date.getText().toString());
        queryValuesMap.put("address", address.getText().toString());
        queryValuesMap.put("clientNumber", clientNumber.getText().toString());

        dbTools.insertClient(queryValuesMap);

        this.callNewClientActivity(view);
    }

    public void callNewClientActivity(View view)
    {
        Intent theIntent = new Intent(getApplication(), NewClientActivity.class);

        startActivity(theIntent);
    }
}
