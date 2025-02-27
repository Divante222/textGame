
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Campaign {

    CombatChoices combatChoices = new CombatChoices();

    public int routeLevel = 1;
    public List<String> monsterEncyclopediaWeak = new ArrayList<>();

    public List<String> monsterList = new ArrayList<>();
    public Random randomNumber = new Random();

    public List<Unit> monsterEncountered = new ArrayList<>();
    public boolean fightStart = true;

    public Unit character;

    public Campaign(){
        setMonsterEncyclopediaWeak();
    }

    public Campaign(Unit character){
        this();
        this.character = character;
    }


    public void campaignStart(){
        System.out.println("This is the start of a series of 10 fights\n");
        
        System.out.println("You encounterd some monsters");
        while (routeLevel < 11) { 
            setupCampaign();
            
            fightStart = true;
            while(fightStart){
                
                if(monsterEncountered.size() > 0 ){

                    combatChoices.listMonstersEncountered(monsterEncountered);

                    this.monsterEncountered = combatChoices.getPlayerCombatChoices(monsterEncountered, character);

                } else {
                    System.out.println("\n=======================================================");
                    System.out.println("You win the battle!");
                    fightStart = false;
                    this.routeLevel +=1;
                    System.out.println("new route level " + this.routeLevel);
                    System.out.println("\n=======================================================\n");
                }
            }
        }
    }

    public void setupCampaign(){
        setWeakMonsters(monsterEncyclopediaWeak.get(0));
        addMonsterListToEncountered();
    }
    
    public void setWeakMonsters(String monster){
        // int randomNumberGenerated = randomNumber.nextInt(3);
        int randomNumberGenerated = 3;
        monsterList.clear();
        for(int i = 0; i < randomNumberGenerated; i++){
            this.monsterList.add(monster);
        }
    }

    public void addMonsterListToEncountered(){
        this.monsterEncountered.clear();
        for(String monster : this.monsterList){
            if(monster.equals("Goblin")){
                this.monsterEncountered.add(new Goblin());
            }
        }
    }

    public void setMonsterEncyclopediaWeak(){
        this.monsterEncyclopediaWeak.add("Goblin");
    }

    public int getRouteLevel() {
        return routeLevel;
    }

    public void setRouteLevel(int routeLevel) {
        this.routeLevel += routeLevel;
    }

    
}
