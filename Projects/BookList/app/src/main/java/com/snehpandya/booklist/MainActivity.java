package com.snehpandya.booklist;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String mString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final boolean netStatus = isNetAvailable();
        Button search = (Button) findViewById(R.id.search_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mString = ((EditText) findViewById(R.id.search)).getText().toString().replace(" ", "");
                if (netStatus) {
                    if (mString.equals("")) {
                        Toast.makeText(MainActivity.this, "Enter Book Name", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent sendUserInput = new Intent(MainActivity.this, BookList.class);
                        sendUserInput.putExtra("input", mString);
                        ((EditText) findViewById(R.id.search)).setText("");
                        startActivity(sendUserInput);
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, NoBook.class);
                    startActivity(intent);
                }
            }
        });

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    private boolean isNetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
