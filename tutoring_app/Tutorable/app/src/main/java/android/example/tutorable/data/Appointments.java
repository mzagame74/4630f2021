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

    public static class Appointment {
        private int hours;
        private String tutorName;
        private String location;
        private String subject;
        private String course;
        private Date date;

        public Appointment(String tutorName, String subject,
                           String course, String location,
                           int hours, Date date) {
            this.tutorName = tutorName;
            this.subject = subject;
            this.course = course;
            this.location = location;
            this.hours = hours;
            this.date = date;
        }

        public String getTutorName() {
            return tutorName;
        }
        public String getSubject() { return subject; }
        public String getCourse() { return course; }
        public String getLocation() {
            return location;
        }
        public int getHours() { return hours; }
        public Date getDate() {
            return date;
        }

        public void setTutorName(String tutorName) { this.tutorName = tutorName; }
        public void setSubject(String subject) { this.subject = subject; }
        public void setCourse(String course) { this.course = course; }
        public void setLocation(String location) {
            this.location = location;
        }
        public void setHours(int hours) { this.hours = hours; }
        public void setDate(Date date) {
            this.date = date;
        }

        @NonNull
        public String toString() {
            return course + " w/ " + tutorName + " at " + location + " from " +
                    date.getHours() + ":00 to " + (date.getHours() + hours) + ":00\n";
        }
    }

    public Appointments() {
        appointments = new ArrayList<Appointment>(MAX_APPOINTMENTS);
    }

    public Appointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

}
