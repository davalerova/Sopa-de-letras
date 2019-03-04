import java.util.Random;

public class PosicionAleatoria {
	private int i,j;
	private boolean dir,eje;
	Random rnd;
	int length;
	public PosicionAleatoria(String palabra) {
		rnd=new Random();
		this.i = i;
		this.j = j;
		this.length=palabra.length();
		this.dir=true;
		this.eje=true;
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
		return dir;
	}
	public void setD(boolean d) {
		this.dir = d;
	}
	public boolean getS() {
		return eje;
	}
	public void setS(boolean s) {
		this.eje = s;
	}
	public void generarPosicionAleatoria() {
		dir=rnd.nextBoolean();
		eje=rnd.nextBoolean();
		if(eje) {
			i=rnd.nextInt(15);
			j=rnd.nextInt(16-length);
		}else {
			i=rnd.nextInt(16-length);
			j=rnd.nextInt(15);
		}
	}
}
