package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        holder.tv_availableRidePrice = (TextView)view.findViewById(R.id.tv_availableRidePrice);
        holder.tv_availableSeats_left = (TextView)view.findViewById(R.id.tv_seatsleft);
        holder.tv_gender = (TextView)view.findViewById(R.id.tv_gender);
        holder.tv_drivername = (TextView)view.findViewById(R.id.tv_drivername);
        holder.tv_carplate = (TextView)view.findViewById(R.id.tv_carplate);
        holder.tv_carseat = (TextView)view.findViewById(R.id.tv_carseat);
        holder.b1 = (Button)view.findViewById(R.id.ATButton1);
        holder.b2 = (Button)view.findViewById(R.id.ATButton2);
        holder.b3 = (Button)view.findViewById(R.id.ATButton3);
        holder.b4 = (Button)view.findViewById(R.id.ATButton4);
        holder.b5 = (Button)view.findViewById(R.id.ATButton5);
        holder.b6 = (Button)view.findViewById(R.id.ATButton6);
        return holder;
    }

    /*holder that hold information of those view*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post_availabletrip post = myData.get(position);
        /*Post_availabletrip data = new Post_availabletrip();*/
        holder.tv_availableRideFrom.setText(post.From);
        holder.tv_availableRideTo.setText(post.To);
        holder.tv_availableRideDate.setText(post.Date);
        holder.tv_availableRideEST.setText(post.Est);
        holder.tv_availableRidePrice.setText(post.Price);
        holder.tv_availableSeats_left.setText(post.Seats_left);
        holder.tv_gender.setText(post.Gender);
        holder.tv_drivername.setText(post.Name);
        holder.tv_carplate.setText(post.Carplate);
        holder.tv_carseat.setText(post.Carseat);
        Log.i("JJJJJJJJ", post.Gender);
        Log.i("SSSSSSSSSSSSSS",post.Seats_left);
        Log.i("SSSAAA",post.Carplate);
        Log.i("SSSAAA",post.Carseat);

        /*First check if seats left == 6 then in this loop*/
        if(post.Carseat.equals("6")){
            if(post.Seats_left.equals("6")){
                holder.b1.setBackgroundColor(Color.GREEN);
                holder.b2.setBackgroundColor(Color.GREEN);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("5")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.GREEN);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("4")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("3")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("2")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.RED);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("1")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.RED);
                holder.b5.setBackgroundColor(Color.RED);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("0")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.RED);
                holder.b5.setBackgroundColor(Color.RED);
                holder.b6.setBackgroundColor(Color.RED);
            }
        }
        if(post.Carseat.equals("4")){
            if(post.Seats_left.equals("4")){
                holder.b1.setBackgroundColor(Color.GREEN);
                holder.b2.setBackgroundColor(Color.GREEN);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("3")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.GREEN);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("2")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("1")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("0")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.RED);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
        }

            /*Once done check then in this loop*/
            /*if(post.Seats_left.equals("6")){
                holder.b1.setBackgroundColor(Color.GREEN);
                holder.b2.setBackgroundColor(Color.GREEN);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("5")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.GREEN);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("4")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("3")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("2")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.RED);
                holder.b5.setBackgroundColor(Color.GREEN);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("1")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.RED);
                holder.b5.setBackgroundColor(Color.RED);
                holder.b6.setBackgroundColor(Color.GREEN);
            }
            else if(post.Seats_left.equals("0")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.RED);
                holder.b5.setBackgroundColor(Color.RED);
                holder.b6.setBackgroundColor(Color.RED);
            }
            *//*if 4 car seats only*//*
        } else if(post.Seats_left.equals("4")){
            if(post.Seats_left.equals("4")){
                holder.b1.setBackgroundColor(Color.GREEN);
                holder.b2.setBackgroundColor(Color.GREEN);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("3")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.GREEN);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("2")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.GREEN);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("1")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.GREEN);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
            else if(post.Seats_left.equals("0")){
                holder.b1.setBackgroundColor(Color.RED);
                holder.b2.setBackgroundColor(Color.RED);
                holder.b3.setBackgroundColor(Color.RED);
                holder.b4.setBackgroundColor(Color.RED);
                holder.b5.setBackgroundColor(Color.BLACK);
                holder.b6.setBackgroundColor(Color.BLACK);
            }
*/
        /*}*/

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

        public TextView tv_availableRideFrom,tv_availableRideTo,tv_availableRideDate,tv_availableRideEST,tv_availableRidePrice
                ,tv_availableSeats_left,tv_gender,tv_drivername,tv_carplate,tv_carseat;
        public Button b1,b2,b3,b4,b5,b6;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
