import java.util.Random;
import java.util.Scanner;

public class Sopa {
	private String letras="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private char [][] matriz;
	private boolean [][] criba;
	private int posX;
	private int posY;
	private boolean d;
	private boolean s;
	Random rnd;
	String palabra="";

	public Sopa() {
		matriz = new char [15][15];
		criba = new boolean [15][15];
		rnd = new Random();
		posX=0;
		posY=0;
		d=true;
		s=true;
	}
/**
 * Método que recorre la matriz de 15 por 15 y asigna un caracter aleatorio a cada posición
 */
	public void llenarSopa() {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				matriz[i][j]=letras.charAt(rnd.nextInt(letras.length()));
				criba[i][j]=true;
			}
		}
	}
	
	/**
	 * Método que imprime la matriz con su contenido
	 */
	public void imprimirSopa() {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Método que imprime la criba o matriz con casillas ocupadas y disponibles
	 */
	public void imprimirCriba() {
		System.out.println();
		for(int i=0;i<criba.length;i++) {
			for(int j=0;j<criba[0].length;j++) {
				if(criba[i][j]) {
					System.out.print("T ");
				}else System.out.print("F ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Método que valida si la posición aleatoria generada, su sentido y su dirección al colocar la palabra sobreescribe a una palabra ya colocada
	 * @return
	 */
	public boolean validarCrilla() {
		boolean a=true;
		if(s) {
			//int indicePalabra=0;
			if(d) {
				for(int i=0;i<palabra.length();i++) {
					if(criba[posX][posY+i]||matriz[posX][posY+i]==palabra.charAt(i)) a=true;
					else {
						a=false; 
						break;
					}
					//indicePalabra++;
				}
			}else {
				for(int i=0;i<palabra.length();i++) {
					if(criba[posX][14-posY-i]||matriz[posX][14-posY-i]==palabra.charAt(i)) a=true;
					else {
						a=false; 
						break;
					}
					//indicePalabra++;
				}
			}
		}else {
			//int indicePalabra=0;
			if(d) {
				for(int i=0;i<palabra.length();i++) {
					if(criba[posX+i][posY]||matriz[posX+i][posY]==palabra.charAt(i)) a=true;
					else {
						a=false; 
						break;
					}
					//indicePalabra++;
				}
			}else {
				for(int i=0;i<palabra.length();i++) {
					if(criba[14-posX-i][posY]||matriz[14-posX-i][posY]==palabra.charAt(i))a=true;
					else {
						a=false; 
						break;
					}
					//indicePalabra++;
				}
			}
		}

		return a;
	}
	
	/**
	 * Método que inserta una palabra de forma horizontal
	 */
	public void insertarPalabraHorizontal() {
		int indicePalabra=0;
		if(d) {
			if(validarCrilla()) {
				for(int i=0;i<palabra.length();i++) {
					matriz[posX][i+posY]=palabra.charAt(indicePalabra);
					criba[posX][i+posY]=false;
					indicePalabra++;
				}
			}else {
				generarPosicionAleatoria();
				if(s)insertarPalabraHorizontal();
				else insertarPalabraVertical();
			}
		}else {
			if(validarCrilla()) {
				for(int i=0;i<palabra.length();i++) {
					matriz[posX][14-posY-i]=palabra.charAt(indicePalabra);
					criba[posX][14-posY-i]=false;
					indicePalabra++;
				}
			}else {
				generarPosicionAleatoria();
				if(s)insertarPalabraHorizontal();
				else insertarPalabraVertical();
			}
		}
	}

	/**
	 * Método que inserta una palabra vertical
	 */
	public void insertarPalabraVertical() {
		int indicePalabra=0;
		if(d) {
			if(validarCrilla()) {
				for(int i=0;i<palabra.length();i++) {
					matriz[posX+i][posY]=palabra.charAt(indicePalabra);
					criba[posX+i][posY]=false;
					indicePalabra++;
				}
			}else {
				generarPosicionAleatoria();
				if(s)insertarPalabraHorizontal();
				else insertarPalabraVertical();
			}
		}else {
			if(validarCrilla()) {
			for(int i=0;i<palabra.length();i++) {
					matriz[14-posX-i][posY]=palabra.charAt(indicePalabra);
					criba[14-posX-i][posY]=false;
					indicePalabra++;
				}
			}else {
				generarPosicionAleatoria();
				if(s)insertarPalabraHorizontal();
				else insertarPalabraVertical();
			}
		}
	}


	/**
	 * Método que crea un objeto posición desde la clase PosicionAleatoria 
	 * 
	 */
	public void generarPosicionAleatoria() {
		PosicionAleatoria pos=new PosicionAleatoria(palabra);
		pos.generarPosicionAleatoria();
		posX=pos.getI();
		posY=pos.getJ();
		d=pos.getD();
		s=pos.getS();
		System.out.println("x: "+posX);
		System.out.println("y: "+posY);
		System.out.println("d: "+d);
		System.out.println("s: "+s);
		System.out.println("Palabra:"+palabra);
	}

	public static void main(String Args[]) {
		Sopa sp = new Sopa();
		sp.llenarSopa();
		Scanner sc=new Scanner(System.in);
		while(sc.hasNext()) {
			sp.palabra=sc.nextLine();
			sp.generarPosicionAleatoria();
			if(sp.s)sp.insertarPalabraHorizontal();
			else sp.insertarPalabraVertical();
			sp.imprimirSopa();
		}
	}
}