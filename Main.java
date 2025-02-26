
import java.util.Scanner;

public class Main {

    public static Unit character;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    
        while(true){
            System.out.println("Main Menu");
            System.out.println("Choose your Character");
            System.out.println("Enter 1 for Warrior\n" +
            "Enter 2 for Mage\n"+ 
            "Enter 3 for Archer");
            System.out.print("Selection: ");
            String playerSelection = sc.next();

            if(playerSelection.equals("1")){
                System.out.println("You choose Warrior!");
                character = new Warriror();
                break;
            } else if(playerSelection.equals("2")){
                System.out.println("You choose Mage!");
                break;
            } else if(playerSelection.equals("3")){
                System.out.println("You choose Archer");
                break;
            } else{
                System.out.println("Please enter a proper selection!");
            }
        }
        
        Campaign campaign = new Campaign(character);

        campaign.campaignStart();
    }
}