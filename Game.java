// Adding a test comment from the cloud repo!
import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.lang.Math;

public class Game {
    // Variables
    static String inputName = ""; // for Player class constructor
    static String team1Name = ""; // team 1 name for constructor
    static String team2Name = ""; // team 2 name for constructor
    static String timeOfDay = ""; // morning/evening stored
    static boolean booleanBatter = false; // for Player class constructor
    static boolean booleanBowler = false; // for Player class constructor
    static boolean booleanCaptain = false; // for Player class constructor
    static ArrayList<Player> playersList1 = new ArrayList<Player>(); // To store player data in team 1
    static ArrayList<Player> playersList2 = new ArrayList<Player>(); // To store player data in team 2
    static ArrayList<Player> tossWinningTeam = new ArrayList<Player>(); // To store toss winning team, used in toss
                                                                        // method
    static ArrayList<Player> batFirstTeamList = new ArrayList<Player>();
    static ArrayList<Player> bowlFirstTeamList = new ArrayList<Player>();
    static ArrayList<Player> tempList = new ArrayList<Player>();
    static String[] bowlerChoices = { "Wicket", "Not wicket", "Not wicket", "Not wicket" };
    static int batOrBowlChoice = 0; // Used in toss() method
    static int chooseFirstTeam;
    static int numRunsRandom = 1;
    static Scanner scanner = new Scanner(System.in);
    static Team team1;
    static Team team2;
    static Team currentBatTeam;
    static Team currentBowlTeam;
    static Team temp;
    static Player batter1;
    static Player batter2;
    static Player batter3;
    static Player currentBatter;
    static Player currentBowler;

    // Main program
    public static void main(String[] args) {
        // Creating team 1
        createTeam1();

        // Create team 2
        createTeam2();

        // Checking batters and bowlers for both teams
        checkBattersAndBowlers(playersList1); // Team 1
        checkBattersAndBowlers(playersList2); // Team 2

        // Checking to see if time is morning or evening
        checkTime();

        // Starting game
        startGame();

        // Toss
        toss();

        // After the toss
        afterToss();

        // Batting order chosen
        chooseBattingOrder();

        // Current bowler chosen
        chooseCurrentBowler();

        // Run first over (6 balls)
        oneOver();

        // Current bowler chosen again for 2nd over
        chooseCurrentBowler();

        // Run second over (6 balls)
        oneOver();

        // Announce end of first innings
        endOfFirstInnings();

        // Batting order chosen
        chooseBattingOrder();

        // Current bowler chosen
        chooseCurrentBowler();

        // Run first over (6 balls)
        oneOver();

        // Current bowler chosen again for 2nd over
        chooseCurrentBowler();

        // Run second over (6 balls)
        oneOver();

        // End of second innings + game ends
        endOfSecondInnings();
    }

    // Methods used in the main program

    public static void checkTime() {
        // Code for learning how to get time sourced from:
        // https://beginnersbook.com/2017/10/java-display-time-in-12-hour-format-with-ampm/
        DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        String dateString = dateFormat.format(new Date()).toString();
        String currentTime = dateString;
        if (currentTime.substring(6, 7).equals("A")) { // To check if time is AM
            timeOfDay = "morning";
        } else if (currentTime.substring(6, 7).equals("P")) { // To check if time is PM
            timeOfDay = "evening";
        }
    }

    public static void createTeam1() {

        // Create team 1
        System.out.println(
                "Hi! You are going to play for team 1! \nMake sure to only choose upto 2 players to bat and upto 2 players to bowl! \nDon't forget to choose one captain!");

        System.out.println("\u001B[1m" + "Enter team 1 name: " + "\033[0;0m");
        team1Name = scanner.nextLine();

        for (int i = 1; i <= 3; i++) {
            createPlayer();
            playersList1.add(new Player(inputName, booleanBatter, booleanBowler, booleanCaptain));
        }

        team1 = new Team(team1Name, playersList1.get(0), playersList1.get(1), playersList1.get(2));

    }

    public static void createTeam2() {
        // Create team 2
        System.out.println(
                "Hi! You are going to play for team 2! Make sure to only choose upto 2 players to bat and upto 2 players to bowl! \n Don't forget to choose one captain!");

        System.out.println("\u001B[1m" + "Enter team 2 name: " + "\033[0;0m");
        team2Name = scanner.nextLine();

        for (int i = 1; i <= 3; i++) {
            createPlayer();
            playersList2.add(new Player(inputName, booleanBatter, booleanBowler, booleanCaptain));
        }

        team2 = new Team(team2Name, playersList2.get(0), playersList2.get(1), playersList2.get(2));

    }

    public static void createPlayer() {
        System.out.println("\u001B[1m" + "Enter player name: " + "\033[0;0m");
        inputName = scanner.nextLine();

        System.out.println("\u001B[1m" + "Is player a batter? Enter Y or N." + "\033[0;0m");
        String inputBatter = scanner.nextLine();
        if (inputBatter.equals("Y")) {
            booleanBatter = true;
        } else if (inputBatter.equals("N")) {
            booleanBatter = false;
        }

        System.out.println("\u001B[1m" + "Is player a bowler? Enter Y or N." + "\033[0;0m");
        String inputBowler = scanner.nextLine();
        if (inputBowler.equals("Y")) {
            booleanBowler = true;
        } else if (inputBowler.equals("N")) {
            booleanBowler = false;
        }

        System.out.println("\u001B[1m" + "Is player the captain? Enter Y or N." + "\033[0;0m");
        String inputCaptain = scanner.nextLine();
        if (inputCaptain.equals("Y")) {
            booleanCaptain = true;
        } else if (inputCaptain.equals("N")) {
            booleanCaptain = false;
        }

    }

    public static void checkBattersAndBowlers(ArrayList<Player> playersListNumber) {
        // Check number of batters and bowlers
        int countBat = 0;
        int countBall = 0;
        for (Player i : playersListNumber) {
            if (i.isBatter == true) {
                countBat++;
            }
            if (i.isBowler == true) {
                countBall++;
            }
        }
        if (countBat > 2) {
            System.out.println("Please restart the game and only choose upto 2 players to bat.");
        } else if (countBall > 2) {
            System.out.println("Please restart the game and only choose upto 2 players to bowl.");
        }
    }

    public static void startGame() {
        // Start game with commentary
        System.out.print("Time to start the game!!");
        askToPressEnter();
        System.out.print("Such a beautiful " + timeOfDay + " at the ground today! Perfect for a cricket match.");
        askToPressEnter();
        System.out.print("Get ready for the clash of the century!!");
        askToPressEnter();
    }

    public static void toss() {
        // Do toss

        System.out.print("Both the captains are here at the ground, ready for the toss!");
        askToPressEnter();
        System.out.println("Okay, let's see what the coin decides!");
        askToPressEnter();
        int tossRandom = (int) (Math.random() * 2);
        if (tossRandom == 0) {
            tossWinningTeam = playersList1;
            chooseFirstTeam = 1;
            System.out.println("Team 1 wins the toss!");
            askToPressEnter();
        } else if (tossRandom == 1) {
            tossWinningTeam = playersList2;
            chooseFirstTeam = 2;
            System.out.println("Team 2 wins the toss!");
            askToPressEnter();
        }

        System.out.println("Captain " + getCaptain(tossWinningTeam)
                + ", do you choose to bat first or bowl first? Enter 1 to bat first and enter 2 to bowl first.");
        batOrBowlChoice = scanner.nextInt();
        scanner.nextLine();
        if (batOrBowlChoice == 1) {
            if (chooseFirstTeam == 1) {
                team1.batFirst = true;
                team2.bowlFirst = true;
                currentBatTeam = team1;
                currentBowlTeam = team2;
                batFirstTeamList = playersList1;
                bowlFirstTeamList = playersList2;
            } else if (chooseFirstTeam == 2) {
                team2.batFirst = true;
                team1.bowlFirst = true;
                currentBatTeam = team2;
                currentBowlTeam = team1;
                batFirstTeamList = playersList2;
                bowlFirstTeamList = playersList1;
            }
        }
        if (batOrBowlChoice == 2) {
            if (chooseFirstTeam == 1) {
                team1.bowlFirst = true;
                team2.batFirst = true;
                currentBatTeam = team2;
                currentBowlTeam = team1;
                batFirstTeamList = playersList2;
                bowlFirstTeamList = playersList1;
            } else if (chooseFirstTeam == 2) {
                team2.bowlFirst = true;
                team1.batFirst = true;
                currentBatTeam = team1;
                currentBowlTeam = team2;
                batFirstTeamList = playersList1;
                bowlFirstTeamList = playersList2;
            }
        }
    }

    public static void afterToss() {
        System.out.print("Team 1 is going to ");
        if (team1.batFirst == true) {
            System.out.println("bat!");
        } else if (team1.bowlFirst == true) {
            System.out.println("bowl!");
        }

        askToPressEnter();

        System.out.print("Team 2 is going to ");
        if (team2.batFirst == true) {
            System.out.println("bat!");
        } else if (team2.bowlFirst == true) {
            System.out.println("bowl!");
        }

        askToPressEnter();
    }

    public static void chooseBattingOrder() {
        System.out.println("Team " + currentBatTeam.teamName
                + ", it's time to decide your batting order. Choose the player who is going to bat first.");
        System.out.println("Existing Players:");
        for (int i = 0; i < batFirstTeamList.size(); i++) {
            System.out.println((i + 1) + ". " + batFirstTeamList.get(i).name);
        }
        batter1 = batFirstTeamList.get(scanner.nextInt() - 1);
        scanner.nextLine();

        System.out.println("Choose the player who is going to bat second.");
        batter2 = batFirstTeamList.get(scanner.nextInt() - 1);
        scanner.nextLine();

        for (int i = 0; i < batFirstTeamList.size(); i++) {
            if (batFirstTeamList.get(i).name != batter1.name && batFirstTeamList.get(i).name != batter2.name) {
                batter3 = batFirstTeamList.get(i);
            }
        }

        System.out.println("Batting order: ");
        System.out.println("1. " + batter1.name + "\n 2. " + batter2.name + "\n 3. " + batter3.name);
    }

    public static void chooseCurrentBowler() {
        System.out.println("Team " + currentBowlTeam.teamName
                + ", it's time to decide your bowler. Choose the player who is going to bowl.");
        System.out.println("Existing Players:");
        for (int i = 0; i < bowlFirstTeamList.size(); i++) {
            System.out.println((i + 1) + ". " + bowlFirstTeamList.get(i).name);
        }
        currentBowler = bowlFirstTeamList.get(scanner.nextInt() - 1);
        scanner.nextLine();
    }

    public static void startFirstInnings() {
        System.out.println("Time to begin the game!");
        askToPressEnter();
        System.out
                .println("Team " + team1.teamName + " and " + "Team " + team2.teamName + " are walking to the pitch!");
        askToPressEnter();
        System.out.println("3");
        askToPressEnter();
        System.out.println("2");
        askToPressEnter();
        System.out.println("1!");
        askToPressEnter();
        System.out.println("First ball, here we go!");
        askToPressEnter();
    }

    public static String getCaptain(ArrayList<Player> teamArray) {
        // Get name of the captain
        String captainName = "";
        for (Player i : teamArray) {
            if (i.isCaptain) {
                captainName = i.name;
            }
        }
        return captainName;
    }

    public static String chooseBowlingType() {
        String[] bowlingOption = { "pace", "spin" };
        int bowlingTypeRandom = (int) (Math.random() * 2);
        return bowlingOption[bowlingTypeRandom];
    }

    public static void announceBallType() {
        System.out.println("It's a " + chooseBowlingType() + " ball from " + currentBowler.name + "!");
        askToPressEnter();
    }

    public static int numRuns() {
        numRunsRandom = (int) (Math.random() * 40);
        return numRunsRandom;
    }

    public static void announceRuns() {
        int currentRuns = numRuns();
        if (currentRuns >= 36) {
            System.out.println("Single and overthrow: 5 runs from this ball!");
        } else if (currentRuns >= 33) {
            System.out.println("It's a SIX! Into the stands!");
            currentBatTeam.runs++;
        } else if (currentRuns >= 29) {
            System.out.println("It's a FOUR! To the boundary!");
            currentBatTeam.runs = currentBatTeam.runs + 2;
        } else if (currentRuns >= 25) {
            System.out.println("Three runs!");
            currentBatTeam.runs = currentBatTeam.runs + 3;
        } else if (currentRuns >= 17) {
            System.out.println("It's a double!");
            currentBatTeam.runs = currentBatTeam.runs + 4;
        } else if (currentRuns >= 11) {
            System.out.println("Single and overthrow: 5 runs from this ball!");
            currentBatTeam.runs = currentBatTeam.runs + 5;
        } else if (currentRuns >= 0) {
            System.out.println("Dot ball!");
            currentBatTeam.runs = currentBatTeam.runs + 6;
        }
        askToPressEnter();

    }

    public static void oneOver() {
        for (int i = 0; i < 6; i++) {
            announceBallType();
            announceRuns();
        }
    }

    public static void oneOver2() {
        for (int i = 0; i < 6; i++) {
            announceBallType();
            announceRuns();
            if (currentBatTeam.runs > currentBowlTeam.runs) {
                // Start here
            }
        }
    }

    public static void endOfFirstInnings() {
        System.out.println("And that is the end of the first innings!");
        askToPressEnter();
        System.out.println(currentBatTeam.teamName + " scores " + currentBatTeam.runs + "!");
        askToPressEnter();
        System.out.println("Time for the second innings to begin!");
        askToPressEnter();
        System.out.println(currentBowlTeam.teamName + " needs " + (currentBatTeam.runs + 1) + " runs to win!");
        askToPressEnter();
        System.out.println("Here we go!");

        tempList = batFirstTeamList;
        batFirstTeamList = bowlFirstTeamList;
        bowlFirstTeamList = tempList;

        temp = currentBowlTeam;
        currentBowlTeam = currentBatTeam;
        currentBatTeam = temp;

    }

    public static void endOfSecondInnings() {
        System.out.println("And that is the end of the second innings!");
        askToPressEnter();
        System.out.println(currentBatTeam.teamName + " scores " + currentBatTeam.runs + "!");
        askToPressEnter();
        if (currentBatTeam.runs > currentBowlTeam.runs) {
            System.out.println(
                    currentBatTeam.teamName + " wins by " + (currentBatTeam.runs - currentBowlTeam.runs) + "runs!");
        } else if (currentBatTeam.runs < currentBowlTeam.runs) {
            System.out.println(
                    currentBowlTeam.teamName + " wins by " + (currentBowlTeam.runs - currentBatTeam.runs) + "runs!");
        }
        askToPressEnter();
        System.out.println("And that was today's game! Tune in again tomorrow for another exciting clash!");
    }

    public static void askToPressEnter() {
        System.out.println(" (Press enter.)");
        scanner.nextLine();
    }
}
