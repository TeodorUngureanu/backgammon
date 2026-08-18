
public class Board {
    
    private int homeWhite;
    private int homeBlack;
    private int totalStolenWhiteDice;
    private int totalStolenBlackDice;
    private int[] stoneCounts;
    private Stone.Color[] stoneColors;

    public Board(){
        initialize();
    }

    private void initialize(){
        homeWhite = 0;
        homeBlack = 0;
        totalStolenWhiteDice = 0;
        totalStolenBlackDice = 0;
        stoneCounts = new int[24];
        stoneColors = new Stone.Color[24];
        for(int i=0; i<24; i++){
                stoneColors[i] = Stone.Color.NONE;
        }
        stoneCounts[0] = 2; stoneColors[0] = Stone.Color.WHITE;
        stoneCounts[11] = 5; stoneColors[11] = Stone.Color.WHITE;
        stoneCounts[16] = 3; stoneColors[16] = Stone.Color.WHITE;
        stoneCounts[18] = 5; stoneColors[18] = Stone.Color.WHITE;

        stoneCounts[23] = 2; stoneColors[23] = Stone.Color.BLACK;
        stoneCounts[12] = 5; stoneColors[12] = Stone.Color.BLACK;
        stoneCounts[7] = 3; stoneColors[7] = Stone.Color.BLACK;
        stoneCounts[5] = 5; stoneColors[5] = Stone.Color.BLACK;
    }

    public int getStoneCount(int i) {
        if(i < 0 || i > 24) return 0;
        return stoneCounts[i];
    }

    public Stone getStone(int i) {
        if(i < 0 || i > 24) return Stone.NONE;

        switch(stoneColors[i]){
            case WHITE: return Stone.WHITE;
            case BLACK: return Stone.BLACK;
            default: return Stone.NONE;
        }
    }

    public int getNumberOfStolenDice(Stone.Color color) {
        switch(color){
            case WHITE: return totalStolenWhiteDice;
            case BLACK: return totalStolenBlackDice;
            default: return 0;
        }
    }

    public boolean canMove(int from, int count){
        Stone.Color who;
        if(from < 0 || from > 24) return false;

        try {
            if (stoneCounts[from] == 0)
                    who = Stone.Color.BLACK;
            else
                    who = stoneColors[from];
        }catch (Exception e)
        {
            who = Stone.Color.BLACK;
        }

        int target;
        if(who == Stone.Color.WHITE){
            if(totalStolenWhiteDice > 0) return false;
            target = from + count;
        }
        else
        {
            if(totalStolenBlackDice > 0) return false;
            target = from - count;
        }
        if(target > 23 || target < 0){
                return hasAllInBase(who,from);
        }
        Stone.Color tarwho = stoneColors[target];
        if(tarwho == who || tarwho == Stone.Color.NONE){
            return true;
        }
        else
        {
            return stoneCounts[target] == 1;
        }
    }

    public boolean canPut(Stone.Color color, int number){
        switch(color){
            case WHITE:
                if(totalStolenWhiteDice == 0) return false;
                if(stoneColors[number-1] == Stone.Color.BLACK && stoneCounts[number-1] > 1) return false;
                break;
            case BLACK:
                if(totalStolenBlackDice == 0) return false;
                if(stoneColors[24-number] == Stone.Color.WHITE && stoneCounts[24-number] > 1) return false;
                break;
            default:    return false;
        }
        return true;
    }

    public boolean hasAllInBase(Stone.Color color, int except){
        int f;
        int t;
        switch(color){
            case WHITE:
                if(totalStolenWhiteDice > 0) return false;
                f=0; t=18;
                    break;
            case BLACK:
                if(totalStolenBlackDice > 0) return false;
                f=6; t=24;
                break;
            default:    return false;
        }

        for(int i=f; i < t; i++){
            if(stoneColors[i] == color && (i != except || stoneCounts[i] > 1)){
                return false;
            }
        }
        return true;
    }

    public boolean hasAllInBase(Stone.Color color){
        int f;
        int t;
        switch(color){
            case BLACK:
                if(totalStolenBlackDice > 0)    return false;
                f=0; t=18;
                color = Stone.Color.WHITE;
                break;
            case WHITE:
                if(totalStolenWhiteDice > 0)    return false;
                f=6; t=24;
                color = Stone.Color.BLACK;
                break;
            default:    return false;
        }
        for(int i=f; i < t; i++){
            if(stoneColors[i] == color ){
                return false;
            }
        }
        return true;
    }

    public void move(int from, int count) {
        if(stoneColors[from] == Stone.Color.WHITE){
            int target = from + count;
            if(target > 23){
                homeWhite++;
            }else if(stoneColors[target] == Stone.Color.BLACK){
                removeStone(target);
                totalStolenBlackDice++;
                addStone(target,Stone.Color.WHITE);
            }
            else
            {
                addStone(target,Stone.Color.WHITE);
            }
        }
        else
        {
            int target = from - count;
            if(target < 0){
                homeBlack++;
            }else if(stoneColors[target] == Stone.Color.WHITE){
                removeStone(target);
                totalStolenWhiteDice++;
                addStone(target,Stone.Color.BLACK);
            }
            else
            {
                addStone(target,Stone.Color.BLACK);
            }
        }
        removeStone(from);
    }

    public void put(Stone.Color color, int number) throws WrongMoveException{
        if(!canPut(color,number)) throw new WrongMoveException();

        switch(color){
            case WHITE:
                totalStolenWhiteDice--;
                addStone(number-1,color);
                break;
            case BLACK:
                totalStolenBlackDice--;
                addStone(24-number,color);
                break;
        }
    }

    private void removeStone(int from) {
        if(stoneCounts[from] <= 0)
            throw new IllegalArgumentException("Removing stone from zero at " + from);
        stoneCounts[from]--;
        if(stoneCounts[from] == 0){
            stoneColors[from] = Stone.Color.NONE;
        }
    }

    private void addStone(int to, Stone.Color color) {
        if(stoneColors[to] != Stone.Color.NONE && stoneColors[to] != color)
            return; // de intrat si sa ia si piesa (nu sa nu intre pentru ca e una de a mea)
        stoneCounts[to]++;
        if(stoneColors[to] == Stone.Color.NONE){
            stoneColors[to] = color;
        }
    }

    public int getHome(Stone.Color color) {
        switch(color){
            case WHITE: return homeWhite;
            case BLACK: return homeBlack;
            default: return 0;
        }
    }

}
