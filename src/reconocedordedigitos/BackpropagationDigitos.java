/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package reconocedordedigitos;

/**
 *
 * @author usuario
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;

class BackpropagationDigitos implements ActionListener{

	JPanel panelPrincipal;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;

	JButton[] button;
	int [] ban;

	JButton aprendizajeBtn;
	JButton reconocimientoBtn;
	JButton salirBtn;
	JTextArea log;
	final static boolean RIGHT_TO_LEFT = false;

	double [][]valoresPrueba;// entrada para probar la red
	aprenderReconocer redDigitos; //crea entrena y reconoce una red neuronal backpropagation
	double [] salidas;

	public BackpropagationDigitos(){

		inicializar();
		panelPrincipal = new JPanel(new GridBagLayout());
		panel1=new JPanel(new GridLayout(7,7));
		panel2=new JPanel(new GridBagLayout());
		panel3=new JPanel();
		panel4=new JPanel();
		agregarComponentes();
		panelPrincipal.add(panel1);
		panelPrincipal.add(panel2);
/*		panelPrincipal.add(panel3);
		panelPrincipal.add(panel4);



*/		}

    public void inicializar(){
    	valoresPrueba= new double[1][49];
    	for(int i=0; i<49;i++){

    		valoresPrueba[0][i]=0;
    		}
    	}

	public void agregarComponentes(){
		crearPanel1();
		crearPanel2();
//		crearPanel3();
//		crearPanel4();
		}


	public void crearPanel1(){
		panel1.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createTitledBorder("Area dibujo"),
           BorderFactory.createEmptyBorder(0,0,0,0)));

		button=new JButton[49];
		ban=new int[49];
		Dimension d = new Dimension(35,35);

		for(int i=0;i<49;i++){

		ban[i]=0;

		}

		for(int i=0;i<49;i++){

		button[i]=new JButton();
		button[i].setPreferredSize(d);
		button[i].setActionCommand(""+i);
		button[i].addActionListener(this);

		String imgLocation = "/imagenes/"+ "no"+ ".gif";
	    URL imageURL = BackpropagationDigitos.class.getResource(imgLocation);
	    button[i].setIcon(new ImageIcon(imageURL,""));

		panel1.add(button[i]);
		}

	/*	String imgLocation = "/imagenes/"+ "si1"+ ".gif";
        URL imageURL = BackpropagationDigitos.class.getResource(imgLocation);
        button[0].setIcon(new ImageIcon(imageURL,""));
     */


		}

	public void actionPerformed(ActionEvent e) {



			for(int i=0;i<49;i++){
				if (e.getActionCommand().equals(""+i))
				 { if(ban[i]==0){
				 	String imgLocation = "/imagenes/"+ "si"+ ".gif";
		        	URL imageURL = BackpropagationDigitos.class.getResource(imgLocation);
		        	button[i].setIcon(new ImageIcon(imageURL,""));
		        	ban[i]=1;

		        	valoresPrueba[0][i]=1; //pixel pintado
		        	}
		        	else{
		     	 	String imgLocation = "/imagenes/"+ "no"+ ".gif";
		        	URL imageURL = BackpropagationDigitos.class.getResource(imgLocation);
		        	button[i].setIcon(new ImageIcon(imageURL,""));
		        	ban[i]=0;

		        	valoresPrueba[0][i]=0; //pixel sin pintar

		        		}


				 }
			  }
			if (e.getSource() == aprendizajeBtn) {
				aprendizajeBtn.setEnabled(false);
				reconocimientoBtn.setEnabled(true);

				redDigitos =new aprenderReconocer();

				log.append("Error :\n"+redDigitos.obtenerRedBackpropagation().obtenerError()+"\n");
				log.append("Iteraciones :\n"+redDigitos.obtenerRedBackpropagation().obtenerIteraciones()+"\n");

            }


            if (e.getSource() == reconocimientoBtn) {

				redDigitos.predecir(valoresPrueba);

				salidas=redDigitos.obtenerRedBackpropagation().obtenerSalidasPredecir();
			    log.append("Salidas :\n"+salidas[0]+" "+salidas[1]+" "+salidas[2]+" "+salidas[3]+"\n");

			    for(int i=0;i<salidas.length;i++)//se los valores de la salida son menor que 0.5 serán 0`s de lo contrario serán 1´s
			    {
			    	if(salidas[i]<0.5){
			    		salidas[i]=0;

			    		}
			    	else{
			    		salidas[i]=1;
			    		}

			    	}
            	log.append("Salidas :\n"+salidas[0]+" - "+salidas[1]+" - "+salidas[2]+" - "+salidas[3]+"\n");
            }

			if (e.getSource() == salirBtn) {
            System.exit(0);
            }





		}

	public void crearPanel2(){

		panel2.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Aprendizaje y Reconocimiento"),
        BorderFactory.createEmptyBorder(0,0,0,0)));

		log = new JTextArea(12,25);
        log.setMargin(new Insets(0,0,0,0));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
  //      JPanel panel =new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        	c.fill = GridBagConstraints.HORIZONTAL;

        	if (RIGHT_TO_LEFT) {
            	panel2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        	}


        aprendizajeBtn = new JButton("Aprendizaje ...");
        aprendizajeBtn.addActionListener(this);

        reconocimientoBtn = new JButton("Reconocer...");
        reconocimientoBtn.addActionListener(this);
        reconocimientoBtn.setEnabled(false);

        salirBtn = new JButton("Salir...");
        salirBtn.addActionListener(this);


        JPanel panelBtn = new JPanel();
        panelBtn.add(aprendizajeBtn);
        panelBtn.add(reconocimientoBtn);
        panelBtn.add(salirBtn);

            panelBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(""),
            BorderFactory.createEmptyBorder(0,0,0,0)));

            c.fill = GridBagConstraints.HORIZONTAL;
        	c.ipady = 0;      //
			c.weightx = 1.0;
			c.weighty = 1.0;
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy = 0;
        panel2.add(panelBtn,c);
         	c.gridwidth = 1;
       		c.weightx = 1.0;
			c.weighty = 1.0;
        	c.ipady = 5;
        //	c.ipadx = 40;
        	c.gridx = 0;
			c.gridy = 1;
        panel2.add(logScrollPane,c);


				}

 	public void crearPanel3(){
		}

	public void crearPanel4(){
	}



	private static void crearMostrarGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        BackpropagationDigitos ventana = new BackpropagationDigitos();
        JFrame frame = new JFrame("Reconocedor de Dígitos                   Jorge Luis Guevara Díaz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(ventana.panelPrincipal);
        frame.pack();
        frame.setVisible(true);
    }

     public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crearMostrarGUI();
            }
        });
    }

	}