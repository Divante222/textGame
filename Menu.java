import java.util.Scanner;

public class Menu {

    public static void menuInCombat(Unit character, Scanner sc){
        String menuSelection = "";
                while(menuSelection != "Exit"){
                    System.out.println("Menu");
                    System.out.println("=======================================================\n");
                    System.out.println("Inventory::1\n");
                    System.out.println("Stats::2\n");
                    System.out.println("Equipment::3\n");
                    System.out.println("Exit::4\n");
                    System.out.println("=======================================================\n");
                    menuSelection = sc.nextLine();
                    switch(menuSelection) {
                        case "1":
                            System.out.println("Inventory is a work in progress");
                            break;
                        case "2":
                            System.out.println("Stats");
                            System.out.println("=======================================================\n");
                            System.out.println("Strength:: " + character.getStrength());
                            System.out.println("Dexterit:: " + character.getDexterity());
                            System.out.println("Constitution:: " + character.getConstitution());
                            System.out.println("Willpower:: " + character.getWillpower());
                            System.out.println("Intelligence:: " + character.getIntelligence());
                            System.out.println("Wisdom:: " + character.getWisdom());
                            System.out.println("\n=======================================================\n");
                            System.out.println("Press enter to continue...");
                            sc.nextLine();
                            break;
                        case "3":
                            System.out.println("Equipment");
                            break;
                        case "4":
                            menuSelection = "Exit";
                            break;
                        default:
                            System.out.println("\n-------------------------------------------------------\n");
                            System.out.println("Please enter a valid selection!");
                            System.out.println("\n-------------------------------------------------------\n");
                            System.out.println("Press Enter to continue...");
                            sc.nextLine();
                    }
                }
    }
    
}
