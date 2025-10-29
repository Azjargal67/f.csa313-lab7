package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class RoomTest {
    // Add test methods here.
    // You are not required to write tests for all classes.

    @Test
    public void testConstructor_default() {
        Room r = new Room();
        assertEquals("", r.getID());
    }

    @Test
    public void testConstructor_withID() {
        Room r = new Room("2B10");
        assertEquals("2B10", r.getID());
    }

    @Test
    public void testAddMeeting_andIsBusy() {
        try {
            Room r = new Room("2B20");
            Meeting m = new Meeting(4, 5, 10, 12, "Department meeting");
            r.addMeeting(m);
            assertTrue(r.isBusy(4, 5, 10, 12));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeeting_conflict() {
        try {
            Room r = new Room("3C05");
            r.addMeeting(new Meeting(3, 15, 9, 11, "First meeting"));
            r.addMeeting(new Meeting(3, 15, 10, 12, "Overlap"));
            fail("Expected TimeConflictException for overlapping meetings");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for room"));
        }
    }

    @Test
    public void testGetMeeting_andRemoveMeeting() {
        try {
            Room r = new Room("1A01");
            Meeting m = new Meeting(7, 22, 14, 15, "Design review");
            r.addMeeting(m);
            Meeting retrieved = r.getMeeting(7, 22, 0);
            assertEquals("Design review", retrieved.getDescription());
            r.removeMeeting(7, 22, 0);
            assertFalse(r.isBusy(7, 22, 14, 15));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPrintAgenda_month() {
        try {
            Room r = new Room("4D03");
            r.addMeeting(new Meeting(8, 10, 13, 14, "Weekly sync"));
            String agenda = r.printAgenda(8);
            assertTrue(agenda.contains("Weekly sync"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPrintAgenda_day() {
        try {
            Room r = new Room("4D03");
            r.addMeeting(new Meeting(8, 12, 9, 10, "Morning session"));
            String agenda = r.printAgenda(8, 12);
            assertTrue(agenda.contains("Morning session"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
