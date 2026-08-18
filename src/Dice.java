
import java.util.Random;

public class Dice {
    
    private int firstDie;
    private int secondDie;

    private int firstDieHowManyTimes;
    private int secondDieHowManyTimes;

    private final Random generator;

    public Dice(){
        generator = new Random();
    }

    public Dice(Dice d) {
        firstDie = d.firstDie;
        secondDie = d.secondDie;
        firstDieHowManyTimes = d.firstDieHowManyTimes;
        secondDieHowManyTimes = d.secondDieHowManyTimes;
        generator = new Random();
    }

    public void roll(){
        firstDie = generator.nextInt(6) + 1;
        secondDie = generator.nextInt(6) + 1;
        if(firstDie == secondDie){
                firstDieHowManyTimes = secondDieHowManyTimes = 2;
        }else{
                firstDieHowManyTimes = secondDieHowManyTimes = 1;
        }
    }

    public boolean isOnDie(int number){
        if(firstDieHowManyTimes > 0 && firstDie == number) return true;
        if(secondDieHowManyTimes > 0 && secondDie == number) return true;
        return false;
    }

    public boolean hasDiceLeft(int number){
        if (number == 1) return firstDieHowManyTimes >0;
        return secondDieHowManyTimes >0;
    }
    public void rollDifferent(){
        do{
            firstDie = generator.nextInt(6) + 1;
            secondDie = generator.nextInt(6) + 1;
        }while(firstDie == secondDie);
        firstDieHowManyTimes = secondDieHowManyTimes = 1;
    }

    public void takeDie(int number){
        if(firstDieHowManyTimes > 0 && firstDie == number){
                firstDieHowManyTimes--;
        }else if(secondDieHowManyTimes > 0 && secondDie == number){
                secondDieHowManyTimes--;
        }else{
                firstDieHowManyTimes--;
                secondDieHowManyTimes--;
        }
    }

    public int getUnusedDice(){
        return firstDieHowManyTimes + secondDieHowManyTimes;
    }

    public boolean isRolled(){
        return firstDieHowManyTimes > 0 || secondDieHowManyTimes > 0;
    }

    public int getFirstDie(){
        return firstDie;
    }

    public int getSecondDie(){
        return secondDie;
    }

}