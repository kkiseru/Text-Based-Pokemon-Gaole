import java.util.Scanner;

public class Game{
    public Game(){
    }

    public void StartGame(){
        Player.readPlayer();
        PokemonAL.readPokelist();
        StartMenu();
        GameMenu();
    }

    public static void StartMenu(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("\n\n\n\n\n\n\n\n\n\nWelcome to Pokemon GaOle!");
        boolean condition = true;
        do{
            System.out.println("Enter 'start' to begin!");
            String beginInput = myObj.nextLine();
            if (beginInput.equals("start")){
                condition = false;
            }
            else{
                System.out.println("Input error!\n");
            }
        } while(condition);
        Player.GetPlayerMenu();
        Player.SetPlayerAcc();
        Player player = new Player(Player.GetCurrentAccUser(), Player.GetCurrentAccScore(), Player.GetCurrentAccList());
        player.EmptyAccChecker();
    }

    public static void GameMenu(){
        boolean condition = true;
        boolean condition1 = true;
        Scanner myObj = new Scanner(System.in);
        Player player = new Player(Player.GetCurrentAccUser(), Player.GetCurrentAccScore(), Player.GetCurrentAccList());
        do{
            try {
                System.out.println("\n\nSelect menu: ");
                System.out.println("1. Battle and Catch Mode");
                System.out.println("2. Highscore Leaderboard");
                System.out.println("3. Save File and Exit");
                System.out.print("Enter '1', '2', '3': ");
                String input = myObj.nextLine();
                if (input.equals("1")||input.equals("2")||input.equals("3")){
                    switch(input){
                        case "1": //Battle and Catch mode
                            Battle.GenerateList();
                            Player.AddOwnedPoke(Battle.GetBushList());
                            Battle.SpamForEnemy();
                            Battle.GenerateEnemy();
                            Battle.Fight();
                            if(player.GetScore() < Battle.getScore()){
                                player.SetScore(Battle.getScore());
                            }
                            break;
                        case "2": //highscore list
                            Player.GetHighscore();
                            break;
                        case "3": //save file and exit
                            Player.writePlayer();
                            System.exit(0);
                    }
                }
                else{
                    System.out.println("Input error!\n");
                }
                do{
                    System.out.println("\n\nEnter 'back' to redirect to the game menu:");
                    String input1 = myObj.nextLine();
                    if(input1.equals("back")){
                        condition1 = false;
                    }
                    else{
                        System.out.println("Input error.");
                    }
                }while(condition1);
            } catch (Exception e) {
                System.out.println("Input error!\n");
            }
        }while(condition);
    }
}