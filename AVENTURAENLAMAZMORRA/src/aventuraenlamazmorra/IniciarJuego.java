package aventuraenlamazmorra;
import java.util.Scanner;

public class IniciarJuego {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char opcion;

        do {
            mostrarMenuPrincipal();
            opcion = scanner.next().charAt(0);

            switch (opcion) {
                case '1': // Iniciar un nuevo juego
                    iniciarJuego();
                    break;
                case '2': // Mostrar instrucciones
                    mostrarInstrucciones();
                    break;
                case '3': // Salir del juego
                    System.out.println("¡Gracias por jugar!");
                    System.exit(0); // Finalizar el programa
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }

        } while (opcion != '3'); // El bucle continúa hasta que el jugador elige salir del juego

        scanner.close();
    }

    // Método que muestra el menú principal
    private static void mostrarMenuPrincipal() {
        System.out.println("\nBienvenido a Aventura en la Mazmorra");
        System.out.println("1. Juego Nuevo");
        System.out.println("2. Mostrar Instrucciones");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    }

    // Método que inicia un nuevo juego
    private static void iniciarJuego() {
        // Crear al jugador
        Jugador jugador = new Jugador(100, 100, 10, 5);

        // Crear la mazmorra con el jugador
        Mazmorra mazmorra = new Mazmorra(10, 10, 3, 2, jugador);  // Mapa de 10x10

        // Mostrar el estado inicial
        System.out.println("Estado inicial del jugador:");
        jugador.mostrarEstado();

        // Crear un scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Bucle para mover al jugador
        char movimiento;
        do {
            System.out.println("\nMapa actual:");
            mazmorra.mostrarMapa();

            // Pedir al jugador que ingrese una dirección
            System.out.println("\nIntroduce un movimiento (w: arriba, a: izquierda, s: abajo, d: derecha, q: salir): ");
            movimiento = scanner.next().charAt(0);

            // Mover al jugador según la dirección ingresada
            if (movimiento != 'q') {
                mazmorra.moverJugador(movimiento);  // Solo pasamos el movimiento
            }

        } while (movimiento != 'q'); // El bucle continúa hasta que el jugador ingrese 'q' para salir

        System.out.println("¡Gracias por jugar!");
    }

    // Método que muestra las instrucciones
    private static void mostrarInstrucciones() {
        System.out.println("\nInstrucciones del juego:");
        System.out.println("Usa las teclas w: arriba, a: izquierda, s: abajo, d: derecha, para moverte dentro de la mazmorra.");
        System.out.println("Usa las las pociones 'O' para recuperar vida cada vez que puedas.");
        System.out.println("Llega a la salida (S) sin morir para completar el juego.");
        System.out.println("Combate enemigos al encontrarlos para abrirte paso a traves de la mazmorra.");
    }
}
