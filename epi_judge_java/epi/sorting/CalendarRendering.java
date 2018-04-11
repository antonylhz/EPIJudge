package epi.sorting;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTestHandler;

import java.util.*;

public class CalendarRendering {

    @EpiUserType(ctorParams = {int.class, int.class})
    public static class Event {
        public int start, finish;

        public Event(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    @EpiTest(testfile = "calendar_rendering.tsv")
    public static int findMaxSimultaneousEvents(List<Event> A) {
        Collections.sort(A, new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return e1.start - e2.start;
            }
        });

        int max = 0;
        PriorityQueue<Integer> concurrent = new PriorityQueue<>();
        for (Event event : A) {
            // Prune any existing events that has end time <= current event's start time
            while (!concurrent.isEmpty() && concurrent.peek() < event.start) {
                concurrent.poll();
            }
            concurrent.add(event.finish);
            max = Math.max(max, concurrent.size());
        }
        return max;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}
