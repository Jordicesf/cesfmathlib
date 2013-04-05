package cesf.math;

/**
 *  <h3>Classe per disposar de nombres complexos.</h3>
 * <p>Els nombres complexos tenen la forma a+bi (per ex. 3+4i), on i &eacute;s l'arrel
 * quadrada de -1 i base dels nombres imaginaris.</p></br>
 * <p>Un nombre complex est&agrave; composat per una part real (a) i una
 * imagin&agrave;ria (b).</p></br>
 * <p>Els nombres complexos ens ofereixen grans possibilitats de c&agrave;lculs, 
 * adem&eacute; d'acceptar tot tipus d'operacions.</p></br>
 * @author JordiCesf
*/
public class Complex {
	
	private final double re;
	private final double im;
	
	
	/**
	 * <h3>Constructor per defecte de la classe Complex.</h3>
	 * <p>Aqu&iacute; s'inicialitzen les variables utilitzades a la classe: re e im.</p>
	 */
	public Complex() { 
		re = 0; 
		im = 0; 
	}
	/** 
	 * Constructor de la classe Complex.
	 * <p>Aquest constructor rep dos par&agrave;metres: la part real i la 
	 * imagin&agrave;ria respectivament.</p>
	 * @param re <p>Variable corresponent a la part real.</p>
	 * @param im <p>Variable corresponent a la part imagin&agrave;ria.</p>
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	/** 
	 * <h3>Funci&oacute; que retorna una representaci&oacute; en cadena de text.</h3>
	 * @return Representaci&oacute; formatada en cadena de text.
	 */
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    /**
     * @return Retorna el m&ograve; del complex.
     */
    public double abs()   { return Math.hypot(re, im); }
    /**
     * @return Retorna l'angle del complex.
     */
    public double phase() { return Math.atan2(im, re); }

    /** 
     * <h3>Funci&oacute; que rep un segon nombre complex i fa la suma.</h3>
     * @param b <p>Segon nombre complex a sumar.</p>
     * @return Resultat de la suma entre dos nombres complexos.
     */
    public Complex add(Complex b) {
        double real = this.re + b.re;
        double imag = this.im + b.im;
        return new Complex(real, imag);
    }

    /** 
     * <h3>Funci&oacute; que rep un segon nombre complex i fa la resta.</h3>
     * @param b <p>Segon nombre complex a restar.</p>
     * @return Resultat de la resta entre dos nombres complexos.
     */
    public Complex substract(Complex b) {
        double real = this.re - b.re;
        double imag = this.im - b.im;
        return new Complex(real, imag);
    }

    /** 
     * <h3>Funci&oacute; que rep un segon nombre complex i fa la multiplicaci&oacute;.</h3>
     * @param b <p>Segon nombre complex a multiplicar.</p>
     * @return Resultat de la multiplicaci&oacute; entre dos nombres complexos.
     */
    public Complex multiply(Complex b) {
        double real = this.re * b.re - this.im * b.im;
        double imag = this.re * b.im + this.im * b.re;
        return new Complex(real, imag);
    }

    /** 
     * <h3>Funci&oacute; que rep un nombre real i multiplica.</h3>
     * @param x <p>Nombre real a multiplicar.</p>
     * @return Resultat de la multiplicaci&oacute; entre un complex i un real.
     */
    public Complex multiply(double x) {
    	double real = x * this.re;
    	double imag = x * this.im;
        return new Complex(real, imag);
    }

    // Retorna la divisió per un segon complex
    /**
     *  <h3>Funci&oacute; que rep un segon nombre complex i fa la divisi&oacute;.</h3>
     * @param b <p>Segon nombre complex a dividir.</p>
     * @return Resultat de la divisi&oacute; entre dos nombres complexos.
     */
    public Complex divide(Complex b) {
        return this.multiply(b.reciprocal());
    }

    /** 
     * <h3>Funci&oacute; que calcula el complex conjugat del nombre actual.</h3>
     * @return Complex conjugat de l'actual
     */
    public Complex conjugate() {
    	return new Complex(this.re, -this.im); 
    }

    /** 
     * <h3>Funci&oacute; que calcula el rec&iacute;proc del complex actual.</h3>
     * @return Rec&iacute;proc del nombre complex actual
     */
    public Complex reciprocal() {
        double x = this.re * this.re + this.im * this.im;
        return new Complex(this.re / x, -this.im / x);
    }

    /** 
     * <h3>Get que retorna nombre real.</h3>
     * @return Nombre real.
     */
    public double getReal() { return this.re; }
    
    /** 
     * <h3>Getter que retorna el nombre imaginari. </h3>
     * @return Nombre imaginari.
     */
    public double getImag() { return this.im; }

    /** 
     * <h3>Funci&oacute; que d'un nombre complex agafa l'exponencial.</h3>
     * @return Exponenciaci&oacute; del nombre complex actual.
     */
    public Complex exp() {
    	double real = Math.exp(this.re) * Math.cos(this.im);
    	double imag = Math.exp(this.re) * Math.sin(this.im);
        return new Complex(real, imag);
    }

    /**
     *  <h3>Funci&oacute; que d'un nombre complex calcula el sinus </h3>
     * @return Sinus del nombre complex actual.
     */
    public Complex sin() {
    	double real = Math.sin(re) * Math.cosh(im);
    	double imag = Math.cos(re) * Math.sinh(im);
        return new Complex(real, imag);
    }

    /** 
     * <h3>Funci&oacute; que d'un nombre complex calcula el cosinus </h3>
     * @return Cosinus del nombre complex.
     */
    public Complex cos() {
    	double real = Math.cos(re) * Math.cosh(im);
    	double imag = -Math.sin(re) * Math.sinh(im);
        return new Complex(real, imag);
    }

    /** 
     * <h3>Funci&oacute; que d'un nombre complex calcula la tangent.</h3>
     * @return Tangent del nombre complex actual.
     */
    public Complex tan() {
        return sin().divide(cos());
    }
    
    /** 
     * <h3>Funci&oacute; en format est&agrave;tic de suma</h3>
     * @param a Primer nombre
     * @param b Segon nombre
     * @return Nombre complex
     */
    public static Complex add(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }
}
