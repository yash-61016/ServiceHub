package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class help extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        recyclerView= findViewById(R.id.recyclerView);

        initData();
        setRecyclerView();
    }

    private void setRecyclerView() {
        VersionAdapter versionAdapter=new VersionAdapter(versionsList);
        recyclerView.setAdapter(versionAdapter);
        recyclerView.setHasFixedSize(true);
    }
    private void initData() {
        versionsList= new ArrayList<>();

        versionsList.add(new Versions("how can we book a service?","search for the service you need,open category and follow the instructions "));
        versionsList.add(new Versions("how to cancel a booking?","select the booking from booking button and you will find cancel button ,when you press it your booking will be cancelled"));
        versionsList.add(new Versions("how to reschedule a booking?","select the booking button and you will find a reschedule option ,when you click on it you can reschedule it"));
        versionsList.add(new Versions("how to know if my request is confirmed?","once you book a service ,you will receive a message informing you that the booking has been accepted"));
        versionsList.add(new Versions("how can I contact the professional?","contact details of the professional will be shared to you and your details to the professional so you will be happy to contact the professional"));
        versionsList.add(new Versions("how can i rate and review the service of a professional?","after each service,you will be asked to rate the professional.when you select a service you can see all the professional with their ratings and reviews"));
        versionsList.add(new Versions("can i book the same person again for the service?","you can search for the person you want and then book the service "));
        versionsList.add(new Versions("refund policy","on cancellation of a request ,refund will be initiated and payment may take 5 to 7 working days"));
        versionsList.add(new Versions("how to pay` for the service?","you can by cash or by mobile transfer to the service provider after the service"));
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, menubtn.class));
    }



}