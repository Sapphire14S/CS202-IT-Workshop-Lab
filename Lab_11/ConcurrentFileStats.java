import java.io.*;
import java.util.*;

class FileStatsThread extends Thread {
    private String fileName;
    private static final Object lock = new Object(); // synchronization lock
    private static final String OUTPUT_FILE = "summary.txt";

    public FileStatsThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        int lineCount = 0, wordCount = 0, charCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
                charCount += line.length();
            }

            String result = String.format(
                "%s -> Lines: %d, Words: %d, Characters: %d%n",
                fileName, lineCount, wordCount, charCount
            );

            // Synchronized writing to prevent conflicts
            synchronized (lock) {
                try (FileWriter fw = new FileWriter(OUTPUT_FILE, true)) {
                    fw.write(result);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("! File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("! Error reading file " + fileName + ": " + e.getMessage());
        }
    }
}

public class ConcurrentFileStats {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\n-------------------------------------------------\n");
            System.out.print("> Enter the number of text files to process: ");
            int n = sc.nextInt();
            sc.nextLine(); 

            System.out.print("\n");
            String[] files = new String[n];
            System.out.println("> Enter file names (e.g., file1.txt): ");
            for (int i = 0; i < n; i++) {
                files[i] = sc.nextLine().trim();
            }
            System.out.print("\n");
            
            // Clear the summary file before writing
            try (FileWriter fw = new FileWriter("summary.txt")) {
                fw.write(">            Summary of Files             \n");
            } catch (IOException e) {
                System.err.println("> Error initializing summary.txt file.");
                return;
            }

            // Create and start threads
            List<FileStatsThread> threads = new ArrayList<>();
            for (String file : files) {
                FileStatsThread t = new FileStatsThread(file);
                threads.add(t);
                t.start();
            }

            // Wait for all threads to complete
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted: " + e.getMessage());
                }
            }

            System.out.println("\n> All files processed successfully!");
            System.out.println("> Summary generated in summary.txt");
            System.out.println("\n-------------------------------------------------\n");
        }
    }
}
