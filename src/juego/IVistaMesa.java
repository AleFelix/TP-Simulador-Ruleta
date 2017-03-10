package juego;

public interface IVistaMesa {

	public abstract boolean estaJugando();

	public abstract void mostrarResultadoTirada(IResultado resultado, int contadorTiradas, int contadorDias, int balance);

	public abstract void mostrarResultadoFinal(boolean gano);

}