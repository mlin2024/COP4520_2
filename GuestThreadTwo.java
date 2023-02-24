public class GuestThreadTwo implements Runnable {
    private int num, totalTimes, timesSoFar;
    private boolean wantsToEnter, inRoom;

    // Constructor
    public GuestThreadTwo(int num) {
        this.num = num;
        this.totalTimes = (int)((Math.random() * 5) + 1);
        this.timesSoFar = 0;
        this.wantsToEnter = false;
        this.inRoom = false;
    }

    // Helper method to tell if guest wants to enter room
    public boolean wantsToEnter() {
        return wantsToEnter;
    }

    // Helper method to tell if guest is in room
    public boolean isInRoom() {
        return inRoom;
    }

    // Helper method to put in room
    public void putInRoom() {
        this.inRoom = true;
    }

    // Helper method to get a guest to enter the room
    public void enterRoom() {
        // Enter room
        wantsToEnter = false;
        inRoom = true;

        System.out.println("Guest #" + num + " enters the room.");

        // Look at the vase for 1 to 10 milliseconds
        int lookingTime = (int)((Math.random() * 10) + 1);
        try {
            Thread.sleep(lookingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Leave the room
        inRoom = false;
        timesSoFar++;
        System.out.println("Guest #" + num + " leaves the room.");

        // Start the cycle to make them want to enter the room again (if not hit max number of times yet)
        if (timesSoFar < totalTimes) wantToEnterRoom();
    }

    // Helper method to have a guest start waiting to enter the room
    public void waitToEnterRoom() {
        // Now they want to go in, check every 10 milliseconds till they are allowed
        while (!inRoom) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        enterRoom();
    }

    public void wantToEnterRoom() {
        // Guest waits random time between .1 and 1 seconds before they want to see vase again
        int delay = (int)((Math.random() * 1000) + 100);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Guest #" + num + " wants to see the vase.");
        wantsToEnter = true;
        waitToEnterRoom();
    }

    public void run() {
        wantToEnterRoom();
        return;
    }
}
