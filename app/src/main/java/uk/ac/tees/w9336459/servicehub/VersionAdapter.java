package uk.ac.tees.w9336459.servicehub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.VersionVH> {
    public VersionAdapter(List<Versions> versionsList) {
        this.versionsList = versionsList;
    }

    List<Versions> versionsList;

    @NonNull
    @Override
    public VersionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new VersionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionVH holder, int position) {

        Versions versions= versionsList.get(position);
        holder.QuestionTxt.setText(versions.getQuestion());
        holder.AnswerTxt.setText(versions.getAnswer());

        boolean isexpandable = versionsList.get(position).isExpandable();
        holder.ExpandableLayout.setVisibility(isexpandable ? View.VISIBLE: View.GONE);

    }

    @Override
    public int getItemCount() {
        return versionsList.size();
    }

    public class VersionVH extends RecyclerView.ViewHolder {

        TextView QuestionTxt,AnswerTxt;
        LinearLayout linearLayout;
        LinearLayout ExpandableLayout;

        public VersionVH(@NonNull View itemView) {
            super(itemView);

            QuestionTxt=itemView.findViewById(R.id.question);
            AnswerTxt=itemView.findViewById(R.id.answer);

            linearLayout=itemView.findViewById(R.id.linear_layout);
            ExpandableLayout=itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Versions versions = versionsList.get(getAdapterPosition());
                    versions.setExpandable(!versions.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }

}

