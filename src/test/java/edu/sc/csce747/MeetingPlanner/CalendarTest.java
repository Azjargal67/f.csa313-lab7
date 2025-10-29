package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CalendarTest {
    // Add test methods here.
    // You are not required to write tests for all classes.

    @Test
    public void testAddMeeting_holiday() {
        // Create Midsommar holiday
        Calendar calendar = new Calendar();
        // Add to calendar object.
        try {
            Meeting midsommar = new Meeting(6, 26, "Midsommar");
            calendar.addMeeting(midsommar);
            // Verify that it was added.
            Boolean added = calendar.isBusy(6, 26, 0, 23);
            assertTrue("Midsommar should be marked as busy on the calendar",added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeetingSuccessfully() {
        try {
            Calendar calendar = new Calendar();
            Meeting meeting = new Meeting(3, 20, 9, 11, "Project meeting");
            calendar.addMeeting(meeting);
            boolean busy = calendar.isBusy(3, 20, 9, 11);
            assertTrue("Calendar should be busy between 9-11 on 3/20.", busy);
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeetingConflict() {
        try {
            Calendar calendar = new Calendar();
            calendar.addMeeting(new Meeting(5, 10, 10, 12, "Morning session"));
            calendar.addMeeting(new Meeting(5, 10, 11, 13, "Team sync"));
            fail("Expected TimeConflictException due to overlapping meetings.");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Overlap"));
        }
    }

    @Test
    public void testAddMeetingInvalidTime() {
        try {
            Calendar calendar = new Calendar();
            calendar.addMeeting(new Meeting(4, 8, 15, 14, "Invalid time"));
            fail("Should throw exception when start >= end.");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("starts before it ends"));
        }
    }

    @Test
    public void testClearSchedule() {
        try {
            Calendar calendar = new Calendar();
            calendar.addMeeting(new Meeting(9, 7, 10, 11, "Quick sync"));
            calendar.clearSchedule(9, 7);
            boolean busy = calendar.isBusy(9, 7, 10, 11);
            assertFalse("Day should be cleared and not busy anymore.", busy);
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPrintAgendaMonth() {
        try {
            Calendar calendar = new Calendar();
            calendar.addMeeting(new Meeting(10, 2, 14, 15, "Client demo"));
            String agenda = calendar.printAgenda(10);
            assertTrue("Agenda should list the meeting description.", agenda.contains("Client demo"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetAndRemoveMeeting() {
        try {
            Calendar calendar = new Calendar();
            Meeting meeting = new Meeting(4, 15, 9, 10, "Morning call");
            calendar.addMeeting(meeting);
            Meeting retrieved = calendar.getMeeting(4, 15, 0);
            assertEquals("Morning call", retrieved.getDescription());
            calendar.removeMeeting(4, 15, 0);
            String agenda = calendar.printAgenda(4, 15);
            assertFalse("Removed meeting should not appear in agenda.", agenda.contains("Morning call"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidMonth() {
        try {
            Calendar calendar = new Calendar();
            calendar.addMeeting(new Meeting(13, 5, 10, 11, "Invalid month"));
            fail("Should throw exception for invalid month.");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Month does not exist"));
        }
    }

    @Test
    public void testInvalidDay() {
        try {
            Calendar calendar = new Calendar();
            calendar.addMeeting(new Meeting(5, 40, 10, 11, "Invalid day"));
            fail("Should throw exception for invalid day.");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Day does not exist"));
        }
    }

    @Test
    public void testRemoveFromEmptyDay() {
        try {
            Calendar calendar = new Calendar();
            calendar.removeMeeting(7, 10, 0);
            assertTrue(true);
        } catch (Exception e) {
            fail("Should not throw exception when removing from empty day.");
        }
    }

    @Test
    public void testAgendaEmptyDay() {
        Calendar calendar = new Calendar();
        String agenda = calendar.printAgenda(8, 15);
        assertTrue(agenda.contains("Agenda for 8/15"));
    }
}
