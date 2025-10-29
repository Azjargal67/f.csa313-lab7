package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrganizationTest {
    // Add test methods here.
    // You are not required to write tests for all classes.

    @Test
    public void testGetEmployees_defaultList() {
        Organization org = new Organization();
        assertEquals(5, org.getEmployees().size());
        assertTrue(org.getEmployees().get(0).getName().equals("Greg Gay"));
    }

    @Test
    public void testGetRooms_defaultList() {
        Organization org = new Organization();
        assertEquals(5, org.getRooms().size());
        assertTrue(org.getRooms().get(0).getID().equals("2A01"));
    }

    @Test
    public void testGetEmployee_existingPerson() {
        try {
            Organization org = new Organization();
            Person p = org.getEmployee("Ryan Austin");
            assertNotNull(p);
            assertEquals("Ryan Austin", p.getName());
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetEmployee_nonExistingPerson() {
        try {
            Organization org = new Organization();
            org.getEmployee("Batman");
            fail("Expected exception for non-existing employee");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("does not exist"));
        }
    }

    @Test
    public void testGetRoom_existingRoom() {
        try {
            Organization org = new Organization();
            Room room = org.getRoom("2A03");
            assertNotNull(room);
            assertEquals("2A03", room.getID());
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetRoom_nonExistingRoom() {
        try {
            Organization org = new Organization();
            org.getRoom("9999");
            fail("Expected exception for non-existing room");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("does not exist"));
        }
    }
}
