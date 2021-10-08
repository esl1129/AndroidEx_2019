package com.test.fragmentsample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.fragmentsample.R;
import com.test.fragmentsample.fragment.ListItem;

import java.util.ArrayList;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{
    private ArrayList<ListItem> items;
    private LayoutInflater inflater;
    private Context mContext;

    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(items.get(position));
    }

    public void setItems(ArrayList<ListItem> list){
        this.items = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView idText;
        TextView statusText;

        public ViewHolder(@NonNull View view){
            super(view);

            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        ListItem item = items.get(pos);
                        if(onItemClickListener != null){
                            onItemClickListener.onItemClick(v, pos);
                        }
                    }
                }
            });
            profile = view.findViewById(R.id.profileImage);
            idText = view.findViewById(R.id.profileId);
            statusText = view.findViewById(R.id.profileStatus);
        }

        void onBind(ListItem item){
            profile.setImageResource(item.getProfile());
            idText.setText(item.getId());
            statusText.setText(item.getStatus());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

//    public MyRecyclerAdapter(ArrayList<ListItem> items, Context context){
//        this.items = items;
//        this.mContext = context;
//        inflater = LayoutInflater.from(mContext);
//    }
//    @Override
//    public int getCount() {
//
//        return items.size();
//
//    }
//
//    @Override
//    public Object getItem(int position) {
//
//        return items.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = inflater.inflate(R.layout.list_item, null);
//
//        ImageView profile = view.findViewById(R.id.profileImage);
//        TextView idText = view.findViewById(R.id.profileId);
//        TextView statusText = view.findViewById(R.id.profileStatus);
//
//        ListItem item = items.get(position);
//
//        profile.setBackground(mContext.getResources().getDrawable(R.drawable.talk));
//        idText.setText(item.getId());
//        statusText.setText(item.getStatus());
//        return null;
//    }
}
