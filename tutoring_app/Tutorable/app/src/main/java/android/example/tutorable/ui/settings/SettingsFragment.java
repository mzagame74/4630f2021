package android.example.tutorable.ui.settings;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.ui.login.LoginFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.example.tutorable.databinding.FragmentSettingsBinding;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;
    private FragmentNavigation fragmentNavigation;
    private Button signoutButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fragmentNavigation = (FragmentNavigation) requireActivity();
        signoutButton = binding.buttonSignOut;

        signoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            fragmentNavigation.replaceFragment(new LoginFragment(), false);
        });

        //final TextView textView = binding.textSettings;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}