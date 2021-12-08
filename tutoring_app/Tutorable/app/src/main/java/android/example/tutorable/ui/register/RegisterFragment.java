package android.example.tutorable.ui.register;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.R;
import android.example.tutorable.databinding.FragmentRegisterBinding;
import android.example.tutorable.ui.login.LoginFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private FragmentRegisterBinding binding;
    private EditText emailEditText, passwordEditText, confirmPasswordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        FragmentNavigation onFragmentInteraction =
                (FragmentNavigation) requireActivity();

        emailEditText = binding.email;
        passwordEditText = binding.password;
        confirmPasswordEditText = binding.confirmPassword;
        final Button loginButton = binding.login;
        final Button registerButton = binding.register;

        registerButton.setOnClickListener(view -> validateEmptyForm());

        loginButton.setOnClickListener(view ->
                onFragmentInteraction.replaceFragment(new LoginFragment(),
                        false));

        return root;
    }

    private void validateEmptyForm() {
        Drawable icon = AppCompatResources.getDrawable(requireContext(),
                R.drawable.ic_error);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(),
                icon.getIntrinsicHeight());
        if (TextUtils.isEmpty(emailEditText.getText().toString().trim())) {
            emailEditText.setError("Please Enter A Valid Email Address", icon);
        }
        if (TextUtils.isEmpty(passwordEditText.getText().toString().trim())) {
            passwordEditText.setError("Please Enter A Password", icon);
        }
        if (TextUtils.isEmpty(confirmPasswordEditText.getText().toString().trim())) {
            confirmPasswordEditText.setError("Please Enter A Password", icon);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}