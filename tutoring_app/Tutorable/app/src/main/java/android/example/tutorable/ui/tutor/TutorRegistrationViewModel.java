package android.example.tutorable.ui.tutor;

import android.example.tutorable.Appointment;

import java.util.ArrayList;
import java.util.Date;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TutorRegistrationViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private ArrayList<Appointment> appointments;
    private final int MAX_APPOINTMENTS = 10;

    public TutorRegistrationViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is the Tutor registration fragment");
        appointments = new ArrayList<Appointment>(MAX_APPOINTMENTS);
        appointments.add(new Appointment("John Mathers", "Math", "Calculus I",
                "Olsen Hall", 3, new Date(2021, 12, 7, 15, 0)));
        appointments.add(new Appointment("Elizabeth McQueen", "English",
                "College Writing I", "Olney Hall", 1, new Date(2021, 12, 9,
                11, 0)));
        appointments.add(new Appointment("Wilbertson III", "Engineering",
                "Logic Design", "Ball Hall", 2, new Date(2021, 12, 13, 9, 0)));
        appointments.add(new Appointment("Sid the Science Kid", "Computer " +
                "Science", "Computing I", "Bad TV", 9, new Date(2011,
                3, 19, 1, 30)));
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public LiveData<String> getText() {
        return mText;
    }
}