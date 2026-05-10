
import java.util.Scanner;

public class EmployeePerformanceExpertSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int score = 0;

        // Input Section
        System.out.print("Enter Attendance Percentage: ");
        int attendance = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Task Completion (high/medium/low): ");
        String task = sc.nextLine().toLowerCase();

        System.out.print("Enter Communication Skill (good/average/poor): ");
        String communication = sc.nextLine().toLowerCase();

        System.out.print("Enter Teamwork (good/average/poor): ");
        String teamwork = sc.nextLine().toLowerCase();

        System.out.print("Enter Productivity (high/medium/low): ");
        String productivity = sc.nextLine().toLowerCase();

        // Rule-Based Evaluation

        // Rule 1: Attendance
        if (attendance > 90) {
            score += 2;
        }

        // Rule 2: Task Completion
        if (task.equals("high")) {
            score += 3;
        } else if (task.equals("medium")) {
            score += 2;
        } else {
            score += 1;
        }

        // Rule 3: Communication
        if (communication.equals("good")) {
            score += 2;
        } else if (communication.equals("average")) {
            score += 1;
        }

        // Rule 4: Teamwork
        if (teamwork.equals("good")) {
            score += 2;
        } else if (teamwork.equals("average")) {
            score += 1;
        }

        // Rule 5: Productivity
        if (productivity.equals("high")) {
            score += 3;
        } else if (productivity.equals("medium")) {
            score += 2;
        } else {
            score += 1;
        }

        // Final Evaluation
        System.out.println("\nTotal Score: " + score);

        if (score >= 10) {
            System.out.println("Performance Evaluation: Excellent");
        } else if (score >= 7) {
            System.out.println("Performance Evaluation: Good");
        } else if (score >= 4) {
            System.out.println("Performance Evaluation: Average");
        } else {
            System.out.println("Performance Evaluation: Poor");
        }

        sc.close();
    }
}