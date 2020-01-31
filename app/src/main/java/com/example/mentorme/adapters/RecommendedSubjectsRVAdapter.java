package com.example.mentorme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentorme.R;
import com.example.mentorme.models.RecommendedSubjectsModel;

import java.util.Vector;

public class RecommendedSubjectsRVAdapter extends RecyclerView.Adapter<RecommendedSubjectsRVAdapter.ViewHolder> {
    private Vector<RecommendedSubjectsModel> recommendedSubjectsModels;
    private Context context;

    public RecommendedSubjectsRVAdapter(Vector<RecommendedSubjectsModel> recommendedSubjectsModels,Context context) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewSubjectName.setText(recommendedSubjectsModels.get(position).getSubjectName());
        holder.textViewSubjectDescription.setText(recommendedSubjectsModels.get(position).getSubjectDescription());
    }

    @Override
    public int getItemCount() {
        return recommendedSubjectsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewSubjectName,textViewSubjectDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSubjectName = itemView.findViewById(R.id.textViewSubjectName);
            textViewSubjectDescription = itemView.findViewById(R.id.textViewSubjectDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Coming soon.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
