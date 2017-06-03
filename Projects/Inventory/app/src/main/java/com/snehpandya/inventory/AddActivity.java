package com.snehpandya.inventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.snehpandya.inventory.data.InventoryContract.InventoryEntry;

public class AddActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int EXISTING_INVENTORY_LOADER = 0;
    private static final int SELECT_PICTURE = 100;
    private EditText mItemName;
    private EditText mItemPrice;
    private EditText mItemQuantity;
    private Button mAddInventoryItemButton;
    private Button mDeleteInventoryItemButton;
    private Button mUploadInventoryImageButton;
    private Uri mCurrentInventoryItemUri;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Intent intent = getIntent();
        mCurrentInventoryItemUri = intent.getData();
        mAddInventoryItemButton = (Button) findViewById(R.id.add_item);
        mDeleteInventoryItemButton = (Button) findViewById(R.id.delete_item);
        mUploadInventoryImageButton = (Button) findViewById(R.id.upload_button);

        if (mCurrentInventoryItemUri == null) {
            setTitle("Add Inventory");
            mAddInventoryItemButton.setText(R.string.add_item);
            mDeleteInventoryItemButton.setVisibility(View.INVISIBLE);
            selectedImageUri = null;
        } else {
            setTitle("Edit Inventory");
            mAddInventoryItemButton.setText(R.string.update_item);
            getLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);
        }

        mItemName = (EditText) findViewById(R.id.title_edit_text);
        mItemPrice = (EditText) findViewById(R.id.price_edit_text);
        mItemQuantity = (EditText) findViewById(R.id.quantity_edit_text);

        mAddInventoryItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInventoryItem();
            }
        });
        mDeleteInventoryItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmation();
            }
        });
        mUploadInventoryImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                ((ImageView) findViewById(R.id.item_image_view)).setImageURI(selectedImageUri);
            }
        }
    }

    private void showDeleteConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this inventory item?");
        builder.setPositiveButton(R.string.delete_item, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteInventory();
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteInventory() {
        if (mCurrentInventoryItemUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentInventoryItemUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, getString(R.string.error_deleting_item),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.delete_item_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveInventoryItem() {
        String inventoryItemNameString = mItemName.getText().toString().trim();
        String inventoryItemPriceString = mItemPrice.getText().toString().trim();
        String inventoryItemQuantityString = mItemQuantity.getText().toString().trim();

        if (inventoryItemNameString.isEmpty()) {
            mItemName.setError("Item Name Required");
            return;
        }
        if (inventoryItemPriceString.isEmpty()) {
            mItemPrice.setError("Item Price Required");
            return;
        }
        if (inventoryItemQuantityString.isEmpty()) {
            mItemQuantity.setError("Item Quantity Required");
            return;
        }
        try {
            int itemPrice = Integer.parseInt(inventoryItemPriceString);
        } catch (NumberFormatException e) {
            mItemPrice.setError("Please Enter Valid Number");
            return;
        }
        try {
            int itemQuantity = Integer.parseInt(inventoryItemQuantityString);
        } catch (NumberFormatException e) {
            mItemQuantity.setError("Please Enter Valid Number");
            return;
        }
        if (selectedImageUri == null) {
            Toast.makeText(this, R.string.choose_image, Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryEntry.COLUMN_ITEM_NAME, inventoryItemNameString);
        contentValues.put(InventoryEntry.COLUMN_ITEM_PRICE, inventoryItemPriceString);
        contentValues.put(InventoryEntry.COLUMN_ITEM_QUANTITY, inventoryItemQuantityString);
        contentValues.put(InventoryEntry.COLUMN_ITEM_IMAGE, selectedImageUri.toString().trim());

        if (mCurrentInventoryItemUri == null) {
            Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, contentValues);

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.save_item_error),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.save_item_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(mCurrentInventoryItemUri, contentValues, null, null);
            if (rowsAffected == 0) {
                Toast.makeText(this, getString(R.string.update_item_error),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.update_item_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_ITEM_NAME,
                InventoryEntry.COLUMN_ITEM_PRICE,
                InventoryEntry.COLUMN_ITEM_QUANTITY,
                InventoryEntry.COLUMN_ITEM_IMAGE};

        return new CursorLoader(this,
                mCurrentInventoryItemUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int inventoryItemNameColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_ITEM_NAME);
            int inventoryItemPriceColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_ITEM_PRICE);
            int inventoryItemQuantityColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_ITEM_QUANTITY);
            int inventoryItemImageColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_ITEM_IMAGE);

            String itemName = cursor.getString(inventoryItemNameColumnIndex);
            int itemPrice = cursor.getInt(inventoryItemPriceColumnIndex);
            int itemQuantity = cursor.getInt(inventoryItemQuantityColumnIndex);
            String itemImageString = cursor.getString(inventoryItemImageColumnIndex);

            mItemName.setText(itemName);
            mItemPrice.setText(Integer.toString(itemPrice));
            mItemQuantity.setText(Integer.toString(itemQuantity));
            if (itemImageString == null)
                Toast.makeText(this, R.string.choose_image, Toast.LENGTH_SHORT).show();
            else {
                selectedImageUri = Uri.parse(itemImageString);
                ((ImageView) findViewById(R.id.item_image_view)).setImageURI(selectedImageUri);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mItemName.setText("");
        mItemPrice.setText("");
        mItemQuantity.setText("");
    }
}