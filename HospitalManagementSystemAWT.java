import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class HospitalManagementSystemAWT extends Frame implements ActionListener {
    PatientList patientList = new PatientList();
    AppointmentQueue appointmentQueue = new AppointmentQueue();
    BillingHistory billingHistory = new BillingHistory();
    LinkedList<Doctor> doctors = new LinkedList<>();
    HashMap<String, String> diseaseToSpecialtyMap = new HashMap<>();


    TextArea outputArea;
    TextField idField, nameField, ageField, diseaseField;
    Button addPatientButton, listPatientsButton,viewDoctorsButton, addAppointmentButton, treatAppointmentButton, addBillButton, viewBillButton;
    Button minimizeButton, closeButton;
    Label statusBar;

    public HospitalManagementSystemAWT() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Creating panels
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(0, 1)); // Vertical layout for buttons
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Creating buttons
        addPatientButton = createButton("Add Patient", new Color(100, 149, 237));
        listPatientsButton = createButton("List Patients", new Color(240, 128, 128));
        viewDoctorsButton = createButton("View Doctors", new Color(144, 238, 144));
        addAppointmentButton = createButton("Add Appointment", new Color(240, 230, 140));
        treatAppointmentButton = createButton("Treat Appointment", new Color(173, 216, 230));
        addBillButton = createButton("Add Bill", new Color(255, 182, 193));
        viewBillButton = createButton("View Bill", new Color(144, 238, 144));
        minimizeButton = createButton("Minimize", new Color(192, 192, 192));
        closeButton = createButton("Close", Color.RED);

        // Adding buttons to the button panel
        buttonPanel.add(addPatientButton);
        buttonPanel.add(listPatientsButton);
        buttonPanel.add(viewDoctorsButton);
        buttonPanel.add(addAppointmentButton);
        buttonPanel.add(treatAppointmentButton);
        buttonPanel.add(addBillButton);
        buttonPanel.add(viewBillButton);
        buttonPanel.add(minimizeButton);
        buttonPanel.add(closeButton);

        // Setting up input area and status bar
        Panel inputPanel = new Panel();
        inputPanel.setLayout(new GridLayout(5, 2)); // 5 rows, 2 columns for input fields

        // Creating text fields for patient information
        idField = new TextField(30);
        nameField = new TextField(30);
        ageField = new TextField(30);
        diseaseField = new TextField(30);

        // Adding labels and text fields to input panel
        inputPanel.add(new Label("Patient ID:"));
        inputPanel.add(idField);
        inputPanel.add(new Label("Patient Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new Label("Patient Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new Label("Disease:"));
        inputPanel.add(diseaseField);

        outputArea = new TextArea(10, 50);
        outputArea.setEditable(false); // Make the output area non-editable
        inputPanel.add(new Label("Output:"));
        inputPanel.add(outputArea);

        // Adding components to the frame
        add(buttonPanel, BorderLayout.WEST);
        add(inputPanel, BorderLayout.CENTER);

        statusBar = new Label("Welcome to the Hospital");
        statusBar.setBackground(new Color(192, 192, 192));
        statusBar.setFont(new Font("Arial", Font.PLAIN, 14));
        add(statusBar, BorderLayout.SOUTH);

        // Set button listeners
        addPatientButton.addActionListener(this);
        listPatientsButton.addActionListener(this);
        viewDoctorsButton.addActionListener(this);
        addAppointmentButton.addActionListener(this);
        treatAppointmentButton.addActionListener(this);
        addBillButton.addActionListener(this);
        viewBillButton.addActionListener(this);
        minimizeButton.addActionListener(this);
        closeButton.addActionListener(this);

        // Handle close window event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Set frame properties
        setTitle("Hospital Management System");
        setSize(600, 500);
        setVisible(true);

        // Mapping diseases to specialties
        diseaseToSpecialtyMap.put("heart attack", "Cardiologist");
        diseaseToSpecialtyMap.put("fever", "Physician");
        diseaseToSpecialtyMap.put("cold", "Pulmonologist");
        diseaseToSpecialtyMap.put("flu", "ENT Specialist");
        diseaseToSpecialtyMap.put("amnesia", "Neurosurgeon");
        diseaseToSpecialtyMap.put("accident", "Orthopedic");
        diseaseToSpecialtyMap.put("cancer", "Oncologist");
        diseaseToSpecialtyMap.put("diabetes", "Endocrinologist");

        // Predefined list of doctors
        doctors.add(new Doctor("D01", "Dr. Vijayakumar", "Cardiologist"));
        doctors.add(new Doctor("D02", "Dr. selvaraj", "Physician"));
        doctors.add(new Doctor("D03", "Dr. Lokesh", "Pulmonologist"));
        doctors.add(new Doctor("D04", "Dr. Vetri", "ENT Specialist"));
        doctors.add(new Doctor("D05", "Dr. Amaran", "Neurosurgeon"));
        doctors.add(new Doctor("D06", "Dr. Abhitha", "Orthopedic"));
        doctors.add(new Doctor("D07", "Dr. Pallavi", "Oncologist"));
        doctors.add(new Doctor("D08", "Dr. Karthik", "Endocrinologist"));
    }

    private Button createButton(String label, Color color) {
        Button button = new Button(label);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        // Handle Minimize Button click
        if (e.getSource() == minimizeButton) {
            setState(Frame.ICONIFIED);  // Minimize the window
        }

        // Handle Close Button click
        if (e.getSource() == closeButton) {
            System.exit(0);  // Close the application
        }


            if (e.getSource() == viewDoctorsButton) {
                outputArea.setText("List of Doctors:\n" + getDoctorList());
            }


        // Add patient functionality
        if (e.getSource() == addPatientButton) {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            int age;
            try {
                age = Integer.parseInt(ageField.getText().trim());
            } catch (NumberFormatException ex) {
                outputArea.setText("Age must be a valid integer.");
                return;
            }
            String disease = diseaseField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || disease.isEmpty()) {
                outputArea.setText("Please fill in all fields.");
                return;
            }

            patientList.addPatient(new Patient(id, name, age, disease));
            outputArea.setText("Patient added: " + name);
            statusBar.setText("Patient " + name + " added successfully.");

            // Clear input fields after adding
            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            diseaseField.setText("");
        }

        // List patients functionality
        if (e.getSource() == listPatientsButton) {
            outputArea.setText("List of Patients:\n" + patientList.toString());
        }

        // Add appointment functionality
        if (e.getSource() == addAppointmentButton) {
            String patientId = idField.getText().trim();
            Patient patient = patientList.getPatientById(patientId);
            if (patient != null) {
                Doctor assignedDoctor = findDoctorForPatient(patient.disease);
                if (assignedDoctor != null) {
                    appointmentQueue.addAppointment(patient, false);
                    outputArea.setText("Appointment added for " + patient.name + " with " + assignedDoctor.name);
                } else {
                    outputArea.setText("No doctor available for the disease: " + patient.disease);
                }
            } else {
                outputArea.setText("Patient not found.");
            }
        }

        // Treat appointment functionality
        if (e.getSource() == treatAppointmentButton) {
            Patient treatedPatient = appointmentQueue.treatPatient();
            if (treatedPatient != null) {
                outputArea.setText("Appointment treated for " + treatedPatient.name);
            } else {
                outputArea.setText("No patients to treat.");
            }
        }

        // Add bill functionality
        if (e.getSource() == addBillButton) {
            String patientId = idField.getText().trim();
            String disease = diseaseField.getText().trim();
            int days;
            try {
                days = Integer.parseInt(ageField.getText().trim()); // Assuming days are also input in ageField
            } catch (NumberFormatException ex) {
                outputArea.setText("Days must be a valid integer.");
                return;
            }

            Patient patient = patientList.getPatientById(patientId);
            if (patient != null) {
                billingHistory.addBill(patient.name, disease, days);
                outputArea.setText("Bill added for " + patient.name);
            } else {
                outputArea.setText("Patient not found.");
            }
        }

        // View bill functionality
        if (e.getSource() == viewBillButton) {
            String patientId = idField.getText().trim();
            Patient patient = patientList.getPatientById(patientId);
            if (patient != null) {
                String bill = billingHistory.getBillForPatient(patient.name);
                outputArea.setText("Bill for " + patient.name + ":\n" + bill);
            } else {
                outputArea.setText("Patient not found.");
            }
        }
    }
    private String getDoctorList() {
        StringBuilder sb = new StringBuilder();
        for (Doctor doctor : doctors) {
            sb.append(doctor.name).append(" - ").append(doctor.specialty).append("\n");
        }
        return sb.toString();
    }


    public Doctor findDoctorForPatient(String disease) {
        String specialty = diseaseToSpecialtyMap.get(disease.toLowerCase());
        if (specialty != null) {
            for (Doctor doctor : doctors) {
                if (doctor.specialty.equalsIgnoreCase(specialty)) {
                    return doctor;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new HospitalManagementSystemAWT();
    }
}

class Patient {
    String id, name, disease;
    int age;

    public Patient(String id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Disease: " + disease;
    }
}

class PatientList {
    ArrayList<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient getPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.id.equals(id)) {
                return patient;
            }
        }
        return null;  // Patient not found
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Patient patient : patients) {
            sb.append(patient.toString()).append("\n");
        }
        return sb.toString();
    }
}

class AppointmentQueue {
    LinkedList<Patient> appointments = new LinkedList<>();

    public void addAppointment(Patient patient, boolean treated) {
        appointments.add(patient);
    }

    public Patient treatPatient() {
        return appointments.poll();  // Treat the first patient in the queue
    }
}

class BillingHistory {
    // Use a HashMap to store bills for each patient
    private Map<String, List<String>> billsMap = new HashMap<>();

    // Add a bill for a patient
    public void addBill(String patientName, String disease, int days) {
        // Calculate the amount
        int amount = days * 100;
        String bill = "Patient: " + patientName + ", Disease: " + disease + ", Days: " + days + ", Amount: Rs" + amount;

        // Add the bill to the corresponding patient's list
        billsMap.putIfAbsent(patientName, new ArrayList<>()); // Initialize if absent
        billsMap.get(patientName).add(bill);
    }

    // Get the bill for a specific patient
    public String getBillForPatient(String patientName) {
        List<String> patientBills = billsMap.get(patientName);
        if (patientBills != null && !patientBills.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String bill : patientBills) {
                sb.append(bill).append("\n");
            }
            return sb.toString();
        }
        return "No bills found for patient: " + patientName;
    }

    // Optional: Display all bills (for testing)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : billsMap.entrySet()) {
            sb.append("Bills for ").append(entry.getKey()).append(":\n");
            for (String bill : entry.getValue()) {
                sb.append(bill).append("\n");
            }
        }
        return sb.toString();
    }
}


class Doctor {
    String id, name, specialty;

    public Doctor(String id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }
}

