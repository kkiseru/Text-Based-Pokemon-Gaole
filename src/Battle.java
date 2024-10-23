import java.awt.Component;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    private static ArrayList<ArrayList<String>> ChosenList = new ArrayList<>();
    private static ArrayList<ArrayList<String>> SelectionList = new ArrayList<>();
    private static ArrayList<ArrayList<String>> BushList = new ArrayList<>();
    private static ArrayList<ArrayList<String>> EnemyList = new ArrayList<>();
    private static ArrayList<ArrayList<String>> AllyList = new ArrayList<>();
    private static ArrayList<String> SelectedPokemon = new ArrayList<>();
    private static int BattleScore;

    Battle(){}

    public static boolean Chance(int percentage){
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance < percentage){
            return true;
        }
        else {
            return false;
        }
    }

    public static void GenerateList(){
        ChosenList = new ArrayList<>();
        try {
            if(PokemonAL.getPokelist(5) != null){}
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Not enough Pokemon in Pokemon list, automatically shutting down program. Please restart program to continue using. ");
            Player.writePlayer();
            System.exit(0);
        }
        for (int i=0; i<5; i++){
            ChosenList.add(PokemonAL.getPokelist(0));
            PokemonAL.removePokelist();
        }
        Random rand = new Random();
        int Remove1Pokemon = rand.nextInt(5);
        SelectionList = new ArrayList<>();
        SelectionList = ChosenList;
        SelectionList.remove(Remove1Pokemon);
        System.out.println("\n\nSelect a region to enter! There will be high chance of catching the pokemon in the selected region!");
        for (int i=0; i<SelectionList.size(); i++){
            System.out.println((i+1) + ". " + SelectionList.get(i));
        }
        Scanner myObj = new Scanner(System.in);
        boolean condition = true;
        do{
            try {
                int input = 0;
                System.out.print("\nEnter '1', '2', '3', '4' to select a region: ");
                input = myObj.nextInt();
                SetSelectedPokemon(input);
                condition = false;
            } catch (Exception e) {
                System.out.println("Error");
                myObj.nextLine();
            }
        }while(condition);
    }

    public static void SetSelectedPokemon(int num){
        SelectedPokemon = SelectionList.get(num-1);
        System.out.println(SelectedPokemon.get(0) + " selected.");
    }

    public static ArrayList<String> GetBushList(){
        BushList = new ArrayList<>();
        ArrayList<String> BushPokemon = new ArrayList<>();
        boolean thisIsSelectedPokemon = false;
        boolean condition1 = true;
        do{
            for (int i=0; i<SelectionList.size(); i++){
                if(SelectionList.get(i).get(0).equals(SelectedPokemon.get(0))){
                    thisIsSelectedPokemon = true;
                }
                if(thisIsSelectedPokemon){
                    if(SelectionList.get(i).get(2).equals("5")){
                        if(Chance(10)){
                            BushList.add(SelectionList.get(i));
                        }
                    }
                    else if(SelectionList.get(i).get(2).equals("4")){
                        if(Chance(40)){
                            BushList.add(SelectionList.get(i));
                        }
                    }
                    else{
                        if(Chance(100)){
                            BushList.add(SelectionList.get(i));
                        }
                    }
                }
                else if(thisIsSelectedPokemon = false){
                    if(SelectionList.get(i).get(2).equals("5")){
                        if(Chance(5)){
                            BushList.add(SelectionList.get(i));
                        }
                    }
                    else if(SelectionList.get(i).get(2).equals("4")){
                        if(Chance(20)){
                            BushList.add(SelectionList.get(i));
                        }
                    }
                    else{
                        if(Chance(75)){
                            BushList.add(SelectionList.get(i));
                        }
                    }
                }
                if(BushList.size()==3){
                    condition1 = false;
                }
            }
        }while(condition1);

        System.out.println("\n\nThese 3 Pokemon appeared from the bush!");
        for (int i=0; i<=2; i++){
            System.out.println((i+1) + ". " + BushList.get(i));
        }
        Scanner myObj = new Scanner(System.in);
        boolean condition3 = true;
        do{
            try {
                System.out.print("\nEnter '1', '2', '3' to catch a Pokemon: ");
                int input = myObj.nextInt();
                BushPokemon = BushList.get(input-1);
                SelectionList.remove(BushPokemon);
                condition3 = false;
            } catch (Exception e) {
                System.out.println("Error");
                myObj.nextLine();
            }
        }while(condition3);
        return BushPokemon;
    }

    public static void SpamForEnemy(){
        System.out.println("\n\nNext, your selected pokemon will roar to attract other Pokemons!");
        System.out.println("When the window pops up, spam your Spacebar as fast as you can to get points!");
        System.out.println("The more points you get, the more likely you are to encounter a rare Pokemon!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Window gamewindow = new Window();
        gamewindow.SpamButton1();
        System.out.println("\n\nYou scored " + gamewindow.getScore() + " points!");
    }

    public static void GenerateEnemy(){
        EnemyList = new ArrayList<>();
        boolean condition1 = true;
        do{
            for (int i=0; i<SelectionList.size(); i++){
                if(SelectionList.get(i).get(0).equals(SelectedPokemon.get(0))){
                    if(SelectionList.get(i).get(2).equals("5")){
                        if(Chance(90)){
                            EnemyList.add(SelectionList.get(i));
                            SelectionList.remove(i);
                        }
                    }
                    else{
                        if(Chance(100)){
                            EnemyList.add(SelectionList.get(i));
                            SelectionList.remove(i);
                        }
                    }
                }
                else {
                    if(SelectionList.get(i).get(2).equals("5")){
                        if(Chance(5)){
                            EnemyList.add(SelectionList.get(i));
                            SelectionList.remove(i);
                        }
                    }
                    else if(SelectionList.get(i).get(2).equals("4")){
                        if(Chance(20)){
                            EnemyList.add(SelectionList.get(i));
                            SelectionList.remove(i);
                        }
                    }
                    else{
                        if(Chance(75)){
                            EnemyList.add(SelectionList.get(i));
                            SelectionList.remove(i);
                        }
                    }
                }
                if(EnemyList.size()==2){
                    condition1 = false;
                }
            }
        }while(condition1);
    }

    public static void Fight(){
        Pokemon enemy1 = new Pokemon();
        Pokemon enemy2 = new Pokemon();
        switch(EnemyList.get(0).get(1)){
            case "Normal":
                enemy1 = new NormalType(EnemyList.get(0).get(0), EnemyList.get(0).get(2), Integer.parseInt(EnemyList.get(0).get(3)), Integer.parseInt(EnemyList.get(0).get(4)));
                break;
            case "Fire":
                enemy1 = new FireType(EnemyList.get(0).get(0), EnemyList.get(0).get(2), Integer.parseInt(EnemyList.get(0).get(3)), Integer.parseInt(EnemyList.get(0).get(4)));
                break;
            case "Water":
                enemy1 = new WaterType(EnemyList.get(0).get(0), EnemyList.get(0).get(2), Integer.parseInt(EnemyList.get(0).get(3)), Integer.parseInt(EnemyList.get(0).get(4)));
                break;
            case "Grass":
                enemy1 = new GrassType(EnemyList.get(0).get(0), EnemyList.get(0).get(2), Integer.parseInt(EnemyList.get(0).get(3)), Integer.parseInt(EnemyList.get(0).get(4)));
                break;
        }
        switch(EnemyList.get(1).get(1)){
            case "Normal":
                enemy2 = new NormalType(EnemyList.get(1).get(0), EnemyList.get(1).get(2), Integer.parseInt(EnemyList.get(1).get(3)), Integer.parseInt(EnemyList.get(1).get(4)));
                break;
            case "Fire":
                enemy2 = new FireType(EnemyList.get(1).get(0), EnemyList.get(1).get(2), Integer.parseInt(EnemyList.get(1).get(3)), Integer.parseInt(EnemyList.get(1).get(4)));
                break;
            case "Water":
                enemy2 = new WaterType(EnemyList.get(1).get(0), EnemyList.get(1).get(2), Integer.parseInt(EnemyList.get(1).get(3)), Integer.parseInt(EnemyList.get(1).get(4)));
                break;
            case "Grass":
                enemy2 = new GrassType(EnemyList.get(1).get(0), EnemyList.get(1).get(2), Integer.parseInt(EnemyList.get(1).get(3)), Integer.parseInt(EnemyList.get(1).get(4)));
                break;
        }
        System.out.println("\n\nEnemy " + enemy1.getName() + " appeared!");
        System.out.println("Enemy " + enemy2.getName() + " appeared!");

        System.out.println("\n\nSelect 2 pokemon to battle!");
        for (int i=0; i<Player.GetOwnedPoke().size(); i++){
            System.out.println((i+1) + ". " + Player.GetOwnedPoke().get(i));
        }
        AllyList = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        do{
            try {
                System.out.print("\nEnter '1', '2', '3'... to choose pokemon to battle: ");
                int input = myObj.nextInt();
                if(AllyList.contains(Player.GetOwnedPoke().get(input-1))){
                    System.out.println("This pokemon has been selected, please select a different pokemon.");
                }
                else{
                    AllyList.add(Player.GetOwnedPoke().get(input-1));
                }
            } catch (Exception e) {
                System.out.println("Error");
                myObj.nextLine();
            }
        }while(AllyList.size() < 2);
        Pokemon ally1 = new Pokemon();
        Pokemon ally2 = new Pokemon();
        switch(AllyList.get(0).get(1)){
            case "Normal":
                ally1 = new NormalType(AllyList.get(0).get(0), AllyList.get(0).get(2), Integer.parseInt(AllyList.get(0).get(3)), Integer.parseInt(AllyList.get(0).get(4)));
                break;
            case "Fire":
                ally1 = new FireType(AllyList.get(0).get(0), AllyList.get(0).get(2), Integer.parseInt(AllyList.get(0).get(3)), Integer.parseInt(AllyList.get(0).get(4)));
                break;
            case "Water":
                ally1 = new WaterType(AllyList.get(0).get(0), AllyList.get(0).get(2), Integer.parseInt(AllyList.get(0).get(3)), Integer.parseInt(AllyList.get(0).get(4)));
                break;
            case "Grass":
                ally1 = new GrassType(AllyList.get(0).get(0), AllyList.get(0).get(2), Integer.parseInt(AllyList.get(0).get(3)), Integer.parseInt(AllyList.get(0).get(4)));
                break;
        }
        switch(AllyList.get(1).get(1)){
            case "Normal":
                ally2 = new NormalType(AllyList.get(1).get(0), AllyList.get(1).get(2), Integer.parseInt(AllyList.get(1).get(3)), Integer.parseInt(AllyList.get(1).get(4)));
                break;
            case "Fire":
                ally2 = new FireType(AllyList.get(1).get(0), AllyList.get(1).get(2), Integer.parseInt(AllyList.get(1).get(3)), Integer.parseInt(AllyList.get(1).get(4)));
                break;
            case "Water":
                ally2 = new WaterType(AllyList.get(1).get(0), AllyList.get(1).get(2), Integer.parseInt(AllyList.get(1).get(3)), Integer.parseInt(AllyList.get(1).get(4)));
                break;
            case "Grass":
                ally2 = new GrassType(AllyList.get(1).get(0), AllyList.get(1).get(2), Integer.parseInt(AllyList.get(1).get(3)), Integer.parseInt(AllyList.get(1).get(4)));
                break;
        }        
        System.out.println("\n\nBattle Start!");
        Window1 window = new Window1();
        Window1.allyWidth = 100;
        Window1.enemyWidth = 100;
        int roundCounter = 0;
        int allyTurnCounter = 1;
        int enemyTurnCounter = 1;
        boolean a1Fainted = false;
        boolean a2Fainted = false;
        boolean e1Fainted = false;
        boolean e2Fainted = false;
        boolean condition1 = true;
        boolean battleWon = false;
        do{
            System.out.println("*************************************************************************************************");
            System.out.println("Round " + roundCounter++);
            System.out.println("\nEnemy:");
            System.out.printf("%-12s | %s type | %s* | Health - %d\n", enemy1.getName(), enemy1.getType(), enemy1.getGrade(), enemy1.getHealth());
            System.out.printf("%-12s | %s type | %s* | Health - %d\n", enemy2.getName(), enemy2.getType(), enemy2.getGrade(), enemy2.getHealth());

            System.out.println("\nAlly:");
            System.out.printf("%-12s | %s type | %s* | Health - %d\n", ally1.getName(), ally1.getType(), ally1.getGrade(), ally1.getHealth());
            System.out.printf("%-12s | %s type | %s* | Health - %d\n", ally2.getName(), ally2.getType(), ally2.getGrade(), ally2.getHealth());

            System.out.println("\n\nWhen the window pops up, spam the Spacebar!");
            System.out.println("The first to reach the end will attack first!");
            Scanner myObj1 = new Scanner(System.in);
            boolean condition = true;
            do{
                System.out.println("Enter 'attack' to start the sequence.");
                String input = myObj1.nextLine();
                if (input.equals("attack")){
                    condition = false;
                }
                else{
                    System.out.println("Input error.");
                }
            }while(condition);

            window.SpamButton2();

            System.out.println("\n\n");
            if(Window1.allyWidth>300){
                Window1.allyWidth = 100;
                if(allyTurnCounter==1){
                    System.out.println(ally1.getName() + " turn to attack.");
                    for(int i=0; i<1; i++){
                        if (a1Fainted){
                            System.out.println(ally1.getName() + " has Fainted.");
                            break;
                        }
                        else {
                            System.out.println(">>" + ally1.getName() + " attacks " + enemy1.getName() + "!");
                            enemy1.setHealth((enemy1.getHealth()-ally1.checkDamage(enemy1.getType())));
                            System.out.println("\n>>" + ally1.getName() + " attacks " + enemy2.getName() + "!");
                            enemy2.setHealth((enemy2.getHealth()-ally1.checkDamage(enemy2.getType())));
                        }
                    }
                    
                    allyTurnCounter++;
                }
                else{
                    System.out.println(ally2.getName() + " turn to attack.");
                    for(int i=0; i<1; i++){
                        if (a2Fainted){
                            System.out.println(ally2.getName() + " has Fainted.");
                            break;
                        }
                        else {
                            System.out.println(">>" + ally2.getName() + " attacks " + enemy1.getName() + "!");
                            enemy1.setHealth((enemy1.getHealth()-ally2.checkDamage(enemy1.getType())));
                            System.out.println("\n>>" + ally2.getName() + " attacks " + enemy2.getName() + "!");
                            enemy2.setHealth((enemy2.getHealth()-ally2.checkDamage(enemy2.getType())));
                        }
                    }
                    allyTurnCounter = 1;
                }
            }
            else if(Window1.enemyWidth>300){
                Window1.enemyWidth = 100;
                if(enemyTurnCounter==1){
                    System.out.println(enemy1.getName() + " turn to attack.");
                    for(int i=0; i<1; i++){
                        if (e1Fainted){
                            System.out.println(enemy1.getName() + " has Fainted.");
                            break;
                        }
                        else {
                            System.out.println(">>" + enemy1.getName() + " attacks " + ally1.getName() + "!");
                            ally1.setHealth((ally1.getHealth()-ally1.checkDamage(ally1.getType())));
                            System.out.println("\n>>" + enemy1.getName() + " attacks " + ally2.getName() + "!");
                            ally2.setHealth((ally2.getHealth()-ally2.checkDamage(ally2.getType())));
                        }
                    }
                    enemyTurnCounter++;
                }
                else{
                    System.out.println(enemy2.getName() + " turn to attack.");
                    for(int i=0; i<1; i++){
                        if (e2Fainted){
                            System.out.println(enemy2.getName() + " has Fainted.");
                            break;
                        }
                        else {
                            System.out.println(">>" + enemy2.getName() + " attacks " + ally1.getName() + "!");
                            ally1.setHealth((ally1.getHealth()-ally1.checkDamage(ally1.getType())));
                            System.out.println("\n>>" + enemy2.getName() + " attacks " + ally2.getName() + "!");
                            ally2.setHealth((ally2.getHealth()-ally2.checkDamage(ally2.getType())));
                        }
                    }
                    enemyTurnCounter = 1;
                }
            }
            
            if(ally1.getHealth() == 0){
                a1Fainted = true;
            }
            if(ally2.getHealth() == 0){
                a2Fainted = true;
            }
            if(enemy1.getHealth() == 0){
                e1Fainted = true;
            }
            if(enemy2.getHealth() == 0){
                e2Fainted = true;
            }

            if (a1Fainted && a2Fainted){
                condition1 = false;
                battleWon = false;
            }
            else if(e1Fainted && e2Fainted){
                condition1 = false;
                battleWon = true;
            }
        }while(condition1);

        if(battleWon){
            System.out.println("\n\nThe enemy Pokemons are fainted!\nYou can start catching them now!");
            catchPokemon();
        }
        else{
            System.out.println("\n\nYour Pokemons have fainted...\nPlease head to the nearest Pokecenter to heal them.");
        }
        calculateScore(roundCounter);
        System.out.println("\nCalculating score now... ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Your score: " + getScore());
    }

    public static void catchPokemon(){
        Ball ball = new Ball();
        Random rand = new Random();
        System.out.println("\n\nGenerating ball to catch...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int rng = rand.nextInt(100);
        if (rng <= 58){
            ball = new Pokeball(20);
            System.out.println("You used a Pokeball!");
        }
        else if(rng <= 88 && rng >= 59){
            ball = new Greatball(40);
            System.out.println("You used a Greatball!");
        }
        else if(rng <= 98 && rng >= 89){
            ball = new Ultraball(60);
            System.out.println("You used a Ultraball!");
        }
        else if(rng <= 99){
            ball = new Masterball(100);
            System.out.println("You used a Masterball!");
        }
        
        System.out.println("\nCatching " + EnemyList.get(0).get(0) + " ...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ball.catchMethod(Integer.parseInt(EnemyList.get(0).get(2)))){
            System.out.println("You caught " + EnemyList.get(0).get(0) + "!");
            Player.AddOwnedPoke(EnemyList.get(0));
            System.out.println(EnemyList.get(0).get(0) + " added to bag.");
        }
        else{
            System.out.println(EnemyList.get(0).get(0) + " ran away...");
        }
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nCatching " + EnemyList.get(1).get(0) + " ...");
        if(ball.catchMethod(Integer.parseInt(EnemyList.get(1).get(2)))){
            System.out.println("You caught " + EnemyList.get(1).get(0) + "!");
            Player.AddOwnedPoke(EnemyList.get(1));
            System.out.println(EnemyList.get(1).get(0) + " added to bag.");
        }
        else{
            System.out.println(EnemyList.get(1).get(0) + " ran away...");
        }
    }

    public static void calculateScore(int roundPassed){
        int baseScore = 54000;
        BattleScore = baseScore - (roundPassed * 2000);
        if(BattleScore < 0){
            BattleScore = 0;
        }
    }

    public static int getScore(){
        return BattleScore;
    }
}

