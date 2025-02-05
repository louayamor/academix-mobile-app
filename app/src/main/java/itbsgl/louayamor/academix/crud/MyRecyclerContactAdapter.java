package itbsgl.louayamor.academix.crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import itbsgl.louayamor.academix.R;
import itbsgl.louayamor.academix.model.Contact;

public class MyRecyclerContactAdapter extends RecyclerView.Adapter<MyRecyclerContactAdapter.MyViewholder> {

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
        ImageView imgCall, imgEdit, imgDelete;

        public MyViewholder(@NonNull View v) {
            super(v);
            txtUsername = v.findViewById(R.id.tvUsername);
            txtPhone = v.findViewById(R.id.tvPhone);
            imgCall = v.findViewById(R.id.imgCall);
            imgEdit = v.findViewById(R.id.imgEdit);
            imgDelete = v.findViewById(R.id.imgDelete);



        }
    }
}
