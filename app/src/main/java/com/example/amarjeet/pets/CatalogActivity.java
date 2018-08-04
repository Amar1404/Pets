package com.example.amarjeet.pets;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.amarjeet.pets.data.PetContract;

import data.PetDbHelper;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        /** Database helper that will provide us access to the database */
       // private PetDbHelper mDbHelper;

            // Setup FAB to open EditorActivity
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                    startActivity(intent);
                }
            });

            // To access our database, we instantiate our subclass of SQLiteOpenHelper
            // and pass the context, which is the current activity.
            // mDbHelper = new PetDbHelper(this);
            displayDatabaseInfo();

        }
                    /**
                 +     * Temporary helper method to display information in the onscreen TextView about the state of
                 +     * the pets database.
                 +     */
                            private void displayDatabaseInfo() {
                 // To access our database, we instantiate our subclass of SQLiteOpenHelp
                    // and pass the context, which is the current activity.
                            PetDbHelper mDbHelper = new PetDbHelper(this);

                    // Create and/or open a database to read from it
                        SQLiteDatabase db = mDbHelper.getReadableDatabase();

                       // Perform this raw SQL query "SELECT * FROM pets"
                        // to get a Cursor that contains all rows from the pets table.
                        Cursor cursor = db.rawQuery("SELECT * FROM " + PetContract.PetEntry.TABLE_NAME, null);
                    try {
                            // Display the number of rows in the Cursor (which reflects the number of rows in the
                          // pets table in the database).
                            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
                            displayView.setText("Number of rows in pets database table: " + cursor.getCount());
                        } finally {
                            // Always close the cursor when you're done reading from it. This releases all its
                            // resources and makes it invalid.
                           cursor.close();
                        }
        }

//        @Override
//        protected void onStart() {
//            super.onStart();
//            displayDatabaseInfo();
//        }
//
//        /**
//         * Temporary helper method to display information in the onscreen TextView about the state of
//         * the pets database.
//         */
//        private void displayDatabaseInfo() {
//            // Create and/or open a database to read from it
//            SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//            // Define a projection that specifies which columns from the database
//            // you will actually use after this query.
//            String[] projection = {
//                    PetContract.PetEntry._ID,
//                    PetContract.PetEntry.COLUMN_PET_NAME,
//                    PetContract.PetEntry.COLUMN_PET_BREED,
//                    PetContract.PetEntry.COLUMN_PET_GENDER,
//                    PetContract.PetEntry.COLUMN_PET_WEIGHT };
//
//            // Perform a query on the pets table
//            Cursor cursor = db.query(
//                    PetContract.PetEntry.TABLE_NAME,   // The table to query
//                    projection,            // The columns to return
//                    null,                  // The columns for the WHERE clause
//                    null,                  // The values for the WHERE clause
//                    null,                  // Don't group the rows
//                    null,                  // Don't filter by row groups
//                    null);                   // The sort order
//
//            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
//
//            try {
//                // Create a header in the Text View that looks like this:
//                //
//                // The pets table contains <number of rows in Cursor> pets.
//                // _id - name - breed - gender - weight
//                //
//                // In the while loop below, iterate through the rows of the cursor and display
//                // the information from each column in this order.
//                displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
//                displayView.append(PetContract.PetEntry._ID + " - " +
//                        PetContract.PetEntry.COLUMN_PET_NAME + " - " +
//                        PetContract.PetEntry.COLUMN_PET_BREED + " - " +
//                        PetContract.PetEntry.COLUMN_PET_GENDER + " - " +
//                        PetContract.PetEntry.COLUMN_PET_WEIGHT + "\n");
//
//                // Figure out the index of each column
//                int idColumnIndex = cursor.getColumnIndex(PetContract.PetEntry._ID);
//                int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
//                int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
//                int genderColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
//                int weightColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);
//
//                // Iterate through all the returned rows in the cursor
//                while (cursor.moveToNext()) {
//                    // Use that index to extract the String or Int value of the word
//                    // at the current row the cursor is on.
//                    int currentID = cursor.getInt(idColumnIndex);
//                    String currentName = cursor.getString(nameColumnIndex);
//                    String currentBreed = cursor.getString(breedColumnIndex);
//                    int currentGender = cursor.getInt(genderColumnIndex);
//                    int currentWeight = cursor.getInt(weightColumnIndex);
//                    // Display the values from each column of the current row in the cursor in the TextView
//                    displayView.append(("\n" + currentID + " - " +
//                            currentName + " - " +
//                            currentBreed + " - " +
//                            currentGender + " - " +
//                            currentWeight));
//                }
//            } finally {
//                // Always close the cursor when you're done reading from it. This releases all its
//                // resources and makes it invalid.
//                cursor.close();
//            }
//        }
//
//        /**
//         * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
//         */
//        private void insertPet() {
//            // Gets the database in write modemDbHelper;
//            SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//            // Create a ContentValues object where column names are the keys,
//            // and Toto's pet attributes are the values.
//            ContentValues values = new ContentValues();
//            values.put(PetContract.PetEntry.COLUMN_PET_NAME, "Toto");
//            values.put(PetContract.PetEntry.COLUMN_PET_BREED, "Terrier");
//            values.put(PetContract.PetEntry.COLUMN_PET_GENDER, PetContract.PetEntry.GENDER_MALE);
//            values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT, 7);
//
//            // Insert a new row for Toto in the database, returning the ID of that new row.
//            // The first argument for db.insert() is the pets table name.
//            // The second argument provides the name of a column in which the framework
//            // can insert NULL in the event that the ContentValues is empty (if
//            // this is set to "null", then the framework will not insert a row when
//            // there are no values).
//            // The third argument is the ContentValues object containing the info for Toto.
//            long newRowId = db.insert(PetContract.PetEntry.TABLE_NAME, null, values);
//        }
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu options from the res/menu/menu_catalog.xml file.
//            // This adds menu items to the app bar.
//            getMenuInflater().inflate(R.menu.menu_catalog, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            // User clicked on a menu option in the app bar overflow menu
//            switch (item.getItemId()) {
//                // Respond to a click on the "Insert dummy data" menu option
//                case R.id.action_insert_dummy_data:
//                    insertPet();
//                    displayDatabaseInfo();
//                    return true;
//                // Respond to a click on the "Delete all entries" menu option
//                case R.id.action_delete_all_entries:
//                    // Do nothing for now
//                    return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//    }
    }

