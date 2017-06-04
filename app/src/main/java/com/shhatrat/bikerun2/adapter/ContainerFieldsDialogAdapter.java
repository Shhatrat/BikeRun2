package com.shhatrat.bikerun2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.model.SingleData;
import com.shhatrat.bikerun2.view.fragment.container.EnumContainerType;

import java.util.List;

/**
 * Created by szymon on 04.06.17.
 */

public class ContainerFieldsDialogAdapter extends RecyclerView.Adapter<ContainerFieldsDialogAdapter.ButtonVH> {

    private List<SingleData> enumDataTypeList;
    private ItemCallback itemCallback;
    private Context context;

    public ContainerFieldsDialogAdapter(Context c) {
        this.context = c;
        enumDataTypeList = EnumContainerType.getContainersList();
    }

    public void setCallbacks(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    @Override
    public ButtonVH onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_container_vertical, parent, false);
        return new ButtonVH(view, this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ButtonVH holder, int position) {
        holder.title.setText(enumDataTypeList.get(position).getTitle());
        holder.content.setText(enumDataTypeList.get(position).getContent());
        holder.imageView.setImageResource(enumDataTypeList.get(position).getDrawable());
    }

    @Override
    public int getItemCount() {
        return enumDataTypeList.size();
    }


    public interface ItemCallback {

        void onItemClicked(SingleData itemIndex);
    }

    interface ButtonCallback {

        void onButtonClicked(int buttonIndex);
    }

    static class ButtonVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView title;
        final TextView content;
        final ImageView imageView;
        final ContainerFieldsDialogAdapter adapter;

        ButtonVH(View itemView, ContainerFieldsDialogAdapter adapter) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.card_container_title);
            content = (TextView) itemView.findViewById(R.id.card_container_desc);
            imageView = (ImageView) itemView.findViewById(R.id.card_container_image);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (adapter.itemCallback == null) {
                return;
            }
            SingleData value = EnumContainerType.getContainersList().get(getAdapterPosition());
            adapter.itemCallback.onItemClicked(value);
        }
    }
}
