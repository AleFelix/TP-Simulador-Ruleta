package juego;

public class Resultado implements IResultado {

	private int numero;
	private int premio;
	private boolean gano;
	private int color;

	public Resultado(int numero, int color, int premio, boolean gano) {
		this.numero = numero;
		this.premio = premio;
		this.gano = gano;
		this.color = color;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getPremio() {
		return premio;
	}
	
	public boolean haGanado() {
		return gano;
	}
	
	public int getColor() {
		return color;
	}
	
}
