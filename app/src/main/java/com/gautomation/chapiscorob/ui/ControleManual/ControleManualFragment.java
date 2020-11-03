package com.gautomation.chapiscorob.ui.ControleManual;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gautomation.chapiscorob.R;

public class ControleManualFragment extends Fragment {

    private ControleManualViewModel controleManualViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        controleManualViewModel =
                new ViewModelProvider(this).get(ControleManualViewModel.class);
        View root = inflater.inflate(R.layout.fragment_controlemanual, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        controleManualViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}