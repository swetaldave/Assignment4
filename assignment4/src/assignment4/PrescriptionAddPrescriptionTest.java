package assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import java.text.SimpleDateFormat;
import java.util.Date;

class PrescriptionAddPrescriptionTest {

    private Prescription validPrescription;
    private Prescription invalidPrescription;

    @BeforeEach
    void setUp() throws Exception {
        // Valid prescription data
        validPrescription = new Prescription(
                "John", "Doea", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 90, -2.0f, 
                new SimpleDateFormat("dd/MM/yyyy").parse("21/10/2024"), 
                "Dr. Anderson");

        // Invalid prescription data (invalid name and address)
        invalidPrescription = new Prescription(
                "Jo", "Do", 
                "Short Address", 
                -25.0f, 190, -5.0f, 
                new SimpleDateFormat("dd/MM/yyyy").parse("21/10/2024"), 
                "A");
    }

    @Test
    void testAddPrescription_ValidData() {
        assertTrue(validPrescription.addPrescription(), "Valid prescription should be added successfully.");
    }

    @Test
    void testAddPrescription_InvalidName() {
        invalidPrescription = new Prescription(
                "Jo", "Smith", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 90, -2.0f, 
                new Date(), 
                "Dr. Anderson");
        assertFalse(invalidPrescription.addPrescription(), "Invalid first name should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_InvalidLastName() {
        invalidPrescription = new Prescription(
                "John", "Do", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 90, -2.0f, 
                new Date(), 
                "Dr. Anderson");
        assertFalse(invalidPrescription.addPrescription(), "Invalid last name should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_InvalidAddress() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                "Short", // Invalid address
                -1.5f, 90, -2.0f, 
                new Date(), 
                "Dr. Anderson");
        assertFalse(invalidPrescription.addPrescription(), "Invalid address should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_InvalidSphere() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -25.0f, 90, -2.0f, // Invalid sphere
                new Date(), 
                "Dr. Anderson");
        assertFalse(invalidPrescription.addPrescription(), "Invalid sphere should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_InvalidAxis() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 190, -2.0f, // Invalid axis
                new Date(), 
                "Dr. Anderson");
        assertFalse(invalidPrescription.addPrescription(), "Invalid axis should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_InvalidOptometristName() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 90, -2.0f, 
                new Date(), 
                "A"); // Invalid optometrist name
        assertFalse(invalidPrescription.addPrescription(), "Invalid optometrist name should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_ValidWithEdgeCases() {
        validPrescription = new Prescription(
                "Anne", "Smith", // Valid edge names (4 chars)
                "5678 Pine Road, Melbourne, VIC 6789, Australia", 
                20.00f, 180, 4.00f, // Valid edge sphere, axis, cylinder
                new Date(), 
                "Dr. Jonathan Anderson");
        assertTrue(validPrescription.addPrescription(), "Valid prescription with edge values should be added successfully.");
    }
    
    @Test
    void testAddPrescription_EmptyOptometrist() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 90, -2.0f, 
                new Date(), 
                ""); // Empty optometrist name
        assertFalse(invalidPrescription.addPrescription(), "Empty optometrist name should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_NullOptometrist() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 90, -2.0f, 
                new Date(), 
                null); // Null optometrist name
        assertFalse(invalidPrescription.addPrescription(), "Null optometrist name should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_NullAddress() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                null, // Null address
                -1.5f, 90, -2.0f, 
                new Date(), 
                "Dr. Anderson");
        assertFalse(invalidPrescription.addPrescription(), "Null address should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_EmptyAddress() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                "", // Empty address
                -1.5f, 90, -2.0f, 
                new Date(), 
                "Dr. Anderson");
        assertFalse(invalidPrescription.addPrescription(), "Empty address should prevent the prescription from being added.");
    }

    @Test
    void testAddPrescription_ExtremeValidValues() {
        validPrescription = new Prescription(
                "Anne", "Smith", 
                "5678 Pine Road, Melbourne, VIC 6789, Australia", 
                -20.00f, 180, 4.00f, // Extreme but valid values
                new Date(), 
                "Dr. Jonathan Anderson");
        assertTrue(validPrescription.addPrescription(), "Prescription with extreme valid values should be added successfully.");
    }



 

    @Test
    void testAddPrescription_InvalidDateFormat() {
        invalidPrescription = new Prescription(
                "John", "Doe", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 90, -2.0f, 
                null, // Invalid examination date
                "Dr. Anderson");
        assertFalse(invalidPrescription.addPrescription(), "Null examination date should prevent the prescription from being added.");
    }
}