package fi.javanainen.migraineapp;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Custom ViewHolder for RecyclerView
 * @author Teemu Pennanen
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView textDate, textTime;
    public CardView cardView;
    public CustomViewHolder (@NonNull View itemView, SelectListener listener) {
        super(itemView);
        textDate = itemView.findViewById(R.id.text_view_date);
        textTime = itemView.findViewById(R.id.text_view_time);

        cardView = itemView.findViewById(R.id.main_container);

        itemView.setOnClickListener(v -> listener.onItemClicked(getAdapterPosition()));

    }
}
