public class Warriror extends Unit{

    private String name;

    public Warriror() {
        this.setConstitution(5);
        this.setHealth();
        this.setStrength(1);
        setUnitName();
        initializeUpperDamageLimit();
    }

    @Override
    void setInitialAbilities() {
        abilities.add("Strong Attack");
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
        this.setUpperDamageLimit(3 + getStrength());
    }
}
