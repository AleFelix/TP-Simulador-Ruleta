package juego;

public class Mesa {

	private static final int ESPERA = 1000;

	private IVistaMesa vista;
	private int velocidad;

	public Mesa(IVistaMesa vista) {
		this.vista = vista;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public synchronized void jugar(String nombreJugador, int cantidadInicial, int topeGanancia, int apuesta, int color, boolean ruletaConCero, int maxTiradas, int maxDias, int velocidad) {
		this.velocidad = velocidad;
		Jugador jugador = new Jugador(nombreJugador, cantidadInicial);
		Ruleta ruleta = new Ruleta(ruletaConCero);
		boolean gano = false;
		int contadorTiradas = 0;
		int contadorDias = 0;
		int milisegundos;
		while (jugador.getBalance() >= apuesta && jugador.getBalance() < topeGanancia && vista.estaJugando()) {
			jugador.restarBalance(apuesta);
			Resultado resultado = ruleta.apostar(color, apuesta);
			jugador.sumarBalance(resultado.getPremio());
			contadorTiradas++;
			if (maxTiradas != 0 && contadorTiradas % maxTiradas == 0) {
				contadorDias++;
			}
			if (vista.estaJugando()) {
				vista.mostrarResultadoTirada(resultado, contadorTiradas, contadorDias, jugador.getBalance());
			}
			if (maxDias != 0 && contadorDias == maxDias) {
				break;
			}
			milisegundos = ESPERA - this.velocidad;
			if (milisegundos > 0) {
				try {
					Thread.sleep(milisegundos);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if (jugador.getBalance() >= topeGanancia) {
			gano = true;
		}
		if (vista.estaJugando()) {
			vista.mostrarResultadoFinal(gano);
		}
	}

}
