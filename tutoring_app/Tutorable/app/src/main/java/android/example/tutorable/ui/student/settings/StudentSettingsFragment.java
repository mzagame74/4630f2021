package android.example.tutorable.ui.student.settings;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.R;
import android.example.tutorable.databinding.FragmentStudentSettingsBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

public class StudentSettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private StudentSettingsViewModel studentSettingsViewModel;
    private FragmentStudentSettingsBinding binding;
    private FragmentNavigation fragmentNavigation;
    private Spinner schoolSpinner;
    private Button signoutButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentSettingsViewModel =
                new ViewModelProvider(this).get(StudentSettingsViewModel.class);

        binding = FragmentStudentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fragmentNavigation = (FragmentNavigation) requireActivity();
        signoutButton = binding.buttonSignOut;
        schoolSpinner = binding.spinnerSchool;

        // create an ArrayAdapter for the school spinner using the string
        // array and a default spinner layout
        ArrayAdapter<CharSequence> schoolAdapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.school_array,
                        android.R.layout.simple_spinner_item);

        // specify the layout to use when the list of choices appears
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply the adapter and item listener to the spinners
        schoolSpinner.setAdapter(schoolAdapter);
        schoolSpinner.setOnItemSelectedListener(this);

        signoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            fragmentNavigation.navigateToFragment(R.id.action_navigation_settings_to_navigation_login);
        });

        //final TextView textView = binding.textSettings;
        studentSettingsViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            //textView.setText(s);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}