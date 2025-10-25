package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;


public class MeetingTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.
    private Meeting meeting;
    private Room room;
    private Person Bat;
    private Person Bold;

    private static class Person {
        private String name;
        public Person(String name) { this.name = name; }
        public String getName() { return name; }
    }

    private static class Room {
        private String id;
        public Room(String id) { this.id = id; }
        public String getID() { return id; }
    }

    @Before
    public void setUp() {
        room = new Room("Room101");
        Bat = new Person("Bat");
        Bold = new Person("Bold");

        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(Bat);
        attendees.add(Bold);

        meeting = new Meeting(10, 25, 9, 11, attendees, room, "Project discussion");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(10, meeting.getMonth());
        assertEquals(25, meeting.getDay());
        assertEquals(9, meeting.getStartTime());
        assertEquals(11, meeting.getEndTime());
        assertEquals("Project discussion", meeting.getDescription());
        assertEquals(room, meeting.getRoom());
        assertEquals(2, meeting.getAttendees().size());
    }

    @Test
    public void testAddAndRemoveAttendees() {
        Person charlie = new Person("Charlie");
        meeting.addAttendee(charlie);
        assertEquals(3, meeting.getAttendees().size());
        assertTrue(meeting.getAttendees().contains(charlie));

        meeting.removeAttendee(Bat);
        assertEquals(2, meeting.getAttendees().size());
        assertFalse(meeting.getAttendees().contains(Bat));
    }

    @Test
    public void testToStringFormat() {
        String output = meeting.toString();
        assertTrue(output.contains("10/25"));
        assertTrue(output.contains("9 - 11"));
        assertTrue(output.contains("Room101"));
        assertTrue(output.contains("Project discussion"));
        assertTrue(output.contains("Bat"));
        assertTrue(output.contains("Bold"));
    }

    @Test
    public void testDayBlockConstructor() {
        Meeting vacation = new Meeting(8, 15);
        assertEquals(8, vacation.getMonth());
        assertEquals(15, vacation.getDay());
        assertEquals(0, vacation.getStartTime());
        assertEquals(23, vacation.getEndTime());
    }

    @Test
    public void testDayBlockConstructorWithDescription() {
        Meeting vacation = new Meeting(12, 31, "New Year holiday");
        assertEquals("New Year holiday", vacation.getDescription());
        assertEquals(0, vacation.getStartTime());
        assertEquals(23, vacation.getEndTime());
    }

    @Test
    public void testSetters() {
        meeting.setMonth(11);
        meeting.setDay(10);
        meeting.setStartTime(14);
        meeting.setEndTime(16);
        meeting.setDescription("Updated meeting");

        assertEquals(11, meeting.getMonth());
        assertEquals(10, meeting.getDay());
        assertEquals(14, meeting.getStartTime());
        assertEquals(16, meeting.getEndTime());
        assertEquals("Updated meeting", meeting.getDescription());
    }
}
