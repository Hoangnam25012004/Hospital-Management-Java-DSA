package sr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorList {
    private List<Doctor> doctors;

    public DoctorList() {
        doctors = new ArrayList<>();
        loadFromFile();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        saveToFile();
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public Doctor findDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DoctorList.txt"))) {
            for (Doctor doctor : doctors) {
                writer.write(doctor.getId() + "," + doctor.getName() + "," + doctor.getContact() + "," + doctor.getSpecialty() + "," + doctor.getFees());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        doctors.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("DoctorList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); 
                if (parts.length == 5) {
                    String id = parts[0];
                    String name = parts[1];
                    String contact = parts[2];
                    String specialty = parts[3];
                    int fees = Integer.parseInt(parts[4]);
                    doctors.add(new Doctor(id, name, contact, specialty, fees));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Doctor doctor : doctors) {
            sb.append(doctor.toString()).append("\n");
        }
        return sb.toString();
    }
}