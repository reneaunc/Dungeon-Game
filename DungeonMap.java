
public class DungeonMap {
    private Room[][] rooms;
    private Player player;

    private int columns;
    private int rows;

    private static final String VISITED_STRING = "[]";
    private static final String UNVISITED_STRING = "  ";
    private static final String WALL_STRING = "##";
    private static String PLAYER_STRING;

    public DungeonMap (int rows, int columns, Player player){
        //loop throught the width and height of the dungeona and adding the rooms to the array
        this.rows = rows;
        this.columns = columns;

        this.player = player;
        
        if(player.getPlayerClass().equalsIgnoreCase("Warior")){
            PLAYER_STRING = "WW";
        }
        else if(player.getPlayerClass().equalsIgnoreCase("Thief")){
            PLAYER_STRING = "TT";
        }


        rooms = new Room[rows][columns];
        for (int y = 0; y < rows; y++){
            for(int x = 0; x < columns; x++){
                rooms[y][x] = new Room();
            }
        }

        rooms[0][0].setVisited(); // the starting room needs to be already visited
    }

    public void print() {
        String printString = "";
        
        //add the top wall row
        for(int i = 0; i < columns + 2; i++){
            printString += DungeonMap.WALL_STRING;
        }
        printString += "\n";

        //TODO: print the dungeon map to the terminal

        for(int y = 0; y < rows; y++){
            String row = DungeonMap.WALL_STRING; //the row always starts with a wall character
            
            for(int x = 0; x < columns; x++ ){
                
                String roomVisitedString = DungeonMap.UNVISITED_STRING;
                if(rooms[y][x].hasVisited()){
                    roomVisitedString = DungeonMap.VISITED_STRING;
                }
                if(player.getXPosition() == x && player.getYPosition() == y){
                    roomVisitedString = DungeonMap.PLAYER_STRING;
                }

                row += roomVisitedString;

            }

            row += DungeonMap.WALL_STRING + "\n"; //the row ends with a wall character and a return
            printString += row;
        }

        //add the bottom wall row
        for(int i = 0; i < columns + 2; i++){
            printString += DungeonMap.WALL_STRING;
        }
        printString += "\n";

        System.out.print(printString);

    }

    public boolean checkPosition(int x, int y){
        if(x<0 || y<0 || x>=columns || y>=rows ){
            return false;
        }
        else{
            return true;
        }

    }

    public Room getCurrentRoom(){
        return rooms[player.getYPosition()][player.getXPosition()];
    }
}
