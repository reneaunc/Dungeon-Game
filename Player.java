public class Player {
    private int health;
    private int maxHealth;

    private int gold;
    private int damage;
    private String playerClass;
    private double lootModifier;
    
    private int xPosition;
    private int yPosition;

    private int lastXPosition;
    private int lastYPosition;
  
  
    private final int WARIOR_STARTING_HEALTH = 100;
    private final int WARIOR_DAMAGE = 15;
    private final double WARIOR_LOOT_MODIFIER = 1.0;
  
    private final int THIEF_STARTING_HEALTH = 70;
    private final int THIEF_DAMAGE = 10;
    private final double THIEF_LOOT_MODIFIER = 1.20;
  
  
    public Player(){
        xPosition = 0;
        yPosition = 0;


        //ask the player what class they want and assign the respective constants
        String answer = "";
        boolean validInput = false;
        do{
            System.out.print("Select your class:\n[1] Warior\n[2] Thief\n>");
            answer = System.console().readLine();
            if (answer.equals("1") || answer.equals("2")){ validInput = true;}
        }
        while(!validInput);
  
        if (answer.equals("1")){
            health = WARIOR_STARTING_HEALTH;
            damage = WARIOR_DAMAGE;
            playerClass = "WARIOR";
            lootModifier = WARIOR_LOOT_MODIFIER;
        }
        else{
            health = THIEF_STARTING_HEALTH;
            damage = THIEF_DAMAGE;
            playerClass = "THIEF";
            lootModifier = THIEF_LOOT_MODIFIER;
        }

        maxHealth = health;
  
    }
  
    public void attack(Monster target) {
        System.out.printf("You do %d points of damage.%n", damage);
        target.onHit(damage);
    }
  
    public void onHit(int damage) {
        health -= damage;
        if(health < 0){health = 0;}
    }
    public void onHeal(int health) {
        this.health += health;
        if(this.health > maxHealth){
            this.health = maxHealth;
        }

    }
  
    public void onLoot(int gold) {
        this.gold += (int) gold*lootModifier;
    }
    public String getPlayerClass(){
        return playerClass;
    }
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }

    public boolean isAlive(){
        return (health > 0);
    }
    public boolean enoughGold(){
        return (gold >= 100);
    }
    public void move(String direction, DungeonMap dungeonMap) {
        lastXPosition = xPosition;
        lastYPosition = yPosition;
        
        if(direction.equalsIgnoreCase("w")) {
            yPosition -= 1;
        }
        else if(direction.equalsIgnoreCase("s")) {
            yPosition += 1;
        }
        else if(direction.equalsIgnoreCase("a")) {
            xPosition -= 1;
        }
        else if(direction.equalsIgnoreCase("d")) {
            xPosition += 1;
        }

        if(!dungeonMap.checkPosition(xPosition, yPosition)){
            System.out.println("You can't move into a wall");
            retreat();
        }
        else{
            dungeonMap.getCurrentRoom().enter(this);
        }
        

    }

    public void retreat(){
        xPosition = lastXPosition;
        yPosition = lastYPosition;
    }

    public void printStats(){
        System.out.printf("GP: %d %nHP: %d/%d%n", gold, health, maxHealth);

    }
 }
 