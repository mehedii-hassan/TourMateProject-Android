package com.example.tourmatenewproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.R;
import com.example.tourmatenewproject.callback.EventDeleteListener;
import com.example.tourmatenewproject.callback.EventEditListener;
import com.example.tourmatenewproject.callback.OnEventItemClickListener;
import com.example.tourmatenewproject.databinding.EventRowDesignBinding;
import com.example.tourmatenewproject.entities.TourEventModel;

import java.util.ArrayList;
import java.util.List;

public class TourEventAdapter extends RecyclerView.Adapter<TourEventAdapter.TourEventViewHolder> {

    private List<TourEventModel> eventList;
    private EventEditListener eventEditListener;
    private EventDeleteListener eventDeleteListener;
    private OnEventItemClickListener onEventItemClickListener;


    public TourEventAdapter(Activity activity) {
        eventList = new ArrayList<>();
        eventEditListener = (EventEditListener) activity;
        eventDeleteListener = (EventDeleteListener) activity;
        onEventItemClickListener = (OnEventItemClickListener) activity;

    }

    @NonNull
    @Override
    public TourEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final EventRowDesignBinding binding = EventRowDesignBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new TourEventViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TourEventViewHolder holder, int position) {
        final TourEventModel eventModel = eventList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEventItemClickListener.onEventItemClicked(eventModel);
            }
        });
        holder.bind(eventModel);


    }

    public void submitNewEventList(List<TourEventModel> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class TourEventViewHolder extends RecyclerView.ViewHolder {
        private EventRowDesignBinding binding;

        public TourEventViewHolder(EventRowDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            this.binding.btnRowMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();

                    final TourEventModel tourEvent = eventList.get(position);

                    PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                    popupMenu.inflate(R.menu.rv_row_item_menu);
                    popupMenu.setForceShowIcon(true);
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.item_delete:
                                    eventDeleteListener.onDeleteEvent(tourEvent);
                                    return true;

                                case R.id.item_update:
                                    eventEditListener.onEditEvent(tourEvent);
                                    return true;

                                default:
                                    return false;
                            }
                        }
                    });
                    Toast.makeText(view.getContext(), "" + eventList.get(position).getTripName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bind(TourEventModel eventModel) {
            binding.setEvent(eventModel);
        }
    }
}
