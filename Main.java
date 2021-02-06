
public class Main {
    public static void main(String[] args) {
        
        int dungeonColumns = 10;
        int dungeonRows = 10;

        if(args.length == 2){
            dungeonColumns = Integer.parseInt(args[0]);
            dungeonRows = Integer.parseInt(args[1]);
        }

        //THID IS A TEST 3

        Player player = new Player();
        DungeonMap map = new DungeonMap(dungeonColumns, dungeonRows, player);

        //create the dungeonGame object
        DungeonGame dungeonGame = new DungeonGame(map, player);

        dungeonGame.play();

    }
}