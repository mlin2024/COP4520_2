import java.util.*;
import java.lang.*;
import java.io.*;

public class ProblemTwo {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        
        // Create sign, starts off as available
        boolean available = true;

        // Make a thread for each guest
        Thread[] threads = new Thread[n];
        GuestThreadTwo[] guests = new GuestThreadTwo[n];
        for (int i = 0; i < n; i++) {
            guests[i] = new GuestThreadTwo(i);
            threads[i] = new Thread(guests[i]);
        }

        // Start all threads
        for (int i = 0; i < n; i++) {
            threads[i].start();
        }

        /*// Join all threads
        for (int i = 0; i < n; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }*/

        // Keep track of who's currently in the room (-1 for nobody)
        int guestInRoom = -1;

        // Keep track of if any threads are still alive
        boolean stillAlive = true;

        while (true) {
            // Check if any threads are still alive
            stillAlive = false;
            for (int i = 0; i < n; i++) stillAlive |= threads[i].isAlive();
            if (!stillAlive) break;

            if (available) {
                // Check if anyone wants to enter room
                for (int i = 0; i < n; i++) {
                    if (guests[i].wantsToEnter()) {
                        guests[i].putInRoom();
                        available = false;
                        guestInRoom = i;
                        break;
                    }
                }
            }

            // Check if the person who's currently in there is done yet
            if (guestInRoom != -1 && !guests[guestInRoom].isInRoom()) {
                // If they are, reset
                available = true;
                guestInRoom = -1;
            }
        }

        System.out.println("Party over!");
    }
}
