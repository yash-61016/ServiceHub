package uk.ac.tees.w9336459.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

        if(view == findViewById(R.id.work_in_progress)){
            fragment = new SP_MS_ActiveJobsFragment();
            androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
            androidx.fragment.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.SP_MS_fragment, fragment);
            ft.commit();
        }
        if(view == findViewById(R.id.post_work)){
            fragment = new SP_MS_PostJobsFragment();
            androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
            androidx.fragment.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.SP_MS_fragment, fragment);
            ft.commit();
        }
    }
}