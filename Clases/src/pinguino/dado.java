package pinguino;

import java.util.Random;

public class dado {
	//declaramos las varibles que vamos a usar
	private static int resultado;
	private static Random rand = new Random();
//hacemos un random del 0 al 5 y le sumamos 1 para que sea del 1 al 6
	public static void DadoNormal() {
		resultado = 0;
		resultado = rand.nextInt(5) + 1;
	}
//lo mismo para el dado rapido, pero sumamos 6	
	public static void DadoRapido() {
		resultado = 0;
		resultado = rand.nextInt(5) + 6;
	}
//lo mismo para el dado lento, pero es del 0 al 2 y le sumamos 1 para que sea del 1 al 3
	public static void DadoLento() {
		resultado = 0;
		resultado = rand.nextInt(2) + 1;
	}
//declaramos el geter y el seter
	public static int getResultado() {
		return resultado;
	}

	public static void setResultado(int resultado) {
		dado.resultado = resultado;
	}
}
