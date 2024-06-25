import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

// TASK: Implement a Counter class which extends Thread
class Counter extends Thread {
    private final String[] array;
    private final HashMap<String, Integer> hashMap;
    private final ReentrantLock lock;

    // Constructor should take 3 arguments: an array, a HashMap and a ReentrantLock.
    public Counter(String[] array, HashMap<String, Integer> hashMap, ReentrantLock lock) {
        this.array = array;
        this.hashMap = hashMap;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (String element : array) {
            // Create a lock before updating the HashMap
            lock.lock();
            try {
                // In the run method, traverse the given array and increment the count of the element in the HashMap on every occurence of the element.
                hashMap.put(element, hashMap.getOrDefault(element, 0) + 1);
            } finally {
                // Release the lock to allow other threads to update the HashMap
                lock.unlock();
            }
        }
    }

    // Create a main method and test your Counter
    public static void main(String[] args) throws InterruptedException {
        // Create a HashMap and a ReentrantLock
        HashMap<String, Integer> hashMap = new HashMap<>();
        ReentrantLock lock = new ReentrantLock();

        // Create an array with a large number of elements, e.g., two arrays containing "a" 400 times
        String[] array1 = new String[400];
        String[] array2 = new String[400];
        for (int i = 0; i < 400; i++) {
            array1[i] = "a";
            array2[i] = "a";
        }

        // Another test result to check if the method can take different values or not
        /*
         String[] array1 = new String[300];
         String[] array2 = new String[300];
         for (int i = 0; i < 300; i++) {
         array1[i] = "a";
         array2[i] = "a";
         }
         */

        // Create two Counter instances for the arrays
        Counter counter1 = new Counter(array1, hashMap, lock);
        Counter counter2 = new Counter(array2, hashMap, lock);

        // Start the threads
        counter1.start();
        counter2.start();

        // Make the threads join
        counter1.join();
        counter2.join();

        // Display the results
        System.out.println("Counting Result of Elements: " + hashMap);
    }
}
