package itbsgl.louayamor.academix.crud;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import itbsgl.louayamor.academix.R;
import itbsgl.louayamor.academix.utils.DatabaseHelper;
import itbsgl.louayamor.academix.model.Contact;

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> contactList;
    private DatabaseHelper dbHelper;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        this.dbHelper = new DatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);
        }

        Contact contact = contactList.get(position);

        TextView txtUsername = convertView.findViewById(R.id.tvUsername);
        TextView txtPhone = convertView.findViewById(R.id.tvPhone);
        ImageView imgCall = convertView.findViewById(R.id.imgCall);
        ImageView imgEdit = convertView.findViewById(R.id.imgEdit);
        ImageView imgDelete = convertView.findViewById(R.id.imgDelete);

        txtUsername.setText(contact.getUsername());
        txtPhone.setText(contact.getNum());

        imgCall.setOnClickListener(view -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + contact.getNum()));
            context.startActivity(callIntent);
        });

        // Edit button action (Open AddContactActivity for editing)
        imgEdit.setOnClickListener(view -> {
            Intent intent = new Intent(context, AddContactActivity.class);
            intent.putExtra("contact_id", position); // Send contact position for updating
            context.startActivity(intent);
        });

        // Delete button action
        imgDelete.setOnClickListener(view -> {
            dbHelper.deleteContact(contact.getUsername());
            contactList.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
