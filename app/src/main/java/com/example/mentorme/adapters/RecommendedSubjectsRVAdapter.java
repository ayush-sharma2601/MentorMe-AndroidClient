package com.example.mentorme.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentorme.R;
import com.example.mentorme.SessionManager;
import com.example.mentorme.activities.FragmentContainerActivity;
import com.example.mentorme.api.RetrofitClient;
import com.example.mentorme.fragments.ListFragment;
import com.example.mentorme.models.RecommendedSubjectsModel;
import com.example.mentorme.models.SearchMentorResponse;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendedSubjectsRVAdapter extends RecyclerView.Adapter<RecommendedSubjectsRVAdapter.ViewHolder> {
    private SessionManager sessionManager;

    private ProgressDialog progressDialog;

    private Vector<RecommendedSubjectsModel> recommendedSubjectsModels;
    private Context context;

    private Call<SearchMentorResponse> call;

    public RecommendedSubjectsRVAdapter(Vector<RecommendedSubjectsModel> recommendedSubjectsModels, Context context) {
        this.recommendedSubjectsModels = recommendedSubjectsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecommendedSubjectsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_recommendedsubject, parent, false);

        return new RecommendedSubjectsRVAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        sessionManager = new SessionManager(context);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Searching for mentor...");

        holder.textViewSubjectName.setText(recommendedSubjectsModels.get(position).getSubjectName());
        holder.textViewSubjectDescription.setText(recommendedSubjectsModels.get(position).getSubjectDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                call = RetrofitClient.getInstance().getApi().searchMentor(sessionManager.getAuthToken(), sessionManager.getUserId(), recommendedSubjectsModels.get(position).getSubjectName());

                call.enqueue(new Callback<SearchMentorResponse>() {
                    @Override
                    public void onResponse(Call<SearchMentorResponse> call, Response<SearchMentorResponse> response) {
                        progressDialog.dismiss();
                        if (response.code() == 200) {
                            if (response.body().getSuccess())
                                context.startActivity(new Intent(context, FragmentContainerActivity.class));
                            else
                                Toast.makeText(context, "Mentor not found.\nTry again after some time.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<SearchMentorResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Connection error\nTry again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedSubjectsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewSubjectName, textViewSubjectDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSubjectName = itemView.findViewById(R.id.textViewSubjectName);
            textViewSubjectDescription = itemView.findViewById(R.id.textViewSubjectDescription);
        }
    }
}
