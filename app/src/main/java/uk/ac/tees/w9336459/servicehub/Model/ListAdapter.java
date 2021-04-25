package uk.ac.tees.w9336459.servicehub.Model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.w9336459.servicehub.R;
import uk.ac.tees.w9336459.servicehub.Requests;
import uk.ac.tees.w9336459.servicehub.SP_Booking;
import uk.ac.tees.w9336459.servicehub.ShowSentRequest;
import uk.ac.tees.w9336459.servicehub.Tiles.*;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mcontext;
    private List<ServiceProviders2> musers;

    public ListAdapter(Context mcontext, List<ServiceProviders2> musers){
        this.musers = musers;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.list_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ServiceProviders2 serviceProviders = musers.get(position);
        holder.username.setText(serviceProviders.getFirstname()+" "+serviceProviders.getLastname());
        Picasso.get().load(serviceProviders.getProfilepicture()).into(holder.profilepic);
        holder.info.setText(serviceProviders.getPhonenum());

        holder.itemView.setOnClickListener(v -> {

            if(mcontext instanceof Requests){
                Intent intent = new Intent(mcontext, ShowSentRequest.class);
                intent.putExtra("name",serviceProviders.getFirstname() + " " + serviceProviders.getLastname());
                intent.putExtra("dp",serviceProviders.getProfilepicture());
                intent.putExtra("category",serviceProviders.getSkills());
                intent.putExtra("phone",serviceProviders.getPhonenum());
                intent.putExtra("id",serviceProviders.getEmailid());
                intent.putExtra("type","ServiceProvider");
                mcontext.startActivity(intent);
            }
            if(mcontext instanceof electronic_service_offered) {
                Intent intent = new Intent(mcontext, SP_Booking.class);
                intent.putExtra("name", serviceProviders.getFirstname() + " " + serviceProviders.getLastname());
                intent.putExtra("dp", serviceProviders.getProfilepicture());
                intent.putExtra("phone", serviceProviders.getPhonenum());
                intent.putExtra("numRate", serviceProviders.getNumrate());
                intent.putExtra("totalRate", serviceProviders.getTotalrate());
                intent.putExtra("avRate", serviceProviders.getAvrate());
                intent.putExtra("emailID",serviceProviders.getEmailid());
                Bundle b = new Bundle();
                b.putDouble("spLat", serviceProviders.getLatitude());
                b.putDouble("spLng", serviceProviders.getLongitude());
                intent.putExtras(b);
                mcontext.startActivity(intent);
            }
            if(mcontext instanceof health_services_offered) {
                Intent intent = new Intent(mcontext, SP_Booking.class);
                intent.putExtra("name", serviceProviders.getFirstname() + " " + serviceProviders.getLastname());
                intent.putExtra("dp", serviceProviders.getProfilepicture());
                intent.putExtra("phone", serviceProviders.getPhonenum());
                intent.putExtra("numRate", serviceProviders.getNumrate());
                intent.putExtra("totalRate", serviceProviders.getTotalrate());
                intent.putExtra("avRate", serviceProviders.getAvrate());
                intent.putExtra("emailID",serviceProviders.getEmailid());
                Bundle b = new Bundle();
                b.putDouble("spLat", serviceProviders.getLatitude());
                b.putDouble("spLng", serviceProviders.getLongitude());
                intent.putExtras(b);
                mcontext.startActivity(intent);
            }
            if(mcontext instanceof home_service_offered) {
                Intent intent = new Intent(mcontext, SP_Booking.class);
                intent.putExtra("name", serviceProviders.getFirstname() + " " + serviceProviders.getLastname());
                intent.putExtra("dp", serviceProviders.getProfilepicture());
                intent.putExtra("phone", serviceProviders.getPhonenum());
                intent.putExtra("numRate", serviceProviders.getNumrate());
                intent.putExtra("totalRate", serviceProviders.getTotalrate());
                intent.putExtra("avRate", serviceProviders.getAvrate());
                intent.putExtra("emailID",serviceProviders.getEmailid());
                Bundle b = new Bundle();
                b.putDouble("spLat", serviceProviders.getLatitude());
                b.putDouble("spLng", serviceProviders.getLongitude());
                intent.putExtras(b);
                mcontext.startActivity(intent);
            }
            if(mcontext instanceof movers_shifters_services_offered) {
                Intent intent = new Intent(mcontext, SP_Booking.class);
                intent.putExtra("name", serviceProviders.getFirstname() + " " + serviceProviders.getLastname());
                intent.putExtra("dp", serviceProviders.getProfilepicture());
                intent.putExtra("phone", serviceProviders.getPhonenum());
                intent.putExtra("numRate", serviceProviders.getNumrate());
                intent.putExtra("totalRate", serviceProviders.getTotalrate());
                intent.putExtra("avRate", serviceProviders.getAvrate());
                intent.putExtra("emailID",serviceProviders.getEmailid());
                Bundle b = new Bundle();
                b.putDouble("spLat", serviceProviders.getLatitude());
                b.putDouble("spLng", serviceProviders.getLongitude());
                intent.putExtras(b);
                mcontext.startActivity(intent);
            }
            if(mcontext instanceof party_event_service_offered) {
                Intent intent = new Intent(mcontext, SP_Booking.class);
                intent.putExtra("name", serviceProviders.getFirstname() + " " + serviceProviders.getLastname());
                intent.putExtra("dp", serviceProviders.getProfilepicture());
                intent.putExtra("phone", serviceProviders.getPhonenum());
                intent.putExtra("numRate", serviceProviders.getNumrate());
                intent.putExtra("totalRate", serviceProviders.getTotalrate());
                intent.putExtra("avRate", serviceProviders.getAvrate());
                intent.putExtra("emailID",serviceProviders.getEmailid());
                Bundle b = new Bundle();
                b.putDouble("spLat", serviceProviders.getLatitude());
                b.putDouble("spLng", serviceProviders.getLongitude());
                intent.putExtras(b);
                mcontext.startActivity(intent);
            }
            if(mcontext instanceof personal_grooming_offered) {
                Intent intent = new Intent(mcontext, SP_Booking.class);
                intent.putExtra("name", serviceProviders.getFirstname() + " " + serviceProviders.getLastname());
                intent.putExtra("dp", serviceProviders.getProfilepicture());
                intent.putExtra("phone", serviceProviders.getPhonenum());
                intent.putExtra("numRate", serviceProviders.getNumrate());
                intent.putExtra("totalRate", serviceProviders.getTotalrate());
                intent.putExtra("avRate", serviceProviders.getAvrate());
                intent.putExtra("emailID",serviceProviders.getEmailid());
                Bundle b = new Bundle();
                b.putDouble("spLat", serviceProviders.getLatitude());
                b.putDouble("spLng", serviceProviders.getLongitude());
                intent.putExtras(b);
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return musers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profilepic;
        public TextView info;

        public ViewHolder(View itemView){
            super(itemView);

            username = itemView.findViewById(R.id.name_list);
            profilepic = itemView.findViewById(R.id.providers_image);
            info = itemView.findViewById(R.id.info_list);
        }
    }
}
