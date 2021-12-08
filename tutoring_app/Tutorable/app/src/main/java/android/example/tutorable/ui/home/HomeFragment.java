package android.example.tutorable.ui.home;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.ui.appointments.AppointmentsFragment;
import android.example.tutorable.ui.login.LoginFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.example.tutorable.R;
import android.example.tutorable.databinding.FragmentHomeBinding;

import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private FragmentNavigation fragmentNavigation;
    private Button lookForAppointmentsButton, signoutButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fragmentNavigation = (FragmentNavigation) requireActivity();

        lookForAppointmentsButton = binding.buttonLookForTutors;
        signoutButton = binding.signOut;

        lookForAppointmentsButton.setOnClickListener(view ->
                fragmentNavigation.replaceFragment(new AppointmentsFragment(),
                        false));

        // TODO: move this button to the settings screen
        signoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            fragmentNavigation.replaceFragment(new LoginFragment(), false);
        });

        //final TextView textView = binding.textHome;
        /*homeViewModel.getText().observe(getViewLifecycleOwner(),
                new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}