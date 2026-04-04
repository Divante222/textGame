import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menu {
    static Scanner sc = new Scanner(System.in);

    public static List<String> defaultSelectionMenu(List<String> menuOptions){
        int iteration = 1;
        System.out.println("=======================================================\n");
        for(String menuOption : menuOptions){
            System.out.println(menuOption + "::" + iteration + "\n");
            iteration +=1;
        }
        System.out.println("=======================================================\n");
        return menuOptions;
    }

    public static List<String> defaultSelectionMenuWithValues(List<String> menuOptions, List<Integer> values){
        int iteration = 0;
        System.out.println("=======================================================\n");
        for(String menuOption : menuOptions){
            System.out.println("Number: " + (iteration + 1) + " | " + menuOption + " : amount | " + values.get(iteration) + "\n");
            iteration +=1;
        }
        System.out.println("=======================================================\n");
        return menuOptions;
    }

    public static void showHpAndMana(Campaign campaign){
        System.out.println("-------------------------------------------------------");
        System.out.println("Health : " + campaign.character.getHealth() +  "\\" + (campaign.character.getConstitution() * campaign.character.getConstitutionMultiplier()));
        System.out.println("Mana : " + campaign.character.getMana());
        System.out.println("-------------------------------------------------------\n");
    }


    public static void menuInCombat(Unit character, Scanner sc, Campaign campaign){
        String menuSelection = "";
                while(menuSelection != "Exit"){
                    defaultSelectionMenu(List.of("Inventory", "Stats", "Equipment", "Use Ability Points", "Exit"));
                    menuSelection = sc.nextLine();
                    switch(menuSelection) {
                        case "1":
                            while (true) { 
                                System.out.println("Inventory");
                                defaultSelectionMenuWithValues(new ArrayList<>(character.items.keySet()), new ArrayList<>(character.items.values()));
                                showHpAndMana(campaign);
                                String playerItemChoice;
                                String itemToUse = "";

                                while (true) { 
                                    System.out.println("Select an Item or enter 0 to exit!");
                                    playerItemChoice = sc.nextLine();
                                    if(playerItemChoice.equals("0")){
                                        itemToUse = "0";
                                        break;
                                    }
                                    try {
                                        itemToUse = new ArrayList<>(character.items.keySet()).get(Integer.parseInt(playerItemChoice) - 1);
                                        
                                        campaign.itemUsage.itemInGame.get(itemToUse).run();
                                        
                                        System.out.println("attempting to use " + itemToUse);
                                        break;
                                    } catch (Exception e) {
                                        CommonText.enterValidSelection();
                                        System.out.println("Press enter to continue...");
                                        sc.nextLine();
                                    }
                                }
                                if(itemToUse.equals("0")){
                                    System.out.println("Did not use an item");
                                    break;
                                } else if(!character.items.containsKey(itemToUse)){
                                    CommonText.enterValidSelection();
                                    System.out.println("Press enter to continue...");
                                    sc.nextLine();
                                    continue;
                                } 
                                break;
                            }


                            System.out.println("Press enter to continue...");
                            sc.nextLine();
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
                            System.out.println("Ability Points:: " + character.getAbilityPoints());
                            System.out.println("\n=======================================================\n");
                            System.out.println("Press enter to continue...");
                            sc.nextLine();
                            break;
                        case "3":
                            List<String> equipmentTypes = new ArrayList<>(List.of("Helmet", "Chest", "Legs", "Hands", "Feet", "MainHand", "OffHand", "Current Equipment Check"));
                    
                            while (true) { 
                                Menu.defaultSelectionMenu(equipmentTypes);
                                System.out.println("Select an opiton or press 0 to exit");
                                String equipmentTypeSelection = sc.nextLine();
                                if(equipmentTypeSelection.equals("0")){
                                    return;
                                }
                                try {
                                    List<Unit.EquipmentStats> equipmentStatsList = campaign.character.equipmentList.get(equipmentTypes.get(Integer.parseInt(equipmentTypeSelection)));
                                    for(Unit.EquipmentStats equipmentStatsIndividual : equipmentStatsList){
                                        System.out.println("Equipment Name: " + equipmentStatsIndividual.equipmentName());
                                        System.out.println("Stat Modified: " + equipmentStatsIndividual.statModified());
                                        System.out.println("Amount Owned: " + equipmentStatsIndividual.amount());
                                        System.out.println("Description: " + equipmentStatsIndividual.toString());
                                    }
                                    break;
                                } catch (Exception e) {
                                    CommonText.enterValidSelection();
                                    System.out.println("Press enter to continue...");
                                    sc.nextLine();
                                }               
                            }
                            System.out.println("Press enter to continue...");
                            sc.nextLine();
                            break;
                        case "4":
                            Boolean continueAtributeSelection = true;
                            while(continueAtributeSelection){
                                List<String> attributeSelectionList = defaultSelectionMenu(List.of("Strength", "Dexterity", "Constitution", "Willpower", "Intelligence", "Wisdom"));
                                System.out.println("Amount of ability points: " + character.getAbilityPoints());

                                if(character.getAbilityPoints() == 0){
                                    System.out.println("You do not have any ability points!");
                                    System.out.println("Press enter to continue...");
                                    sc.nextLine();
                                    break;
                                }

                                System.out.println("Select an attribute or enter 0 to exit");
                                String playerSelection = sc.nextLine();
                                
                                List<String> attributeSelectionNumberList = new ArrayList<>();

                                for(int iteration = 1; iteration <= attributeSelectionList.size(); iteration++){
                                    attributeSelectionNumberList.add(String.valueOf(iteration));
                                }
          
                                if(playerSelection.equals("0")){
                                    continueAtributeSelection = false;
                                    System.out.println("Press enter to continue...");
                                    sc.nextLine();
                                    continue;
                                } else if(!attributeSelectionNumberList.contains(playerSelection)){
                                    CommonText.enterValidSelection();
                                    System.out.println("Press enter to continue...");
                                    sc.nextLine();
                                    continue;
                                }

                                String amountOfPointsToInvest;
                                int amountOfPointsToInvestConverted;

                                while(true){
                                    System.out.println("Select the amount of points to invest or enter 0 to exit");
                                    amountOfPointsToInvest = sc.nextLine();
                                    
                                    try {
                                        amountOfPointsToInvestConverted = Integer.parseInt(amountOfPointsToInvest);
                                        if(amountOfPointsToInvestConverted > character.getAbilityPoints()){
                                            System.out.println("You do not have " + amountOfPointsToInvest + " Ability Point(s)!");
                                            continue;
                                        } else if (amountOfPointsToInvest.equals("0")){
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("You did not enter a valid number!");
                                        continue;
                                    }

                                    break;
                                }

                                switch (playerSelection) {
                                    case "1":
                                        character.setStrength(character.getStrength() + amountOfPointsToInvestConverted);
                                        break;
                                    case "2":
                                        character.setDexterity(character.getDexterity() + amountOfPointsToInvestConverted);
                                        break;
                                    case "3":
                                        character.setConstitution(character.getConstitution() + amountOfPointsToInvestConverted);
                                        break;
                                    case "4": 
                                        character.setWillpower(character.getWillpower() + amountOfPointsToInvestConverted);
                                        break;
                                    case "5":
                                        character.setIntelligence(character.getIntelligence() + amountOfPointsToInvestConverted);
                                        break;
                                    case "6":
                                        character.setWisdom(character.getWisdom() + amountOfPointsToInvestConverted);
                                        break;
                                    case "7":
                                        continueAtributeSelection = false;
                                        continue;
                                    default:
                                        CommonText.enterValidSelection();
                                        continue;
                                    }
                                    CommonText.abilityPointInvestmentText(attributeSelectionList.get(Integer.parseInt(playerSelection) - 1), amountOfPointsToInvest);
                                    character.setAbilityPoints(character.getAbilityPoints() - amountOfPointsToInvestConverted);

                                    System.out.println("Press enter to continue...");
                                    sc.nextLine();
                            }

                            break;
                        case "5":
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
