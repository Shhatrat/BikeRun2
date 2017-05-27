package com.shhatrat.bikerun2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.adapter.helper.ItemTouchHelperAdapter;
import com.shhatrat.bikerun2.adapter.helper.ItemTouchHelperViewHolder;
import com.shhatrat.bikerun2.adapter.helper.OnStartDragListener;
import com.shhatrat.bikerun2.db.NormalContainer;
import com.shhatrat.bikerun2.db.RealmContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by szymon on 01.05.17.
 */

public class DraggableContainersAdapter extends RecyclerView.Adapter<DraggableContainersAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private  List<NormalContainer> mItems = new ArrayList<>();

    private final OnStartDragListener mDragStartListener;

    public DraggableContainersAdapter(Context context, OnStartDragListener dragStartListener, List<NormalContainer> l) {
        mDragStartListener = dragStartListener;
        if(l.size()!=0)
        mItems = l;
    }

    public void addSection(RealmContainer s)
    {
        mItems.add(new NormalContainer(s));
        notifyDataSetChanged();
    }

    public List<NormalContainer> getCollection()
    {
        Stream.of(mItems).forEachIndexed((i, e) -> e.setPosition(i));
        return mItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_container_fragment, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position).getContainerType().name());

        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView textView;
        public final ImageView handleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.row_con_text);
            handleView = (ImageView) itemView.findViewById(R.id.row_con_image);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(Color.WHITE);
        }
    }
}
