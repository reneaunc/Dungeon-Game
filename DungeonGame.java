//import jdk.internal.org.objectweb.asm.tree.InnerClassNode;

public class DungeonGame {
    private DungeonMap map;
    private Player player;

    private static final String[] VALID_MOVE_INPUTS = new String[]{
        "W", "A", "S", "D"}; 


    public DungeonGame(DungeonMap map_, Player player_){
        map = map_;
        player = player_;
    }

    public void play() {
        map.print();
        player.printStats();
        do{
        

            String moveInput = takeInput("Select a door: Up (W), Left (A), Down (S), Right (D)" , VALID_MOVE_INPUTS);
            player.move(moveInput, map);

            if(!player.isAlive() || player.enoughGold()){
                break;
            }

            System.out.println("Press enter...");
            System.console().readLine();

            System.out.print("\n========================================\n\n");

            map.print();
            player.printStats();


        }
        while(true);

        if(!player.isAlive()){
            System.out.println("\nYour health has dropped to zero!\nI'm sorry pal, buy you're dead.");
        }
        if(player.enoughGold()){
            System.out.println("Congradulations! You now have 100 gold and you can leave.");
        }

    }

    private String takeInput(String question, String[] validInputs){

        Boolean validEntered = false;
        String input;
        do{
            System.out.println(question);
            input = System.console().readLine();
            input = input.toUpperCase();

            
            for(int i = 0; i < validInputs.length; i ++){
                if(input.equalsIgnoreCase(validInputs[i])){
                    validEntered = true;
                }
            }

            if(!validEntered){
                System.out.printf("\"%s\" is not a valid input. Try again.%n", input);
            }
        }
        while(!validEntered);

        return input;
    }
}
