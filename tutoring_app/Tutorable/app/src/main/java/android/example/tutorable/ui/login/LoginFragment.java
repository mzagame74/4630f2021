package android.example.tutorable.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.R;
import android.example.tutorable.ui.home.HomeFragment;
import android.example.tutorable.ui.register.RegisterFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.example.tutorable.databinding.FragmentLoginBinding;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;
    private FragmentNavigation fragmentNavigation;
    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button loginButton, registerButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fragmentNavigation = (FragmentNavigation) requireActivity();
        mAuth = FirebaseAuth.getInstance();

        // initialize view elements
        emailEditText = binding.email;
        passwordEditText = binding.password;
        loginButton = binding.login;
        registerButton = binding.register;

        loginButton.setOnClickListener(view -> validateForm());
        registerButton.setOnClickListener(view ->
                fragmentNavigation.navigateToFragment(R.id.navigation_register));

        return root;
    }

    private void validateForm() {
        Drawable errorIcon = AppCompatResources.getDrawable(requireContext(),
                R.drawable.ic_error);
        if (errorIcon != null) {
            errorIcon.setBounds(0, 0, errorIcon.getIntrinsicWidth(),
                    errorIcon.getIntrinsicHeight());
        }
        if (TextUtils.isEmpty(emailEditText.getText().toString().trim())) {
            emailEditText.setError("Please Enter An Email Address", errorIcon);
        }
        if (TextUtils.isEmpty(passwordEditText.getText().toString().trim())) {
            passwordEditText.setError("Please Enter A Password", errorIcon);
        }

        if ((!emailEditText.getText().toString().isEmpty()) &&
                (!passwordEditText.getText().toString().isEmpty())) {
            // validate email
            if (emailEditText.getText().toString().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")) {
                // validate password
                if (passwordEditText.getText().toString().length() >= 5) {
                    firebaseSignIn();
                } else {
                    passwordEditText.setError("Password Must Be At Least 5 " +
                                    "Characters Long",
                            errorIcon);
                }
            } else {
                emailEditText.setError("Please Enter A Valid Email Address",
                        errorIcon);
            }
        }
    }

    private void firebaseSignIn() {
        loginButton.setEnabled(false);
        loginButton.setAlpha(0.5f);
        mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(),
                passwordEditText.getText().toString()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Login Success!",
                        Toast.LENGTH_SHORT).show();
                fragmentNavigation.navigateToFragment(R.id.navigation_home);
            } else {
                loginButton.setEnabled(true);
                loginButton.setAlpha(1.0f);
                Toast.makeText(requireContext(),
                        Objects.requireNonNull(task.getException()).getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}