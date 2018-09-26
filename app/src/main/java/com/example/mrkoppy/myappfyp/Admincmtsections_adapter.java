package com.example.mrkoppy.myappfyp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Admincmtsections_adapter extends RecyclerView.Adapter<Admincmtsections_adapter.ViewHolder> {

    private Context myContext;
    private ArrayList<Post_admincomments> myData;
    private my_cmtfromuser_interface my_cmtfromuser_interface;

    public Admincmtsections_adapter(Context context, ArrayList<Post_admincomments> data) {
        /*this.myContext = context;*/
        this.myData = data;
    }

    /*interface need to be changed*/
    public Admincmtsections_adapter(ArrayList<Post_admincomments> arrayList, my_cmtfromuser_interface my_cmtfromuser_interface ) {
        this.myData = arrayList;
        this.my_cmtfromuser_interface = my_cmtfromuser_interface;

    }

    /*used to store other class view*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cmtsectiondata, parent, false);
        /*View view = LayoutInflater
                .from(myContext)
                .inflate(R.layout.my_rider_booking, parent, false);*/
        ViewHolder holder = new ViewHolder(view);
        holder.tv_UserID = (TextView) view.findViewById(R.id.cmtsectiondataUserID);
        holder.tv_RouteID = (TextView) view.findViewById(R.id.cmtsectiondataRouteID);
        holder.tv_Overallstar = (TextView) view.findViewById(R.id.cmtsectiondataOverallstar);
        holder.tv_Comment = (TextView) view.findViewById(R.id.cmtsectiondataComment);

        return holder;
    }

    /*holder that hold information of those view*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post_admincomments post = myData.get(position);
        /*Post_availabletrip data = new Post_availabletrip();*/
        holder.tv_UserID.setText(post.UserID);
        holder.tv_RouteID.setText(post.RouteID);
        holder.tv_Overallstar.setText(post.Overallstar);
        holder.tv_Comment.setText(post.Comment);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   my_cmtfromuser_interface.onClick(post);
                                               }
                                           }
        );
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_UserID,tv_RouteID,tv_Overallstar,tv_Comment;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
