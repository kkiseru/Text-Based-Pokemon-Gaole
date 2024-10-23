import java.util.Random;

public class Ball {
    private int catchRate;

    Ball(int catchRate){
        this.catchRate = catchRate;
    }
    Ball(){}

    public boolean catchMethod(int grade){
        switch(grade){
            case 1:
                catchRate = catchRate + 50;
                break;
            case 2:
                catchRate = catchRate + 40;
                break;
            case 3:
                catchRate = catchRate + 30;
                break;
            case 4:
                catchRate = catchRate + 10;
                break;
            case 5:
                break;
        }

        Random rand = new Random();
        int rng = rand.nextInt(100);
        if (rng < catchRate){
            return true;
        }
        else{
            return false;
        }
    }
}
