import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CombatChoices{
    Scanner sc = new Scanner(System.in);
    List actions = new ArrayList(Arrays.asList("Attack", "Defend", "Use Ability"));
    Random randomNumber = new Random();
    
    
    public List<Unit> getPlayerCombatChoices(List<Unit> monsterEncountered, Unit character){
        boolean playerAttacking = true;
        if(monsterEncountered.size() == 0){
            playerAttacking = false;
        } 

        if(character.isDefending()){
            if(character.getDefendingTurnCount() == 1){
                character.setDefending(false);
                character.setDefendingTurnCount(0);
            }
            if(!character.isDefending()){
                System.out.println("Player is no longer defending!");
            }
            character.addToDefendingTurnCount();
        }
        while(playerAttacking){
            String playerCombatChoice = playerCombatText();
            if(playerCombatChoice.equals("1")){
                monsterEncountered = playerRegularAttack(monsterEncountered, character);
                playerAttacking = false;
            } else if(playerCombatChoice.equals("2")){
                System.out.println("Player is Defending");
                character.setDefending(true);
                character.setDefendingTurnCount(0);
                break;
            } else if(playerCombatChoice.equals("3")){
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
                System.out.print("Which monster do you want to attack: ");
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

    public Unit enemyAttacks(List<Unit> monsterEncountered, Unit character){
        System.out.println("Monsters are Attacking!");
        System.out.println("============================");
        for(Unit monster : monsterEncountered){
            character = isDefending(monster, character);
            if(character.getHealth() <= 0){
                System.out.println("You Died!");
                break;
            }
        }
        System.out.println("Remaining HP: " + character.getHealth());
        System.out.println("============================");
        System.out.println("Press Enter to continue.");
        sc.nextLine();
        return character;
    }

    public String playerCombatText(){
        int iteration = 1;
        System.out.println("Make your move");
        System.out.println("=======================================================");
        for(Object action : this.actions){
            System.out.print(action.toString() + ": " + iteration + "\t");
            iteration +=1;
        }
        System.out.println("\n=======================================================\n");
        System.out.print("Selection: ");
        String playerCombatChoice = sc.nextLine();
        System.out.println();
        return playerCombatChoice;
    }
    
    public List<Unit> playerRegularAttack(List<Unit> monsterEncountered, Unit character){
        System.out.println("Player is Attacking");
        int enemyToAttack = selectEnemyNumber(monsterEncountered.size(), monsterEncountered) - 1;
        monsterEncountered.get(enemyToAttack).subtractHealth(character.getStrength());
        System.out.println("=====================================");
        System.out.println("Player hit " + monsterEncountered.get(enemyToAttack).getName() + " for " + character.getStrength() + " Damage!");
        
        if(monsterEncountered.get(enemyToAttack).checkAlive() == false){
            System.out.println(monsterEncountered.get(enemyToAttack).getName() + " Defeated!");
            monsterEncountered.remove(enemyToAttack);
        }
        System.out.println("=====================================");
        System.out.println("Press Enter to continue.");
        String keepGoing = sc.nextLine();
        return monsterEncountered;
    }

    public Unit isDefending(Unit monster, Unit character){
        if(character.isDefending()){
            character.setBlockChance(monster);
            character.setParryChance(monster);
            character.setBlockStrength(monster);

            int damageDelt = randomNumber.nextInt(monster.getUpperDamageLimit());
            System.out.println("-----------------------");
            if(character.getBlockChance() > randomNumber.nextInt(100)){
                if(character.getParryChance() > randomNumber.nextInt(100)){
                    System.out.println("Parried no damage taken!");
                    monster.setParried(true);
                } else {

                    System.out.println(monster.getName() + " Damage: " + damageDelt);
                    System.out.println(character.getName() + " Block: " + character.getBlockStrength());

                    if((character.getBlockStrength() - damageDelt) < 0){

                        
                            character.subtractHealth((character.getBlockStrength() - damageDelt) * -1);
                            System.out.println(monster.getName() + " Attacks for " + ((character.getBlockStrength() - damageDelt) * -1) + " Damage!");
                       
                    } 
                    
                    else{
                        System.out.println(monster.getName() + " Attack is blocked!");
                    }
                }
            } else {
                System.out.println(monster.getName() + " Attacks for " + damageDelt + " Damage!");
                character.subtractHealth(damageDelt);
            }
        } else {
            System.out.println("Isn't defending");
            System.out.println(monster.getName() + " Attacks for " + monster.getStrength() + " Damage!");
            character.subtractHealth(monster.getStrength());
        }
        System.out.println("-----------------------");
        System.out.println("Press Enter to continue.");
        sc.nextLine();
        return character;
    }
}
