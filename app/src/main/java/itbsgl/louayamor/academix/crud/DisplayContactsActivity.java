package itbsgl.louayamor.academix.crud;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import itbsgl.louayamor.academix.R;
import itbsgl.louayamor.academix.utils.DatabaseHelper;
import itbsgl.louayamor.academix.model.Contact;

public class DisplayContactsActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        listView = findViewById(R.id.lvContacts);
        dbHelper = new DatabaseHelper(this);

        List<Contact> contactList = dbHelper.getAllContacts();
        List<String> contactStrings = new ArrayList<>();

        for (Contact contact : contactList) {
            contactStrings.add(contact.getUsername() + " - " + contact.getNum());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactStrings);
        listView.setAdapter(adapter);
    }
}
