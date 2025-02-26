public class Warriror extends Unit{

    private String name;

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
}
