package com.snehpandya.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.snehpandya.inventory.data.InventoryContract.InventoryEntry;

public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String TAG = InventoryDbHelper.class.getSimpleName();
    public static final String DATABASE_NAME = "inventory.db";
    public static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_ITEM_ENTRY =
                "CREATE TABLE " + InventoryEntry.TABLE_NAME + " (" +
                        InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        InventoryEntry.COLUMN_ITEM_NAME + " TEXT, " +
                        InventoryEntry.COLUMN_ITEM_PRICE + " INTEGER, " +
                        InventoryEntry.COLUMN_ITEM_QUANTITY + " INTEGER, " +
                        InventoryEntry.COLUMN_ITEM_IMAGE + " TEXT" + ")";
        db.execSQL(SQL_CREATE_INVENTORY_ITEM_ENTRY);
        Log.d(TAG, SQL_CREATE_INVENTORY_ITEM_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_INVENTORY_ITEM_ENTRY =
                "DELETE FROM " + InventoryEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_INVENTORY_ITEM_ENTRY);
    }
}