/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reconocedordedigitos;

 
import java.io.*;
import java.util.*;

public  class neurona implements java.io.Serializable{

     private double[] entrada ;
 	 private double[] pesos;
 	 private double salida;
 	 private double alfa;	 //valor de activación

 	 public neurona (double[] entrada,double []pesos)
 	 {
 	     this.entrada=entrada;
 		 this.pesos=pesos;
 	     calcularAlfa();
 	     calcularSalida();
 	   //  mostrar();
 	 }

 	 //entrada[0] es 1 , peso[0] es el valor del bias
 	 private void calcularAlfa()
 	 {
 	 	int i;
 	 	alfa=0;
 	 	for (i= 0; i<entrada.length;i++)
 	 	{
 	 		alfa=alfa+entrada[i]*pesos[i];
 	 	}
 	 }

 	 public void calcularSalida()
 	 {
 	 	salida=1/(1+(1/Math.exp(alfa)));
 	 }
 	 public double getSalida()
 	 {
 	 	return salida;
 	 }

 	 public void mostrar()
 	 {	int i;
 	 	System.out.println("entradas");
 	 	for( i=0;i<entrada.length;i++)
 	 	{
 	 		System.out.print(" "+entrada[i]);
 	 	}
 	 	System.out.println("");
 	 	System.out.println("pesos");
 	 	for( i=0;i<pesos.length;i++)
 	 	{
 	 		System.out.print(" "+pesos[i]);
 	 	}
 	 	System.out.println("");
 	 	System.out.println(" salida "+salida);
 	 	System.out.println("");
 	 }
}