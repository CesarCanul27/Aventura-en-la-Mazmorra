package aventuraenlamazmorra;

public class Pocion extends Objeto {
    public Pocion(String nombre) {
        super(nombre);
    }

    @Override
    public void usar(Jugador jugador) {
        System.out.println("Has usado una " + getNombre());
        // Aquí se puede implementar una curación o mejora de atributos
        jugador.restablecerSaludMaxima();  // Restablece la salud del jugador al máximo
    }
}
