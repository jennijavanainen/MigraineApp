package fi.javanainen.migraineapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MigraineAdapter extends RecyclerView.Adapter<MigraineAdapter.MigraineHolder> {

    private List<MigraineEvent> migraines = new ArrayList<>();
    @NonNull
    @Override
    public MigraineAdapter.MigraineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.migrane_item,parent,false);
        return new MigraineHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MigraineAdapter.MigraineHolder holder, int position) {
        MigraineEvent currentMigraineEvent = migraines.get(position);
        holder.textViewDate.setText((CharSequence) currentMigraineEvent.getDate());
        holder.textViewTime.setText((CharSequence) currentMigraineEvent.getTime());
        holder.textViewTrigger.setText((CharSequence) currentMigraineEvent.getMedicines());
        holder.textViewTreatment.setText((CharSequence) currentMigraineEvent.getTreatments());
        holder.textViewPain.setText(currentMigraineEvent.getPain());

    }

    @Override
    public int getItemCount() {
        return migraines.size();
    }

    public void setMigraines(List<MigraineEvent> migraines){
        this.migraines = migraines;
        notifyDataSetChanged();
    }

    class MigraineHolder extends RecyclerView.ViewHolder{
        private TextView textViewDate;
        private TextView textViewTime;
        private TextView textViewTrigger;
        private TextView textViewPain;
        private TextView textViewTreatment;

        public MigraineHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewTime= itemView.findViewById(R.id.text_view_time);
            textViewTrigger= itemView.findViewById(R.id.text_view_trigger);
            textViewTreatment= itemView.findViewById(R.id.text_view_treatment);
            textViewPain= itemView.findViewById(R.id.text_view_pain);
        }
    }
}
