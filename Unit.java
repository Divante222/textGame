import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Unit {
    List items = new ArrayList<>(Arrays.asList("Potion", "Potion", "Potion"));
    List<Ability> abilities = new ArrayList<>(Arrays.asList());

    private String name;

    private int defendingTurnCount = 0;


    private int upperDamageLimit;
    private int strength = 1; 
    private int dexterity = 1;
    private int constitution = 1;
    private int willpower = 1; 
    private int intelligence = 1;
    private int wisdom = 1;

    private double baseBlockChance = 30.0;
    private double baseParryChance = 15.0;
    private int baseBlockStrength = 1;

    private int blockStrength = 0;
    private double blockChance = 0.0;
    private double parryChance = 0.0;

    private int constitutionMultiplier = 2;
    private int willpowerMultiplier = 2;

    private int health = constitutionMultiplier * constitution;
    private int mana = willpower * willpowerMultiplier;
    
    private boolean alive = true;
    private boolean defending = false;
    private boolean parried = false;

    abstract void setInitialAbilities(Campaign campaign);
    abstract void setUnitName();
    abstract void initializeUpperDamageLimit();

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

    public void subtractStrength(int strength){
        this.strength -= strength;
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

    public void subtractHealth(int healthToSubtract){
        this.health -= healthToSubtract;
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

    public boolean isAlive() {
        return alive;
    }

    public boolean checkAlive(){
        if(getHealth() <=0){
            setAlive(false);
        }
        return this.isAlive();
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getBlockStrength() {
        return blockStrength;
    }

    public void setBlockStrength(Unit enemyUnit) {
        this.blockStrength = (getStrength() - enemyUnit.getStrength()) + baseBlockStrength;
    }

    public double getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(Unit enemyUnit) {
        this.blockChance = (getDexterity() - enemyUnit.getDexterity()) + baseBlockChance;
    }

    public double getParryChance() {
        return parryChance;
    }

    public void setParryChance(Unit enemyUnit) {
        this.parryChance = (getStrength() - enemyUnit.getStrength()) + (getDexterity() - enemyUnit.getDexterity()) + this.baseParryChance;
    }

    public void resetParryChance(){
        this.parryChance = 0.0;
    }

    public double getBaseParryChance() {
        return baseParryChance;
    }

    public void setBaseParryChance(double baseParryChance) {
        this.baseParryChance = baseParryChance;
    }

    public double getBaseBlockChance() {
        return baseBlockChance;
    }

    public void setBaseBlockChance(double baseBlockChance) {
        this.baseBlockChance = baseBlockChance;
    }

    public int getBaseBlockStrength() {
        return baseBlockStrength;
    }

    public void setBaseBlockStrength(int baseBlockStrength) {
        this.baseBlockStrength = baseBlockStrength;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isParried() {
        return parried;
    }

    public void setParried(boolean parried) {
        this.parried = parried;
    }

    public int getDefendingTurnCount() {
        return defendingTurnCount;
    }

    public void setDefendingTurnCount(int defendingTurnCount) {
        this.defendingTurnCount = defendingTurnCount;
    }

    public void addToDefendingTurnCount() {
        this.defendingTurnCount += 1;
    }

    public int getUpperDamageLimit() {
        return upperDamageLimit;
    }

    public void setUpperDamageLimit(int upperDamageLimit) {
        this.upperDamageLimit = upperDamageLimit;
    }
}
