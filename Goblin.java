public class Goblin extends Unit{

    private String name;

    public Goblin(){
        setConstitution(3);
        setUnitName();
        initializeUpperDamageLimit();
        setExperience(100);
    }

    @Override
    void setInitialAbilities(Campaign campaign) {
        // abilities.add("Sneaky Stab");
        System.out.print("");
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