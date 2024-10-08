package aventuraenlamazmorra;

import java.util.HashMap;

public class Mazmorra {
    private char[][] mapa;
    private int jugadorX;
    private int jugadorY;
    private HashMap<String, Enemigo> enemigos;
    private HashMap<String, Objeto> objetos;
    private Jugador jugador; // Nuevo atributo para guardar el jugador
    private boolean juegoTerminado; // Variable para indicar si el juego ha terminado

    // Constructor con parámetros para el tamaño del mapa, posición inicial del jugador y el jugador
    public Mazmorra(int filas, int columnas, int jugadorX, int jugadorY, Jugador jugador) {
        mapa = new char[filas][columnas];
        this.jugadorX = 0;
        this.jugadorY = 0;
        this.jugador = jugador; // Inicializamos el jugador
        enemigos = new HashMap<>();
        objetos = new HashMap<>();
        juegoTerminado = false;
        inicializarMapa();
    }

    // Inicializa el mapa con paredes, enemigos y objetos
    private void inicializarMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = '.'; // '.' representa una celda vacía
            }
        }

     // Añadir paredes
        mapa[4][0] = '#'; mapa[4][1] = '#'; mapa[4][2] = '#';
        mapa[4][4] = '#'; mapa[4][5] = '#'; mapa[4][6] = '#'; 
        mapa[4][7] = '#'; mapa[4][8] = '#'; mapa[4][9] = '#'; 
        mapa[5][4] = '#';
        mapa[6][4] = '#'; 
        mapa[7][4] = '#';
        mapa[9][4] = '#';

        // Añadir enemigos
        mapa[4][3] = 'E'; 
        mapa[5][3] = 'E'; 
        mapa[8][4] = 'E'; 
        
        // Añadir objetos
        mapa[9][0] = 'O'; 
        mapa[9][8] = 'O';
        
        // Añadir salida
        mapa[9][9] = 'S'; 

        // Inicializar enemigos y objetos en el mapa
        enemigos.put("4-3", new Enemigo("Orco", 50, 10, 5));
        enemigos.put("5-3", new Enemigo("Esqueleto", 40, 8, 3));
        enemigos.put("8-4", new Enemigo("Dragon", 90,10,5));
        objetos.put("9-0", new Pocion("Poción de Salud"));
        objetos.put("9-8", new Pocion("Poción de Salud"));

        // Poner al jugador en la posición inicial
        mapa[jugadorX ][jugadorY] = 'J';
    }

    // Muestra el estado actual del mapa
    public void mostrarMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Mueve al jugador en la dirección indicada
    public void moverJugador(char direccion) {
    	//Si el juego ya ha terminado, no se puede mover al jugador
    	if(juegoTerminado) {
    		System.out.println("El juego ha terminado y no puedes moverte mas. PRESIONA LA LETRA ''Q'' PARA SALIR ");
    		return;
    	}
        mapa[jugadorX][jugadorY] = '.'; // Eliminar la posición anterior del jugador

        // Actualizar las coordenadas del jugador basándose en la dirección
        switch (direccion) {
            case 'w': // Arriba
                if (jugadorX > 0 && mapa[jugadorX - 1][jugadorY] != '#') {
                    jugadorX--;
                }
                break;
            case 's': // Abajo
                if (jugadorX < mapa.length - 1 && mapa[jugadorX + 1][jugadorY] != '#') {
                    jugadorX++;
                }
                break;
            case 'a': // Izquierda
                if (jugadorY > 0 && mapa[jugadorX][jugadorY - 1] != '#') {
                    jugadorY--;
                }
                break;
            case 'd': // Derecha
                if (jugadorY < mapa[0].length - 1 && mapa[jugadorX][jugadorY + 1] != '#') {
                    jugadorY++;
                }
                break;
            default:
                System.out.println("Movimiento no válido.");
                return;
        }
        
        //Verificar si el jugador ha llegado a la salida
        if (jugadorX == 9 && jugadorY == 9) {
            System.out.println("¡Has alcanzado la salida! ¡JUEGO COMPLETADO!.  PRESIONA LA LETRA ''Q'' PARA SALIR  ");
            juegoTerminado = true; // Marcamos el juego como terminado
            return;
        }

        // Verificar si el jugador se encuentra con un enemigo
        if (hayEnemigo()) {
            Enemigo enemigo = obtenerEnemigo(); // Obtener el enemigo en esa posición
            iniciarCombate(jugador, enemigo); // Iniciar combate con ese enemigo
        }

        // Verificar si el jugador se encuentra con un objeto
        else if (mapa[jugadorX][jugadorY] == 'O') { // 'O' representa un objeto en el mapa
            System.out.println("¡Has encontrado un objeto!");
            Objeto objeto = objetos.get(jugadorX + "-" + jugadorY); // Obtener el objeto en esa posición
            objeto.usar(jugador); // Usar el objeto directamente en el jugador
            objetos.remove(jugadorX + "-" + jugadorY); // Eliminar el objeto del mapa después de recogerlo
        }


        // Actualizar la posición del jugador en el mapa
        mapa[jugadorX][jugadorY] = 'J'; // Poner al jugador en su nueva posición
    }

    // Verificar si hay un enemigo en la posición actual del jugador
    public boolean hayEnemigo() {
        return mapa[jugadorX][jugadorY] == 'E'; // Verifica si hay un enemigo en la posición del jugador
    }

    // Obtener el enemigo en la posición actual del jugador
    public Enemigo obtenerEnemigo() {
        return enemigos.get(jugadorX + "-" + jugadorY); // Devuelve el enemigo en esa posición
    }

    // Sistema de combate por turnos entre el jugador y un enemigo
    public void iniciarCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("¡Combate contra " + enemigo.getNombre() + " comienza!");

        while (jugador.getSalud() > 0 && enemigo.getSalud() > 0) {
            // Mostrar el estado de ambos antes del turno
            jugador.mostrarEstado();
            System.out.println(enemigo.getNombre() + " tiene " + enemigo.getSalud() + " de salud.");

            // Turno del jugador
            System.out.println("Turno del jugador.");
            enemigo.recibirDanio(jugador.atacar());

            // Verificar si el enemigo ha sido derrotado
            if (enemigo.getSalud() <= 0) {
                System.out.println("¡Has derrotado al " + enemigo.getNombre() + "!");
                enemigos.remove(jugadorX + "-" + jugadorY); // Eliminar enemigo del mapa
                mapa[jugadorX][jugadorY] = '.'; // Limpiar la posición del enemigo derrotado
                return;
            }
            
            // Verificación adicional para asegurarse que el enemigo ya no existe
            if (enemigos.get(jugadorX + "-" + jugadorY) == null) {
                System.out.println("Enemigo eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el enemigo.");
            }

            // Turno del enemigo
            System.out.println("Turno del enemigo.");
            jugador.recibirDanio(enemigo.atacar());

            // Verificar si el jugador ha sido derrotado
            if (jugador.getSalud() <= 0) {
                System.out.println("¡Has sido derrotado por " + enemigo.getNombre() + "!");
                System.out.println("GAME OVER"); // Mensaje de Game Over
                System.exit(0); // Terminar el juego
            }
        }
    }
}