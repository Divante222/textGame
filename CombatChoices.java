import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CombatChoices{
    Scanner sc = new Scanner(System.in);
    List actions = new ArrayList(Arrays.asList("Attack", "Defend", "Run", "Use Ability"));
    
    
    public List<Unit> getPlayerCombatChoices(List<Unit> monsterEncountered, Unit character){
        boolean playerAttacking = true;
        if(monsterEncountered.size() == 0){
            playerAttacking = false;
        } 
        while(playerAttacking){
            int iteration = 1;
            System.out.println("Make your move");
            System.out.println("=======================================================");
            for(Object action : actions){
                System.out.print(action.toString() + ": " + iteration + "\t");
                iteration +=1;
            }
            System.out.println("\n=======================================================\n");
            System.out.print("Selection: ");
            String playerCombatChoice = sc.nextLine();
            System.out.println();
            if(playerCombatChoice.equals("1")){
                System.out.println("Player is Attacking");
                int enemyToAttack = selectEnemyNumber(monsterEncountered.size(), monsterEncountered) - 1;
                monsterEncountered.get(enemyToAttack).subtractHealth(character.getStrength());
                System.out.println("Player hit " + monsterEncountered.get(enemyToAttack).getName() + " for " + character.getStrength() + " Damage!");
                if(monsterEncountered.get(enemyToAttack).checkAlive() == false){
                    monsterEncountered.remove(enemyToAttack);
                };
                playerAttacking = false;
            } else if(playerCombatChoice.equals("2")){
                System.out.println("Player is Defending");
                break;
            } else if(playerCombatChoice.equals("3")){
                System.out.println("Player attempts to Run");
                break;
            } else if(playerCombatChoice.equals("4")){
                System.out.println("Player is using Ability");
                break;
            } else{
                System.out.println("Please enter a valid selection");
            }
        }
        return monsterEncountered;
    }

    public int selectEnemyNumber(int listSize, List<Unit> monsterEncountered){
        int playerChoice;
        while (true) { 
            try {
                System.out.println("Which monster do you want to attack: ");
                playerChoice = Integer.parseInt(sc.nextLine());
                if(playerChoice < listSize + 1 && playerChoice > 0){
                    break;
                }
                System.out.println("Please enter a correct enemy number 1 through " + listSize);
                listMonstersEncountered(monsterEncountered);
            } catch (Exception e) {
                System.out.println("Please enter a valid selection");
            }
        }
        return playerChoice;
    }

    public void listMonstersEncountered(List<Unit> monsterEncountered){
        Unit unit = new Warriror();
        List<String> statNamesToGet = new ArrayList<>();
        statNamesToGet.addAll(Arrays.asList("Enemy Number", "Name", "Hp"));

        List<String> enemyNumberList = new ArrayList<>();
        List<String> enemyNameList = new ArrayList<>();
        List<String> enemyHpList = new ArrayList<>();

        List<List<String>> enemyInfo = new ArrayList<>(Arrays.asList(enemyNumberList, enemyNameList, enemyHpList));
        int infoCounter = 0;
        for(int threeStats = 0; threeStats < monsterEncountered.size(); threeStats++){
            goblinTextSetup(infoCounter, monsterEncountered, statNamesToGet, enemyNumberList, enemyNameList, enemyHpList);
            infoCounter +=1;
        }

        System.out.println("\nMonsters Encountered");
        System.out.println("======================================================");

       
        for(int i = 0; i < enemyInfo.size(); i++){
            printEnemyInfo(enemyInfo.get(i));
        }

        System.out.println("======================================================\n");
    }
    
    public void printEnemyInfo(List<String> enemyInfoSubList){
        for(int i = 0; i < enemyInfoSubList.size(); i++){
            System.out.print(enemyInfoSubList.get(i));
        }
        System.out.println();
    }

    public void goblinTextSetup(int infoCounter, List<Unit> monsterEncountered, List<String> statNamesToGet, List<String> enemyNumberList, List<String> enemyNameList, List<String> enemyHpList){
        
        String stringToAdd;
        for(int i = 0; i < 3; i++){
            switch(statNamesToGet.get(i)){
                case "Enemy Number": 
                    stringToAdd = "Enemy Number: " + (infoCounter + 1);
                    for(int spacesToAdd = 0; spacesToAdd < (24 - stringToAdd.length()); spacesToAdd++){
                        stringToAdd = stringToAdd + " ";
                    }
                    enemyNumberList.add(stringToAdd);
                    break;
                case "Name":
                    stringToAdd = "Enemy Name: " + monsterEncountered.get(infoCounter).getName();
                    for(int spacesToAdd = 0; spacesToAdd < (22 - stringToAdd.length()); spacesToAdd++){
                        stringToAdd = stringToAdd + " ";
                    }
                    enemyNameList.add(stringToAdd);
                    break;
                case "Hp":
                    stringToAdd = "Enemy Hp: " + monsterEncountered.get(infoCounter).getHealth();
                    for(int spacesToAdd = 0; spacesToAdd < (28 - stringToAdd.length()); spacesToAdd++){
                        stringToAdd = stringToAdd + " ";
                    }
                    enemyHpList.add(stringToAdd);
                    break;
            }
        }
    }
}
