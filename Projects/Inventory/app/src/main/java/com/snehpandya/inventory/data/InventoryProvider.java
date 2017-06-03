package com.snehpandya.inventory.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.snehpandya.inventory.data.InventoryContract.InventoryEntry;

public class InventoryProvider extends ContentProvider {
    public static final String TAG = InventoryProvider.class.getSimpleName();
    private static final int INVENTORY = 100;
    private static final int INVENTORY_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private InventoryDbHelper mInventoryDbHelper;

    static {
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, INVENTORY);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", INVENTORY_ID);
    }

    @Override
    public boolean onCreate() {
        mInventoryDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mInventoryDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return insertInventoryItem(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not support for " + uri);
        }
    }

    private Uri insertInventoryItem(Uri uri, ContentValues values) {
        String itemName = values.getAsString(InventoryEntry.COLUMN_ITEM_NAME);
        if (itemName == null) {
            throw new IllegalArgumentException("Inventory item requires Name");
        }
        Integer itemPrice = values.getAsInteger(InventoryEntry.COLUMN_ITEM_PRICE);
        if (itemPrice == null || itemPrice < 0) {
            throw new IllegalArgumentException("Inventory item requires Price");
        }
        Integer itemQuantity = values.getAsInteger(InventoryEntry.COLUMN_ITEM_QUANTITY);
        if (itemQuantity == null || itemQuantity < 0) {
            throw new IllegalArgumentException("Inventory item requires Quantity");
        }
        String itemImage = values.getAsString(InventoryEntry.COLUMN_ITEM_IMAGE);
        if (itemImage == null) {
            throw new IllegalArgumentException("Inventory item requires Image");
        }
        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();
        long id = database.insert(InventoryEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(TAG, "Failed to insert row for" + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return updateInventoryItem(uri, values, selection, selectionArgs);
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateInventoryItem(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateInventoryItem(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(InventoryEntry.COLUMN_ITEM_NAME)) {
            String itemName = values.getAsString(InventoryEntry.COLUMN_ITEM_NAME);
            if (itemName == null) {
                throw new IllegalArgumentException("Inventory item requires Name");
            }
        }
        if (values.containsKey(InventoryEntry.COLUMN_ITEM_PRICE)) {
            Integer itemPrice = values.getAsInteger(InventoryEntry.COLUMN_ITEM_PRICE);
            if (itemPrice == null || itemPrice < 0) {
                throw new IllegalArgumentException("Inventory item requires Price");
            }
        }
        if (values.containsKey(InventoryEntry.COLUMN_ITEM_QUANTITY)) {
            Integer itemQuantity = values.getAsInteger(InventoryEntry.COLUMN_ITEM_QUANTITY);
            if (itemQuantity == null || itemQuantity < 0) {
                throw new IllegalArgumentException("Inventory item requires Quantity");
            }
        }
        if (values.containsKey(InventoryEntry.COLUMN_ITEM_IMAGE)) {
            String itemImage = values.getAsString(InventoryEntry.COLUMN_ITEM_IMAGE);
            if (itemImage == null) {
                throw new IllegalArgumentException("Inventory item requires Image");
            }
        }
        if (values.size() == 0) {
            return 0;
        }
        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();
        int rowUpdated = database.update(InventoryEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowUpdated != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mInventoryDbHelper.getWritableDatabase();
        int rowDeleted;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                rowDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case INVENTORY_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + match);
        }
    }
}