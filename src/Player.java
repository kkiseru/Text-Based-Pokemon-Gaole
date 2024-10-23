import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private String Username;
    private int Score;
    private static ArrayList <ArrayList<String>> OwnedPokemon = new ArrayList<ArrayList<String>>();
    private static ArrayList<String> currentAcc = new ArrayList<>();
    private static ArrayList<String> slot1 = new ArrayList<>();
    private static ArrayList<String> slot2 = new ArrayList<>();
    private static ArrayList<String> slot3 = new ArrayList<>();
    private static ArrayList<String> slot4 = new ArrayList<>();
    private static ArrayList<String> slot5 = new ArrayList<>();

    Player(String Username, int Score, ArrayList <ArrayList<String>> OwnedPokemon){
        this.Username = Username;
        this.OwnedPokemon = OwnedPokemon;
        this.Score = Score;
    }

    Player(){}

    public static void readPlayer(){
        try{
            ArrayList<String> playerRead = new ArrayList<String>();
            String line;
            FileReader readfile = new FileReader("player.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            while ((line = readbuffer.readLine())!=null) {
                playerRead.add(line);
            }
            ArrayList<String> currentList = null;
            for (String item : playerRead) {
                if (item.startsWith("slot")) {
                    switch (item) {
                        case "slot1":
                            currentList = slot1;
                            break;
                        case "slot2":
                            currentList = slot2;
                            break;
                        case "slot3":
                            currentList = slot3;
                            break;
                        case "slot4":
                            currentList = slot4;
                            break;
                        case "slot5":
                            currentList = slot5;
                            break;
                    }
                }
                else {
                    currentList.add(item);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void writePlayer(){
        try {
            FileWriter writefile = new FileWriter("player.txt");
            BufferedWriter writebuffer = new BufferedWriter(writefile);
            String str = null;
            ArrayList<String> currentList = null;
            for (int num=0; num<5; num++){
                switch(num){
                    case 0:
                        str = "slot1";
                        currentList = slot1;
                        break;
                    case 1:
                        str = "slot2";
                        currentList = slot2;
                        break;
                    case 2:
                        str = "slot3";
                        currentList = slot3;
                        break;
                    case 3:
                        str = "slot4";
                        currentList = slot4;
                        break;
                    case 4:
                        str = "slot5";
                        currentList = slot5;
                        break;
                }
                writebuffer.write(str);
                writebuffer.newLine();
                for (int i=0; i<currentList.size(); i++){
                    writebuffer.write(currentList.get(i));
                    writebuffer.newLine();
                }
            }
            writebuffer.close();
            System.out.println("File write successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void GetPlayerMenu(){
        ArrayList<String> currentList = null;
        System.out.println("\n\nChoose a player account:");
        for (int i=1; i<6; i++){
            switch (i){
                case 1:
                    currentList = slot1;
                    break;
                case 2:
                    currentList = slot2;
                    break;
                case 3:
                    currentList = slot3;
                    break;
                case 4:
                    currentList = slot4;
                    break;
                case 5:
                    currentList = slot5;
                    break;
            }
            System.out.println("*************************************************");
            System.out.println("Slot " + i);
            System.out.println("Account Name: " + currentList.get(0));
            System.out.println("High Score: " + currentList.get(1));
            System.out.println("Owned Pokemon: ");
            for (int x=2; x<currentList.size(); x++){
                System.out.println(currentList.get(x));
            }
        }
        System.out.println("*************************************************");
    }

    public void EmptyAccChecker(){
        if (Username.equals("empty")){
            Scanner myObj1 = new Scanner(System.in);
            System.out.print("Enter name for new account: ");
            SetUsername(myObj1.nextLine());
        }
    }

    public static void SetPlayerAcc(){
        boolean condition = true;
        String slot = "0";
        Scanner myObj2 = new Scanner(System.in);
        do{
            System.out.println("Enter selected player account: ");
            slot = myObj2.nextLine();
            if (slot.equals("1")||slot.equals("2")||slot.equals("3")||slot.equals("4")||slot.equals("5")){
                switch (slot) {
                    case "1":
                        currentAcc = slot1;
                        break;
                    case "2":
                        currentAcc = slot2;
                        break;
                    case "3":
                        currentAcc = slot3;
                        break;
                    case "4":
                        currentAcc = slot4;
                        break;
                    case "5":
                        currentAcc = slot5;
                        break;
                }
                condition = false;
            }
            else{
                System.out.println("error");
            }
        } while(condition);
        System.out.println("Player account: [" + currentAcc.get(0) + "] selected.");
    }

    public static String GetCurrentAccUser(){
        return currentAcc.get(0);
    }

    public static int GetCurrentAccScore(){
        return Integer.parseInt(currentAcc.get(1));
    }

    public static ArrayList <ArrayList<String>> GetCurrentAccList(){
        ArrayList <ArrayList<String>> PokeList = new ArrayList<ArrayList<String>>();
        int counter = 0;
        for (int i=2; i<currentAcc.size(); i++) {
            String[] pokeInfo = currentAcc.get(i).split(",");
            PokeList.add(new ArrayList<>());
            PokeList.get(counter).add(pokeInfo[0]); //name
            PokeList.get(counter).add(pokeInfo[1]); //type
            PokeList.get(counter).add(pokeInfo[2]); //grade
            PokeList.get(counter).add(pokeInfo[3]); //atk
            PokeList.get(counter).add(pokeInfo[4]); //health
            counter++;
        }
        return PokeList;
    }

    public static void GetHighscore(){
        ArrayList<Integer> Highscore = new ArrayList<>(Arrays.asList(0,0));
        ArrayList<String> HighscoreName = new ArrayList<>(Arrays.asList(null,null));
        ArrayList<String> currentList = null;
        for(int y=0; y<5; y++){
            switch(y){
                case 0:
                    currentList = slot1;
                    break;
                case 1:
                    currentList = slot2;
                    break;
                case 2:
                    currentList = slot3;
                    break;
                case 3:
                    currentList = slot4;
                    break;
                case 4:
                    currentList = slot5;
                    break;
            }
            for (int i=0; i<Highscore.size(); i++){
                if (Integer.parseInt(currentList.get(1)) >= Highscore.get(i)){
                    Highscore.add(i,Integer.parseInt(currentList.get(1)));
                    HighscoreName.add(i,currentList.get(0));
                    break;
                }
            }
        }
        for (int z=5; z<HighscoreName.size();){
                Highscore.remove(z);
                HighscoreName.remove(z);
        }
        System.out.println("\n\n");
        for (int x=0; x<Highscore.size(); x++){
            System.out.printf("%d. %-7d %s\n", (x+1), Highscore.get(x), HighscoreName.get(x));
        }
    }

    public String GetUsername(){
        return Username;
    }
    public void SetUsername (String name){
        Username = name;
        currentAcc.set(0, name);
    }

    public int GetScore(){
        return Score;
    }
    public void SetScore(int score){
        Score = score;
        currentAcc.set(1, String.valueOf(score));
    }

    public static void AddOwnedPoke(ArrayList<String> list){
        OwnedPokemon.add(list);
        currentAcc.add(3, String.join(",", list.get(0),list.get(1),list.get(2),list.get(3),list.get(4)));
    }
    public static ArrayList <ArrayList<String>> GetOwnedPoke(){
        return OwnedPokemon;
    }
}
