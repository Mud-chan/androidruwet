package com.pramudya.apidatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import model.User;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, List<User> objects){
        super (context, 0, objects);
    }
    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
        User user = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from((getContext())).inflate(R.layout.item_user, parent, false);
        }

        TextView txtemail = convertView.findViewById(R.id.email);
        TextView txtnama = convertView.findViewById(R.id.nama);
        TextView pssword = convertView.findViewById(R.id.pass);

        txtemail.setText(user.getUser_email());
        txtnama.setText(user.getUser_fullname());
        pssword.setText(user.getUser_password());

        return convertView;
    }

}

