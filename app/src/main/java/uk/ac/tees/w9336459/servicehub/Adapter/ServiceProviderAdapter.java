package uk.ac.tees.w9336459.servicehub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import uk.ac.tees.w9336459.servicehub.Model.ServiceProvider;
import uk.ac.tees.w9336459.servicehub.R;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderAdapter.ViewHolder> {

    private Context mContext;
    private List <ServiceProvider> mServiceProvider;

    public ServiceProviderAdapter (Context mContext, List mServiceProvider){
        this.mContext = mContext;
        this.mServiceProvider = mServiceProvider;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.service_provider_items,parent,false);
        return new ServiceProviderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceProvider serviceProvider = mServiceProvider.get(position);
        holder.serviceProvider.setText(ServiceProvider.getServiceProviderName());
        if(ServiceProvider.getImgUrl().equals("default")){
            holder.profile_img.setImageResource(R.mipmap.ic_launcher);
        }else{
             Glide.with(mContext).load(ServiceProvider.getImgUrl()).into(holder.profile_img);
        }
    }

    @Override
    public int getItemCount() {
        return mServiceProvider.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView serviceProvider;
        public ImageView profile_img;

        public ViewHolder(View view){
            super(view);

            serviceProvider = itemView.findViewById(R.id.SP_MS_Msg_serviceProvider_name);
            profile_img = itemView.findViewById(R.id.SP_MS_Msg_Profile_Img);
        }
    }

}
