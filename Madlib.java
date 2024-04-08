import java.io.*;
import java.util.*;

public class Madlib{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            printMenu();
            choice = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (choice) {
                case 'C':
                    createMadLib(scanner);
                    break;
                case 'V':
                    viewMadLib(scanner);
                    break;
                case 'Q':
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'Q');

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nMad Libs Menu:");
        System.out.println("C - Create a new Mad Lib");
        System.out.println("V - View an existing Mad Lib");
        System.out.println("Q - Quit");
        System.out.print("Enter your choice: ");
    }
  
    private static void createMadLib(Scanner scanner) {
        String outputFileName; // Stores the output file name entered by the user
    
        System.out.print("Enter the name of the output file: ");
        outputFileName = scanner.nextLine();
    
        try (Scanner inputFile = new Scanner(new File("madlib.txt"));
             PrintStream outputFile = new PrintStream(new File(outputFileName))) {
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine();
                outputFile.println(processLine(scanner, line));
            }
    
            // Print the created Mad Lib content directly after writing
            System.out.println("\n** Your New Mad Lib: **");
            try (Scanner newScanner = new Scanner(new File(outputFileName))) {
                while (newScanner.hasNextLine()) {
                    System.out.println(newScanner.nextLine());
                }
            } catch (IOException e) {
                System.err.println("Error reading Mad Lib to print: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
    
    private static String processLine(Scanner scanner, String line) {
        StringBuilder sb = new StringBuilder();
        Scanner lineScanner = new Scanner(line);
    
        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
    
            if (isPlaceholder(token)) {
                String replacement = processPlaceholder(scanner, token);
                sb.append(replacement);
            } else {
                sb.append(token + " ");
            }
        }
    
        lineScanner.close();
        return sb.toString().trim();
    }
    

    private static boolean isPlaceholder(String token) {
    return token.startsWith("<") && token.endsWith(">") && token.length() > 2; // Ensures non-empty placeholders
}

    private static String processPlaceholder(Scanner scanner, String placeholder) {
        String prompt;
        String response;

        placeholder = placeholder.substring(1, placeholder.length() - 1);
        placeholder = placeholder.replace("-", " ");

        if (isVowel(placeholder.charAt(0))) {
            prompt = "Please type an " + placeholder + ": ";
        } else {
            prompt = "Please type a " + placeholder + ": ";
        }

        System.out.print(prompt);
        response = scanner.nextLine();

        return response;
    }

    private static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    private static void viewMadLib(Scanner scanner) {
        String fileName;
    
        do {
            System.out.print("Enter the name of the Mad Lib to view (madlib.txt is the default file name): ");
            fileName = scanner.nextLine();
    
            if (!fileExists(fileName)) {
                System.out.println("File not found. Please try again.");
            }
        } while (!fileExists(fileName));
    
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    private static boolean fileExists(String fileName) {
        return new File(fileName).exists();
    }
}