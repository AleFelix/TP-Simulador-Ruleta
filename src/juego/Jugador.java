package juego;

public class Jugador {
	
	private int balance;
	private String nombre;
	
	public Jugador(String nombre, int b) {
		this.nombre = nombre;
		balance = b;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void restarBalance(int apuesta) {
		balance -= apuesta;
	}
	
	public void sumarBalance(int apuesta) {
		balance += apuesta;
	}

}
