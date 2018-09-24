package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Available_alluseradapter extends RecyclerView.Adapter<Available_alluseradapter.ViewHolder> {

    private Context myContext;
    private ArrayList<Post_alluser> myData;
    private my_alluser_interface my_alluser_interface;

    public Available_alluseradapter(Context context, ArrayList<Post_alluser> data) {
        /*this.myContext = context;*/
        this.myData = data;
    }

    public Available_alluseradapter(ArrayList<Post_alluser> arrayList, my_alluser_interface my_alluser_interface) {
        this.myData = arrayList;
        this.my_alluser_interface = my_alluser_interface;

    }

    /*used to store other class view*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.alluserdata, parent, false);
        /*View view = LayoutInflater
                .from(myContext)
                .inflate(R.layout.my_rider_booking, parent, false);*/
        ViewHolder holder = new ViewHolder(view);
        holder.tv_Name = (TextView) view.findViewById(R.id.userName);
        holder.tv_StudentID = (TextView) view.findViewById(R.id.userStudentID);
//        holder.tv_mobile = (TextView) view.findViewById(R.id.userMobile);
        holder.tv_Email = (TextView) view.findViewById(R.id.userEmail);
        holder.tv_gender = (TextView) view.findViewById(R.id.userGender);
        holder.tv_mobile = (TextView) view.findViewById(R.id.userMobile);
        holder.tv_postcode = (TextView) view.findViewById(R.id.userPostcode);
        holder.tv_studentid = (TextView) view.findViewById(R.id.userStuID);
        holder.tv_Carplate = (TextView) view.findViewById(R.id.userCarplate);
        holder.tv_Carseats = (TextView) view.findViewById(R.id.userCarseats);

        return holder;
    }

    /*holder that hold information of those view*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post_alluser post = myData.get(position);
        /*Post_availabletrip data = new Post_availabletrip();*/
        holder.tv_Name.setText(post.Name);
        holder.tv_Email.setText(post.Email);
        holder.tv_StudentID.setText(post.StudentID);
        holder.tv_Email.setText(post.Email);
        holder.tv_gender.setText(post.Gender);
        holder.tv_mobile.setText(post.Mobile);
        holder.tv_postcode.setText(post.Postcode);
        holder.tv_studentid.setText(post.StudentID);
        holder.tv_Carplate.setText(post.Carplate);
        holder.tv_Carseats.setText(post.Carseats);
//        holder.tv_mobile.setText(post.Mobile);
        Log.i("JJJJJJJJ", post.Name);
        Log.i("SSSSSSSSSSSSSS",post.Email);
        Log.i("SSSAAA",post.StudentID);
        Log.i("SSSSSSS",post.Email);
        Log.i("aaa",post.Gender);
        Log.i("SSSSavvvSSS",post.Mobile);
        Log.i("aadsf",post.Postcode);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_alluser_interface.onClick(post);
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_Name,tv_Email,tv_StudentID,tv_mobile,tv_gender,tv_postcode,tv_studentid,tv_Carplate,tv_Carseats;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
