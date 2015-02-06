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
    EditText client_name;
    EditText date_created;
    EditText client_address;
    EditText client_number;

    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_edit);

        client_name = (EditText) findViewById( R.id.client_name );
        date_created = (EditText) findViewById( R.id.date_created );
        client_address = (EditText) findViewById( R.id.client_address );
        client_number = (EditText) findViewById( R.id.client_number );

        Intent theIntent = getIntent();
        String clientId = theIntent.getStringExtra("client_id");

        HashMap<String, String> clientList = dbTools.getClientInfo(clientId);

        if(clientList.size() != 0)
        {
            client_name.setText(clientList.get("client_name"));
            date_created.setText(clientList.get("date_created"));
            client_address.setText(clientList.get("client_address"));
            client_number.setText(clientList.get("client_number"));
        }
    }

    public void editClient(View view)
    {
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        client_name = (EditText) findViewById( R.id.client_name );
        date_created = (EditText) findViewById( R.id.date_created );
        client_address = (EditText) findViewById( R.id.client_address );
        client_number = (EditText) findViewById( R.id.client_number );

        Intent theIntent = getIntent();
        String client_id = theIntent.getStringExtra("client_id");

        queryValuesMap.put("client_id", client_id);
        queryValuesMap.put("client_name", client_name.getText().toString());
        queryValuesMap.put("date_created", date_created.getText().toString());
        queryValuesMap.put("client_address", client_address.getText().toString());
        queryValuesMap.put("client_number", client_number.getText().toString());
        queryValuesMap.put("user_created", "dcrackingapp");

        dbTools.updateClient(queryValuesMap);

        this.callNewClientActivity(view);
    }

    public void removeClient(View view)
    {
        Intent theIntent = getIntent();
        String client_id = theIntent.getStringExtra("client_id");

        dbTools.deleteClient(client_id);

        this.callNewClientActivity(view);
    }

    public void callNewClientActivity(View view)
    {
        Intent theIntent = new Intent(getApplication(), ClientActivity.class);

        startActivity(theIntent);
    }
}
