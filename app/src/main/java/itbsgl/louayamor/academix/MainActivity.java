package itbsgl.louayamor.academix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import itbsgl.louayamor.academix.crud.DisplayContactsActivity;
import itbsgl.louayamor.academix.utils.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private EditText edName, edPassword;
    private Button btnLogin, btnExit;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edname_auth);
        edPassword = findViewById(R.id.edpasswd_auth);
        btnLogin = findViewById(R.id.btnlogin_auth);
        btnExit = findViewById(R.id.btnexit_auth);

        dbHelper = new DatabaseHelper(this);

        //dbHelper.clearContacts();

        //dbHelper.addUser("louay", "louay");
        //dbHelper.addUser("louai", "louai");
        //dbHelper.addUser("louey", "louey");
        //dbHelper.addUser("louei", "louei");
        //dbHelper.addUser("lou", "lou");
        //dbHelper.addUser("amor", "amor");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edName.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if user exists
                    if (dbHelper.checkUser(username, password)) {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, DisplayContactsActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}