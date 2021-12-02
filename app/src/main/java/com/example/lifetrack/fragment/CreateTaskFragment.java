package com.example.lifetrack.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.lifetrack.R;
import com.example.lifetrack.adapter.TaskAdapter;
import com.example.lifetrack.databinding.FragmentCreateTaskBinding;
import com.example.lifetrack.model.TaskModel;
import com.example.lifetrack.utils.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateTaskFragment extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {
    FragmentCreateTaskBinding binding;
    String userTask;
    String deadline;
    String repeatCount;
    private int startyear;
    private int startMonth;
    private int startDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     initClickers();
    }

    private void initClickers() {
        binding.appBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passModelToHomeFragment();
            }
        });
        binding.dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowsDialog();
            }
        });
        binding.repeatTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRepeatDialog();
            }
        });
    }


    private void showRepeatDialog() {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.repeat_dialog, null);
        Dialog alertDialog = new Dialog(requireContext());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.show();
        RadioButton neverBtn = alertDialog.findViewById(R.id.never);
        neverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(neverBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton dayBtn = alertDialog.findViewById(R.id.day);
        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(dayBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton weekBtn = alertDialog.findViewById(R.id.week);
        weekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(weekBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton monthBtn = alertDialog.findViewById(R.id.month);
        monthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(monthBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton yearBtn = alertDialog.findViewById(R.id.year);
        yearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(yearBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
        RadioButton customBtn = alertDialog.findViewById(R.id.custom);
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repeatTv.setText(customBtn.getText().toString());
                alertDialog.dismiss();
            }
        });
    }
           private void ShowsDialog() {
        Calendar calendar = Calendar.getInstance();
        startyear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        startDay  = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog =  new DatePickerDialog(
                requireContext(),this::onDateSet,startyear,startMonth,startDay);
        datePickerDialog.show();
    }

    private void passModelToHomeFragment() {
            ArrayList<TaskModel> list = new ArrayList<>();
            userTask = binding.taskEd.getText().toString();
            deadline = binding.dateTv.getText().toString();
            repeatCount = binding.repeatTv.getText().toString();
            TaskModel model = new TaskModel(userTask,deadline,repeatCount);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.MODEL, (Serializable) model);
            TaskAdapter taskAdapter =new TaskAdapter();
            list.add(model);
            TaskAdapter.fillist(list);
            dismiss();
        }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        binding.dateTv.setText(year+"."+month+"."+day);

    }

    }

