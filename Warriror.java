import java.util.Map;
import java.util.concurrent.Callable;

public class Warriror extends Unit{

    private String name;

    public Warriror() {
        this.setConstitution(5);
        this.setHealth();
        this.setStrength(2);
        setUnitName();
        initializeUpperDamageLimit();
        setExperienceThreshold(100);
        setLevel(1);
        setAbilityPoints(1);
        setInitialItems();
    }

    @Override
    Map<String, Callable<String>> setInitialAbilities(Ability ability, Map<String, Callable<String>> abilityList){
        abilityList.put("Strong Attack", () -> ability.strongAttackExecution());
        abilityList.put("Exit", () -> ability.exitAbilityList());
        return abilityList;
    }

    @Override
    void setUnitName() {
        this.name = "Warrior";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    void initializeUpperDamageLimit() {
        this.setUpperDamageLimit(getStrength() * 1.5);
    }

    public void setInitialItems(){
        this.items.put("potion", 3);
    }
}