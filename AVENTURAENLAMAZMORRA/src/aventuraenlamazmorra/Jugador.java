package aventuraenlamazmorra;

	import java.util.ArrayList;

	//atributos
	public class Jugador {
	    private int salud;
	    private int saludMaxima;
	    private int ataque;
	    private int defensa;
	    private ArrayList<Objeto> inventario; // Inventario del jugador

	    public Jugador(int salud, int saludMaxima, int ataque, int defensa) {
	        this.salud = salud;
	        this.saludMaxima = saludMaxima;
	        this.ataque = ataque;
	        this.defensa = defensa;
	        this.inventario = new ArrayList<>();
	    }

	    // Método para atacar
	    public int atacar() {
	        System.out.println("Has atacado al enemigo con " + ataque + " puntos de daño.");
	        return ataque; // Devuelve los puntos de ataque
	    }

	    // Método para recibir daño
	    public void recibirDanio(int puntosDanio) {
	        int danioFinal = puntosDanio - defensa;
	        if (danioFinal > 0) {
	            salud -= danioFinal;
	        }
	        System.out.println("Has recibido " + danioFinal + " puntos de daño. Salud restante: " + salud);
	    }

	    public int getSalud() {
	        return salud;
	    }

	    // Método para recoger un objeto y añadirlo al inventario
	    public void recogerObjeto(Objeto objeto) {
	        inventario.add(objeto);
	        System.out.println("Has recogido un(a) " + objeto.getNombre() + ".");
	    }

	    // Mostrar estado del jugador
	    public void mostrarEstado() {
	    	System.out.println("Salud del jugador: " + salud + "/" + saludMaxima + ", Ataque: " + ataque + ", Defensa: " + defensa);
	    }

	    public void restablecerSaludMaxima() {
	        salud = saludMaxima;  // Restablece la salud a su valor máximo
	        System.out.println("Tu salud ha sido restablecida a " + saludMaxima);
	    }

}
