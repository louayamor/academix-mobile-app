package itbsgl.louayamor.academix;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnExit, btnLogin;
    private EditText edName, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        btnExit = findViewById(R.id.btnexit_auth);
        btnLogin = findViewById(R.id.btnlogin_auth);
        edName = findViewById(R.id.edname_auth);
        edPassword = findViewById(R.id.edpasswd_auth);
    }

    private void setupListeners() {
        btnExit.setOnClickListener(view -> finish());

        btnLogin.setOnClickListener(view -> {
            String name = edName.getText().toString();
            String password = edPassword.getText().toString();

            if (name.equals("louay") && password.equals("louay")) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}