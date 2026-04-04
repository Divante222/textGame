import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

public abstract class Unit {

    public TreeMap<String, List<EquipmentStats>> equipmentList = new TreeMap<>();
    TreeMap<String, Integer> items = new TreeMap<>();

    public Unit() {
        equipmentList.putAll(Map.of(
            "Helmet", new ArrayList<>(
                    List.of(
                        new EquipmentStats("None", "No stat modified", 1, "You lack a helmet")
                    )
                ),
                "Chest", new ArrayList<>(
                    List.of(
                        new EquipmentStats("None", "No stat modified", 1, "You lack Chest armor")
                    )
                ),
                "Legs", new ArrayList<>(
                    List.of(
                        new EquipmentStats("None", "No stat modified", 1, "You lack Leg armor")
                    )
                ),
                "Hands", new ArrayList<>(
                    List.of(
                        new EquipmentStats("None", "No stat modified", 1, "You lack Hand armor")
                    )
                ),
                "Feet", new ArrayList<>(
                    List.of(
                        new EquipmentStats("None", "No stat modified", 1, "You lack foot armor")
                    )
                ),
                "MainHand", new ArrayList<>(
                    List.of(
                        new EquipmentStats("None", "No stat modified", 1, "You lack a Main hand weapon")
                    )
                ),
                "OffHand", new ArrayList<>(
                    List.of(
                        new EquipmentStats("None", "No stat modified", 1, "You lack a Off Hand tool")
                    )
                )
            )
        );
    }

    public static record EquipmentStats(String equipmentName, String statModified, int amount, String description){
        @Override
        public final String toString() {
            return description;
        }
    }
    
    private String name;
    private int experience = 0;
    private int experienceThreshold;
    private int level;
    private int abilityPoints;

    private int defendingTurnCount = 0;

    private double upperDamageLimit;
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
    
    abstract Map<String, Callable<String>> setInitialAbilities(Ability ability, Map<String, Callable<String>> abilityList);
    abstract void setUnitName();
    abstract void initializeUpperDamageLimit();

    public void addItem(String itemToAdd){
        items.compute(itemToAdd, (key, val) -> (val == null) ? 1 : val + 1);
    };
    
    public void removeItem(String itemToRemove){
        items.compute(itemToRemove, (key, val) -> (val == null) ? 1 : val - 1);
        if(items.get(itemToRemove) == 0){
            items.remove(itemToRemove);
        }
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

    public int getExperience(){
        return experience;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void subtractStrength(int strength){
        this.strength -= strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity += dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
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

    public void addHealth(int healthToAdd) {
        this.health += healthToAdd;
    }

    public void subtractHealth(int healthToSubtract){
        this.health -= healthToSubtract;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void addExperience(int experienceAmount){
        this.experience += experienceAmount;
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

    public void setExperience(int experienceAmount){
        this.experience = experienceAmount;
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
        return (int) Math.ceil(upperDamageLimit);
    }

    public void setUpperDamageLimit(double upperDamageLimit) {
        this.upperDamageLimit = upperDamageLimit;
    }

    public int getExperienceThreshold() {
        return experienceThreshold;
    }

    public void setExperienceThreshold(int experienceThreshold) {
        this.experienceThreshold = experienceThreshold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAbilityPoints() {
        return abilityPoints;
    }

    public void setAbilityPoints(int abilityPoints) {
        this.abilityPoints = abilityPoints;
    }

    // public String abilityExecution(Map<String, Runnable> abilityList){
    //     // try {
    //     //     abilityList.get()
    //     // } catch (Exception e) {
    //     // }
    //     // case "Strong Attack":
    //     //             strongAttackExecution();
    //     //             return "SuccessfulAttack";
    //     return "";
    // }
}
