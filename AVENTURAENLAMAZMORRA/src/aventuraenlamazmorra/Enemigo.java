package aventuraenlamazmorra;

public class Enemigo {
		//atributos
	    private String nombre;
	    private int salud;
	    private int ataque;
	    private int defensa;

	    public Enemigo(String nombre, int salud, int ataque, int defensa) {
	        this.nombre = nombre;
	        this.salud = salud;
	        this.ataque = ataque;
	        this.defensa = defensa;
	    }

	    // Método para atacar
	    public int atacar() {
	        System.out.println(nombre + " te ataca con " + ataque + " puntos de daño.");
	        return ataque; // Devuelve los puntos de ataque
	    }

	    // Método para recibir daño
	    public void recibirDanio(int puntosDanio) {
	        int danioFinal = puntosDanio - defensa;
	        if (danioFinal > 0) {
	            salud -= danioFinal;
	        }
	        System.out.println(nombre + " ha recibido " + danioFinal + " puntos de daño. Salud restante: " + salud);
	    }

	    public int getSalud() {
	        return salud;
	    }

	    public String getNombre() {
	        return nombre;
	    }
	}

