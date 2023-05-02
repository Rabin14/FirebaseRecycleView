package com.techpathak.firebaserecycleview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;
import java.util.List;

public class Recycleiew extends AppCompatActivity {

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleiew);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView mFirestoreList = findViewById(R.id.recycleview);
        mFirestoreList.setItemAnimator(null);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading....");
        progressDialog.show();

        Query query = FirebaseFirestore.getInstance()
                .collection("DearResult")
                .orderBy("tid", Query.Direction.DESCENDING)
                .limit(100);
        //RecyleView
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<User, MyViewHolder>(options) {
            @NonNull
            @Override
            public Recycleiew.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
                return new Recycleiew.MyViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull User model) {
                holder.tid.setText(model.getTid());
                holder.tdate.setText(model.getTdate());
                holder.ttime.setText(model.getTtime());
                progressDialog.dismiss();

//item click

            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
        // mFirestoreList.setItemAnimator(null);

    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tid;
        private final TextView tdate;
        private final TextView ttime;
        private final TextView turl;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tid = itemView.findViewById(R.id.tid);
            tdate = itemView.findViewById(R.id.tdate);
            ttime = itemView.findViewById(R.id.ttime);
            turl = itemView.findViewById(R.id.turl);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}




