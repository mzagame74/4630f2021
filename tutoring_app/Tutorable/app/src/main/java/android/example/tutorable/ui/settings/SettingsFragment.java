package android.example.tutorable.ui.settings;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.example.tutorable.databinding.FragmentSettingsBinding;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;
    private FragmentNavigation fragmentNavigation;
    private Spinner schoolSpinner;
    private Button signoutButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fragmentNavigation = (FragmentNavigation) requireActivity();
        signoutButton = binding.buttonSignOut;

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
            fragmentNavigation.navigateToFragment(R.id.navigation_login);
        });

        //final TextView textView = binding.textSettings;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), s -> {
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