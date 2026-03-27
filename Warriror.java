public class Warriror extends Unit{

    private String name;

    public Warriror() {
        this.setConstitution(5);
        this.setHealth();
        this.setStrength(2);
        setUnitName();
        initializeUpperDamageLimit();
        setExperienceThreshold(100);
        setLevel(1);
        setAbilityPoints(1);
        setInitialItems();
    }

    @Override
    void setInitialAbilities(Campaign campaign) {
        campaign.character.abilities.add("Strong Attack");
        campaign.character.abilities.add("Exit");
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
        this.setUpperDamageLimit(getStrength() * 1.5);
    }

    public void setInitialItems(){
        this.items.put("potion", 3);
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

    public void setAbilities(){
        campaign.character.abilities.add("Strong Attack");
        campaign.character.abilities.add("Exit");
    }

    public String abilityExecution(String abilityString){
        while(true){
            switch(abilityString){
                case "Strong Attack":
                    strongAttackExecution();
                    return "SuccessfulAttack";
                case "Exit":{
                    return "Exit";
                } default:
                    CommonText.enterValidSelection();
                    campaign.sc.nextLine();
                    return "InvalidTryAgain";
            }  
        }
    }

    public void strongAttackExecution(){
        System.out.println("Used Strong Attack!");
        int strengthBefore = campaign.character.getStrength();
        campaign.character.setStrength(campaign.character.getStrength() + (2 * campaign.character.getLevel()));
        campaign.character.initializeUpperDamageLimit();
        campaign.combatChoices.playerRegularAttack(campaign.monsterEncountered, campaign.character);
        campaign.character.setStrength(strengthBefore);
        campaign.character.initializeUpperDamageLimit();
    }
}
