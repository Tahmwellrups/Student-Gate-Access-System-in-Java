import java.util.*;

public class AdminProgram {
    static Scanner scan = new Scanner(System.in);
    public static void registration()
    {
        int choice = 0;

        try
        {
            System.out.println("PRESS THE CORRESPONDING NUMBERS");
            System.out.println("TO NAVIGATE THROUGH MENU.");
            System.out.println("ID REGISTRATION");
            System.out.println("1. STUDENT RECORD");
            System.out.println("2. FACULTY RECORD");
            System.out.println("3. MAIN MENU");
            System.out.println("4. EXIT");
            System.out.print("\033[0;36mENTER HERE: "); choice = scan.nextInt();
            if(choice == 3){
                return;
            }
            if(choice == 4){
                System.exit(1);
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Choose from 1-4 ONLY!");
        }
        getRec(choice);

    }

    public static void getRec(int choice)
    {
        scan.nextLine();
        Data r = new Data();
        if(choice == 1)
        {

            System.out.print("Name: ");
            r.name = scan.nextLine();
            System.out.print("Course: ");
            r.course = scan.nextLine();
            System.out.print("Year & Section: ");
            r.yrSec = scan.nextLine();
            System.out.print("Student ID: ");
            r.schoolID = scan.nextLine();
            System.out.print("Address: ");
            r.address = scan.nextLine();
            System.out.print("Contact Number (+63): ");
            r.contactNum = scan.nextLine();
        }
        if(choice == 2)
        {
            System.out.print("Name: ");
            r.name = scan.nextLine();
            System.out.print("Faculty ID: ");
            r.schoolID = scan.nextLine();
            System.out.print("Address: ");
            r.address = scan.nextLine();
            System.out.print("Contact Number (+63): ");
            r.contactNum = scan.nextLine();
        }
        List.addRec(r);
        List.saveDB();
    }
    
    public static int menu(){
        int choice;

        System.out.println("MAIN MENU");
        System.out.println("1. NEW ID RECORD");
        System.out.println("2. DATE LOGS");
        System.out.println("3. RECORD DATABASE");
        System.out.println("4. EXIT");
        System.out.print("CHOICE: ");
        choice = scan.nextInt();
        return choice;
    }

    public static void main(String[] args) 
    {
        Loading ldScreen =  new Loading();
        /*ldScreen.showLDScreen();

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //ldScreen.hideLDScreen();*/

        List.retrieveDB();
        while(true)
        {
            switch(menu())
            {
                case 1: registration(); break;
                //case 2: logs_main(); break;
                case 3: List.displayDB(); break;
                case 4: System.exit(0);
                default: System.out.print("\033[0;31mCHOOSE 1-4 ONLY!");

            }
        }
    }
}
