package assignment4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

class PrescriptionAddRemarkTest {

    private Prescription validPrescription;
    
    @BeforeEach
    void setUp() throws Exception {
        validPrescription = new Prescription(
                "John", "Doeo", 
                "1234 Elm Street, Springfield, VIC 1234, Australia", 
                -1.5f, 90, -2.0f, 
                new SimpleDateFormat("dd/MM/yyyy").parse("21/10/2024"), 
                "Dr. Anderson");
    }

    @Test
    void testAddRemark_ValidClientRemark() {
        assertTrue(validPrescription.addRemark("This is a valid remark because this has more than 6 words.", "Client"), "Valid client remark should be added successfully.");
    }

    @Test
    void testAddRemark_ValidOptometristRemark() {
        assertTrue(validPrescription.addRemark("This is another valid remark from Optometrist.", "Optometrist"), "Valid optometrist remark should be added successfully.");
    }

    @Test
    void testAddRemark_InvalidRemarkLength() {
        assertFalse(validPrescription.addRemark("Too short.", "Client"), "Remark with less than 6 words should not be added.");
    }

    @Test
    void testAddRemark_InvalidRemarkType() {
        assertFalse(validPrescription.addRemark("This is a invalid remark of Doctor.", "Doctor"), "Remark with invalid type should not be added.");
    }

    @Test
    void testAddRemark_InvalidRemarkCapitalization() {
        assertFalse(validPrescription.addRemark("this is a invalid remark because of the capitalization .", "Client"), "Remark with no capitalized first letter should not be added.");
    }

    @Test
    void testAddRemark_ExceedsRemarkLimit() {
        validPrescription.addRemark("This is the first valid remark.", "Client");
        validPrescription.addRemark("This is the second valid remark.", "Optometrist");
        assertFalse(validPrescription.addRemark("This is the third remark which will be invalid.", "Client"), "Third remark should not be added.");
    }

    @Test
    void testAddRemark_ValidClientRemarkEdge() {
        assertTrue(validPrescription.addRemark("This is an edge case remark with", "Client"), "Edge case remark should be added successfully.");
    }

    @Test
    void testAddRemark_ValidOptometristRemarkEdge() {
        assertTrue(validPrescription.addRemark("This remark exactly has twenty words for validation testing edge case condition.", "Optometrist"), "Edge case remark with 20 words should be added successfully.");
    }
    @Test
    void testAddRemark_EmptyRemark() {
        assertFalse(validPrescription.addRemark("", "Client"), "Empty remark should not be added.");
    }



    @Test
    void testAddRemark_CaseInsensitiveRemarkType() {
        assertTrue(validPrescription.addRemark("This is a valid remark 6 7.", "CLIENT"), "Remark type should be case-insensitive.");
    }
 
    @Test
    void testAddRemark_RemarkTypeInMixedCase() {
        assertTrue(validPrescription.addRemark("This is a valid remark 6 7 8.", "OptoMetrIst"), "Mixed case remark type should be valid.");
    }


    @Test
    void testAddRemark_RemarkLengthAtUpperBoundary() {
        assertTrue(validPrescription.addRemark("This remark exactly has twenty words for testing the upper limit edge condition successfully.", "Client"), "Remark with exactly 20 words should be added.");
    }

    @Test
    void testAddRemark_NullRemarkType() {
        assertFalse(validPrescription.addRemark("This is a valid remark.", null), "Null remark type should not be accepted.");
    }

    @Test 
    void testAddRemark_ValidAfterOneRemark() {
        validPrescription.addRemark("This is a valid first remark.", "Client");
        assertTrue(validPrescription.addRemark("This is a valid second remark.", "Optometrist"), "Second valid remark should be added.");
    }
    
}