package android.example.tutorable.ui.register;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.R;
import android.example.tutorable.databinding.FragmentRegisterBinding;
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

public class RegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

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
        final Spinner schoolSpinner = binding.spinnerSchool;
        final Button loginButton = binding.login;
        final Button registerButton = binding.register;

        // create an ArrayAdapter for the school spinner using the string
        // array and a default spinner layout
        ArrayAdapter<CharSequence> schoolAdapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.school_array,
                        android.R.layout.simple_spinner_item);

        // specify the layout to use when the list of choices appears
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply the adapter and item listener to the spinners\
        schoolSpinner.setAdapter(schoolAdapter);
        schoolSpinner.setOnItemSelectedListener(this);

        registerButton.setOnClickListener(view -> validateForm());
        loginButton.setOnClickListener(view ->
                onFragmentInteraction.replaceFragment(new LoginFragment(),
                        false));

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
        if (TextUtils.isEmpty(confirmPasswordEditText.getText().toString().trim())) {
            confirmPasswordEditText.setError("Please Enter A Password",
                    errorIcon);
        }

        if ((!emailEditText.getText().toString().isEmpty()) &&
                (!passwordEditText.getText().toString().isEmpty()) &&
                (!confirmPasswordEditText.getText().toString().isEmpty())) {
            // validate email
            if (emailEditText.getText().toString().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")) {
                // validate password
                if (passwordEditText.getText().toString().length() >= 5) {
                    // make sure both password inputs are equal
                    if (passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
                        Toast.makeText(requireContext(), "Registration " +
                                        "Success!",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        confirmPasswordEditText.setError("Passwords Do Not " +
                                "Match", errorIcon);
                    }
                }
                else {
                    passwordEditText.setError("Please Enter At Least 5 " +
                            "Characters", errorIcon);
                }
            }
            else {
                emailEditText.setError("Please Enter A Valid Email Address",
                        errorIcon);
            }
        }
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