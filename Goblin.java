public class Goblin extends Unit{

    private String name;

    public Goblin(){
        setConstitution(3);
        setUnitName();
    }

    @Override
    void setInitialAbilities() {
        abilities.add("Sneaky Stab");
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
}