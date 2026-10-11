// import statements needed
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
public class ElectricityBill {
    public static void main(String [] args)
            // handle filenotfound error
        throws FileNotFoundException {
            Scanner input = new Scanner(System.in);
            // display menu for customer information input preference
            System.out.println("Choose input method");
            System.out.println("1 - Enter information using keyboard");
            System.out.println("2 - Enter information by uploading file");
            System.out.print("Enter the number code for your input method choice: ");
            // declare variables
            int menuOption = input.nextInt();
            input.nextLine();
            //initializing string with empty string to stop error when value hasn't been assigned yet
            String name = "";
            String accountNum = "";
            int kWh = 0;
            String overdue = "";
            double elecBill;
            // initialize latefee to zero in case the customers payment is not late
            double lateFee;
            double total;
            //display input questions if they choose to type info manually
            if (menuOption == 1) {
                System.out.println("Enter your name: ");
                //read customer input
                name = input.nextLine();
                System.out.println("Enter your account number: ");
                accountNum = input.nextLine();
                System.out.println("Enter the number of kWh used during the month: ");
                kWh = input.nextInt();
                System.out.println("Is the payment overdue? (yes/no): ");
                overdue = input.next();
                }
            // if customer chooses 2, read file input
            else if (menuOption == 2) {
                File file = new File("customerInfo.txt");
                Scanner customerFile = new Scanner(file);
                name = customerFile.nextLine();
                accountNum = customerFile.nextLine();
                kWh = customerFile.nextInt();
                overdue = customerFile.next();
                //close scanner used for customer input file
                customerFile.close();
            }
            else {
                System.out.println("Invalid option. Please choose 1 or 2.");
            }
            //Calculate the electricity charge
            if (kWh <= 100) {
                elecBill = kWh * 0.12;
            }
            else if (kWh <= 300) {
                elecBill = (100 * 0.12) + ((kWh - 100) * 0.15);
            }
            else {
                elecBill = (100 * 0.12) + (200 * 0.15) + ((kWh - 300) * 0.18);
            }
            // Calculate late fee
            if (overdue.equals("yes")) {
                lateFee = 25.00;
            }
            else {
                lateFee = 0.00;
            }
            //Calculate total bill amount
            total = elecBill + lateFee;
            //Display formatted bill on screen
            System.out.println("Customer name: " + name);
            System.out.println("Account number: " + accountNum);
            System.out.println("Electricity usage: " + kWh + " kWh");
            System.out.printf("Electricity charge: $%.2f%n", elecBill);
            System.out.printf("Late-payment fee: $%.2f%n", lateFee);
            System.out.printf("Total amount due: $%.2f%n", total);
            //Save a copy of the completed bill to an output file
            PrintWriter output = new PrintWriter("electricityBill.txt");

            output.println("Customer name: " + name);
            output.println("Account number: " + accountNum);
            output.println("Electricity usage: " + kWh + " kWh");
            output.printf("Electricity charge: $%.2f%n", elecBill);
            output.printf("Late-Payment fee: $%.2f%n", lateFee);
            output.printf("Total Amount due: $%.2f%n", total);

            output.close();
            input.close();
        }
    }
