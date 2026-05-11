public class PriorityQueueArray {
    private int[] arr;   
    private int size;    
    private int capacity;

    public PriorityQueueArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Queue is full!");
            return;
        }

        int i = size - 1;

        while (i >= 0 && arr[i] > value) {
            arr[i + 1] = arr[i];
            i--;
        }
        
        arr[i + 1] = value;
        size++;
    }

    public int extract() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return -1;
        }
        int highestPriority = arr[0];

        for (int i = 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        size--;

        return highestPriority;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.print("Priority Queue: { ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println(" }");
    }

    public static void main(String[] args) {
        PriorityQueueArray pq = new PriorityQueueArray(20);

        int[] input = {10, 7, 2, 5, 3, 18, 4, 12, 9};
        for (int val : input) pq.insert(val);

        System.out.print("Initial ");
        pq.display();


        int extracted = pq.extract();
        System.out.println("Extracted element: " + extracted);

        System.out.print("After extraction ");
        pq.display();
    }
}
