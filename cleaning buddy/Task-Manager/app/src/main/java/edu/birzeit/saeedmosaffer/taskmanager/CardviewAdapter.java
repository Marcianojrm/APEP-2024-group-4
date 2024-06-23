// CardviewAdapter.java
package edu.birzeit.saeedmosaffer.taskmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.ViewHolder> {

    private List<Room> rooms;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Room room);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView details;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            details = view.findViewById(R.id.details);
        }
    }

    public CardviewAdapter(List<Room> rooms) {
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = rooms.get(position);
        holder.title.setText(room.getTitle());
        holder.details.setText(room.getDetails());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(room);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }
}
