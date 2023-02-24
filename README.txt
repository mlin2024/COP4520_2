Problem 1:
	To compile:
	javac ProblemOne.java GuestThreadOne.java

	To run:
	java ProblemOne num_guests
	(e.g. "java ProblemOne 100" for 100 guests)

Problem 2:
	To compile:
	javac ProblemTwo.java GuestThreadTwo.java

	To run:
	java ProblemTwo

-------------------------------------
Problem 1:
The solution to this problem is faily straightforward. The n guests number themselves 0 through n-1 and arbitrarily choose a "checker" (WLOG, let this be guest 0). The counter's job is to keep a tally in their head of the number of people who have DEFINITELY entered the labyrinth. The guests all follow the same protocol: they can each only eat the cupcake ONCE. Once a non-checker has eaten the cupcake, all the other guests must leave the plate empty until the checker enters, at which point they note that the cupcake has been eaten, increment a counter in their head, and replace the cupcake. (If the checker has not eaten yet, they eat the cupcake too, update their counter, and replace it). Since ONLY the checker is allowed to replace the cupcake, they know that everyone has entered the labyrinth at least once when they have replaced the cupcake n times.

To test the efficiency and effectiveness of my solution, I ran it on various values of n multiple times each. Using print statements, I guaranteed that the checker was only declaring victory after each guest had indeed entered the labyrinth at least once. Even with values of n up to 100, my solution runs well under 1 second every time, though it does vary slightly due to the nondeterminism of the problem.


Problem 2:
The second strategy is clearly the most effective one. The first strategy is similar to the second one, but guests would waste time checking if the showroom is occupied. However, even this is more effective than the third strategy, which could not only lead to a long queue, but could also easily lead to a deadlock: if one guest leaves the room and no one is waiting outside, there is no one to notify that the room is free, so no one is able to enter the room for the rest of the party, since no one knows the room is free. The second strategy is the most optimal, it removes the need for long queues and uses a simple flag algorithm to ensure mutual exclusion.

My implementation assigns each guest a random number of times they want to see the vase during the party (so that there is a defined end to the party). Each time they visit the room, they visit for a random length of time, and after they leave they have a random delay before wanting to visit it again. This is achieved through the use of several helper functions, which are affected by the main thread, which is constantly checking to see if anybody wants to enter the room, and letting people in one at a time if they do. They work in a cycle: the guest wants to enter the room, the guest starts waiting to enter the room (looking at the sign periodically to see if they can do so), the guest enters the room, then (if they still want to see the vase again at some point after that), the guest goes back to waiting before they want to see the vase again.

I tested the efficiency of my solution by running it multiple times on multiple different values of n. I also used print statements to clearly show what is happening at the party, such as when each guest gets the urge to look at the vase, when they enter the room, and when they leave. For all cases I tested, the output is random but consistent with how the simulation is meant to work, and all runtimes are relatively low (under 10s).