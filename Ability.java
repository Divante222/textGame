import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ability{

    Campaign campaign;
    private String name;
    Scanner sc = new Scanner(System.in);

    Ability(Campaign campaign){
        this.campaign = campaign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String strongAttackExecution(){
        if(campaign.character.getMana() >= 2){
            System.out.println("Used Strong Attack!");
            int strengthBefore = campaign.character.getStrength();
            campaign.character.setStrength(campaign.character.getStrength() + (2 * campaign.character.getLevel()));
            campaign.character.initializeUpperDamageLimit();
            campaign.combatChoices.playerRegularAttack(campaign.monsterEncountered, campaign.character);
            campaign.character.setStrength(strengthBefore);
            campaign.character.initializeUpperDamageLimit();
            campaign.character.setMana(campaign.character.getMana() - 2);
            return "SuccessfulAttack"; 
        } else{
            while(true){
                System.out.println("Not enough mana...\nPerform reckless attack?");
                Menu.defaultSelectionMenu((List<String>) new ArrayList<>(List.of("Yes", "No")));
                String playerAnswer = sc.nextLine();
                switch (playerAnswer) {
                    case "1":
                        Double hitSuccessChance = new Random().nextDouble(0, 100);
                        System.out.println();
                        if(hitSuccessChance > 65){
                            System.out.println("Successful Attack!!!\n");
                            int strengthBefore = campaign.character.getStrength();
                            campaign.character.setStrength(campaign.character.getStrength() + (2 * campaign.character.getLevel()));
                            campaign.character.initializeUpperDamageLimit();
                            campaign.combatChoices.playerRegularAttack(campaign.monsterEncountered, campaign.character);
                            campaign.character.setStrength(strengthBefore);
                            campaign.character.initializeUpperDamageLimit();
                            return "SuccessfulAttack";
                        } else{
                            System.out.println("Attack Failure!!!\n");
                            System.out.println((campaign.character.getStrength() * 2) + " Damage recieved");
                            campaign.character.subtractHealth(campaign.character.getHealth() - (campaign.character.getStrength() * 2));
                            return "SuccessfulAttack";
                        }
                    case "2":
                        return "Exit";
                    default:
                        CommonText.enterValidSelection();
                        System.out.println("Press enter to continue...");
                        sc.nextLine();
                }
            }

        }
    }

    public String exitAbilityList(){
        return "Exit";
    }
}
