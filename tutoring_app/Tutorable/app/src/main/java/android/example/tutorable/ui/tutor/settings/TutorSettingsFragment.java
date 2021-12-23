package android.example.tutorable.ui.tutor.settings;

import androidx.lifecycle.ViewModelProvider;

import android.example.tutorable.R;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TutorSettingsFragment extends Fragment {

    private TutorSettingsViewModel mViewModel;

    public static TutorSettingsFragment newInstance() {
        return new TutorSettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tutor_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TutorSettingsViewModel.class);
        // TODO: Use the ViewModel
    }

}