package fi.javanainen.migraineapp;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fi.javanainen.migraineapp.CustomViewHolder;
import fi.javanainen.migraineapp.Migraine;
import fi.javanainen.migraineapp.MigraineEvent;


/**
 * Custom adaper for RecyclerView
 * @author Teemu Pennanen
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private MigraineList list;
    private SelectListener listener;

    public CustomAdapter(Context context, MigraineList list) {
        this.context = context;
        this.list = list;

    }

    /** Custom ViewHolder for RecyclerView
    * @author Teemu Pennanen
    */
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.migraine_item, parent, false),listener);

    }

    /** Activity for showing users previous migraines.
     * Shows date and time.
     * In order of newest to oldest.
     * @author Teemu Pennanen
     */
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.textDate.setText(list.getMigraine(position).getFirstEvent().getDate().toString());
        holder.textTime.setText(list.getMigraine(position).getFirstEvent().getTime().toString());
    }

    @Override
    public int getItemCount() {
        return list.getMigraines().size();
    }

     /** Listener for recycler view
     * @author Teemu Pennanen
     */
    public void setOnItemClickedListener(SelectListener listener){
        this.listener = listener;
    }
}
