import java.util.*;
import java.lang.*;
import java.io.*;

public class ProblemOne {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        // Make a thread for each guest
        Thread[] threads = new Thread[n];
        GuestThreadOne[] guests = new GuestThreadOne[n];
        for (int i = 0; i < n; i++) {
            guests[i] = new GuestThreadOne();
            threads[i] = new Thread(guests[i]);
        }

        // Cupcake starts off as uneaten
        boolean cupcakeEaten = false;

        // The checker's running total of people confirmed to have entered
        int count = 0;

        // Start all threads
        for (int i = 0; i < n; i++) {
            threads[i].start();
        }

        // Start the party
        while (true) {
            // Pick next guest randomly (from guest 0 to guest n-1)
            int nextGuest = (int)(Math.random() * n);
            System.out.println("Guest #" + nextGuest + " enters the labyrinth.");

            if (!guests[nextGuest].hasEaten() && !cupcakeEaten) {
                // If they haven't eaten, eat it
                guests[nextGuest].eat();
                cupcakeEaten = true;
                System.out.println("Guest #" + nextGuest + " eats the cupcake.");
            }

            if (nextGuest == 0) { // guest 0 is the checker
                if (cupcakeEaten) {
                    // Cupcake has been eaten so increment counter
                    count++;
                    System.out.println("Cupcake eaten, increment counter. Count = " + count);
                    if (count == n) break;
                    // Replace the cupcake (ONLY checker can do this)
                    cupcakeEaten = false;
                }
            }
        }

        System.out.println("Everyone has entered the labyrinth at least once!");

        // End all threads
        for (int i = 0; i < n; i++) {
            guests[i].endParty();
        }
    }
}
