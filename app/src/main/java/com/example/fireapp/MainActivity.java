package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edit_name = findViewById(R.id.txtInPut);
        final EditText edit_position = findViewById(R.id.txtPosition);
        Button add = findViewById(R.id.btnAdd);
        Button update = findViewById(R.id.btnUpdate);
        Button delete = findViewById(R.id.btnDelete);
        DAOEmployee dao = new DAOEmployee();
        Button open = findViewById(R.id.btn_Open);

        add.setOnClickListener(v->{
            Employee emp = new Employee(edit_name.getText().toString(),edit_position.getText().toString());
            dao.add(emp).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name",edit_name.getText().toString());
            hashMap.put("position", edit_position.getText().toString());
            dao.update("-MnOXZAQsLeQZWeBO5cD", hashMap).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        update.setOnClickListener(view->{
            HashMap<String, Object> hashMapp = new HashMap<>();
            hashMapp.put("name",edit_name.getText().toString());
            hashMapp.put("position", edit_position.getText().toString());
            dao.update("-MnOXZAQsLeQZWeBO5cD", hashMapp).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        delete.setOnClickListener(ve->{
            dao.remove("-MnOXZAQsLeQZWeBO5cD").addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Record is removed", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        open.setOnClickListener(v->
        {
            Intent intent =new Intent(MainActivity.this, RVactivity.class);
            startActivity(intent);
        });
    }
}