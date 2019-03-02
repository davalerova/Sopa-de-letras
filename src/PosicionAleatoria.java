import java.util.Random;

public class PosicionAleatoria {
	private int i,j;
	private boolean d,s;
	Random rnd;
	int length;
	public PosicionAleatoria(String palabra) {
		rnd=new Random();
		this.i = i;
		this.j = j;
		this.length=palabra.length();
		this.d=true;
		this.s=true;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	
	public boolean getD() {
		return d;
	}
	public void setD(boolean d) {
		this.d = d;
	}
	public boolean getS() {
		return s;
	}
	public void setS(boolean s) {
		this.s = s;
	}
	public void generarPosicionAleatoria() {
		d=rnd.nextBoolean();
		s=rnd.nextBoolean();
		if(s) {
			i=rnd.nextInt(15);
			j=rnd.nextInt(16-length);
		}else {
			i=rnd.nextInt(16-length);
			j=rnd.nextInt(15);
		}
	}
}
