package sr;

import javax.swing.*;
import java.awt.*;

public class UI {
    private DoctorList doctorList;
    private PatientList patientList;
    private CheckupManager checkupManager;

    public UI() {
        doctorList = new DoctorList();
        patientList = new PatientList();
        checkupManager = new CheckupManager();
    }

    public void launch() {
        JFrame frame = new JFrame("Hospital Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(173, 216, 230)); // Light blue

       // "HOANGNAM HOSPITAL" label
        JLabel hospitalLabel = new JLabel("<html><div style='text-align: center;'>HOANG NAM<br>HOSPITAL</div></html>", SwingConstants.CENTER);
        hospitalLabel.setFont(new Font("Arial", Font.BOLD, 100));
        hospitalLabel.setForeground(Color.BLACK);

        // Increase width to ensure all text fits
        int labelWidth = 800; 
        int labelHeight = 500;
        hospitalLabel.setBounds(250, (screenSize.height - labelHeight) / 2 - 400, labelWidth, labelHeight); 
        frame.add(hospitalLabel);

        // Menu panel on the right
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(screenSize.width - 900, screenSize.height / 4 - 200, 600, 1000);
        menuPanel.setLayout(new GridLayout(6, 1, 20, 20)); // 6 rows, 1 column, spacing
        menuPanel.setOpaque(false);

        // Menu title
        JLabel menuTitle = new JLabel("MENU", SwingConstants.CENTER);
        menuTitle.setFont(new Font("Arial", Font.BOLD, 35));
        menuTitle.setOpaque(true);
        menuTitle.setBackground(new Color(255, 223, 0)); // Yellow
        menuTitle.setForeground(Color.BLACK);
        menuPanel.add(menuTitle);

        // Load the image using ImageIcon
        ImageIcon imageIcon = new ImageIcon("../Hospital Management/Hospital Management/doctor.jpg"); 

        // Scale the image (optional)
        Image image = imageIcon.getImage(); // Get the Image from ImageIcon
        Image scaledImage = image.getScaledInstance(900, 600, Image.SCALE_SMOOTH); // Resize the image
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled image

        // Add the image to a JLabel
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(200, 500, 900, 600); // Set position and size
        frame.add(imageLabel);

        // Show the frame
        frame.setVisible(true);

        // Add buttons
        String[] buttonLabels = {"Input Doctor", "Input Patient", "Doctor List", "Patient List", "Waiting List"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 25));
            button.setBackground(new Color(255, 165, 0)); // Orange
            button.setForeground(Color.BLACK);
            menuPanel.add(button);

            // Add action listeners (as in previous code)
            if (label.equals("Input Doctor")) {
                button.addActionListener(e -> {
                    String id = JOptionPane.showInputDialog("Enter Doctor ID:");
                    String name = JOptionPane.showInputDialog("Enter Doctor Name:");
                    String contact = JOptionPane.showInputDialog("Enter Doctor Contact:");
                    String specialty = JOptionPane.showInputDialog("Enter Doctor Specialty:");
                    int fees = Integer.parseInt(JOptionPane.showInputDialog("Enter Doctor Fees:"));
                    doctorList.addDoctor(new Doctor(id, name, contact, specialty, fees));
                });
            } else if (label.equals("Input Patient")) {
                button.addActionListener(e -> {
                    String id = JOptionPane.showInputDialog("Enter Patient ID:");
                    String name = JOptionPane.showInputDialog("Enter Patient Name:");
                    String contact = JOptionPane.showInputDialog("Enter Patient Contact:");
                    patientList.addPatient(new Patient(id, name, contact));
                });
            } else if (label.equals("Doctor List")) {
                button.addActionListener(e -> showList(frame, "Doctor List", doctorList.toString()));
            } else if (label.equals("Patient List")) {
                button.addActionListener(e -> showList(frame, "Patient List", patientList.toString()));
            } else if (label.equals("Waiting List")) {
                button.addActionListener(e -> {
                    String doctorId;
                    Doctor doctor;
                    while (true) {
                        doctorId = JOptionPane.showInputDialog("Enter Doctor ID (0 to exit):");
                        if (doctorId.equals("0")) return;
                        doctor = doctorList.findDoctorById(doctorId);
                        if (doctor != null) break;
                        JOptionPane.showMessageDialog(frame, "Doctor not found. Try again.");
                    }

                    while (true) {
                        String patientId = JOptionPane.showInputDialog("Enter Patient ID (0 to exit):");
                        if (patientId.equals("0")) break;
                        Patient patient = patientList.getPatients().stream()
                                .filter(p -> p.getId().equals(patientId))
                                .findFirst()
                                .orElse(null);
                        if (patient == null) {
                            JOptionPane.showMessageDialog(frame, "Patient not found. Try again.");
                            continue;
                        }

                        String priorityStr = JOptionPane.showInputDialog("Enter Priority for Patient " + patient.getName() + " (0: emergency, 1: quick, 2: basic):");
                        int priority = Integer.parseInt(priorityStr);
                        String recommendation = JOptionPane.showInputDialog("Enter Recommendation for Patient " + patient.getName() + ":");

                        Checkup checkup = new Checkup(doctor, patient, priority, recommendation);
                        checkupManager.addCheckup(checkup);
                    }

                    StringBuilder sb = new StringBuilder("Waiting List:\n");
                    for (Checkup checkup : checkupManager.getCheckupsByDoctor(doctor)) {
                        sb.append(checkup.getPatient().getId()).append("; ")
                                .append(checkup.getPatient().getName()).append("; Priority: ")
                                .append(checkup.getPriority()).append("; Recommendation: ")
                                .append(checkup.getRecommendation()).append("\n");
                    }
                    showList(frame, "Waiting List", sb.toString());
                });
            }
        }

        frame.add(menuPanel);
        frame.setVisible(true);
    }

    private void showList(JFrame frame, String title, String content) {
        JTextArea textArea = new JTextArea(content);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(frame.getWidth() - 100, frame.getHeight() - 200));

        JOptionPane.showMessageDialog(frame, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
