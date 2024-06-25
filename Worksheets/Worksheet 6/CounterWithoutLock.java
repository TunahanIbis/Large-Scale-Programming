import java.util.HashMap;

// TASK: Implement a Counter class which extends Thread
class CounterWithoutLock extends Thread {
    private final String[] array;
    private final HashMap<String, Integer> hashMap;

    // Constructor should take 3 arguments: an array, a HashMap and a ReentrantLock.
    public CounterWithoutLock(String[] array, HashMap<String, Integer> hashMap) {
        this.array = array;
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        for (String element : array) {
            // In the run method, traverse the given array and increment the count of the element in the HashMap on every occurence of the element.
            hashMap.put(element, hashMap.getOrDefault(element, 0) + 1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a HashMap
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Create an array with a large number of elements, e.g., two arrays containing "a" 400 times
        String[] array1 = new String[400];
        String[] array2 = new String[400];
        for (int i = 0; i < 400; i++) {
            array1[i] = "a";
            array2[i] = "a";
        }

        // Create two Counter instances for the arrays
        CounterWithoutLock counter1 = new CounterWithoutLock(array1, hashMap);
        CounterWithoutLock counter2 = new CounterWithoutLock(array2, hashMap);

        // Start the threads
        counter1.start();
        counter2.start();

        // Make the threads join
        counter1.join();
        counter2.join();

        // Display the potentially incorrect results
        System.out.println("Counting Result of Elements: " + hashMap);
    }
}
