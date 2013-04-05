package cesf.math;

/** 
 * Classe de representaci&oacute; i manipulaci&oacute; de matrius de nombres reals.
 * <p>Recopilaci&oacute; de funcions b&agrave;siques de c&agrave;lcul sobre matrius M x N.</p>
 * <p>Aquesta &eacute;s una classe inmutable.</p>
 * @author JordiCesf
 */
final public class Matrix {
    private final int M;             
    private final int N;             
    private final double[][] data;   

    /**
     *  Constructor de la classe Matrix.
     * <p>Crea una matriu M per N amb zeros</p>
     * @param M N&uacute;mero de files.
     * @param N N&uacute;mero de columnes.
     */
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    /**
     *  Aix&ograve; &eacute;s el constructor de la classe Matrix.
     * <p>Crea una matriu a partir d'una matriu bidimensional proporcionada anteriorment.</p>
     * @param data Matriu bidimensional.
     */
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
        	for (int j = 0; j < N; j++)
            	this.data[i][j] = data[i][j];
    }

    /**
     *  Constructor de c&ograve;pia
     * @param A Matriu M per N
     */
    private Matrix(Matrix A) {
    	this(A.data); 
    }

    /** 
     * Funci&oacute; que retorna una matriu M per N amb valors entre 0 i 1.
     * @param M N&uacute;mero de files
     * @param N N&uacute;mero de columnes
     * @return Matriu M  per N aleat&ograve;ria.
     */
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    /** 
     * Funci&oacute; que retorna una matriu M per N identitat (n&uacute;meros uns a la diagonal).
     * @param N N&uacute;mero de columnes
     * @return Matriu M x N identitat
     */
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    /** 
     * Funci&oacute; que intercanvia les files "i" i "j".
     * @param i Una fila
     * @param j Una fila
     */
    public void swapRows(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /** 
     * Funci&oacute; que intercanvia les columnes "i" i "j".
     * @param i una columna
     * @param j una columna
     */
    public void swapColumns(int i, int j) {
    	for (int r = 0; r < M; r++) {
    		double temp = data[r][i];
    		data[r][i] = data[r][j];
    		data[r][j] = temp;
    	}
    }

    /** 
     * <p>Funci&oacute; que crea i retorna la matriu transposada de la matriu actual.</p>
     * 
     * @return Matriu transposada de l'actual.
     */
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    /** 
     * <p>Funci&oacute; que suma dues matrius i retorna el resultat.</p>
     * @param B Una matriu
     * @return Suma de dues matrius.
     */
    public Matrix add(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N)
        	throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }

    /** 
     * <p>Funci&oacute; que resta dues matrius i retorna el resultat.</p>
     * @param B Una matriu
     * @return Resta de dues matrius.
     */
    public Matrix substract(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) 
        	throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    /** 
     * <p>Funci&oacute; que compara dues matrius.</p>
     * <p>La comparaci&oacute; es fa a partir dels valors de les mateixes.</p>
     * @param B Una matriu
     * @return Retorna verdader si les matrius s&oacute;n iguals. Retorna fals en cas contrari.
     */
    public boolean equals(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) 
        	throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) 
                	return false;
        return true;
    }

    /**
     *  <p>Funci&oacute; que multiplica dues matrius i retorna el resultat.</p>
     * @param B Una matriu.
     * @return Multiplicaci&oacute; de dues matrius.
     */
    public Matrix multiply(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) 
        	throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

    /**
     *  <p>Funci&oacute; que soluciona el sistema a partir d'una matriu proporcionada.</p>
     * <p>La matriu proporcionada ha de ser d'una sola columna i tenir els resultats de les equacions.</p>
     * <p>La matriu actual ha de contenir els coeficients i tenir el rang adient per ser resoluble (a m&eacute;s de ser quadrada).</p>
     * @param rhs Una matriu d'una sola columna
     * @return Sistema de matriu.
     */
    public Matrix solve(Matrix rhs) {
        if (M != N || rhs.M != N || rhs.N != 1)
            throw new RuntimeException("Illegal matrix dimensions.");
        Matrix A = new Matrix(this);
        Matrix b = new Matrix(rhs);
        for (int i = 0; i < N; i++) {
            int max = i;
            for (int j = i + 1; j < N; j++)
                if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i]))
                    max = j;
            A.swapRows(i, max);
            b.swapRows(i, max);
            if (A.data[i][i] == 0.0) 
            	throw new RuntimeException("Matrix is singular.");
            for (int j = i + 1; j < N; j++)
                b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];
            for (int j = i + 1; j < N; j++) {
                double m = A.data[j][i] / A.data[i][i];
                for (int k = i+1; k < N; k++) {
                    A.data[j][k] -= A.data[i][k] * m;
                }
                A.data[j][i] = 0.0;
            }
        }
        Matrix x = new Matrix(N, 1);
        for (int j = N - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < N; k++)
                t += A.data[j][k] * x.data[k][0];
            x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
        }
        return x;
    }

    /** 
     * <p>Funcio que retorna una representcio en cadena de text.</p>
	 * @return Representacio formatada en cadena de text.
	 */
    public String toString() {
    	String res = "";
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) { 
            	res += String.format("%9.4f ", data[i][j]);
            }
            res += "\n";
        }
        return res;
    }
}
