public class Player {
    String name = "";
    boolean isBatter = false;
    boolean isBowler = false;
    boolean isAllRounder = false;
    boolean isCaptain = false;
    int totalRuns = 0;
    int totalWickets = 0;

    public Player(String name, boolean isBatter, boolean isBowler, boolean isCaptain) {
        this.name = name;
        this.isBatter = isBatter;
        this.isBowler = isBowler;
        if (isBatter && isBowler) {
            this.isAllRounder = true;
        }
        this.isCaptain = isCaptain;
    }

    public void getPlayerName() {
        System.out.println(this.name);
    }

}