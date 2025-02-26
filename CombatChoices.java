import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CombatChoices{
    Scanner sc = new Scanner(System.in);
    List actions = new ArrayList(Arrays.asList("Attack", "Defend", "Run", "Use Ability"));
    
    
    public List getPlayerCombatChoices(List<Unit> monsterEncounterd, Unit character){
        boolean playerAttacking = true;
        if(monsterEncounterd.size() == 0){
            playerAttacking = false;
        } 
        while(playerAttacking){
            int iteration = 1;
            System.out.println("Make your move");
            System.out.println("\n=======================================================");
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
                int enemyToAttack = selectEnemyNumber(monsterEncounterd.size(), monsterEncounterd) - 1;
                monsterEncounterd.get(enemyToAttack).subtractHealth(character.getStrength());
                System.out.println("Player hit " + monsterEncounterd.get(enemyToAttack).getName() + " for " + character.getStrength() + " Damage!");
                if(monsterEncounterd.get(enemyToAttack).checkAlive() == false){
                    monsterEncounterd.remove(enemyToAttack);
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
        System.out.println("I am returning to main campaign");
        return monsterEncounterd;
    }

    public int selectEnemyNumber(int listSize, List<Unit> monsterEncounterd){
        int playerChoice;
        while (true) { 
            try {
                System.out.println("Which monster do you want to attack: ");
                playerChoice = Integer.parseInt(sc.nextLine());
                if(playerChoice < listSize + 1 && playerChoice > 0){
                    break;
                }
                System.out.println("Please enter a correct enemy number 1 through " + listSize);
                listMonstersEncountered(monsterEncounterd);
            } catch (Exception e) {
                System.out.println("Please enter a valid selection");
            }
        }
        return playerChoice;
    }

    public void listMonstersEncountered(List<Unit> monsterEncounterd){
        for(int i = 0; i < monsterEncounterd.size(); i++){
            System.out.println(
                "\n=======================================================\n" +
                "Enemy number: " + (i + 1) + 
                "\nEnemy Name: " + monsterEncounterd.get(i).getName() +
                "\nEnemy Hp: " + monsterEncounterd.get(i).getHealth() +
                "\n=======================================================\n"
            );
        }
    }
}
