
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("\t\t    Backgammon Game\n");
        System.out.println("\t\t        -Board-\n");
        System.out.println("\t  Colors: WHITE = 'O' and BLACK = 'X'\n");
        int count = 1;
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        int flag = 0, computerFlag = 0;
        Game g = new Game();
        Board b = g.getBoard();
        Dice d = g.getDice();
        boolean played = false;

        DrawBoard.draw(b);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        g.roll();        
        Dice d2 = new Dice(g.getDice());
        
        try
        {
            while(!g.isFinal()) {
                if(d.getFirstDie() == d.getSecondDie()) count = 2;
                else count =1;
                d = g.getDice();
                d2 = new Dice(g.getDice());
                //d2.roll();

                Stone.Color player = g.getPlayer();
                if (g.getPlayer() == Stone.Color.WHITE)
                    System.out.println("\nPlayer: " + g.getPlayer() + " rolled: " + d.getFirstDie() + " and " + d.getSecondDie());
                else
                    System.out.println("\nPlayer: " + g.getPlayer() + " rolled: " + d2.getFirstDie() + " and " + d2.getSecondDie());
                System.out.println("\n\t\t     CURRENT BOARD\n");
                Thread.sleep(2000);
                DrawBoard.draw(b);
                System.out.println("");

                int in;
                if (b.getNumberOfStolenDice(player) > 0) {
                    if (g.isValidPutTransition(d.getFirstDie())) {
                        g.makePutTransition(d.getFirstDie());
                        if(player == Stone.Color.BLACK) in = 25 - d.getFirstDie();
                        else in = d.getFirstDie();
                        System.out.println("Placing in " + (in) );

                    } else if (g.isValidPutTransition(d.getSecondDie())) {
                        g.makePutTransition(d.getSecondDie());
                        if(player == Stone.Color.BLACK) in = 25 - d.getSecondDie();
                        else in = d.getSecondDie();
                        System.out.println("Placing in " + (in) );

                    } else {
                        System.out.println("Can't play...");
                        Thread.sleep(2000);

                    }
                } else {


                    if(g.getPlayer() == Stone.Color.WHITE){ //Jucatorul
                        boolean _flag = true;
                        for (int i = 0; i < count && _flag==true; i++) {
                            while(flag == 0) {
                                System.out.print("First die change the position:  ");
                                choose = scanner.nextInt();
                                
                                if(b.getStone(choose - 1) == Stone.WHITE && g.isValidTransition(choose - 1, d.getFirstDie())) flag = 1;
                                
                                _flag = false;
                                for(int indice=0; indice<24; indice++)
                                {
                                    if(g.isValidTransition(indice, d.getFirstDie()))
                                        _flag = true;
                                }
                            }
                            g.makeTransition(choose - 1, d.getFirstDie());
                            while(flag == 1) {
                                DrawBoard.draw(b);
                                System.out.print("\nSecond die change the position: ");
                                choose = scanner.nextInt();
                                if(b.getStone(choose - 1) == Stone.WHITE && g.isValidTransition(choose - 1, d.getSecondDie())) flag = 0;
                            
                                 _flag = false;
                                for(int indice=0; indice<24; indice++)
                                {
                                    if(g.isValidTransition(indice, d.getFirstDie()))
                                        _flag = true;
                                }
                            }

                            g.makeTransition(choose - 1, d.getSecondDie());
                        }
                    }
                    else 
                    {  //Calculatorul 
                        Random randomOrAI;
                        randomOrAI = new Random();
                        int choice = randomOrAI.nextInt(2);

                        switch(choice)
                        {
                            case 0 : 
                            {
                                for (int k = 0; k < count; k++) 
                                {
                                    Random randomPosition;
                                    randomPosition = new Random();
                                    int from = randomPosition.nextInt(24);
                                    int from2 = randomPosition.nextInt(24);
                                    boolean playedFirstDie = false;
                                    boolean playedSecondDie = false;
                                    
                                    while (playedFirstDie == false || playedSecondDie == false) 
                                    {
                                        if (b.getStone(from).color() == player) {
                                            
                                            if (g.isValidTransition(from, d2.getFirstDie())) {
                                                g.makeTransition(from, d2.getFirstDie());
                                                System.out.println("[RandomFirstDie]Moving from " + (from + 1) + " " + (d2.getFirstDie()) + "steps."); 
                                                playedFirstDie = true;
                                                while (playedSecondDie == false)
                                                {
                                                    if (b.getStone(from2).color() == player)
                                                    {
                                                        if(g.isValidTransition(from2, d2.getSecondDie()))
                                                        {
                                                            g.makeTransition(from2, d2.getSecondDie());
                                                            System.out.println("[RandomSecondDie]Moving from " + (from2 + 1) + " " + (d2.getSecondDie()) + "steps."); 
                                                            playedSecondDie = true;
                                                        }
                                                    }
                                                    from2 = randomPosition.nextInt(24);
                                                }
                                            }
                                        }
                                        from = randomPosition.nextInt(24);
                                    }
                                }
                                break;
                            }
                            
                            case 1 : 
                            {
                                for (int k = 0; k < count; k++) 
                                {
                                    played = false;
                                    for (int i = 0; i < 24 && computerFlag == 0; i++) {
                                        int from = 23 - i;
                                        if (b.getStone(from).color() == player) {
                                            if (g.isSafeToMove(from)) {
                                                if (g.isValidTransition(from, d.getFirstDie())) {
                                                    g.makeTransition(from, d.getFirstDie());
                                                    g.makeTransition(from - d.getFirstDie(), d.getSecondDie());
                                                    System.out.println("[IA]Moving from " + (from + 1) + " " + (d.getFirstDie() + d.getSecondDie()) + "steps.");
                                                } else {
                                                    g.makeTransition(from, d.getSecondDie());
                                                    g.makeTransition(from - d.getSecondDie(), d.getFirstDie());
                                                    System.out.println("[IA]Moving from " + (from + 1) + " " + (d.getFirstDie() + d.getSecondDie()) + "steps.");
                                                }
                                                played = true;
                                            }
                                            if (played == false) {
                                                for (int j = i + 1; j < 24; j++) {
                                                    int from2 = 23 - j;

                                                    if (b.getStone(from2).color() == player) {
                                                        if (g.isSafeToMove(from, from2) == 1) {
                                                            g.makeTransition(from, d.getFirstDie());
                                                            g.makeTransition(from2, d.getSecondDie());
                                                            System.out.println("[IA]Moving from " + (from + 1) + " " + d.getFirstDie() + "steps.");
                                                            System.out.println("[IA]Moving from " + (from2 + 1) + " " + +d.getSecondDie() + "steps.");
                                                            played = true;
                                                        } else if (g.isSafeToMove(from, from2) == 2) {
                                                            g.makeTransition(from, d.getSecondDie());
                                                            g.makeTransition(from2, d.getFirstDie());
                                                            System.out.println("[IA]Moving from " + (from + 1) + " " + d.getSecondDie() + "steps.");
                                                            System.out.println("[IA]Moving from " + (from2 + 1) + " " + +d.getFirstDie() + "steps.");
                                                            played = true;
                                                        }

                                                    }
                                                }
                                            }
                                        }
                                    }

                                    if (computerFlag == 0) {
                                        if (played == false)
                                            do {
                                                played = false;

                                                for (int i = 0; i < 24; i++) {
                                                    int from = i;
                                                    if (player == Stone.Color.BLACK) {
                                                        from = 23 - i;
                                                    }
                                                    if (b.getStone(from).color() == player) {
                                                        if (g.isValidTransition(from, d.getFirstDie()) && d.hasDiceLeft(1)) {
                                                            g.makeTransition(from, d.getFirstDie());
                                                            played = true;

                                                            System.out.println("[IA]Moving from " + (from + 1) + " " + d.getFirstDie() + "steps.");
                                                            break;
                                                        } else if (g.isValidTransition(from, d.getSecondDie()) && d.hasDiceLeft(2)) {
                                                            g.makeTransition(from, d.getSecondDie());
                                                            played = true;
                                                            System.out.println("[IA]Moving from " + (from + 1) + " " + d.getSecondDie() + "steps.");
                                                            break;
                                                        }
                                                    }
                                                }

                                            } while (played /*&& d.getUnusedDices() > 0*/);
                                    }
                                }
                                break;
                            }
                        }
                    }

                }
                if(g.getPlayer() == Stone.Color.WHITE) {
                    g.setPlayer(Stone.Color.BLACK);
                    g.setDice(d2);
                }
                else
                    g.roll();
            }
        }catch (InterruptedException | WrongMoveException e){}

        DrawBoard.draw(b);
        System.out.println(g.winner() + " is the winner!");
    }
    
}