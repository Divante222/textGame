public class Ability{

    Campaign campaign;
    private String name;

    Ability(Campaign campaign){
        this.campaign = campaign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String strongAttackExecution(){
        System.out.println("Used Strong Attack!");
        int strengthBefore = campaign.character.getStrength();
        campaign.character.setStrength(campaign.character.getStrength() + (2 * campaign.character.getLevel()));
        campaign.character.initializeUpperDamageLimit();
        campaign.combatChoices.playerRegularAttack(campaign.monsterEncountered, campaign.character);
        campaign.character.setStrength(strengthBefore);
        campaign.character.initializeUpperDamageLimit();
        return "SuccessfulAttack"; 
    }

    public String exitAbilityList(){
        return "Exit";
    }
}
