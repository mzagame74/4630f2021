package android.example.tutorable.ui.appointments;

import android.example.tutorable.data.Appointment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;

public class AppointmentsViewModel extends ViewModel {
    //private MutableLiveData<String> mText;
    private ArrayList<Appointment> appointments;
    private final int MAX_APPOINTMENTS = 10;        // 10 appointments max

    public AppointmentsViewModel() {
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
        //mText = new MutableLiveData<>();
        //mText.setValue("This is the appointments fragment");
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /*public LiveData<String> getText() {
        return mText;
    }*/
}