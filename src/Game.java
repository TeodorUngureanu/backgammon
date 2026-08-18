
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
	Random generator;
	Board board;
	Stone.Color player;
	Dice dice;

	public void setDice(Dice dice) {
            this.dice = dice;
	}

	public void setPlayer(Stone.Color player) {
            this.player = player;
	}

	public Game(){
            generator = new Random();
            board = new Board();
            player = Stone.Color.NONE;
            dice = new Dice();
	}

	public void roll(){
            try {
                System.out.print("Rolling dice");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("");
            if(player == Stone.Color.NONE){
                    dice.rollDifferent();
            }else{
                    dice.roll();
            }

            switch(player){
                case WHITE:
                        player = Stone.Color.BLACK;
                        break;
                case BLACK:
                        player = Stone.Color.WHITE;
                        break;
                case NONE:
                        player = Stone.Color.WHITE;
                        break;
            }
	}

	public boolean isValidTransition(int from, int count){
            if(!dice.isRolled()) return false;
            if(!board.canMove(from, count)) return false;
            return true;
	}

	public void makeTransition(int from, int count) {
            board.move(from,count);
            dice.takeDie(count);
	}

	public boolean isValidPutTransition(int number){
            if(!dice.isRolled()) return false;
            if(!dice.isOnDie(number)) return false;
            if(!board.canPut(player, number)) return false;
            return true;
	}

	public void makePutTransition(int number) throws WrongMoveException{
            if(!isValidPutTransition(number)) throw new WrongMoveException();
            board.put(player,number);
            dice.takeDie(number);
	}

	public Board getBoard(){
            return board;
	}

	public Stone.Color getPlayer(){
            return player;
	}

	public Dice getDice(){
            return dice;
	}

	public boolean isFinal(){
            if(board.hasAllInBase(Stone.Color.WHITE)  || board.hasAllInBase(Stone.Color.BLACK) ) return  true;
            return false;
	}

	public Stone.Color winner(){
            if(!isFinal()) return Stone.Color.NONE;
            if(board.hasAllInBase(Stone.Color.WHITE) ) return Stone.Color.BLACK;
            else return Stone.Color.WHITE;
	}

	public boolean isSafeToMove(int from) {
            int d1 = dice.getFirstDie();
            int d2 = dice.getSecondDie();

            if(board.getStoneCount(from) == 2) return false;
            
            if(isValidTransition(from, d1) && isValidTransition(from - d1, d2)) {    					
                    if(board.getStone(from - d1 - d2).getColor() == Stone.Color.BLACK )
                    return true;
            }
            if(isValidTransition(from, d2) && isValidTransition(from - d2, d1)) {
                    if(board.getStone(from - d1 - d2).getColor() == Stone.Color.BLACK)
                    return true;
            }

            return false;
	}

	public int isSafeToMove(int from, int from2) {
            int d1 = dice.getFirstDie();
            int d2 = dice.getSecondDie();

            if(board.getStoneCount(from) == 2) return 0;
            if(board.getStoneCount(from2) == 2) return 0;

            if(from - from2 == d1 - d2){
                    if(isValidTransition(from, d1)) return 1 ;
            }
            if(from - from2 == d2 - d1){
                    if(isValidTransition(from, d2)) return 2;
            }
            if(isValidTransition(from, d1) && isValidTransition(from2, d2)) {
                    if(board.getStone(from - d1).getColor() == Stone.Color.BLACK  && board.getStone(from2 - d2).getColor() == Stone.Color.BLACK)
                            return 1;
            }
            if(isValidTransition(from, d2) && isValidTransition(from2, d1)) { 
                    if(board.getStone(from - d2).getColor() == Stone.Color.BLACK  && board.getStone(from2 - d1).getColor() == Stone.Color.BLACK)
                            return 2;
            }

            return 0;
	}
}
