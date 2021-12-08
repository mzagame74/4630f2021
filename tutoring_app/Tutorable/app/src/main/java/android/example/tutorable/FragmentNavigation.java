package android.example.tutorable;

import androidx.fragment.app.Fragment;

public interface FragmentNavigation {
    void replaceFragment(Fragment fragment, boolean addToStack);
}
