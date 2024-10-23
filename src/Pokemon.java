public class Pokemon {
    private String Name;
    private String Grade;
    private int Atk;
    private int Health;

    Pokemon(String Name, String Grade, int Atk, int Health){
        this.Name = Name;
        this.Grade = Grade;
        this.Atk = Atk;
        this.Health = Health;
    }

    Pokemon(){}

    public String getName(){
        return Name;
    }

    public String getGrade(){
        return Grade;
    }

    public int getAtk(){
        return Atk;
    }

    public int getHealth(){
        if (Health < 0){
            Health = 0;
        }
        return Health;
    }
    public void setHealth(int h){
        Health = h;
    }

    public String getType(){
        String type = "no type";
        return type;
    }

    public int checkDamage(String type){
        return 0;
    }

    public String toString(){
        return String.format("name:%s, grade:%s, atk:%s, health:%s", Name, Grade, Atk, Health);
    }
}
