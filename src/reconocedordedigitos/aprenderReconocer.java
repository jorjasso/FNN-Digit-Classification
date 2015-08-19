/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reconocedordedigitos;

/**
 *
 * @author usuario
 */
public class aprenderReconocer{

	 public backPropagation red;

	public aprenderReconocer(){


        backPropagation red= new backPropagation(patronesEntrenamiento(),patronesEntrenamientoSalida(),0.25,4);
		red.diseñoCapa(1,4,49); //capa oculta II (nro capa, nro neuronas, nro de entradas)
		red.diseñoCapa(2,32,32);  //capa oculta I
		red.diseñoCapa(3,4,32); //capa da salida
		red.aprendizaje();


       red.diseñoCapa(1,32,64); //capa oculta II (nro capa, nro neuronas, nro de entradas)
		red.diseñoCapa(2,32,32);  //capa oculta I
		red.diseñoCapa(3,2,32); //capa da salida
		red.aprendizaje();

		red1=new backPropagation(49,0.25,3,4);
		red1.diseñoCapa(1,4,49);
		red1.diseñoCapa(2,4,4);
		red1.patronEntrada(patronesEntrenamiento());
		red1.patronSalida(patronesEntrenamientoSalida());
		red1.aprendizaje();

		}
     public backPropagation obtenerRedBackpropagation(){

     	return red1;
     	}

	public double[][] patronesEntrenamiento(){
		double[][] patronEntrada={{0,0,1,1,1,0,0,
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,  //0
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,
   								   0,0,1,1,1,0,0},
   								   {0,0,0,1,0,0,0,
   								   0,0,1,1,0,0,0,
   								   0,1,0,1,0,0,0,
   								   0,0,0,1,0,0,0,
   								   0,0,0,1,0,0,0,
   								   0,0,0,1,0,0,0, //1
   								   0,0,0,1,0,0,0},
   								   {0,0,0,1,1,0,0,
   								   0,0,1,0,0,1,0,
   								   0,0,0,0,1,0,0,
   								   0,0,0,1,0,0,0,//2
   								   0,0,1,0,0,0,0,
   								   0,1,1,1,1,1,0,
   								   0,0,0,0,0,0,0},
   								  {0,1,1,1,1,1,0,
   								   0,0,0,0,0,1,0,
   								   0,0,0,0,0,1,0,  //3
   								   0,1,1,1,1,1,0,
   								   0,0,0,0,0,1,0,
   								   0,0,0,0,0,1,0,
   								   0,1,1,1,1,1,0},
   								  {0,0,0,0,1,0,0,
   								   0,0,0,1,1,0,0,
   								   0,0,1,0,1,0,0,
   								   0,1,1,1,1,0,0,
   								   0,0,0,0,1,0,0,  //4
   								   0,0,0,0,1,0,0,
   								   0,0,0,0,1,0,0},
   								  {0,1,1,1,1,1,0,
   								   0,1,0,0,0,0,0,
   								   0,1,1,1,1,0,0,  //5
   								   0,0,0,0,0,1,0,
   								   0,0,0,0,0,1,0,
   								   0,1,1,1,1,0,0,
   								   0,0,0,0,0,0,0},
							   	  {0,0,0,1,1,0,0,
   								   0,0,1,0,0,0,0,
   								   0,1,0,0,0,0,0,
   								   0,1,0,1,1,0,0,
   								   0,1,1,0,0,1,0,
   								   0,0,1,0,0,1,0, //6
   								   0,0,0,1,1,0,0},
   								  {0,1,1,1,1,1,0,
   								   0,0,1,0,0,1,0,
   								   0,0,0,0,1,0,0,
   								   0,0,0,1,0,0,0,//7
   								   0,0,1,0,0,0,0,
   								   0,1,0,0,0,0,0,
   								   0,0,0,0,0,0,0},
   								  {0,1,1,1,1,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,  //8
   								   0,1,1,1,1,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,1,1,1,1,0},
   								  {0,1,1,1,1,0,0,
   								   0,1,0,0,1,0,0,
   								   0,1,0,0,1,0,0,
   								   0,1,1,1,1,0,0,
   								   0,0,0,0,1,0,0,  //9
   								   0,0,0,0,1,0,0,
   								   0,0,0,0,1,0,0},
   								  {0,1,1,1,1,1,0,
   								   0,1,0,0,0,0,0,
   								   0,1,1,1,1,1,0,  //A
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0},
   								  {0,1,1,1,1,1,0,
   								   0,1,0,0,0,0,0,
   								   0,1,0,0,0,1,0,  //B
   								   0,1,1,1,1,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,1,1,1,1,0},
   								  {0,1,1,1,1,1,0,
   								   0,1,0,0,0,0,0,
   								   0,1,0,0,0,0,0,  //C
   								   0,1,0,0,0,0,0,
   								   0,1,0,0,0,0,0,
   								   0,1,0,0,0,0,0,
   								   0,1,1,1,1,1,0}
   								   };
		return patronEntrada;
		}

	public double[][] patronesEntrenamientoSalida(){
		double[][] patronSalida={{0,0,0,0},{0,0,0,1},{0,0,1,0},{0,0,1,1},{0,1,0,0},{0,1,0,1},{0,1,1,0},{0,1,1,1},{1,0,0,0},{1,0,0,1},
								  {1,1,1,1},{1,1,1,1},{1,1,1,1}};
		return patronSalida;
			}

 	public void predecir(double[][] patronPrueba)
 	{

    // patron de prueba

/*		 double[][] patronPrueba={ {0,0,0,1,0,0,0,
   								   0,0,1,1,0,0,0,
   								   0,1,0,1,0,0,0,
   								   0,0,0,1,0,0,0,
   								   0,0,0,1,0,0,0,
   								   0,0,0,1,0,0,0, //1
   								   0,0,0,1,0,0,0},
   								  {0,0,0,1,1,0,0,
   								   0,0,1,0,0,1,0,
   								   0,0,0,0,1,0,0,
   								   0,0,0,1,0,0,0,//2
   								   0,0,1,0,0,0,0,
   								   0,1,1,1,1,1,0,
   								   0,0,0,0,0,0,0},
   								  {0,1,1,1,1,1,0,
   								   0,1,0,0,0,1,0,
   								   0,0,1,0,1,0,0,  //8
   								   0,0,1,1,1,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,0,0,0,1,0,
   								   0,1,1,1,1,1,0}
                                    };
*/
		red1.predecir(patronPrueba);
	}
	}



