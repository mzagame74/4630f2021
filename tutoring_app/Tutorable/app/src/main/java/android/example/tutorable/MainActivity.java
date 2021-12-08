package android.example.tutorable;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.example.tutorable.ui.login.LoginFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.example.tutorable.databinding.ActivityMainBinding;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements FragmentNavigation {

    private ActivityMainBinding binding;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // start login activity
        getSupportFragmentManager().beginTransaction().add(R.id.container,
                new LoginFragment()).commit();

        // TODO: do all this stuff after successfully logging in
        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_appointments,
                R.id.navigation_settings)
                .build();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController;
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            throw new IllegalStateException("Activity " + this + " does not " +
                    "have a NavHostFragment");
        }
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/
    }

    // TODO: fix so that fragments are switched properly
    public void replaceFragment(Fragment fragment, boolean addToStack) {
        FrameLayout frameLayout = binding.navHostFragmentActivityMain;
        frameLayout.removeAllViews();
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (addToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}