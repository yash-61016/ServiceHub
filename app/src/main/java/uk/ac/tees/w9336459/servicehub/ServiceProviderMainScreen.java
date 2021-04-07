package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class ServiceProviderMainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_main_screen);
    }
    public void ChangeFragment(View view){
        Fragment fragment;

        if(view == findViewById(R.id.SP_MS_jobsBtn)){
            fragment = new SP_MS_JobsFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.SP_MS_fragment, fragment);
            ft.commit();
        }
        if(view == findViewById(R.id.SP_MS_msgsBtn)){
            fragment = new SP_MS_MsgsFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.SP_MS_fragment, fragment);
            ft.commit();
        }
    }
}