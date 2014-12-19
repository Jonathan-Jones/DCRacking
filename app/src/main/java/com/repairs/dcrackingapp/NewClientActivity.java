package com.repairs.dcrackingapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to add a new client to the list and database.
 */
public class NewClientActivity extends ListActivity
{
    TextView client_id;

    DBTools dbTools = new DBTools(this);

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        ArrayList<HashMap<String, String>> clientList = dbTools.getAllClients();

        if(clientList.size() != 0)
        {
            ListView listView = this.getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    client_id = (TextView) view.findViewById(R.id.client_id);

                    String clientIdValue = client_id.getText().toString();

                    Intent theIntent = new Intent(getApplication(), EditClientActivity.class);

                    theIntent.putExtra("client_id", clientIdValue);

                    startActivity(theIntent);
                }
            });

            ListAdapter adapter = new SimpleAdapter(
                    NewClientActivity.this, clientList, R.layout.activity_client_entry,
                    new String[] {"client_id", "client_name", "client_number"},
                    new int[] {R.id.client_id, R.id.client_name, R.id.client_number});

            setListAdapter(adapter);
        }
    }

    public void showAddClient(View view)
    {
        Intent theIntent = new Intent(getApplication(), AddClientActivity.class);

        startActivity(theIntent);
    }
}







