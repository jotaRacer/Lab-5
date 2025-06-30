package model;


public class Player {
    private String playerName;
    private int wins;
    private int draws;
    private int losses;

    public Player(String playerName) {
        this.playerName = playerName;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }
    public void addWin() {
        wins++;
    }

    public void addDraw() {
        draws++;
    }
    public void addLoss() {
        losses++;
    }
    public double winRate() {
        int totalGames = wins + draws + losses;
        if (totalGames == 0) {
            return 0.0;
        }
        return (double) wins / totalGames;
    }

}
