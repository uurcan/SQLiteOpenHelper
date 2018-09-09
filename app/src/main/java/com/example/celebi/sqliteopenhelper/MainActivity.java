package com.example.celebi.sqliteopenhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText userName,password,oldName,newName,delete;
    MyDBHandler helper;
    Button btnAdd,btnDelete,btnShow,btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.editName);
        password = (EditText) findViewById(R.id.editPass);
        oldName = (EditText) findViewById(R.id.editText3);
        newName = (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);

        helper = new MyDBHandler(this);

    }
            public void addUser(View view){
            String t1 = userName.getText().toString();
            String t2 = password.getText().toString();
            if (t1.isEmpty() || t2.isEmpty()) {
                Message.message(getApplicationContext(), "Enter Both Name and Password");
            } else {
                long id = helper.insertData(t1, t2);
                if (id <= 0) {
                    Message.message(getApplicationContext(), "Insertion Unsuccessful");
                    userName.setText("");
                    password.setText("");
                } else {
                    Message.message(getApplicationContext(), "Insertion Successful");
                    userName.setText("");
                    password.setText("");
                }
            }
        }


            public void update(View v) {
                String u1 = oldName.getText().toString();
                String u2 = newName.getText().toString();

                if (u1.isEmpty() || u2.isEmpty()) {
                    Message.message(getApplicationContext(), "Empty Value");
                } else {
                    int a = helper.updateName(u1, u2);
                    if (a <= 0) {
                        Message.message(getApplicationContext(), "Could not find any item ");
                        oldName.setText("");
                        newName.setText("");
                    } else {
                        Message.message(getApplicationContext(), "Update Completed ");
                        oldName.setText("");
                        newName.setText("");
                    }
                }
            }
            public void delete(View v) {
                String uName = oldName.getText().toString();

                if (uName.isEmpty()) {
                    Message.message(getApplicationContext(), "Empty Value");
                } else {
                    int a = helper.delete(uName);
                    if (a <= 0) {
                        Message.message(getApplicationContext(), "Could not find any item ");
                        delete.setText("");
                    } else {
                        Message.message(getApplicationContext(), "Delete Completed ");
                        delete.setText("");
                    }
                }
            }
            public void viewData(View view)
            {
        String data = helper.getData();
        Message.message(this,data);
            }
}
