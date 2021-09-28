package com.example.fullbackendarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fullbackendarchitecture.model.Contact;
import com.example.fullbackendarchitecture.model.ContactViewModel;

public class NewContact extends AppCompatActivity {

    private EditText enterName;
    private EditText enterNumber;
    private Button save;
    private ContactViewModel contactViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        ContactViewModel contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this
                .getApplication())
                .create(ContactViewModel.class);

        enterName = findViewById(R.id.name);
        enterNumber = findViewById(R.id.number);
        save = findViewById(R.id.saveButton);

        save.setOnClickListener(view -> {
            if(TextUtils.isEmpty(enterName.getText()) && TextUtils.isEmpty(enterNumber.getText())){
                Contact contact = new Contact(enterName.getText().toString(), enterNumber.getText().toString());
                ContactViewModel.insert(contact);
            } else {
                Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
            }

        });


    }
}