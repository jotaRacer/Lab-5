package model;

import java.util.ArrayList;
import java.util.List;
import structures.hash.HashST;
import structures.bst.BST;

/**
 * Clase que gestiona el registro de jugadores y el seguimiento de partidas
 * en el juego Conecta 4. Utiliza una tabla de hash para almacenar jugadores
 * y un árbol binario de búsqueda para organizarlos por victorias.
 */
public class Scoreboard {
    private HashST<String, Player> players;
    private BST winTree;
    private int playedGames;

    /**
     * Constructor que inicializa el scoreboard con estructuras vacías.
     */
    public Scoreboard() {
        this.players = new HashST<>();
        this.winTree = new BST();
        this.playedGames = 0;
    }

    /**
     * Registra un nuevo jugador con el nombre especificado.
     * Si el jugador ya existe, no se registra nuevamente.
     * 
     * @param playerName El nombre del jugador a registrar
     */
    public void registerPlayer(String playerName) {
        if (!players.containsKey(playerName)) {
            Player newPlayer = new Player(playerName);
            players.put(playerName, newPlayer);
        }
    }

    /**
     * Verifica si un jugador está registrado en el sistema.
     * 
     * @param playerName El nombre del jugador a verificar
     * @return true si el jugador está registrado, false en caso contrario
     */
    public boolean checkPlayer(String playerName) {
        return players.containsKey(playerName);
    }

    /**
     * Registra el resultado de una partida y actualiza las estadísticas
     * de los jugadores involucrados.
     * 
     * @param winnerPlayerName El nombre del jugador ganador (null si es empate)
     * @param looserPlayerName El nombre del jugador perdedor (null si es empate)
     * @param draw true si la partida terminó en empate, false si hay ganador
     */
    public void addGameResult(String winnerPlayerName, String looserPlayerName, boolean draw) {
        playedGames++;
        
        if (draw) {
            // Ambos jugadores suman un empate
            Player player1 = players.get(winnerPlayerName);
            Player player2 = players.get(looserPlayerName);
            
            if (player1 != null) {
                player1.addDraw();
                // Actualizar en el árbol con las nuevas victorias
                winTree.insert(player1.getWins(), player1.getPlayerName());
            }
            
            if (player2 != null) {
                player2.addDraw();
                // Actualizar en el árbol con las nuevas victorias
                winTree.insert(player2.getWins(), player2.getPlayerName());
            }
        } else {
            // Hay un ganador y un perdedor
            Player winner = players.get(winnerPlayerName);
            Player looser = players.get(looserPlayerName);
            
            if (winner != null) {
                winner.addWin();
                // Actualizar en el árbol con las nuevas victorias
                winTree.insert(winner.getWins(), winner.getPlayerName());
            }
            
            if (looser != null) {
                looser.addLoss();
            }
        }
    }

    /**
     * Retorna un arreglo con los jugadores cuya cantidad de victorias
     * está entre lo y hi (inclusive).
     * 
     * @param lo Límite inferior de victorias
     * @param hi Límite superior de victorias
     * @return Arreglo de jugadores en el rango especificado
     */
    public Player[] winRange(int lo, int hi) {
        List<String> playerNames = winTree.winRange(lo, hi);
        ArrayList<Player> playerList = new ArrayList<>();
        
        for (String playerName : playerNames) {
            Player player = players.get(playerName);
            if (player != null) {
                playerList.add(player);
            }
        }
        
        return playerList.toArray(new Player[0]);
    }

    /**
     * Retorna un arreglo con los jugadores que tienen la siguiente
     * cantidad de victorias mayor a wins.
     * 
     * @param wins Cantidad de victorias de referencia
     * @return Arreglo de jugadores con la siguiente cantidad de victorias mayor
     */
    public Player[] winSuccessor(int wins) {
        List<String> playerNames = winTree.winSuccessor(wins);
        if (playerNames == null) {
            return new Player[0];
        }
        
        ArrayList<Player> playerList = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = players.get(playerName);
            if (player != null) {
                playerList.add(player);
            }
        }
        
        return playerList.toArray(new Player[0]);
    }

    /**
     * Obtiene el número total de partidas jugadas.
     * 
     * @return El número de partidas jugadas
     */
    public int getPlayedGames() {
        return playedGames;
    }
}
