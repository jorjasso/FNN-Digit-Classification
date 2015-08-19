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

import java.io.*;
import java.util.*;

public  class backPropagation implements java.io.Serializable{


 	 public int numeroCapas;

     public double[][] matrizPatronesEntrada;	//matriz de todos los patrones de entrada
     public double[][] patronE1;				//patron para predecir red neuronal
     public double[][] matrizPatronesSalida;	//matriz con los patrones de salida deseados
 	 public double[] entrada ;					//vector de entrada
 	 public double[] salidas; 					//vector de salidas
 	 public double[] d;							//patron de salida deseado
 	 public double ErrorGlobal;
 	 public double[] errorPatron;				//vector de errores por patrones
 	 public double factorAprendizaje;			//factor de aprendizaje
 	 public double factorMomento    ;			//factor de momento
 	 public capa[] capas;						//capas de neuronas

 	 //para reportar el error de salida final , el número de iteraciones y la matriz ajustada de pesos
 	 public double errorSalida;
 	 public int iteracionesSalida;
 	 public double[][] matrizPesosOcultaSalida;  //pesos ajustados de salida para la capa oculta
 	 public double[][] matrizPesosSalidaSalida;  //pesos ajustados de salida para la capa de salida
 	 //public double[] delta;

 	 public backPropagation(double [][] matrizPatronesEntrada,double[][] matrizPatronesSalida, double factorAprendizaje,int numeroCapas)
 	 {
 	 	int numeroEntradas 		=	matrizPatronesEntrada[0].length;
 	 	int numeroSalidas		=	matrizPatronesSalida[0].length;
 	 	this.numeroCapas		=	numeroCapas;
 	 	capas					=	new capa[numeroCapas]; // no usar capa de entrada

 	 	errorPatron				=	new double[matrizPatronesEntrada.length];

 	 	entrada					=	new double[numeroEntradas];
		salidas					=	new double[numeroSalidas];

 	 	this.factorAprendizaje	=	factorAprendizaje;
 	 	factorMomento			=   0.17;
 	 	d						=	new double[numeroSalidas];

 	 	setMatrizPatronesEntrada(matrizPatronesEntrada);
 	 	setMatrizPatronesSalida(matrizPatronesSalida);
 	 }

 	 private void setMatrizPatronesEntrada(double[][] matrizPatronesEntrada)
 	 {
 	 	this.matrizPatronesEntrada=matrizPatronesEntrada;
 	 }

 	 private void setMatrizPatronesSalida(double[][] matrizPatronesSalida)
 	 {
 	 	this.matrizPatronesSalida=matrizPatronesSalida;
 	 }


 	 public void diseñoCapa(int numeroCapa,int numeroNeuronas,int nEntradas)
 	 {
 	 	int i,n,e;
 	 	i=numeroCapa;
 	 	n=numeroNeuronas;
 	 	e=nEntradas;
 	 	capas[i]=new capa(n,e);// me da la estructura de las capas que conforman la red
 	 }

 	 public void aprendizaje()
 	 {
 	 	double E,E0; //
 	 	int i,j,contador,k,l;
 	 	double tem,tem1,suma,error;
 	 //	double ErrorGlobal;

 	 	int iter=1; // para contar iteraciones
 	 	int itercont=1; // para contar iteraciones

 	 	double valorMomento;

 	 	contador=0;
 	 	E0=1000;
 	 	do{
 	 		for(i=0;i<matrizPatronesEntrada.length;i++)
 	 		{

 	 			for(j=0;j<matrizPatronesEntrada[0].length;j++)
 	 			{
 	 				entrada[j]=matrizPatronesEntrada[i][j];//vectores de entrada
 	 		//		System.out.print(" "+entrada[j]);
 	 			}

 	 			//fase hacia adelante
 	 			for(k=1;k<numeroCapas;k++)
 	 			{
 	 				if(k==1)
 	 				{
 	 					capas[k].setEntradas(entrada);
 	 					capas[k].procesarCapa();
 	 				}
 	 				else
 	 				{
 	 					capas[k].setEntradas(capas[k-1].getSalidas());
 	 					capas[k].procesarCapa();
 	 				}
 	 			}

 	 			//calcular el error

 	 			//para n neuronas de salida hay un delta y un error  respectivo
 	 			error=0;
 	 			salidas=capas[numeroCapas-1].getSalidas(); //salidas de la capa de salida

 	 			for(k=0;k<salidas.length;k++)
 	 			{
 	 				d[k]=matrizPatronesSalida[i][k]; 			//salidas deseadas

 	 				error=error+0.5*(Math.pow((d[k]-salidas[k]),2));
 	 			}

 	 			errorPatron[i]=error;


 	 			//fase hacia atras

 	 			//obtener los deltas =(dk-yk)yk(1-dk)
 	 			//para todas las neuronas de la capa de salida
 	 			for(k=0;k<capas[numeroCapas-1].getSalidas().length;k++)
 	 			{
 	 				capas[numeroCapas-1].delta[k]=(d[k]-salidas[k])*salidas[k]*(1-salidas[k]);
 	 			}

 	 			//hallando los deltas de la capa oculta I
 	 			for(l=0;l<capas[numeroCapas-2].getSalidas().length;l++)
 	 			{
 	 				suma=0;
 	 				for(k=0;k<capas[numeroCapas-1].getSalidas().length;k++)
 	 				{
 	 			     suma=suma+capas[numeroCapas-1].delta[k]* capas[numeroCapas-1].matrizPesos[l][k];
   	 				}

   	 			capas[numeroCapas-2].delta[l]=suma*capas[numeroCapas-2].salidas[l]*(1-capas[numeroCapas-2].salidas[l]);
 	 			}

 	 			/*****************************/
 	 			//hallando los deltas de la capa oculta II
 	 			for(l=0;l<capas[numeroCapas-3].getSalidas().length;l++)
 	 			{
 	 				suma=0;
 	 				for(k=0;k<capas[numeroCapas-2].getSalidas().length;k++)
 	 				{
 	 			     suma=suma+capas[numeroCapas-2].delta[k]* capas[numeroCapas-2].matrizPesos[l][k];
   	 				}

   	 			capas[numeroCapas-3].delta[l]=suma*capas[numeroCapas-3].salidas[l]*(1-capas[numeroCapas-3].salidas[l]);
 	 			}
 	 			/*****************************/


 	 		    //capa salida
 	 		    //ajustar los pesos para todas las neuronas de la capa de salida
 	 		    for(k=0;k<capas[numeroCapas-1].entradas.length;k++)
 	 		    {
 	 		    	for(l=0;l<salidas.length;l++)  //por cada neurona
 	 		    	{

 	 		    		capas[numeroCapas-1].matrizPesos[k][l]=	capas[numeroCapas-1].matrizPesos[k][l]+factorAprendizaje*capas[numeroCapas-1].delta[l]*capas[numeroCapas-1].entradas[k] + factorMomento*capas[numeroCapas-1].matrizMomentos[k][l];
 	 		    		capas[numeroCapas-1].matrizMomentos[k][l]=factorAprendizaje*capas[numeroCapas-1].delta[l]*capas[numeroCapas-1].entradas[k]; //variacion de pesos anterior

 	 			    }

 	 			}

				//capa oculta I
				//actualizar pesos
				for(k=0;k<capas[numeroCapas-2].entradas.length;k++)
				{
					for(l=0;l<capas[numeroCapas-2].salidas.length;l++)  //por cada neurona
 	 		    	{
 	 		    		capas[numeroCapas-2].matrizPesos[k][l]=	capas[numeroCapas-2].matrizPesos[k][l]+factorAprendizaje*capas[numeroCapas-2].delta[l]*capas[numeroCapas-2].entradas[k]+factorMomento*capas[numeroCapas-2].matrizMomentos[k][l];
 	 		    		capas[numeroCapas-2].matrizMomentos[k][l]=factorAprendizaje*capas[numeroCapas-2].delta[l]*capas[numeroCapas-2].entradas[k];
 	 			    }
 	 		    }

 	 		    //capa oculta II
				//actualizar pesos
				for(k=0;k<capas[numeroCapas-3].entradas.length;k++)
				{
					for(l=0;l<capas[numeroCapas-3].salidas.length;l++)  //por cada neurona
 	 		    	{
 	 		    		capas[numeroCapas-3].matrizPesos[k][l]=	capas[numeroCapas-3].matrizPesos[k][l]+factorAprendizaje*capas[numeroCapas-3].delta[l]*capas[numeroCapas-3].entradas[k]+factorMomento*capas[numeroCapas-3].matrizMomentos[k][l];
 	 		    		capas[numeroCapas-3].matrizMomentos[k][l]=factorAprendizaje*capas[numeroCapas-3].delta[l]*capas[numeroCapas-3].entradas[k];
 	 			    }
 	 		    }

 	 		}


 	 		contador=contador+1;
 	 		ErrorGlobal=calcularErrorGlobal();

 	 		if(contador==iter)
 	 		{System.out.println(" Error Global = "+ErrorGlobal);
 	 		 System.out.println(" Iteraciones  = "+contador);
 	 		 itercont++;
 	 		 iter=itercont*100;
 	 		 }

 	 		 if(contador==1000000)
 	 		 {
 	 		 	break;
 	 		 }

 	 	}while(ErrorGlobal > 50);
 	 	System.out.println(" Error Global = "+ErrorGlobal);
 	 	System.out.println(" Iteraciones  = "+contador);


 	 }



 	 	public double calcularErrorGlobal(){
 	 	int i;
 	 	double suma;
 	 	suma=0;
 	 	for(i=0;i<errorPatron.length;i++){
 	 		suma=suma+errorPatron[i];
 	 	}
 	 	return suma;
 	 }



 	public void predecir(double[][] matrizP){  //entra los patrones a predecir

 	 	double E,E0; //E va a ser  a errorGlobal
 	 	int i,j,contador,k,l;
 	 	double tem,tem1,suma,err;
 	 	contador=0;
 	 	E0=1000;
 	 	patronE1=matrizP;

 	 		for(i=0;i<patronE1.length;i++)
 	 		{
 	 			for(j=0;j<patronE1[0].length;j++){
 	 				entrada[j]=patronE1[i][j];//lleno mi primer valor de entrada
 	 			}

 	 			//fase hacia adelante
 	 			for(k=1;k<numeroCapas;k++){
 	 				if(k==1)
 	 				{
 	 					capas[k].setEntradas(entrada);
 	 					capas[k].procesarCapa();
 	 				}
 	 				else
 	 				{
 	 					capas[k].setEntradas(capas[k-1].getSalidas());
 	 					capas[k].procesarCapa();
 	 				}
 	 			}

 	 			System.out.println("predecir");
 	 			//para n neuronas de salida hay un delta y un error  respectivo
 	 			for(k=0;k<salidas.length;k++){
 	 			salidas[k]=capas[numeroCapas-1].salidas[k];

 	 			System.out.print("   "+salidas[k]);		//salidas para patrones de prueba
// 	 			System.out.print("  patronE1.length= "+patronE1.length);
 	 			}
 	 			System.out.println("");
 	 		}
 		}

 		/* Predice solo para un patron
 		 *
 		 **/

 		public double[] predecir(double[] patronE1){  //entra los patrones a predecir

 	 	double E,E0; //E va a ser  a errorGlobal
 	 	int i,j,contador,k,l;
 	 	double tem,tem1,suma,err;
 	 	contador=0;
 	 	E0=1000;

 	 			for(j=0;j<patronE1.length;j++){
 	 				entrada[j]=patronE1[j];//lleno mi primer valor de entrada
 	 			}

 	 			//fase hacia adelante
 	 			for(k=1;k<numeroCapas;k++){
 	 				if(k==1)
 	 				{
 	 					capas[k].setEntradas(entrada);
 	 					capas[k].procesarCapa();
 	 				}
 	 				else
 	 				{
 	 					capas[k].setEntradas(capas[k-1].getSalidas());
 	 					capas[k].procesarCapa();
 	 				}
 	 			}

 	 //			System.out.println("predecir");
 	 			//para n neuronas de salida hay un delta y un error  respectivo
 	 			for(k=0;k<salidas.length;k++){
 	 			salidas[k]=capas[numeroCapas-1].salidas[k];

 	 			System.out.print("   "+salidas[k]);		//salidas para patrones de prueba
// 	 			System.out.print("  patronE1.length= "+patronE1.length);
 	 			}
 	 //			System.out.println("");
 	 		return salidas;

 		}
 		public double obtenerError(){
			return errorSalida;

			}

		public double obtenerIteraciones(){
			return iteracionesSalida;
			}

 		public double[] obtenerSalidasPredecir(){
 			return salidas;
 			}

 	 }



