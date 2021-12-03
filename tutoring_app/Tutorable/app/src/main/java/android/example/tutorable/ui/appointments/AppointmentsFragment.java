package android.example.tutorable.ui.appointments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.example.tutorable.R;
import android.example.tutorable.data.Appointment;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.example.tutorable.databinding.FragmentAppointmentsBinding;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AppointmentsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AppointmentsViewModel appointmentsViewModel;
    private FragmentAppointmentsBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Spinner subjectSpinner, courseSpinner;
    private TextView latTextView, lonTextView;
    public static final int PERMISSION_ID = 100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appointmentsViewModel =
                new ViewModelProvider(this).get(AppointmentsViewModel.class);

        binding = FragmentAppointmentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        subjectSpinner = (Spinner) root.findViewById(R.id.spinner_subject);
        courseSpinner = (Spinner) root.findViewById(R.id.spinner_course);

        // create an ArrayAdapter for the subject spinner using the string
        // array and a default spinner layout
        ArrayAdapter<CharSequence> subjectAdapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.subject_array,
                        android.R.layout.simple_spinner_item);
        // specify the layout to use when the list of choices appears
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // apply the adapter and item listener to the spinner
        subjectSpinner.setAdapter(subjectAdapter);
        subjectSpinner.setOnItemSelectedListener(this);

        // TODO: remove location testing text when ready
        latTextView = root.findViewById(R.id.latTextView);
        lonTextView = root.findViewById(R.id.lonTextView);

        // initialize location provider client
        fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(getActivity());

        // check for location permissions
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // permission granted
            getCurrentLocation();
        } else {
            // permission not granted, request permission
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_ID);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch((String) parent.getItemAtPosition(position)) {
            case "Math":
                // TODO: modify the available courses based on selection
                break;
            case "English":
                break;
            case "Electrical Engineering":
                break;
            case "Computer Science":
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // check for location permissions
        if (requestCode == PERMISSION_ID && grantResults.length > 0
                && (grantResults[0] + grantResults[1] ==
                PackageManager.PERMISSION_GRANTED)) {
            // permission granted
            getCurrentLocation();
        } else {
            // permission not granted, display permission denied message
            Toast.makeText(getActivity(), "Permission denied",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        // initialize location manager
        LocationManager locationManger =
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // if location service is enabled then get last location
        if (locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManger.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(
                    new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            // initialize location
                            Location location = task.getResult();
                            // make sure last location is not null
                            if (location != null) {
                                // do something with location
                                latTextView.setText(String.valueOf(location.getLatitude()));
                                lonTextView.setText(String.valueOf(location.getLongitude()));
                            } else {
                                // initialize location request
                                LocationRequest locationRequest = new LocationRequest()
                                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                        .setInterval(10000)
                                        .setFastestInterval(1000)
                                        .setNumUpdates(1);
                                // initialize location call back
                                LocationCallback locationCallback =
                                        new LocationCallback() {
                                            @Override
                                            public void onLocationResult(
                                                    @NonNull LocationResult locationResult) {
                                                // initialize location
                                                Location location =
                                                        locationResult.getLastLocation();
                                                latTextView.setText(String.valueOf(location.getLatitude()));
                                                lonTextView.setText(String.valueOf(location.getLongitude()));
                                            }
                                        };
                                // request location updates
                                fusedLocationProviderClient.requestLocationUpdates(
                                        locationRequest, locationCallback, Looper.myLooper());
                            }
                        }
                    });
        } else {
            // location service is not enabled, prompt location settings
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}