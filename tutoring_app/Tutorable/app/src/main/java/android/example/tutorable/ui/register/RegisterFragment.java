package android.example.tutorable.ui.register;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.MainActivity;
import android.example.tutorable.R;
import android.example.tutorable.databinding.FragmentRegisterBinding;
import android.example.tutorable.ui.home.HomeFragment;
import android.example.tutorable.ui.login.LoginFragment;
import android.example.tutorable.ui.register.RegisterViewModel;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private RegisterViewModel registerViewModel;
    private FragmentRegisterBinding binding;
    private FragmentNavigation fragmentNavigation;
    private FirebaseAuth mAuth;
    private EditText nameEditText, emailEditText, passwordEditText,
            confirmPasswordEditText;
    private Spinner schoolSpinner;
    private Button loginButton, registerButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fragmentNavigation = (FragmentNavigation) requireActivity();
        mAuth = FirebaseAuth.getInstance();

        // initialize view elements
        nameEditText = binding.name;
        emailEditText = binding.email;
        passwordEditText = binding.password;
        confirmPasswordEditText = binding.confirmPassword;
        schoolSpinner = binding.spinnerSchool;
        loginButton = binding.login;
        registerButton = binding.register;

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

        registerButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword =
                    confirmPasswordEditText.getText().toString();
            validateForm(name, email, password, confirmPassword);
        });
        loginButton.setOnClickListener(view ->
                fragmentNavigation.navigateToFragment(R.id.navigation_login));

        return root;
    }

    private void validateForm(String name, String email, String password,
                              String confirmPassword) {
        Drawable errorIcon = AppCompatResources.getDrawable(requireContext(),
                R.drawable.ic_error);
        if (errorIcon != null) {
            errorIcon.setBounds(0, 0, errorIcon.getIntrinsicWidth(),
                    errorIcon.getIntrinsicHeight());
        }

        if (TextUtils.isEmpty(name.trim())) {
            nameEditText.setError("Please Enter A Name", errorIcon);
            nameEditText.requestFocus();
        } else if (TextUtils.isEmpty(email.trim())) {
            emailEditText.setError("Please Enter An Email Address", errorIcon);
            emailEditText.requestFocus();
        } else if (TextUtils.isEmpty(password.trim())) {
            passwordEditText.setError("Please Enter A Password", errorIcon);
            passwordEditText.requestFocus();
        } else if (TextUtils.isEmpty(confirmPassword.trim())) {
            confirmPasswordEditText.setError("Please Enter A Password",
                    errorIcon);
            confirmPasswordEditText.requestFocus();
        }

        if ((!name.isEmpty()) && (!email.isEmpty()) &&
                (!password.isEmpty()) && (!confirmPassword.isEmpty())) {
            // validate email
            if (email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\" +
                    ".[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")) {
                // validate password
                if (password.length() >= 5) {
                    // make sure both password inputs are equal
                    if (password.equals(confirmPassword)) {
                        firebaseSignUp(email, password);
                    } else {
                        confirmPasswordEditText.setError("Passwords Do Not " +
                                "Match", errorIcon);
                        confirmPasswordEditText.requestFocus();
                    }
                } else {
                    passwordEditText.setError("Please Enter At Least 5 " +
                            "Characters", errorIcon);
                    passwordEditText.requestFocus();
                }
            } else {
                emailEditText.setError("Please Enter A Valid Email Address",
                        errorIcon);
                emailEditText.requestFocus();
            }
        }
    }

    private void firebaseSignUp(String email, String password) {
        registerButton.setEnabled(false);
        registerButton.setAlpha(0.5f);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Registration Success!",
                        Toast.LENGTH_SHORT).show();
                fragmentNavigation.navigateToFragment(R.id.navigation_home);
            } else {
                registerButton.setEnabled(true);
                registerButton.setAlpha(1.0f);
                Toast.makeText(requireContext(), Objects.requireNonNull(task.getException()).getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // TODO: save selected school
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}