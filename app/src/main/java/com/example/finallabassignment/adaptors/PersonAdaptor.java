package com.example.finallabassignment.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finallabassignment.MainActivity;
import com.example.finallabassignment.R;
import com.example.finallabassignment.activities.EditActivity;
import com.example.finallabassignment.constants.Constants;
import com.example.finallabassignment.database.AppDatabase;
import com.example.finallabassignment.model.Person;

import java.util.List;


public class PersonAdaptor extends RecyclerView.Adapter<PersonAdaptor.MyViewHolder> {
    private Context context;
    private List<Person> mPersonList;

    public PersonAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdaptor.MyViewHolder myViewHolder, final int i) {
        myViewHolder.name.setText(mPersonList.get(i).getName());
        myViewHolder.lastname.setText(mPersonList.get(i).getlastname());
        myViewHolder.number.setText(mPersonList.get(i).getNumber());
        myViewHolder.email.setText(mPersonList.get(i).getaddress());
        myViewHolder.city.setText(mPersonList.get(i).getCity());

        myViewHolder.ll_laycard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPersonList == null) {
            return 0;
        }
        return mPersonList.size();

    }

    public void setTasks(List<Person> personList) {
        mPersonList = personList;
        notifyDataSetChanged();
    }

    public List<Person> getTasks() {

        return mPersonList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, lastname, email, number, city;
        ImageView editImage;
        AppDatabase mDb;
        LinearLayout ll_laycard;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.contact_name);
            ll_laycard = itemView.findViewById(R.id.list_layout);
            lastname = itemView.findViewById(R.id.contact_lastname);
            email = itemView.findViewById(R.id.contact_email);
            number = itemView.findViewById(R.id.contact_number);
            city = itemView.findViewById(R.id.contact_city);
            editImage = itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = mPersonList.get(getAdapterPosition()).getId();
                    Intent i = new Intent(context, EditActivity.class);
                    i.putExtra(Constants.UPDATE_Person_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}
