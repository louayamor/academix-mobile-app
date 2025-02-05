package itbsgl.louayamor.academix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import itbsgl.louayamor.academix.crud.AddContactActivity;
import itbsgl.louayamor.academix.crud.DisplayContacts;
import itbsgl.louayamor.academix.model.Contact;

public class HomeActivity extends AppCompatActivity {

    public static  ArrayList<Contact> data = new ArrayList<Contact>();
    Button btnAdd,btnDsiplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);

        btnAdd = findViewById(R.id.add_H);
        btnDsiplay = findViewById(R.id.display_home);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(HomeActivity.this, AddContactActivity.class);
                startActivity(i);
            }
        });

        btnDsiplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent();//action a lancer
                i.setAction(Intent.ACTION_DIAL); //lancer la numerotation
                i.setData(Uri.parse("tel:"+"12345678"));
                startActivity(i);*/


                Intent intent =new Intent(HomeActivity.this, DisplayContacts.class);
                startActivity(intent);



            }
        });
    }
}