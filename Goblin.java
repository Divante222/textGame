import java.util.Map;
import java.util.concurrent.Callable;

public class Goblin extends Unit{

    private String name;

    public Goblin(){
        setConstitution(3);
        setUnitName();
        initializeUpperDamageLimit();
        setExperience(100);
    }

    @Override
    Map<String, Callable<String>> setInitialAbilities(Ability ability, Map<String, Callable<String>> abilityList){
        abilityList.put("Sneaky Stab", () -> ability.strongAttackExecution());
        return abilityList;
    }

    @Override
    void setUnitName() {
        this.name = "Goblin";
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
}