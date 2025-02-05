package itbsgl.louayamor.academix.crud;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import itbsgl.louayamor.academix.R;
import itbsgl.louayamor.academix.utils.DatabaseHelper;
import itbsgl.louayamor.academix.model.Contact;

public class DisplayContactsActivity extends AppCompatActivity {

    RecyclerView rv;
    DatabaseHelper dbHelper;
    List<Contact> contactList;
    MyRecyclerContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        rv = findViewById(R.id.rvContacts);
        dbHelper = new DatabaseHelper(this);

        // Fetch contacts from database
        contactList = dbHelper.getAllContacts();

        LinearLayoutManager manager = new LinearLayoutManager(DisplayContactsActivity.this,
                LinearLayoutManager.VERTICAL,
                true);
                rv.setLayoutManager(manager);

        // Set up custom adapter
        adapter = new MyRecyclerContactAdapter(this, contactList);
        rv.setAdapter(adapter);
    }
}
