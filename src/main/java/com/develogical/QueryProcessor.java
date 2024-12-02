package com.develogical;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {

    // Method to check if a number is a perfect square
    public static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    // Method to check if a number is a perfect cube
    public static boolean isPerfectCube(int num) {
        int cbrt = (int) Math.cbrt(num);
        return cbrt * cbrt * cbrt == num;
    }

    public String process(String query) {

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.contains("your name")) {
            return "bangalibhoot";
        }

        if (query.contains("plus")) {
            Pattern pattern = Pattern.compile("\\d+");  // This matches one or more digits
            Matcher matcher = pattern.matcher(query);

            // Find the numbers in the string
            int x = 0, y = 0;
            if (matcher.find()) {
                x = Integer.parseInt(matcher.group());  // First number
            }
            if (matcher.find()) {
                y = Integer.parseInt(matcher.group());  // Second number
            }

            // Perform the addition
            int sum = x + y;
            return Integer.toString(sum);
        }

        if (query.contains("Which of the following numbers is both a square and a cube:")) {

        // Regular expression to extract numbers from the string
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(query);

        // Loop through all the found numbers
        while (matcher.find()) {
            String numStr = matcher.group();
            try {
                int num = Integer.parseInt(numStr);  
                 // Check if the number is both a perfect square and a perfect cube
                if (isPerfectSquare(num) && isPerfectCube(num)) {
                    return numStr;  // Add the number to the result list
                }
            } catch (NumberFormatException e) {
                // Handle case where the string is not a valid integer
                System.out.println("Invalid number format: " + numStr);
            }
        }
        
        return "";
        }

        if (query.contains("Which of the following numbers is the largest:")) {
            // Regular expression to extract numbers from the string
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(query);

        // List to store the extracted numbers
        ArrayList<Integer> numbers = new ArrayList<>();

        // Extract all the numbers from the string
        while (matcher.find()) {
            String numStr = matcher.group();
            try {
                numbers.add(Integer.parseInt(numStr));  // Convert string to integer and add to list
            } catch (NumberFormatException e) {
                // Handle case where the string is not a valid integer
                System.out.println("Invalid number format: " + numStr);
            }
        }
            // If the list is empty, return a message
            if (numbers.isEmpty()) {
                return "No valid numbers found.";
            }

            // Find the largest number using Collections.max()
            int largestNumber = Collections.max(numbers);

            // Return the largest number as a string
            return String.valueOf(largestNumber);
        }

        return "";
    }

}