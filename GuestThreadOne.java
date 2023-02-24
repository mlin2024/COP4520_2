public class GuestThreadOne implements Runnable {
    private boolean eaten, partyOver;

    // Constructor
    public GuestThreadOne() {
        this.eaten = false;
        this.partyOver = false;
    }

    // Helper method to indicate whether this guest has eaten the cupcake before
    public boolean hasEaten() {
        return eaten;
    }

    // Helper method to eat the cupcake (set eaten to true)
    public void eat() {
        eaten = true;
    }

    // Helper method to end party
    public void endParty() {
        partyOver = true;
    }

    public void run() {
        while (!partyOver) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return;
    }
}
