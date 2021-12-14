package android.example.tutorable.ui.login;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.R;
import android.example.tutorable.databinding.FragmentLoginBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

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

        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            validateForm(email, password);
        });
        registerButton.setOnClickListener(view ->
                fragmentNavigation.navigateToFragment(R.id.navigation_register));

        return root;
    }

    private void validateForm(String email, String password) {
        Drawable errorIcon = AppCompatResources.getDrawable(requireContext(),
                R.drawable.ic_error);
        if (errorIcon != null) {
            errorIcon.setBounds(0, 0, errorIcon.getIntrinsicWidth(),
                    errorIcon.getIntrinsicHeight());
        }

        if (TextUtils.isEmpty(email.trim())) {
            emailEditText.setError("Please Enter An Email Address", errorIcon);
            emailEditText.requestFocus();
        } else if (TextUtils.isEmpty(password.trim())) {
            passwordEditText.setError("Please Enter A Password", errorIcon);
            passwordEditText.requestFocus();
        }

        if ((!email.isEmpty()) &&
                (!password.isEmpty())) {
            // validate email
            if (email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")) {
                // validate password
                if (password.length() >= 5) {
                    firebaseSignIn(email, password);
                } else {
                    passwordEditText.setError("Password Must Be At Least 5 " +
                                    "Characters Long",
                            errorIcon);
                    passwordEditText.requestFocus();
                }
            } else {
                emailEditText.setError("Please Enter A Valid Email Address",
                        errorIcon);
                emailEditText.requestFocus();
            }
        }
    }

    private void firebaseSignIn(String email, String password) {
        loginButton.setEnabled(false);
        loginButton.setAlpha(0.5f);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Login Success!",
                        Toast.LENGTH_SHORT).show();
                fragmentNavigation.navigateToFragment(R.id.action_navigation_login_to_navigation_home);
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