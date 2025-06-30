package main;

import java.util.Scanner;
import model.Game;

/**
 * Clase principal para ejecutar el juego Conecta 4 desde consola.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Bienvenido a Conecta 4 ===");
        System.out.print("Ingresa el nombre del Jugador A (X): ");
        String playerA = scanner.nextLine().trim();
        while (playerA.isEmpty()) {
            System.out.print("El nombre no puede estar vacío. Ingresa el nombre del Jugador A (X): ");
            playerA = scanner.nextLine().trim();
        }
        System.out.print("Ingresa el nombre del Jugador B (O): ");
        String playerB = scanner.nextLine().trim();
        while (playerB.isEmpty()) {
            System.out.print("El nombre no puede estar vacío. Ingresa el nombre del Jugador B (O): ");
            playerB = scanner.nextLine().trim();
        }
        System.out.println("\n¡Comienza la partida entre " + playerA + " (X) y " + playerB + " (O)!");
        Game game = new Game(playerA, playerB);
        String winner = game.play();
        if (!winner.isEmpty()) {
            System.out.println("\n¡Ganó " + winner + "!");
        } else {
            System.out.println("\nEmpate.");
        }
        scanner.close();
    }
} 