package itbsgl.louayamor.academix.crud;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import itbsgl.louayamor.academix.R;
import itbsgl.louayamor.academix.model.Contact;
import itbsgl.louayamor.academix.utils.DatabaseHelper;

public class MyRecyclerContactAdapter extends RecyclerView.Adapter<MyRecyclerContactAdapter.MyViewholder> {

    DatabaseHelper dbHelper;
    Context con;
    List<Contact> data;

    public MyRecyclerContactAdapter(Context con, List<Contact> data) {
        this.con = con;
        this.data = data;
    }

    @NonNull
    @Override
    public MyRecyclerContactAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf=LayoutInflater.from(con);
        View v = inf.inflate(R.layout.contact_list_item,  null);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerContactAdapter.MyViewholder holder, int position) {

        Contact c = data.get(position);
        holder.txtUsername.setText(c.getUsername());
        holder.txtPhone.setText(c.getNum());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView txtUsername, txtPhone;
        ImageView imgCall, imgEdit, imgDelete, btnAddContact;


        public MyViewholder(@NonNull View v) {
            super(v);
            txtUsername = v.findViewById(R.id.tvUsername);
            txtPhone = v.findViewById(R.id.tvPhone);
            imgCall = v.findViewById(R.id.imgCall);
            imgEdit = v.findViewById(R.id.imgEdit);
            imgDelete = v.findViewById(R.id.imgDelete);



            imgCall.setOnClickListener(view -> {

                int index = getAdapterPosition();
                Contact c = data.get(index);
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + c.getNum()));
                con.startActivity(callIntent);
            });

            imgDelete.setOnClickListener(view -> {
                int index = getAdapterPosition();
                if (index != RecyclerView.NO_POSITION) {
                    Contact c = data.get(index);
                    dbHelper.deleteContact(c.getUsername());
                    data.remove(index);
                    notifyItemRemoved(index);
                    notifyItemRangeChanged(index, data.size());
                }
            });

            imgEdit.setOnClickListener(view -> {
                int index = getAdapterPosition();
                if (index != RecyclerView.NO_POSITION) {
                    Contact c = data.get(index);
                    showEditDialog(c, index);
                }
            });

        }

        private void showEditDialog(Contact contact, int index) {
            AlertDialog.Builder builder = new AlertDialog.Builder(con);
            builder.setTitle("Edit Contact");

            LayoutInflater inflater = LayoutInflater.from(con);
            View dialogView = inflater.inflate(R.layout.activity_edit_contact, null);
            builder.setView(dialogView);

            EditText edUsername = dialogView.findViewById(R.id.edUsername);
            EditText edPhone = dialogView.findViewById(R.id.edPhone);

            edUsername.setText(contact.getUsername());
            edPhone.setText(contact.getNum());

            builder.setPositiveButton("Save", (dialog, which) -> {
                String newUsername = edUsername.getText().toString();
                String newPhone = edPhone.getText().toString();

                if (!newUsername.isEmpty() && !newPhone.isEmpty()) {
                    dbHelper.updateContact(contact.getId(), newUsername, newPhone);

                    contact.setUsername(newUsername);
                    contact.setNum(newPhone);
                    data.set(index, contact);
                    notifyItemChanged(index);
                    Toast.makeText(con, "Contact updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(con, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", null);
            builder.show();
        }

    }
}
