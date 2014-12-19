package com.repairs.dcrackingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

/**
 * This class is used to edit and/or remove a client from the list and database.
 */
public class EditClientActivity extends Activity
{
    EditText clientName;
    EditText date;
    EditText address;
    EditText clientNumber;

    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        clientName = (EditText) findViewById( R.id.clientName );
        date = (EditText) findViewById( R.id.date );
        address = (EditText) findViewById( R.id.address );
        clientNumber = (EditText) findViewById( R.id.clientNumber );

        Intent theIntent = getIntent();

        String clientId = theIntent.getStringExtra("clientId");

        HashMap<String, String> clientList = dbTools.getClientInfo(clientId);

        if(clientList.size() != 0)
        {
            clientName.setText(clientList.get("clientName"));
            date.setText(clientList.get("date"));
            address.setText(clientList.get("address"));
            clientNumber.setText(clientList.get("clientNumber"));
        }
    }

    public void editClient(View view)
    {
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        clientName.setText(queryValuesMap.get("clientName"));
        date.setText(queryValuesMap.get("date"));
        address.setText(queryValuesMap.get("address"));
        clientNumber.setText(queryValuesMap.get("clientNumber"));

        Intent theIntent = getIntent();

        String clientId = theIntent.getStringExtra("clientId");

        queryValuesMap.put("clientId", clientId);
        queryValuesMap.put("clientName", clientName.getText().toString());
        queryValuesMap.put("date", date.getText().toString());
        queryValuesMap.put("address", address.getText().toString());
        queryValuesMap.put("clientNumber", clientNumber.getText().toString());

        dbTools.updateClient(queryValuesMap);

        this.callNewClientActivity(view);
    }

    public void removeClient(View view)
    {
        Intent theIntent = getIntent();

        String clientId = theIntent.getStringExtra("clientId");

        dbTools.deleteClient(clientId);

        this.callNewClientActivity(view);
    }

    public void callNewClientActivity(View view)
    {
        Intent theIntent = new Intent(getApplication(), NewClientActivity.class);

        startActivity(theIntent);
    }
}
