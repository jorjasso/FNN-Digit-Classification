/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reconocedordedigitos;

/**
 *
 * @author usuario
 */

import java.io.*;
import java.util.*;

public  class capa implements java.io.Serializable
{
 	 private int numeroEntradas;
 	 private neurona[] neuronas;		//neuronas de la capa
 	 public double[][] matrizPesos; 	//matriz de pesos de la capa de neuronas
 	 public double[][] matrizMomentos; 	//matriz de momentos
 	 public double[] entradas;			//entradas a la capa
 	 public double[] salidas;			//salidas de la capa
 	 public double[] delta;		   	    //deltas de la capa

 	 public capa(int numeroNeuronas ,int numeroEntradas)
 	 {
 	 	this.numeroEntradas =	numeroEntradas;
 	 	neuronas 			=	new neurona[numeroNeuronas];
 	 	matrizPesos			=	new double[numeroEntradas+1][numeroNeuronas];
 	 	matrizMomentos      =	new double[numeroEntradas+1][numeroNeuronas];
 	 	entradas			=	new double[numeroEntradas+1];//entrada para el valor umbral
 	 	salidas				=	new double[numeroNeuronas];
 	 	delta				=	new double[numeroNeuronas+1]; // en la capa de salida tamaño de delta
 	 														  //sera numeroNeuronas, en las capas
 	 	generarMatrizAleatorio();							  //ocultas sera numeroNeuronas +1 por el
 	 }														  //valor del bias

 	 private void generarMatrizAleatorio()
 	 {
 	 	int i,j;

 	 	for ( i=0;i<matrizPesos.length;i++)
 	 	{
 	 		for(j=0;j<matrizPesos[0].length;j++)
 	 		{
 	 			matrizPesos[i][j]=Math.random();
 	 		 }
 	     }
 	 }

 	 private void generarMatrizMomentos()
 	 {
 	 	int i,j;

 	 	for ( i=0;i<matrizMomentos.length;i++)
 	 	{
 	 		for(j=0;j<matrizMomentos[0].length;j++)
 	 		{
 	 			matrizMomentos[i][j]=0;
 	 		 }
 	     }
 	 }

 	 public void setEntradas(double[] entradas){
 	 	int i;
 	 	this.entradas[0]=1;
 	 	for(i=1;i<=entradas.length;i++){

 	 	this.entradas[i]=entradas[i-1];
 	 	}
 	 }

	public double[] getSalidas()
	{
		return salidas;
	}


	 //propagacion de las entradas y pesos hacia las neuronas
	 //y proceso de cada neurona
	public void procesarCapa()
	{
		double[] vPesos;
	 	int i,k;
	 	vPesos=new double[entradas.length];//pesos que van a entrar a la neurona

	 	for(i=0;i<neuronas.length;i++)  //para cada una de las neuronas de la capa
	 	{
	 		for(k=0;k<matrizPesos.length;k++)
	 		{
	 			vPesos[k]=matrizPesos[k][i];
	 		}

	 		neuronas[i]=new neurona (entradas,vPesos);
	 		salidas[i] =neuronas[i].getSalida();
	 	}

	 }

	 // se ajustan los pesos de la matriz
	 public void setMatrizPesos(double[][] matrizPesos)
	 {
	 	this.matrizPesos=matrizPesos;
	 }

}





