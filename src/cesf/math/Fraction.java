package cesf.math;

/** 
 * <p>Classe que representa fraccions enteres.</p>
 * <p>Aquesta &eacute;s una classe inmutable.</p>
 * @author JordiCesf
 */
public class Fraction {

	private final int num;
	private final int den;
	
	/**
	 * <p>Constructor per defecte de la classe Fraction.</p>
	 * <p>Aqu&iacute; s'inicialitzen les variables utilitzades a la classe: num i den.</p>
	 */
	public Fraction() { 
		num=0; 
		den=1;
	}
	
	/** 
	 * <p>Constructor de la classe Fraction.</p>
	 * <p>contructor que rep numerador i denominador.</p>
	 * <p>El denominador proporcionat no pot ser mai zero. Si ho fos, retornaria una excepci&oacute;.</p>
	 * @param num <p>Variable corresponent al numerador.</p>
	 * @param den <p>Variable corresponent al denominador.</p>
	 */
	public Fraction(int num, int den) { 
		if (den == 0) 
			throw new IllegalArgumentException("Denominator can not be zero!"); 
		this.num = num;
		this.den = den; 
	}

	/**
	 * @return Retorna el numerador.
	 */
	public int getNum() { return this.num; }
	
	/**
	 * @return Retorna el denominador.
	 */
	public int getDen() { return this.den; }
	
	/**
	 * @return Valor en format num&egrave;ric de la fracci&oacute;.
	 */
	public double valueOf() {
		double x = (double)(this.num) / this.den; 
		return x;
	}
	
	/** 
	 * @param a Una fracci&oacute;.
	 * @return Valor en format num&egrave;ric de la fracci&oacute; en versi&oacute; est&agrave;tica.
	 */
	public static double valueOf(Fraction a) {
		double x = (double)(a.num) / a.den; 
		return x;		
	}
	
	/** 
	 * <p>Funci&oacute; que retorna una representaci&oacute; en cadena de text.</p>
	 * @return Representaci&oacute; formatada d'una fracci&oacute; en cadena de text.
	 */
	public String toString() {
		return this.num + "/" + this.den;
	}
	
	/** 
	 * <p>Funci&oacute; que simplifica lo maxim possible la fraccio</p>
	 * @return Fracci&oacute; reduida.
	 */
	public Fraction reduce() {
		int mcd = mcd(this.num, this.den);
		int n = this.num / mcd;
		int d = this.den / mcd;
		if (d < 0) { n = -n; d = -d; }
		return new Fraction(n, d);
	}
	
	/** 
	 * <p>Funcio la qual suma dues fraccions</p>
	 * @param b Una fracci&oacute;.
	 * @return Suma de dues fraccions posteriorment reduides.
	 */
	public Fraction add(Fraction b) {
		int n = this.num * b.den + this.den * b.num;
		int d = this.den * b.den;
		return new Fraction(n, d).reduce();
	}
	
	/** 
	 * <p>Funci&oacute; la qual resta dues fraccions.</p>
	 * @param b Una fracci&oacute;.
	 * @return Resta de dues fraccions posteriorment reduides.
	 */
	public Fraction substract(Fraction b) {
		int n = this.num * b.den - this.den * b.num;
		int d = this.den * b.den;
		return new Fraction(n, d).reduce();
	}
	
	/** 
	 * <p>Funci&oacute; la qual multiplica dues fraccions</p>
	 * @param b Una fracci&oacute;.
	 * @return Multiplicaci&oacute; de dues fraccions posteriorment reduides.
	 */
	public Fraction multiply(Fraction b) {
		int n = this.num * b.num;
		int d = this.den * b.den;
		return new Fraction(n, d).reduce();
	}
	
	/** 
	 * Funci&oacute; la qual multiplica una fraccio pel nombre que introdueixes.
	 * @param x Un nombre.
	 * @return Multiplicaci&oacute; d'una fracci&oacute; i un nombre posteriorment reduida.
	 */
	public Fraction multiply(int x) {
		int n = this.num * x;
		int d = this.den;
		return new Fraction(n, d).reduce();
	}
	
	/**
	 * <p> Funci&oacute; la qual divideixes dues fraccions.</p>
	 * @param b Una fracci&oacute;.
	 * @return Divisi&oacute; de dues fraccions posteriorment reduides.
	 */
	public Fraction divide(Fraction b) {
		return this.multiply(b.reciprocal());
	}

	// retorna 1 / F (nota*: ?¿?¿?¿)
	public Fraction reciprocal() {
		return new Fraction(this.den, this.num).reduce();
	}
	
	/** 
	 * <p>Funci&oacute; que calcula el MCD del numerador i denominador.</p>
	 * @param a Un numerador.
	 * @param b Un denominador.
	 * @return MCD del numerador i denominador proporcionats.
	 */
	protected int mcd(int a, int b) {
		int d; 
		while (b != 0) {
			d = b;
			b = a % b;
			a = d;
		}
		return a;
	}

	// compara dos fraccions
	/**
	 *  <p>Funci&oacute; que compara dues fraccions.</p>
	 * @param b Una fracci&oacute;.
	 * @return Retorna verdader si les fraccions s&oacute;n iguals. Retorna fals si no ho s&oacute;n.
	 */
	public boolean equals(Fraction b) {
		Fraction f1 = this.reduce();
		Fraction f2 = b.reduce();
		if (f1.num != f2.num) return false;
		if (f1.den != f2.den) return false; 
		return true;
	}
}
