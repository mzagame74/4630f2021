package android.example.tutorable.ui.register;

import android.example.tutorable.OnFragmentInteractionListener;
import android.example.tutorable.R;
import android.example.tutorable.databinding.FragmentLoginBinding;
import android.example.tutorable.databinding.FragmentRegisterBinding;
import android.example.tutorable.ui.login.LoginFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private FragmentRegisterBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        OnFragmentInteractionListener onFragmentInteraction =
                (OnFragmentInteractionListener) requireActivity();

        Button loginButton = root.findViewById(R.id.login);
        loginButton.setOnClickListener(view ->
                onFragmentInteraction.replaceFragment(new LoginFragment(),
                        false));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}