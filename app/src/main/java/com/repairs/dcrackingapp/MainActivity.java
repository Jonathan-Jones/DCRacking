package com.repairs.dcrackingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the createNewJob button */
    public void createNewJob(View view)
    {
        Intent intent = new Intent(this, NewJobActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the createRepair button */
    public void createRepair(View view)
    {
        Intent intent = new Intent(this, CreateRepairActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the editRepair button */
    public void editRepair(View view)
    {
        Intent intent = new Intent(this, EditRepairActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the appSettings button */
    public void appSettings(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the uploadWork button */
    public void uploadWork(View view)
    {
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the companyInfo button */
    public void companyInfo(View view)
    {
        Intent intent = new Intent(this, CompanyActivity.class);
        startActivity(intent);
    }
}
