package juego;

public interface IResultado {

	public static final int ROJO = 0;
	public static final int NEGRO = 1;
	public static final int CERO = 2;
	
	public int getNumero();
	public int getColor();
	public int getPremio();
	public boolean haGanado();
	
}
