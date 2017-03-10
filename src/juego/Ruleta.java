package juego;

import java.util.Random;

public class Ruleta {

	public static final int MAX_NUM = 36;
	public static final int[] ROJAS = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
	public static final int[] NEGRAS = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
	private boolean conCero;
	private Random r;

	public Ruleta(boolean conCero) {
		this.conCero = conCero;
		r = new Random();
	}
	
	public Resultado apostar(int colorElegido, int suma) {
		boolean gano = false;
		int premio = 0;
		int colorNumero;
		int numero = girar();
		int[] numerosApostados;
		if (colorElegido == IResultado.ROJO) {
			numerosApostados = ROJAS;
		} else {
			numerosApostados = NEGRAS;
		}
		if (acertoUnValor(numero, numerosApostados)) {
			premio = premiar(suma);
			gano = true;
			colorNumero = colorElegido;
		} else if (numero == 0) {
			colorNumero = IResultado.CERO;
		} else {
			if (colorElegido == IResultado.ROJO) {
				colorNumero = IResultado.NEGRO;
			} else {
				colorNumero = IResultado.ROJO;
			}
		}
		return new Resultado(numero, colorNumero, premio, gano);
	}
	
	private int girar() {
		if (conCero) {
			return r.nextInt(MAX_NUM + 1);
		} else {
			return r.nextInt(MAX_NUM) + 1;
		}
	}
	
	private int premiar(int suma) {
		return suma * 2;
	}
	
	private boolean acertoUnValor(int numero, int[] numerosApostados) {
		boolean acerto = false;
		for (int n : numerosApostados) {
			if (n == numero) {
				acerto = true;
				break;
			}
		}
		return acerto;
	}
	
}
