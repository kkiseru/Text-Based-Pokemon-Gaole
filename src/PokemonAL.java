import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PokemonAL {
    private static ArrayList <ArrayList<String>> Pokelist = new ArrayList<ArrayList<String>>();

    PokemonAL(){}

    public static void readPokelist(){
        try{
            try{
                if (Files.readAllLines(Paths.get("pokemon.txt")).get(5)!=null){
                }
            }catch(Exception e){
                System.out.println(e);                    
                String line1;
                ArrayList<String> pokeori = new ArrayList<>();
                FileReader readfile = new FileReader("pokemonOri.txt");
                BufferedReader readbuffer = new BufferedReader(readfile);
                while ((line1 = readbuffer.readLine())!=null) {
                    pokeori.add(line1);
                }
                FileWriter fw = new FileWriter("pokemon.txt",true);
                BufferedWriter writebuffer = new BufferedWriter(fw);
                writebuffer.newLine();
                for (int i=0; i<pokeori.size(); i++){
                    writebuffer.write(pokeori.get(i));
                    if(i < pokeori.size()-1){
                        writebuffer.newLine();
                    }
                }
                writebuffer.close();
                System.out.println("Pokemon list updated.");
            }
            String line;
            int counter = 0;
            FileReader readfile = new FileReader("pokemon.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            while ((line = readbuffer.readLine())!=null) {
                String[] pokeInfo = line.split(",");
                Pokelist.add(new ArrayList<>());
                Pokelist.get(counter).add(pokeInfo[0]); //name
                Pokelist.get(counter).add(pokeInfo[1]); //type
                Pokelist.get(counter).add(pokeInfo[2]); //grade
                Pokelist.get(counter).add(pokeInfo[3]); //atk
                Pokelist.get(counter).add(pokeInfo[4]); //health
                counter++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removePokelist(){
        Pokelist.remove(0);
    }
    
    public static void addPokelist(ArrayList<String> poke){
        Pokelist.add(0, poke);
    }

    public static ArrayList<String> getPokelist(int index){ 
        return Pokelist.get(index);
    }
}
