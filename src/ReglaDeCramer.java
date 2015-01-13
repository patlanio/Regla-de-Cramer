import java.util.Scanner;
public class ReglaDeCramer {	
	static Scanner teclado = new Scanner(System.in);
	static float[][] a = new float[3][3];
	static float[][] b = new float[3][1];
	static float[][] s = new float[3][5];
	static float[][] x = new float[3][5];
	static float[][] y = new float[3][5];
	static float[][] z = new float[3][5];
	static float detS;
	static float detX;
	static float detY;
	static float detZ;
	
	public static void main(String[] args){
		resolverSistema();
	}
	
	public static void resolverSistema(){
		capturaA();
		espaciar(40, true);
		capturaB();
		espaciar(40, true);
		imprimirMatriz(a,3,3, "MATRIZ A");
		espaciar(40, true);
		imprimirMatriz(b,3,1, "MATRIZ B");
		espaciar(40, true);
		s=crearMatrizAumentada(s, b, 3);
		detS=calcularDeterminante(s);
		imprimirMatriz(s,3,5, "DETERMINANTE S");
		espaciar(40, true);
		x=crearMatrizAumentada(x, b, 0);
		detX=calcularDeterminante(x);
		imprimirMatriz(x,3,5, "DETERMINANTE X");
		espaciar(40, true);
		y=crearMatrizAumentada(x, b, 1);
		detY=calcularDeterminante(y);
		imprimirMatriz(y,3,5, "DETERMINANTE Y");
		espaciar(40, true);
		z=crearMatrizAumentada(x, b, 2);
		detZ=calcularDeterminante(z);
		imprimirMatriz(z,3,5, "DETERMINANTE Z");
		espaciar(40, true);
		imprimirResultados("RESULTADO FINALES");
	}
	
	public static void capturaA(){
		System.out.println("INTRODUCE LOS VALORES DE 'A'");
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				System.out.print("Introduce el valor A["+(j+1)+","+(i+1)+"] :");
				a[i][j]= teclado.nextFloat();
			}
		}
	}
	
	public static void capturaB(){
		System.out.println();
		System.out.println("INTRODUCE LOS VALORES DE 'B'");
		for (int i=0; i<3; i++){
			System.out.print("Introduce el valor B["+(i+1)+"] :");
			b[i][0]=teclado.nextFloat();
		}
	}
	
	public static float[][] crearMatrizAumentada(float[][] tabla, float[][] bDup, int pos){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(j!=pos || pos==3){
					tabla[i][j]=a[i][j];
				}else{
					tabla[i][j]=b[i][0];
				}
				if (j<2){
					tabla[i][j+3]=tabla[i][j];
				}
			}
		}
		return tabla;
	}
	
	public static void imprimirMatriz(float[][] tabla, int renglones, int columnas, String leyenda){
		System.out.print(leyenda);
		espaciar(40-leyenda.length(), true);
		System.out.print("[");
		for(int i=0; i<renglones; i++){
			for(int j=0; j<columnas; j++){
				if (j==3){
					System.out.print("|");
				}
				System.out.print(tabla[i][j]+"\t");
			}
			if (i==1 && columnas==5){
				System.out.print("]");
				imprimirOperaciones(tabla);
				System.out.println();
			}else{
				System.out.println("]");
			}
		}
	}
	
	public static float calcularDeterminante(float[][] tabla){
		float determinante=0;
		
			for (int i=0; i<3; i++){
				float producto = tabla[0][i] * tabla[1][i+1] * tabla[2][i+2];
				determinante+=producto;
			}
			
			for (int i=4; i>1; i--){
				float producto = tabla[0][i] * tabla[1][i-1] * tabla[2][i-2];
				determinante-=producto;
			}
			return determinante;
	}
	
	public static void imprimirOperaciones(float[][] tabla){
		float determinante=0;
		
		System.out.print("= ");
			for (int i=0; i<3; i++){
				float producto = tabla[0][i] * tabla[1][i+1] * tabla[2][i+2];
				System.out.print("+("+producto+")");
				determinante+=producto;
			}
			
			for (int i=4; i>1; i--){
				float producto = tabla[0][i] * tabla[1][i-1] * tabla[2][i-2];
				System.out.print("-("+producto+")");
				determinante-=producto;
			}
			System.out.print(" = "+determinante);
	}
	
	public static void imprimirResultados(String leyenda){
		System.out.print(leyenda);
		espaciar(40-leyenda.length(), true);
		System.out.println("x = "+detX/detS);
		System.out.println("y = "+detY/detS);
		System.out.println("z = "+detZ/detS);
	}
	
	public static void espaciar(int lim, boolean salto){
		for (int i=0; i<lim; i++){
			System.out.print("_");
		}
		if (salto = true){
			System.out.println();
		}
	}

}
