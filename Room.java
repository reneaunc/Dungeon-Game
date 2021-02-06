
import java.util.Random;

import javax.xml.validation.Validator;

public class Room {
    private boolean visited;

    private static final int MIN_GOLD = 1;
    private static final int MAX_GOLD = 20;

    private static final int MIN_HEAL = 1;
    private static final int MAX_HEAL = 8;



    public Room(){
        visited = false;
    }

    public void enter(Player player) {
        if(this.visited){
            System.out.println("This room has already been visited.");
        }
        else{
            this.visited = true;
            Random rand = new Random();
            int roomType = rand.nextInt(3);
        
            //roomType = {0:gold, 1:potion, 2:monster}

            //gold room
            if(roomType == 0){
                int gold = rand.nextInt(Room.MAX_GOLD) + Room.MIN_GOLD;
                System.out.printf("%nYou found a bag of gold lying on the floor with %d pieces of gold.%n", gold);

                player.onLoot(gold);

                player.printStats();
                System.out.print("\n");
            }
            //health room
            else if(roomType == 1){
                int heal = rand.nextInt(Room.MAX_HEAL) + Room.MIN_HEAL;
                System.out.printf("%nYou found a health potion on the ground.%nYou are healed for %d health points.%n", heal);

                player.onHeal(heal);

                player.printStats();
                System.out.print("\n");
            }
            //monster room
            else if(roomType == 2){
                int monsterIndex = rand.nextInt(Monster.MONSTER_TYPES.length);
                String monsterType;
                monsterType = Monster.MONSTER_TYPES[monsterIndex];

                encounter(monsterType, player);

            }
        }
    }

    public boolean hasVisited() {
        return this.visited;
    }

    private void encounter(String monsterType, Player player){
        System.out.print("\n");
        
        Monster monster = new Monster(monsterType);

        System.out.printf("A %s appears!%n%n", monsterType);
        
    
        boolean run = false;

        do{
            String input = attackOrRunDialog();
            if(input.equals("2")){
                run  = true;
            }
            else{
                System.out.printf("%nThe %s attacks!%n", monsterType);
                monster.attack(player);
                
                System.out.print("\n");
                
                player.attack(monster);

                System.out.print("\n");

                player.printStats();

                System.out.print("\n");

                monster.printHealth();

                System.out.print("\n");

            }
            
        }
        while(monster.isAlive() && player.isAlive() && !run);
        
        if(run){
            monster.attack(player);
            player.retreat();
        }

        if(!monster.isAlive()){
            System.out.printf("You have thwarted the %s!%n%n", monsterType);
        }

    }

    private String attackOrRunDialog(){
        String input;
        boolean validInput = false;;
        do{
            System.out.print("Select an action: attack[1] or run[2]\n>");
            input = System.console().readLine();

            if(input.equals("1") || input.equals("2")){
                validInput = true;
            }
            else{
                System.out.printf("\"%s\" is not a valid input. Try again.%n", input);
            }
        }
        while(!validInput);

        return input;
    }

    public void setVisited(){
        visited = true;
    }

}
