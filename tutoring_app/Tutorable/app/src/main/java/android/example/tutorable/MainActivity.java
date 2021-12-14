package android.example.tutorable;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.example.tutorable.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements FragmentNavigation {

    private ActivityMainBinding binding;
    private NavController navController;
    private FirebaseAuth mAuth;
    private ActionBar actionBar;    // TODO: fix back button in action bar

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mAuth = FirebaseAuth.getInstance();

        // set up navigation
        BottomNavigationView navView = binding.navView;
        navView.setVisibility(View.VISIBLE);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_appointments,
                R.id.navigation_settings)
                .build();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            throw new IllegalStateException("Activity " + this + " does not " +
                    "have a NavHostFragment");
        }
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            navigateToFragment(R.id.navigation_home);
        } else {
            // start login activity
            navigateToFragment(R.id.action_navigation_home_to_navigation_login);
        }
    }

    public void navigateToFragment(int resId) {
        if (resId == R.id.navigation_login || resId == R.id.navigation_register || resId == R.id.navigation_tutor_registration
                || resId == R.id.action_navigation_home_to_navigation_login ||
                resId == R.id.action_navigation_settings_to_navigation_login ||
                resId == R.id.action_navigation_login_to_navigation_register ||
                resId == R.id.action_navigation_register_to_navigation_login ||
                resId == R.id.action_navigation_register_to_navigation_tutor_registration) {
            binding.navView.setVisibility(View.GONE);
        } else {
            binding.navView.setVisibility(View.VISIBLE);
        }
        navController.navigate(resId);
    }
}