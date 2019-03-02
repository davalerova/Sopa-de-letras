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

	public void llenarSopa() {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				matriz[i][j]=letras.charAt(rnd.nextInt(26));
				criba[i][j]=true;
			}
		}
	}
	public void imprimirSopa() {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println();
		}
	}
	
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
	public boolean validarCrilla() {
		boolean a=true;
		if(s) {
			//int indicePalabra=0;
			if(d) {
				for(int i=0;i<palabra.length();i++) {
					if(criba[posX][posY+i]) a=true;
					else {
						a=false; 
						break;
					}
					//indicePalabra++;
				}
			}else {
				for(int i=0;i<palabra.length();i++) {
					if(criba[posX][14-posY-i]) a=true;
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
					if(criba[posX+i][posY]) a=true;
					else {
						a=false; 
						break;
					}
					//indicePalabra++;
				}
			}else {
				for(int i=0;i<palabra.length();i++) {
					if(criba[14-posX-i][posY])a=true;
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