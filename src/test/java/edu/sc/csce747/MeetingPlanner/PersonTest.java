package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class PersonTest {
    // Add test methods here.
    // You are not required to write tests for all classes.

    @Test
    public void testConstructor_default() {
        Person p = new Person();
        assertEquals("", p.getName());
    }

    @Test
    public void testConstructor_withName() {
        Person p = new Person("John Doe");
        assertEquals("John Doe", p.getName());
    }

    @Test
    public void testAddMeeting_andIsBusy() {
        try {
            Person p = new Person("Greg");
            Meeting m = new Meeting(5, 10, 9, 10, "Morning standup");
            p.addMeeting(m);
            assertTrue(p.isBusy(5, 10, 9, 10));
        } catch (TimeConflictException e) {
            fail("Should not throw: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeeting_conflict() {
        try {
            Person p = new Person("Greg");
            p.addMeeting(new Meeting(6, 15, 10, 12, "Team sync"));
            p.addMeeting(new Meeting(6, 15, 11, 13, "Overlap"));
            fail("Expected conflict exception");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for attendee"));
        }
    }

    @Test
    public void testGetMeeting_andRemoveMeeting() {
        try {
            Person p = new Person("Jane");
            Meeting m = new Meeting(7, 2, 14, 15, "Client demo");
            p.addMeeting(m);
            Meeting retrieved = p.getMeeting(7, 2, 0);
            assertEquals("Client demo", retrieved.getDescription());
            p.removeMeeting(7, 2, 0);
            assertFalse(p.isBusy(7, 2, 14, 15));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPrintAgenda_month() {
        try {
            Person p = new Person("Ryan");
            p.addMeeting(new Meeting(8, 1, 9, 10, "Budget review"));
            String agenda = p.printAgenda(8);
            assertTrue(agenda.contains("Budget review"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPrintAgenda_day() {
        try {
            Person p = new Person("Ryan");
            p.addMeeting(new Meeting(8, 2, 11, 12, "Lunch meeting"));
            String agenda = p.printAgenda(8, 2);
            assertTrue(agenda.contains("Lunch meeting"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
