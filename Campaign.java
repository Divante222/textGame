
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Campaign {

    CombatChoices combatChoices = new CombatChoices();

    private int routeLevel = 1;
    public List<String> monsterEncyclopediaWeak = new ArrayList<>();

    public List<String> monsterList = new ArrayList<>();
    public Random randomNumber = new Random();

    public List<Unit> monsterEncounterd = new ArrayList<>();

    public Campaign(){
        setMonsterEncyclopediaWeak();
        setWeakMonsters(routeLevel, monsterEncyclopediaWeak.get(0));
        addMonsterListToEncountered();
    }

    public void campaignStart(){
        System.out.println("This is the start of a series of 10 fights\n");

        while(true){
            System.out.println("You encounterd some monsters");

            for(int i = 0; i < monsterEncounterd.size(); i++){
                System.out.println("\n=======================================================\n");
                System.out.println("Enemy Name: " + monsterEncounterd.get(i).getName() +
                "\nEnemy Hp: " + monsterEncounterd.get(i).getHealth()
                );
                System.out.println("\n=======================================================\n");
            }
            combatChoices.getPlayerCombatChoices();
        }
    }
    
    public void setWeakMonsters(int numberOfMonsters, String monster){
        int randomNumberGenerated = randomNumber.nextInt(numberOfMonsters);
        monsterList.clear();
        for(int i = randomNumberGenerated; i < numberOfMonsters; i++){
            this.monsterList.add(monster);
        }
    }

    public void addMonsterListToEncountered(){
        this.monsterEncounterd.clear();
        for(String monster : this.monsterList){
            if(monster.equals("Goblin")){
                this.monsterEncounterd.add(new Goblin());
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
