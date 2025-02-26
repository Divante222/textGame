import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Unit {
    List items = new ArrayList<>(Arrays.asList("Potion", "Potion", "Potion"));
    List abilities = new ArrayList<>(Arrays.asList());

    private String name;

    private int strength = 1; 
    private int dexterity = 1;
    private int constitution = 1;
    private int willpower = 1; 
    private int intelligence = 1;
    private int wisdom = 1;

    private int constitutionMultiplier = 2;
    private int willpowerMultiplier = 2;

    private int health = constitutionMultiplier * constitution;
    private int mana = willpower * willpowerMultiplier;

    abstract void setInitialAbilities();
    abstract void setUnitName();

    public void addItem(String itemToAdd){
        items.add(itemToAdd);
    };
    
    public void removeItem(String itemToRemove){
        items.remove(itemToRemove);
    }

    public void addAbility(String abilityToAdd){
        abilities.remove(abilityToAdd);
    }

    public int getStrength(){
        return strength;
    }

    public int getDexterity(){
        return dexterity;
    }

    public int getConstitution(){
        return constitution;
    }

    public int getWillpower(){
        return willpower;
    }
    
    public int getIntelligence(){
        return intelligence;
    }

    public int getWisdom(){
        return wisdom;
    }

    public void setStrength(int strength) {
        this.strength += strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity += dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution += constitution;
    }

    public void setWillpower(int willpower) {
        this.willpower += willpower;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence += intelligence;
    }

    public void setWisdom(int wisdom) {
        this.wisdom += wisdom;
    }

    public int getConstitutionMultiplier() {
        return constitutionMultiplier;
    }

    public void setConstitutionMultiplier(int constitutionMultiplier) {
        this.constitutionMultiplier = constitutionMultiplier;
    }

    public int getWillpowerMultiplier() {
        return willpowerMultiplier;
    }

    public void setWillpowerMultiplier(int willpowerMultiplier) {
        this.willpowerMultiplier = willpowerMultiplier;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth() {
        this.health = constitutionMultiplier * constitution;
    }

    public int getMana() {
        return mana;
    }

    public void setMana() {
        this.mana = willpower * willpowerMultiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
