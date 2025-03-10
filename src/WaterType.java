public class WaterType extends Pokemon{
    private String Name;
    private String Grade;
    private int Atk;
    private int Health;

    WaterType(String Name, String Grade, int Atk, int Health){
        super(Name,Grade,Atk,Health);
        this.Name = Name;
        this.Grade = Grade;
        this.Atk = Atk;
        this.Health = Health;
    }

    public String getType(){
        String type = "Water";
        return type;
    }

    public int checkDamage(String type){
        int damage = 0;

        if (type == "Fire"){ //effectiveness x1.5
            damage = (int) Math.round(Atk * 1.5);
            System.out.println("Attack is super effective!");
        }

        else if (type == "Grass"){ //effectiveness x0.5
            damage = (int) Math.round(Atk * 0.5);
            System.out.println("Attack is not very effective...");
        }

        else { //effectiveness x1.0
            damage = Atk;
        }
        return damage;
    }
}
