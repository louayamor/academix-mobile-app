package itbsgl.louayamor.academix.crud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    SearchView edtSearch;
    Button btnSortContacts; // Sort button
    boolean isSortedByDate = false; // Toggle state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        rv = findViewById(R.id.rvContacts);
        btnAddContact = findViewById(R.id.btn_add_contact);
        edtSearch = findViewById(R.id.searchView);
        btnSortContacts = findViewById(R.id.btn_sort_contacts); // Initialize sort button
        dbHelper = new DatabaseHelper(this);

        contactList = dbHelper.getAllContacts();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        // Set up custom adapter
        adapter = new MyRecyclerContactAdapter(this, contactList);
        rv.setAdapter(adapter);

        // Handle Add Contact button click
        btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayContactsActivity.this, AddContactActivity.class);
            startActivity(intent);
        });

        // Handle Search functionality with SearchView
        edtSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Contact> filteredContacts = dbHelper.searchContacts(newText);
                adapter.updateData(filteredContacts);
                return true;
            }
        });

        // Handle sorting when sort button is clicked
        btnSortContacts.setOnClickListener(v -> toggleSortByDate());
    }

    private void toggleSortByDate() {
        if (isSortedByDate) {
            contactList = dbHelper.getAllContacts();  // Load unsorted contacts
            isSortedByDate = false;
        } else {
            contactList = dbHelper.getAllContactsSortedByDate();  // Load sorted contacts
            isSortedByDate = true;
        }

        adapter.updateData(contactList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactList = dbHelper.getAllContacts();
        adapter.updateData(contactList);
    }
}
