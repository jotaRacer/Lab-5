package model;

/**
 * Clase que representa el tablero y la lógica del juego Conecta 4.
 */
public class ConnectFour {
    private char[][] grid; // grid[7][6]: columnas x filas
    private char currentSymbol;

    /**
     * Constructor que inicializa el tablero vacío y el símbolo inicial.
     */
    public ConnectFour() {
        grid = new char[7][6];
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                grid[col][row] = ' ';
            }
        }
        currentSymbol = 'X';
    }

    /**
     * Intenta insertar el símbolo actual en la columna indicada.
     * @param col columna (0 a 6)
     * @return true si el movimiento es válido y se realiza, false en caso contrario
     */
    public boolean makeMove(int col) {
        if (col < 0 || col >= 7) return false;
        for (int row = 5; row >= 0; row--) {
            if (grid[col][row] == ' ') {
                grid[col][row] = currentSymbol;
                // Alternar símbolo
                currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
                return true;
            }
        }
        return false; // Columna llena
    }

    /**
     * Verifica si el juego ha terminado (ganador o empate).
     * @return 'X', 'O', "DRAW" o null si el juego sigue
     */
    public Object isGameOver() {
        // Revisar todas las posiciones
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                char symbol = grid[col][row];
                if (symbol == ' ') continue;
                // Horizontal
                if (col <= 3 && symbol == grid[col+1][row] && symbol == grid[col+2][row] && symbol == grid[col+3][row])
                    return symbol;
                // Vertical
                if (row <= 2 && symbol == grid[col][row+1] && symbol == grid[col][row+2] && symbol == grid[col][row+3])
                    return symbol;
                // Diagonal positiva
                if (col <= 3 && row <= 2 && symbol == grid[col+1][row+1] && symbol == grid[col+2][row+2] && symbol == grid[col+3][row+3])
                    return symbol;
                // Diagonal negativa
                if (col <= 3 && row >= 3 && symbol == grid[col+1][row-1] && symbol == grid[col+2][row-2] && symbol == grid[col+3][row-3])
                    return symbol;
            }
        }
        // Verificar empate
        boolean full = true;
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                if (grid[col][row] == ' ') {
                    full = false;
                    break;
                }
            }
            if (!full) break;
        }
        if (full) return "DRAW";
        return null;
    }

    /**
     * Retorna una representación en texto del tablero actual.
     * @return String con el tablero
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 6; row++) {
            sb.append("|");
            for (int col = 0; col < 7; col++) {
                sb.append(grid[col][row]).append("|");
            }
            sb.append("\n");
        }
        sb.append(" 0 1 2 3 4 5 6\n");
        return sb.toString();
    }

    /**
     * Permite obtener la matriz del tablero (lectura).
     */
    public char[][] getGrid() {
        return grid;
    }
}
