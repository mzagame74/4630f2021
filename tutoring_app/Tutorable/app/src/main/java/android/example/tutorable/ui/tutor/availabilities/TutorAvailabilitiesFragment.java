package android.example.tutorable.ui.tutor.availabilities;

import androidx.lifecycle.ViewModelProvider;

import android.example.tutorable.R;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TutorAvailabilitiesFragment extends Fragment {

    private TutorAvailabilitiesViewModel mViewModel;

    public static TutorAvailabilitiesFragment newInstance() {
        return new TutorAvailabilitiesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tutor_availabilities, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TutorAvailabilitiesViewModel.class);
        // TODO: Use the ViewModel
    }

}