public class Goblin extends Unit{

    private String name;

    public Goblin(){
        setConstitution(3);
        setUnitName();
        initializeUpperDamageLimit();
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
        this.setUpperDamageLimit(3 + getStrength());
    }
}