package itbsgl.louayamor.academix.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import itbsgl.louayamor.academix.R;
import itbsgl.louayamor.academix.model.Contact;
import itbsgl.louayamor.academix.utils.DatabaseHelper;

public class DisplayContactsActivity extends AppCompatActivity {

    RecyclerView rv;
    DatabaseHelper dbHelper;
    List<Contact> contactList;
    MyRecyclerContactAdapter adapter;
    FloatingActionButton btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        rv = findViewById(R.id.rvContacts);
        btnAddContact = findViewById(R.id.btn_add_contact);
        dbHelper = new DatabaseHelper(this);

        // Fetch contacts from database
        contactList = dbHelper.getAllContacts();

        // Set up RecyclerView with LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        // Set up custom adapter
        adapter = new MyRecyclerContactAdapter(this, contactList);
        rv.setAdapter(adapter);

        // Handle Add Contact button click
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayContactsActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });
    }
}
