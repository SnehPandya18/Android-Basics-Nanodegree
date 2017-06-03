package com.snehpandya.inventory;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.snehpandya.inventory.data.InventoryContract.InventoryEntry;

public class ItemDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private Uri mCurrentItemUri;
    private static final int EXISTING_INVENTORY_LOADER = 0;
    private TextView mItemNameTextView;
    private ImageView mItemImageView;
    private TextView mItemPriceTextView;
    private TextView mItemQuantityTextView;
    private Button mIncrementButton;
    private Button mDecrementButton;
    private Button mItemOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        final Intent intent = getIntent();
        String itemIdString = intent.getStringExtra(InventoryCursorAdapter.ITEM_INDEX);
        long itemId = Long.parseLong(itemIdString);

        mItemNameTextView = (TextView) findViewById(R.id.inventory_item_name_view);
        mItemImageView = (ImageView) findViewById(R.id.inventory_item_image_view);
        mItemPriceTextView = (TextView)findViewById(R.id.inventory_item_price_text_view);
        mItemQuantityTextView = (TextView) findViewById(R.id.inventory_item_quantity_text_view);
        mIncrementButton = (Button) findViewById(R.id.inventory_item_increment);
        mDecrementButton = (Button) findViewById(R.id.inventory_item_decrement);
        mItemOrderButton = (Button) findViewById(R.id.inventory_item_order_button);

        mCurrentItemUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI,itemId);
        mIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemQuantity = Integer.parseInt(mItemQuantityTextView.getText().toString());
                itemQuantity += 1;
                mItemQuantityTextView.setText(itemQuantity +"");
                ContentValues values = new ContentValues();
                values.put(InventoryEntry.COLUMN_ITEM_QUANTITY,itemQuantity);
                getContentResolver().update(mCurrentItemUri,values,null,null);
            }
        });
        mDecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemQuantity = Integer.parseInt(mItemQuantityTextView.getText().toString());
                itemQuantity -= 1;
                if(itemQuantity == 0)
                    Toast.makeText(ItemDetailActivity.this, R.string.max
                            ,Toast.LENGTH_SHORT).show();
                else {
                    mItemQuantityTextView.setText(itemQuantity + "");
                    ContentValues values = new ContentValues();
                    values.put(InventoryEntry.COLUMN_ITEM_QUANTITY, itemQuantity);
                    getContentResolver().update(mCurrentItemUri,values,null,null);
                }
            }
        });
        mItemOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Urgent Required Quantity for inventory item");
                intent.putExtra(Intent.EXTRA_TEXT, "Required quantity for : \n" +
                        ((TextView) findViewById(R.id.inventory_item_name_view)).getText().toString().trim() +
                        "\nQuantity : " + ((TextView) findViewById(R.id.inventory_item_quantity_text_view))
                        .getText().toString().trim() +
                        "\n\n\nFrom : PSneh Inventory Pvt. Ltd.");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        getLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);
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
                mCurrentItemUri,
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
            final int inventoryItemQuantityColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_ITEM_QUANTITY);
            int inventoryItemImageColumnIndex = cursor.getColumnIndex(InventoryEntry.
                    COLUMN_ITEM_IMAGE);

            String itemName = cursor.getString(inventoryItemNameColumnIndex);
            int itemPrice = cursor.getInt(inventoryItemPriceColumnIndex);
            int itemQuantity = cursor.getInt(inventoryItemQuantityColumnIndex);
            String itemImageString = cursor.getString(inventoryItemImageColumnIndex);

            mItemNameTextView.setText(itemName);
            mItemPriceTextView.setText(Integer.toString(itemPrice));
            mItemQuantityTextView.setText(Integer.toString(itemQuantity));
            if (itemImageString == null)
                Toast.makeText(this, R.string.image_fail, Toast.LENGTH_SHORT).show();
            else {
                mItemImageView.setImageURI(Uri.parse(itemImageString));
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mItemNameTextView.setText("");
        mItemPriceTextView.setText("");
        mItemQuantityTextView.setText("");
        mItemImageView.setImageURI(null);
    }
}