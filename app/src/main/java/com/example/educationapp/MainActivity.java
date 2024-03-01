package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.educationapp.databinding.ActivityMainBinding;


import navFragment.QuizzesFragment;
import navFragment.SettingFragment;
import navFragment.VideoFragment;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new VideoFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {


                if(item.getItemId()== R.id.video) {
                    replaceFragment(new VideoFragment());
                }
                else if(item.getItemId()== R.id.quizzes) {
                    replaceFragment(new QuizzesFragment());
                }
                else if(item.getItemId()== R.id.logout) {
                    replaceFragment(new SettingFragment());
                }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutView,fragment);
        fragmentTransaction.commit();
    }
}