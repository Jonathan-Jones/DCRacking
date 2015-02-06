package com.repairs.dcrackingapp;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// SQLiteOpenHelper helps you open or create a database
public class DBTools  extends SQLiteOpenHelper
{
    // Context : provides access to application-specific resources and classes
    public DBTools(Context applicationContext)
    {
        // Call use the database or to create it
        super(applicationContext, "dcracking.db", null, 1);
    }

    // onCreate is called the first time the database is created

    public void onCreate(SQLiteDatabase database)
    {
        // How to create a table in SQLite
        // Make sure you don't put a ; at the end of the query

        String queryCreateClient = "CREATE TABLE CLIENT ( client_id INTEGER PRIMARY KEY autoincrement, client_name TEXT, " +
        "date_created DATE, client_address TEXT, client_number TEXT, user_created TEXT)";

        /*String queryCreateItem = "CREATE TABLE ITEM ( item_id INTEGER PRIMARY KEY autoincrement, client_id INTEGER, " +
                "before_image_1 TEXT, before_image_2 TEXT, repair_id TEXT, inspection_id TEXT, " +
                "slot_location TEXT, interim_image_1 TEXT, interim_image_2 TEXT, repair_category TEXT, " +
                "sub_category TEXT, after_image_1 TEXT, after_image_2 TEXT, customization TEXT, " +
                "date_created DATE)";*/

        // Executes the query provided as long as the query isn't a select
        // or if the query doesn't return any data

        database.execSQL(queryCreateClient);
        /*database.execSQL(queryCreateItem);*/
    }

    // onUpgrade is used to drop tables, add tables, or do anything
    // else it needs to upgrade
    // This is dropping the table to delete the data and then calling
    // onCreate to make an empty table

    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version)
    {
        String query = "DROP TABLE IF EXISTS CLIENT";

        // Executes the query provided as long as the query isn't a select
        // or if the query doesn't return any data
        database.execSQL(query);
        this.onCreate(database);
    }

    public void insertClient(HashMap<String, String> queryValues)
    {
        try
        {
            // Open a database for reading and writing
            SQLiteDatabase database = this.getWritableDatabase();

            // Stores key value pairs being the column name and the data
            // ContentValues data type is needed because the database
            // requires its data type to be passed
            ContentValues values = new ContentValues();

            values.put("client_name", queryValues.get("client_name"));
            values.put("date_created", queryValues.get("date_created"));
            values.put("client_address", queryValues.get("client_address"));
            values.put("client_number", queryValues.get("client_number"));
            values.put("user_created", "dcrackingapp");

            // Inserts the data in the form of ContentValues into the
            // table name provided
            database.insert("CLIENT", null, values);

            // Release the reference to the SQLiteDatabase object

            database.close();
        } // end try
        catch(Exception ex)
        {
            ex.getMessage();
        } // end catch()
    } // end insertClient()

    public int updateClient(HashMap<String, String> queryValues)
    {
        try
        {
            // Open a database for reading and writing
            SQLiteDatabase database = this.getWritableDatabase();

            // Stores key value pairs being the column name and the data
            ContentValues values = new ContentValues();

            values.put("client_name", queryValues.get("client_name"));
            values.put("date_created", queryValues.get("date_created"));
            values.put("client_address", queryValues.get("client_address"));
            values.put("client_number", queryValues.get("client_number"));
            values.put("user_created", "dcrackingapp");
            // update(TableName, ContentValueForTable, WhereClause, ArgumentForWhereClause)
            return database.update("CLIENT", values, "client_id= ?", new String[]{queryValues.get("client_id")});
        } // end try
        catch (Exception ex)
        {
            ex.getMessage();
            return 0;
        } // end catch()
    } // end updateClient()

    // Used to delete a contact with the matching contactId
    public void deleteClient(String client_id)
    {
        try
        {
            // Open a database for reading and writing
            SQLiteDatabase database = this.getWritableDatabase();

            String deleteQuery = "DELETE FROM CLIENT where client_id='" + client_id + "'";

            // Executes the query provided as long as the query isn't a select
            // or if the query doesn't return any data
            database.execSQL(deleteQuery);
        } // end try
        catch (Exception ex)
        {
            ex.getMessage();
        } // end catch()
    } // end deleteClient()

    public ArrayList<HashMap<String, String>> getAllClients()
    {
        // ArrayList that contains every row in the database
        // and each row key / value stored in a HashMap
        ArrayList<HashMap<String, String>> clientArrayList;
        clientArrayList = new ArrayList<HashMap<String, String>>();

        try
        {
            // Open a database for reading and writing
            SQLiteDatabase database = this.getWritableDatabase();

            String selectQuery = "SELECT * FROM CLIENT";

            // Cursor provides read and write access for the
            // data returned from a database query

            // rawQuery executes the query and returns the result as a Cursor
            Cursor cursor = database.rawQuery(selectQuery, null);

            // Move to the first row
            if (cursor.moveToFirst())
            {
                do
                {
                    HashMap<String, String> contactMap = new HashMap<String, String>();

                    // Store the key / value pairs in a HashMap
                    // Access the Cursor data by index that is in the same order
                    // as used when creating the table

                    contactMap.put("client_id", cursor.getString(0));
                    contactMap.put("client_name", cursor.getString(1));
                    contactMap.put("date_created", cursor.getString(2));
                    contactMap.put("client_address", cursor.getString(3));
                    contactMap.put("client_number", cursor.getString(4));
                    contactMap.put("user_created", cursor.getString(5));

                    clientArrayList.add(contactMap);
                } // end do
                while (cursor.moveToNext()); // Move Cursor to the next row
            } // end if()

            if (!cursor.isClosed()) cursor.close();
        } // end try
        catch (Exception ex)
        {
            ex.getMessage();
        } // end catch()

        // return client list
        return clientArrayList;
    } // end getAllClients()

    public HashMap<String, String> getClientInfo(String client_id)
    {
        HashMap<String, String> clientMap = new HashMap<String, String>();

        try
        {
            // Open a database for reading only
            SQLiteDatabase database = this.getReadableDatabase();

            String selectQuery = "SELECT * FROM CLIENT where client_id='" + client_id + "'";

            // rawQuery executes the query and returns the result as a Cursor
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst())
            {
                do
                {
                    clientMap.put("client_id", cursor.getString(0));
                    clientMap.put("client_name", cursor.getString(1));
                    clientMap.put("date_created", cursor.getString(2));
                    clientMap.put("client_address", cursor.getString(3));
                    clientMap.put("client_number", cursor.getString(4));
                    clientMap.put("user_created", cursor.getString(5));
                } // end do
                while (cursor.moveToNext());
            } // end if()

            if(!cursor.isClosed()) cursor.close();
        } // end try
        catch (Exception ex)
        {
            ex.getMessage();
        } // end catch()

        // return client info
        return clientMap;
    } // end getClientInfo()
}