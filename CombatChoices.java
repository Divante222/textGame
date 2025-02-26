import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CombatChoices{
    Scanner sc = new Scanner(System.in);
    List actions = new ArrayList(Arrays.asList("Attack", "Defend", "Run", "Use Ability"));
    
    public void getPlayerCombatChoices(){
        while(true){
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
                break;
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
    }
}
