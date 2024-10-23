public class NormalType extends Pokemon{
    private String Name;
    private String Grade;
    private int Atk;
    private int Health;

    NormalType(String Name, String Grade, int Atk, int Health){
        super(Name,Grade,Atk,Health);
        this.Name = Name;
        this.Grade = Grade;
        this.Atk = Atk;
        this.Health = Health;
    }

    public String getType(){
        String type = "Normal";
        return type;
    }

    public int checkDamage(String type){
        int damage = 0;
        damage = Atk;
        return damage;
    }
}
