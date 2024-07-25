public class Team {
    String teamName = "";
    Player player1;
    Player player2;
    Player player3;
    boolean batFirst = false;
    boolean bowlFirst = false;
    int runs = 0;

    public Team(String teamName, Player player1, Player player2, Player player3) {
        this.teamName = teamName;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
    }

    public void getPlayer1() {
        System.out.println(this.player1.name);
    }

    public void setBatFirst() {
        this.batFirst = true;
    }
}
