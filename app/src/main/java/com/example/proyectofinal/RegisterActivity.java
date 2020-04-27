package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static EditText etNameRegister,
            etLastnameFather,
            etLastnameMother,
            etAgeRegister,
            etEmailRegister,
            etPhone,
            etPassRegister;

    Spinner spinner;

    Button btnRegister;

    static String name,
            father,
            mother,
            email,
            password,
            school,
            age,
            phone;

    static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(this);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        etNameRegister = findViewById(R.id.etNameRegister);
        etLastnameFather = findViewById(R.id.etLastnameFather);
        etLastnameMother = findViewById(R.id.etLastnameMother);
        etAgeRegister = findViewById(R.id.etAgeRegister);
        etEmailRegister = findViewById(R.id.etEmailRegister);
        etPhone = findViewById(R.id.etPhone);
        etPassRegister = findViewById(R.id.etEmailRegister);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                name = etNameRegister.getText().toString();
                father = etLastnameFather.getText().toString();
                mother = etLastnameMother.getText().toString();
                age = etAgeRegister.getText().toString();
                phone = etPhone.getText().toString();
                email = etEmailRegister.getText().toString();
                password = etPassRegister.getText().toString();
                if(name.equals("")){
                    etNameRegister.setError("No puede estar vacio!");
                }
                if(father.equals("")){
                    etLastnameFather.setError("No puede estar vacio!");
                }
                if(age.equals("")){
                    etAgeRegister.setError("No puede estar vacio!");
                }
                if(email.equals("")){
                    etEmailRegister.setError("No puede estar vacio!");
                }
                if(password.equals("")){
                    etPassRegister.setError("No puede estar vacio!");
                }
                if(phone.equals("")){
                    etPhone.setError("No puede estar vacio!");
                }

                if (!name.equals("") && !father.equals("") && !age.equals("") &&
                        !email.equals("") && !password.equals("") && !etPhone.equals("")) {
                    //Checo si ya existe el mail

                    if(!dbHelper.getExistingMail(email).equals("")){
                        Toast.makeText(RegisterActivity.this, "Este mail ya esta registrado", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Users u = new Users(name, father, mother, email, school, Integer.parseInt(age), phone ,password);
                    System.out.println(u);
                    RegisterActivity.dbHelper.insertUser(u);
                    RegisterActivity.dbHelper.insertUserCredential(RegisterActivity.password);
                    //StartActivityApp
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        school = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, school, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
