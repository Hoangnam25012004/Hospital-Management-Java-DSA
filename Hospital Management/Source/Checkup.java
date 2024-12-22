package sr;

public class Checkup {
    Doctor Doctor;
    Patient Patient;
    int Priority;
    String Recommendation;

    public Checkup(Doctor Doctor, Patient Patient, int Priority, String Recommendation) {
        this.Doctor = Doctor;
        this.Patient = Patient; 
        this.Priority = Priority;
        this.Recommendation = Recommendation;
    }
    // Getters
    public Doctor getDoctor() {
        return Doctor;
    }

    public Patient getPatient() {
        return Patient;
    }

    public int getPriority() {
        return Priority;
    }

    public String getRecommendation() {
        return Recommendation;
    }
     

    // Setters
    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public void setPatient(Patient Patient) {
        this.Patient = Patient;
    }

    public void setPriority(int Priority) {
        this.Priority = Priority;
    }

    public void setRecommendation(String Recommendation) {
        this.Recommendation = Recommendation;
    }


    @Override
    public String toString() {
        return "Checkup:" +
               "Doctor=" + Doctor.toString() +
               ", Patient=" + Patient.toString() +
               ", Priority=" + Priority +
               ", Recommendation='" + Recommendation;
    }
}

