package model;

import java.util.Scanner;

/**
 * Clase que representa una partida de Conecta 4 entre dos jugadores.
 */
public class Game {
    private String status; // "IN_PROGRESS", "VICTORY", "DRAW"
    private String winnerPlayerName;
    private String playerNameA; // Juega con 'X'
    private String playerNameB; // Juega con 'O'
    private ConnectFour connectFour;

    /**
     * Constructor que inicializa la partida con los nombres de los jugadores.
     * El estado inicial es "IN_PROGRESS".
     */
    public Game(String playerNameA, String playerNameB) {
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        this.status = "IN_PROGRESS";
        this.winnerPlayerName = "";
        this.connectFour = new ConnectFour();
    }

    /**
     * Ejecuta el ciclo de juego interactivo por consola.
     * @return El nombre del ganador, o cadena vacía si fue empate.
     */
    public String play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(connectFour.toString());
            char currentSymbol = getCurrentSymbol();
            String currentPlayer = (currentSymbol == 'X') ? playerNameA : playerNameB;
            System.out.print("Turno de " + currentPlayer + " (" + currentSymbol + "). Ingresa columna (0-6): ");
            int col;
            while (true) {
                String input = scanner.nextLine();
                try {
                    col = Integer.parseInt(input);
                    if (connectFour.makeMove(col)) {
                        break;
                    } else {
                        System.out.print("Movimiento inválido. Intenta otra columna (0-6): ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Entrada inválida. Ingresa un número de columna (0-6): ");
                }
            }
            Object result = connectFour.isGameOver();
            if (result != null) {
                System.out.println(connectFour.toString());
                if (result.equals("DRAW")) {
                    status = "DRAW";
                    winnerPlayerName = "";
                    System.out.println("¡Empate!");
                    return "";
                } else {
                    status = "VICTORY";
                    winnerPlayerName = (result.equals('X')) ? playerNameA : playerNameB;
                    System.out.println("¡Victoria de " + winnerPlayerName + " (" + result + ")!");
                    return winnerPlayerName;
                }
            }
        }
    }

    /**
     * Obtiene el símbolo del jugador que debe jugar en este turno.
     */
    private char getCurrentSymbol() {
        int countX = 0, countO = 0;
        char[][] grid = connectFour.getGrid();
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                if (grid[col][row] == 'X') countX++;
                if (grid[col][row] == 'O') countO++;
            }
        }
        return (countX <= countO) ? 'X' : 'O';
    }

    /**
     * Retorna el estado actual de la partida.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Retorna el nombre del jugador ganador (vacío si fue empate).
     */
    public String getWinnerPlayerName() {
        return winnerPlayerName;
    }
}
