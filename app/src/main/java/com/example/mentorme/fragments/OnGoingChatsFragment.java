package com.example.mentorme.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentorme.R;
import com.example.mentorme.SessionManager;
import com.example.mentorme.adapters.ListAdapter;
import com.example.mentorme.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OnGoingChatsFragment extends Fragment {

    private SessionManager sessionManager;

    private RecyclerView recyclerViewOnGoingChats;
    private ListAdapter listAdapter;
    private List<User> userList;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ongoingchats, container, false);

        recyclerViewOnGoingChats = view.findViewById(R.id.rvOnGoingChats);
        recyclerViewOnGoingChats.setHasFixedSize(true);
        recyclerViewOnGoingChats.setLayoutManager(new LinearLayoutManager(getContext()));

        sessionManager = new SessionManager(getContext());

        userList = new ArrayList<>();

        List<User> list = ListFragment.userList;

        databaseReference = FirebaseDatabase.getInstance().getReference("chats");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    if (shot.getKey().substring(0,25).equals(sessionManager.getUserId())) {
                        // Do something
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listAdapter = new ListAdapter(getContext(), userList);
        recyclerViewOnGoingChats.setAdapter(listAdapter);

        return view;
    }
}
