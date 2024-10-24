package assignment4;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
 
public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = { "Client", "Optometrist" };
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Constructor
    public Prescription(String firstName, String lastName, String address, float sphere, float axis, float cylinder, Date examinationDate, String optometrist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    public boolean addPrescription() {
        // Check conditions for prescription validity
        if (!isValidName(firstName) || !isValidName(lastName)) return false;
        if (!isValidAddress(address)) return false;
        if (!isValidSphere(sphere) || !isValidCylinder(cylinder) || !isValidAxis(axis)) return false;
        if (!isValidOptometrist(optometrist)) return false;

        // If all conditions are met, save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/swetal dave/Desktop/Java/presc.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String prescriptionData = String.format("First Name: %s, Last Name: %s, Address: %s, Sphere: %.2f, Cylinder: %.2f, Axis: %.2f, Examination Date: %s, Optometrist: %s\n",
                    firstName, lastName, address, sphere, cylinder, axis, dateFormat.format(examinationDate), optometrist);
            writer.write(prescriptionData);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addRemark(String remark, String remarkType) {
        // Check if remark type is valid
        if (!isValidRemarkType(remarkType)) return false;

        // Check if prescription already has 2 remarks
        if (postRemarks.size() >= 2) return false;

        // Validate the remark text
        if (!isValidRemarkText(remark)) return false;

        // If valid, save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/swetal dave/Desktop/Java/remark.txt", true))) {
            writer.write(String.format("Prescription ID: %d, Remark: %s, Type: %s\n", prescID, remark, remarkType));
            postRemarks.add(remark);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper Methods for Validation

    private boolean isValidName(String name) {
        return name.length() >= 4 && name.length() <= 15;
    }

    private boolean isValidAddress(String address) {
        return address.length() >= 20;
    }

    private boolean isValidSphere(float sphere) {
        return sphere >= -20.00 && sphere <= 20.00;
    }

    private boolean isValidCylinder(float cylinder) {
        return cylinder >= -4.00 && cylinder <= 4.00;
    }

    private boolean isValidAxis(float axis) {
        return axis >= 0 && axis <= 180;
    }

    private boolean isValidOptometrist(String optometrist) {
        return optometrist.length() >= 8 && optometrist.length() <= 25;
    }

    private boolean isValidRemarkType(String type) {
        for (String validType : remarkTypes) {
            if (validType.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidRemarkText(String remark) {
        String[] words = remark.split(" ");
        return words.length >= 6 && words.length <= 20 && Character.isUpperCase(remark.charAt(0));
    }

}

