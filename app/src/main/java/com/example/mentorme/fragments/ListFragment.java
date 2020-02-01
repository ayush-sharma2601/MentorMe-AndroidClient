package com.example.mentorme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentorme.R;
import com.example.mentorme.SessionManager;
import com.example.mentorme.adapters.ListAdapter;
import com.example.mentorme.api.RetrofitClient;
import com.example.mentorme.models.User;
import com.example.mentorme.models.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {

    private SessionManager sessionManager;

    private TextView tv;

    private RecyclerView recyclerViewList;
    private ListAdapter listAdapter;
    public static List<User> userList;

    private Call<UserResponse> call;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        tv = view.findViewById(R.id.tv);

        recyclerViewList = view.findViewById(R.id.rvList);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));

        sessionManager = new SessionManager(getContext());

        userList = new ArrayList<>();

        readUsers();

        return view;
    }

    private void readUsers() {
        if (sessionManager.getRoleSelected().equals("mentee")) {
            call = RetrofitClient.getInstance().getApi().getMentorsList(sessionManager.getAuthToken(), sessionManager.getEmail());

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();
                    if (response.code() == 200) {
                        if (userResponse.getSuccess()) {
                            userList.addAll(userResponse.getUserList());
                        }
                        else
                            Toast.makeText(getContext(), "No mentors found", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), "Unknown error\nTry again", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            listAdapter = new ListAdapter(getContext(), userList);
            recyclerViewList.setAdapter(listAdapter);

            if (userList.size() == 0) {
                tv.setVisibility(View.VISIBLE);
                recyclerViewList.setVisibility(View.INVISIBLE);
            }
        } else {
            call = RetrofitClient.getInstance().getApi().getMenteesList(sessionManager.getAuthToken(), sessionManager.getUserId());

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();
                    if (response.code() == 200) {
                        if (userResponse.getSuccess()) {
                            userList.addAll(userResponse.getUserList());
                        }
                        else
                            Toast.makeText(getContext(), "No mentees found", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), "Unknown error\nTry again", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(getContext(),"Connection error\nTry again", Toast.LENGTH_SHORT).show();
                }
            });

            listAdapter = new ListAdapter(getContext(), userList);
            recyclerViewList.setAdapter(listAdapter);

            if (userList.size() == 0) {
                tv.setVisibility(View.VISIBLE);
                recyclerViewList.setVisibility(View.INVISIBLE);
            }
        }
    }
}
