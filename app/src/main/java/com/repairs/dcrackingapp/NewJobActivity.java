package com.repairs.dcrackingapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jonathan on 10/6/2014.
 */
public class NewJobActivity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        EditText editText = (EditText) findViewById( R.id.new_job_date_edit_text );
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        editText.setText(sdf.format(new Date()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
