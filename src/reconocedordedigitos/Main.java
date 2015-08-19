/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reconocedordedigitos;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[][] E={{0,0},{0,1},{1,0},{1,1}};
		double[][] S={{0},{1},{1},{0}};
		int nombre;

		backPropagation red= new backPropagation(E,S,0.25,4);
		red.diseñoCapa(1,2,2); //capa oculta II (nro capa, nro neuronas, nro de entradas)
		red.diseñoCapa(2,1,2);  //capa oculta I
		red.diseñoCapa(3,1,2); //capa da salida
		red.aprendizaje();

		red.predecir(E);
    }

}
