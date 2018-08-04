package com.example.amarjeet.pets.data;

import android.provider.BaseColumns;

import java.net.PortUnreachableException;

/**
 * Created by amarjeet on 2/10/18.
 */

public final class PetContract {
    private PetContract() {}
    public final static class PetEntry implements BaseColumns {
        public static final String TABLE_NAME="pet";
        public static final String _ID= BaseColumns._ID;
        public static final String COLUMN_PET_NAME ="Name";
        public static final String COLUMN_PET_WEIGHT="Weight";
        public static final String COLUMN_PET_GENDER ="Gender";
        public static final String COLUMN_PET_BREED ="Breed";


        public static final int GENDER_UNKNOWN =0;
        public static final int GENDER_MALE =1;
        public static final int GENDER_FEMALE =2;


    }
}
