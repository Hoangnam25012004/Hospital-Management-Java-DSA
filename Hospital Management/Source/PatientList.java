package sr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientList {
    private List<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
        loadFromFile();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        saveToFile();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("PatientList.txt"))) {
            for (Patient patient : patients) {
                writer.write(patient.getId() + "," + patient.getName() + "," + patient.getContact());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        patients.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("PatientList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Change to ","
                
                if (parts.length == 3) {
                    String id = parts[0];
                    String name = parts[1];
                    String contact = parts[2];
                    
                    patients.add(new Patient(id, name, contact));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Patient patient : patients) {
            sb.append(patient.toString()).append("\n");
        }
        return sb.toString();
    }
}