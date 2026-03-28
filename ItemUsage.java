
import java.util.HashMap;
import java.util.Map;

public class ItemUsage {

    Map<String, Runnable> itemInGame = new HashMap<>();
    Campaign campaign;

    public ItemUsage(Campaign campaign){
        this.itemInGame = setInitialItems(this.itemInGame);
        this.campaign = campaign;
    }

    public Map<String, Runnable> setInitialItems(Map<String, Runnable> addingToItemInGame){
        addingToItemInGame.put("potion", () -> ItemUsage.potionUse(this.campaign));
        return addingToItemInGame;
    }

    public static void potionUse(Campaign campaign){
        if((campaign.character.getConstitution() * campaign.character.getConstitutionMultiplier()) < (campaign.character.getHealth() + 7)){
            campaign.character.setHealth();
            System.out.println("HP added would be more than max");
            System.out.println("New HP: " + campaign.character.getHealth());
        }else{ 
            campaign.character.addHealth(7);
            System.out.println("Hp added would be lower than max");
            System.out.println("New HP: " + campaign.character.getHealth());
        }
    }
}
