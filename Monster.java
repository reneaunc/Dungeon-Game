import java.io.FilenameFilter;

import java.util.Random;

public class Monster {
    private int health;
    private int damage;
    private String monsterType;
    private final int GOBLINDAMAGE = 10;
    private final int GOBLINHEALTH = 6;
    private final int ZOMBIEDAMAGE = 15;
    private final int ZOMBIEHEALTH = 12;
    private final int ORCDAMAGE = 20;
    private final int ORCHEALTH = 18;
    private final int DENEKEDAMAGE = 5;
    private final int DENEKEHEALTH = 55;
    
    public final static String[] MONSTER_TYPES = new String[]{"Goblin", "Zombie", "Orc", "Deneke"};

    public Monster(String monsterType) {
        this.monsterType = monsterType;
        if(monsterType.equals("Goblin")) {
            this.health = GOBLINHEALTH;
            this.damage = GOBLINDAMAGE;
        }
        else if(monsterType.equals("Zombie")) {
            this.health = ZOMBIEHEALTH;
            this.damage = ZOMBIEDAMAGE;
        }
        else if(monsterType.equals("Orc")) {
            this.health = ORCHEALTH;
            this.damage = ORCDAMAGE;
        }
        else if(monsterType.equals("Deneke")) {
            this.health = DENEKEHEALTH;
            this.damage = DENEKEDAMAGE;
        }
    }
    public void attack(Player target) {
        Random rand = new Random();

        int damageAmount = rand.nextInt(damage);
        target.onHit(damageAmount);
        System.out.println("The " + monsterType + " does " + damageAmount + " points of damage to you");
    }
  
    public void onHit(int damage) {
        //TODO: remove the ammound of health from the monster
        health -= damage;
        if(health < 0){health = 0;}
    }

    public boolean isAlive(){
        return (health > 0);
    }

    public void printHealth(){
        System.out.printf("Monster HP: %d%n", health);
    }
  
 }
 