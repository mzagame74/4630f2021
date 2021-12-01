/*
 * Tutorable Copyright 2021
 * Appointments.java maintains an ArrayList of Appointment(s)
 */
package android.example.tutorable.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

public class Appointments {
    private ArrayList<Appointment> appointments;
    private final int MAX_APPOINTMENTS = 10;        // 10 appointments max

    private static class Appointment {
        private String tutorName;
        private String location;
        private Date date;

        public Appointment(String tutorName, String location, Date date) {
            this.tutorName = tutorName;
            this.location = location;
            this.date = date;
        }

        public String getTutorName() {
            return tutorName;
        }

        public String getLocation() {
            return location;
        }

        public Date getDate() {
            return date;
        }

        public void setTutorName(String tutorName) {
            this.tutorName = tutorName;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @NonNull
        public String toString() {
            return tutorName + " " + location + " " + date.toString();
        }
    }

    public Appointments() {
        appointments = new ArrayList<Appointment>(MAX_APPOINTMENTS);
    }

    public Appointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public Appointment getAppointment(int index) {
        return appointments.get(index);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

}
