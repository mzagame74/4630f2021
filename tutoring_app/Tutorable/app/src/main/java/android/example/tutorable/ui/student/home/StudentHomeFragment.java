package android.example.tutorable.ui.student.home;

import android.example.tutorable.FragmentNavigation;
import android.example.tutorable.databinding.FragmentStudentHomeBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class StudentHomeFragment extends Fragment {

    private StudentHomeViewModel studentHomeViewModel;
    private FragmentStudentHomeBinding binding;
    private FragmentNavigation fragmentNavigation;
    private Button lookForAppointmentsButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentHomeViewModel =
                new ViewModelProvider(this).get(StudentHomeViewModel.class);

        binding = FragmentStudentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fragmentNavigation = (FragmentNavigation) requireActivity();

        //final TextView textView = binding.textHome;
        /*studentHomeViewModel.getText().observe(getViewLifecycleOwner(),
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