package com.marishkakravchenko.filestorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    Button saveBtn,loadBtn;
    TextView responseText;
    EditText myInputText;

    String data;
    private String file = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();

        saveBtn=(Button)findViewById(R.id.button1);
        loadBtn=(Button)findViewById(R.id.button2);

        myInputText=(EditText)findViewById(R.id.editText1);
        responseText=(TextView)findViewById(R.id.response_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data = myInputText.getText().toString();

                try {
                    FileOutputStream fOut = openFileOutput(file, Context.MODE_PRIVATE);
                    fOut.write(data.getBytes());
                    fOut.close();
                    Toast.makeText(getBaseContext(),"File saved successfully!",Toast.LENGTH_SHORT).show();
                }

                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream fin = openFileInput(file);
                    int c;
                    String temp="";

                    while( (c = fin.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }
                    responseText.setText(temp);
                    Toast.makeText(getBaseContext(),"File read successfully!",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                }
            }
        });
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
