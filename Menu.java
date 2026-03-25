import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void defaultSelectionMenu(List<String> menuOptions){
        int iteration = 1;
        System.out.println("Menu");
        System.out.println("=======================================================\n");
        for(String menuOption : menuOptions){
            System.out.println(menuOption + "::" + iteration + "\n");
            iteration +=1;
        }
        System.out.println("=======================================================\n");
    }


    public static void menuInCombat(Unit character, Scanner sc){
        String menuSelection = "";
                while(menuSelection != "Exit"){
                    defaultSelectionMenu(List.of("Inventory", "Stats", "Equipment", "Exit"));
                    menuSelection = sc.nextLine();
                    switch(menuSelection) {
                        case "1":
                            System.out.println("Inventory is a work in progress");
                            break;
                        case "2":
                            System.out.println("Stats");
                            System.out.println("=======================================================\n");
                            System.out.println("Strength:: " + character.getStrength());
                            System.out.println("Dexterity:: " + character.getDexterity());
                            System.out.println("Constitution:: " + character.getConstitution());
                            System.out.println("Willpower:: " + character.getWillpower());
                            System.out.println("Intelligence:: " + character.getIntelligence());
                            System.out.println("Wisdom:: " + character.getWisdom());
                            System.out.println("Experience:: " + character.getExperience());
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
                            CommonText.enterValidSelection();
                            sc.nextLine();
                    }
                }
    }

    public static void shopMenuAfterLevel(Unit character, Scanner sc){

        Boolean stillShopping = true;

        while(stillShopping == true){
            defaultSelectionMenu(List.of("Buy", "Sell", "Exit"));

            String playerSelection = sc.nextLine();
            switch (playerSelection) {
                case "1":
                    System.out.println("comming sooon");
                    break;
                case "2":  
                    System.out.println("comming sooon");
                    break;
                case "3":
                    System.out.println("comming sooon");
                    stillShopping = false;
                    break;
                default:
                    CommonText.enterValidSelection();
                    sc.nextLine();
            }
        }
        
    }
    
}
