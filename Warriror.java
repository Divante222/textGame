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
    void setInitialAbilities(Campaign campaign) {
        abilities.add(new Ability(campaign, "Strong Attack"));
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

class Ability{

    Campaign campaign;
    private String name;

    Ability(Campaign campaign, String abilityName){
        this.campaign = campaign;
        this.name = abilityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void AbilityExecution(){
        switch(this.getName()){
            case "Strong Attack":{
                strongAttackExecution();
                break;
            }
        }
    }

    public void strongAttackExecution(){
        System.out.println("Strong Attack!");
        campaign.character.setStrength(7);
        campaign.character.initializeUpperDamageLimit();
        campaign.combatChoices.playerRegularAttack(campaign.monsterEncountered, campaign.character);
        campaign.character.subtractStrength(7);
        campaign.character.initializeUpperDamageLimit();
    }
}
