package com.example.comp1786_l5_android_persistence.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp1786_l5_android_persistence.Models.Person;
import com.example.comp1786_l5_android_persistence.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
        private List<Person> persons;
        private OnDeleteClickListener onDeleteClickListener;
        public interface OnDeleteClickListener {
            void onDeleteClick(Person person);
        }
        public ContactAdapter(List<Person> persons) {
            this.persons = persons;
            this.onDeleteClickListener = onDeleteClickListener;
        }
        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_card, parent, false);
            return new ContactViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            Person person = persons.get(position);
            holder.personName.setText(person.name);
//        holder.personDetails.setText(person.person_id + " " + person.dob + " " + person.email);
            holder.personDetails.setText("DOB: " + person.dob + "\nEmail: " + person.email);

            // Load and display the image using a library like Picasso, Glide, or directly from the path
            // Example using Picasso:
//      Picasso.get().load(person.imagePath).into(holder.personImage);
            holder.personImage.setImageResource(person.image);

            holder.itemView.setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(persons.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }

        public static class ContactViewHolder extends RecyclerView.ViewHolder {
            TextView personName, personDetails;
            ImageView personImage;

            public ContactViewHolder(@NonNull View itemView) {
                super(itemView);
                personName = itemView.findViewById(R.id.personName);
                personDetails = itemView.findViewById(R.id.personDetails);
                personImage = itemView.findViewById(R.id.personImage); // Assuming you have an ImageView with id "personImage"
            }
        }
    }
