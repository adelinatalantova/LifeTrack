package com.example.lifetrack.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        binding.addFab.setOnClickListener(new View.OnClickListener() {
            Bundle bundle = new Bundle();


            @Override
            public void onClick(View view) {
                CreateTaskFragment createTaskFragment = new CreateTaskFragment();
                createTaskFragment.show(requireActivity().getSupportFragmentManager(),"ololo");
                navController.navigate(R.id.homeFragment);

//                Bundle bundle = this.getArguments ();
//                if (bundle != null) {
//                    String key = new String();
//                    int defaultValue = 0;
//                    int myInt = bundle.getInt(key, defaultValue);
//                }

            }

//            private Bundle getArguments() {
//
//                return null;
//            }
        });

        }
    }

