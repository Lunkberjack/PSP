package main.act1_11;
/**
 * @author LuciaLM
 * @version 1.0
 */
public class Act1_11_dame {
	public static void main(String[] args) {
		// Si se llama sin parámetros se devuelve el código de error 1
		if(args.length == 0)
			System.exit(1);
		// Si se llama con un parámetro (String), se devuelve 2
		else if(args.length == 1 && args[0] instanceof String) {
		    int n = 0;
		    // Si no se puede parsear a int, es un String (2)
		    try {
		        n = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		    	System.exit(2);
		    }
		    // Si no salta excepción, sabemos que se ha podido parsear a int
		    // Si es menor que 0, se devuelve 3
		    if (n < 0) {
		    	System.exit(3);
		    }
		}
		// En los demás casos
		else
			System.exit(0);
	}
}