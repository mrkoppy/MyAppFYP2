package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Availabletrip_adapter extends RecyclerView.Adapter<Availabletrip_adapter.ViewHolder> {

    private Context myContext;
    private ArrayList<Post_availabletrip> myData;
    private my_rider_booking_interface my_rider_booking_interface;

    public Availabletrip_adapter(Context context, ArrayList<Post_availabletrip> data) {
        /*this.myContext = context;*/
        this.myData = data;
    }

    public Availabletrip_adapter(ArrayList<Post_availabletrip> arrayList, my_rider_booking_interface my_rider_booking_interface) {
        this.myData = arrayList;
        this.my_rider_booking_interface = my_rider_booking_interface;

    }

    /*used to store other class view*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.my_rider_booking, parent, false);
        /*View view = LayoutInflater
                .from(myContext)
                .inflate(R.layout.my_rider_booking, parent, false);*/
        ViewHolder holder = new ViewHolder(view);
        holder.tv_availableRideFrom = (TextView) view.findViewById(R.id.tv_availableRideFrom);
        holder.tv_availableRideTo = (TextView) view.findViewById(R.id.tv_availableRideTo);
        holder.tv_availableRideDate = (TextView) view.findViewById(R.id.tv_availableRideDate);
        holder.tv_availableRideEST = (TextView) view.findViewById(R.id.tv_availableRideEST);

        return holder;
    }

    /*holder that hold information of those view*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post_availabletrip post = myData.get(position);
        holder.tv_availableRideFrom.setText(post.From);
        holder.tv_availableRideTo.setText(post.To);
        holder.tv_availableRideDate.setText(post.Date);
        holder.tv_availableRideEST.setText(post.Est);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               my_rider_booking_interface.onClick(post);
            }
            }
        );
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_availableRideFrom,tv_availableRideTo,tv_availableRideDate,tv_availableRideEST;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
