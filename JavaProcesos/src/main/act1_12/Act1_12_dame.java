package main.act1_12;

/**
 * @author LuciaLM
 * @version 1.0
 */
public class Act1_12_dame {
	private static final int NUM_REP = 5;

	public static void main(String[] args) {
		// Si tiene un parámetro (String) aunque al ser un arrray de String
		// ya sabemos que lo es:
		if(args.length == 1 && args[0] instanceof String) {
			for(int i = 0; i < NUM_REP; i++) {
				System.out.println(args[0]);
			}
		// Si no le llega ninguno:
		} else if (args.length == 0) {
			System.exit(-1);
		}
	}
}