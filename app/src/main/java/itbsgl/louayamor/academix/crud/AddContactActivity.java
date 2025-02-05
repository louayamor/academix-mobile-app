package itbsgl.louayamor.academix.crud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import itbsgl.louayamor.academix.R;
import itbsgl.louayamor.academix.model.Contact;
import itbsgl.louayamor.academix.utils.DatabaseHelper;

public class AddContactActivity extends AppCompatActivity {

    EditText edusername, ednum;
    Button btnAdd, btnBack;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        btnAdd = findViewById(R.id.save_add);
        btnBack = findViewById(R.id.exit_add);
        edusername = findViewById(R.id.username_add);
        ednum = findViewById(R.id.phone_add);

        dbHelper = new DatabaseHelper(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edusername.getText().toString().trim();
                String num = ednum.getText().toString().trim();

                if (username.isEmpty() || num.isEmpty()) {
                    Toast.makeText(AddContactActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.addContact(username, num);
                    Toast.makeText(AddContactActivity.this, "Contact added successfully!", Toast.LENGTH_SHORT).show();

                    edusername.setText("");
                    ednum.setText("");
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes this activity
            }
        });
    }
}
