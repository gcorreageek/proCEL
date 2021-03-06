package gui;


import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import javax.swing.*;
import beans.Globales;
import com.hexidec.ekit.Ekit;
import miLib.EnviarMail;
import miLib.Fecha;
import miLib.GUI;


	
	
	public class EnvioMailsAutoCliente  extends Ekit implements ActionListener{
		JFrame frame;
		JPanel panel;
		GUI objGUI;
		String texto,texto2;
		Globales objGlo;
		Fecha objfec;
		EnviarMail objEnviar;
		String nomven,nomcli,sexcli,fecp,fecs,referencia="";
		MantCliente objCli;
		EnvioMailsAutoCliente() {
			
			super.ekitCore.jtpMain.setVisible(false);
			aMostrar();
		}
		
		public void envio(){
			objCli= new MantCliente();
			
			String correo1=objCli.correo1;
			String correo2=objCli.correo2;
			String cc="";
			String asunto="";
			String archivo1="";
			String mensaje1=super.ekitCore.jtpMain.getText();
			int sale = 1;
			String sCadenaInvertida = "";
			String nombreRuta="";
			asunto=cargar2();

			for (int x=archivo1.length()-1;x>=0;x--)
				sCadenaInvertida = sCadenaInvertida + archivo1.charAt(x);
			
			if(correo2==null || correo2.trim().equals("") || correo2.trim().equals("0")){
				System.out.println(correo1+"|"+asunto+"|"+nombreRuta+"|"+archivo1+"|"+mensaje1);
				sale=objEnviar.Enviar1(correo1, asunto, archivo1, mensaje1);			
			}
			else{
				System.out.println(correo1+"|"+correo2+"|"+asunto+"|"+nombreRuta+"|"+archivo1+"|"+mensaje1);
				sale=objEnviar.EnviarConCopia(correo1, correo2, asunto, mensaje1);		
			}	
			if(sale==0){
				objGUI.mostrarAviso("Se Mando su Mensaje!");
				System.out.println("Se Mando su Mensaje!");
			}else{
				objGUI.mostrarAviso("Hubo un ERROR!");
				System.out.println("Hubo un ERROR!");
			}
				
		}
		
		public void cargar(){
			String letra="";
			
			letra = "//Servidor/d/ProyectoCEL/File/SpeachEnvioCAuto/BienvenidaCliente/MENSAJE_BIENVENIDA.html";
					   
			System.out.println(letra);
			File archivo = null;
		    FileReader fr = null;
		    BufferedReader br = null;
		    InputStream in=null;
			try {
		         // Apertura del fichero y creacion de BufferedReader para poder
		         // hacer una lectura comoda (disponer del metodo readLine()).
		         archivo = new File (letra);
		         fr = new FileReader (archivo);
		         br = new BufferedReader(fr);
		         // Lectura del fichero
		         String linea;
		         while((linea=br.readLine())!=null){
		        	
		        		 texto=texto+linea;
		         }
		        // System.out.println(""+texto);
		        
		      }
		      catch(Exception e){
		         e.printStackTrace();
		      }finally{
		         // En el finally cerramos el fichero, para asegurarnos
		         // que se cierra tanto si todo va bien como si salta 
		         // una excepcion.
		         try{                    
		            if( null != fr ){   
		               fr.close();     
		            }                  
		         }catch (Exception e2){ 
		            e2.printStackTrace();
		         }
		      }		    
		}
		
		public String cargar2(){
			
			String letra="";
			String nomdia=objfec.fechaActual4();

			if(nomdia.equals("lun")){
				letra = "//Servidor/d/ProyectoCEL/File/SpeachEnvioCAuto/BienvenidaCliente/PRIMER_ASUNTO.txt";
			}else if(nomdia.equals("mar")){
				letra = "//Servidor/d/ProyectoCEL/File/SpeachEnvioCAuto/BienvenidaCliente/SEGUNDO_ASUNTO.txt";
			}else if(nomdia.equals("mi�")){
				letra = "//Servidor/d/ProyectoCEL/File/SpeachEnvioCAuto/BienvenidaCliente/TERCER_ASUNTO.txt";
			}else if(nomdia.equals("jue")){
				letra = "//Servidor/d/ProyectoCEL/File/SpeachEnvioCAutoBienvenidaCliente/CUARTO_ASUNTO.txt";
			}else if(nomdia.equals("vie")){
				letra = "//Servidor/d/ProyectoCEL/File/SpeachEnvioCAuto/BienvenidaCliente/QUINTO_ASUNTO.txt";
			}else if(nomdia.equals("s�b")){
				letra = "//Servidor/d/ProyectoCEL/File/SpeachEnvioCAuto/BienvenidaCliente/PRIMER_ASUNTO.txt";
			}else{
				letra = "";
			}
	   
			System.out.println(letra);
			File archivo = null;
		    FileReader fr = null;
		    BufferedReader br = null;
		    InputStream in=null;
			try {
		         // Apertura del fichero y creacion de BufferedReader para poder
		         // hacer una lectura comoda (disponer del metodo readLine()).
		         archivo = new File (letra);
		         fr = new FileReader (archivo);
		         br = new BufferedReader(fr);
		         // Lectura del fichero
		         String linea;
		         while((linea=br.readLine())!=null){
		        	
		        		 texto2=texto2+linea;
		         }
		        // System.out.println(""+texto);
		        
		      }
		      catch(Exception e){
		         e.printStackTrace();
		      }finally{
		         // En el finally cerramos el fichero, para asegurarnos
		         // que se cierra tanto si todo va bien como si salta 
		         // una excepcion.
		         try{                    
		            if( null != fr ){   
		               fr.close();     
		            }                  
		         }catch (Exception e2){ 
		            e2.printStackTrace();
		         }
		      }
			return texto2;		    
		}
		
		public void aMostrar(){
			
			objCli= new MantCliente();
			texto="";
			texto2="";
			String fechact=objfec.fechaActual();
			String sexo="";
			String refi="";
			sexcli=objCli.sexcli;
			nomven=objCli.nom_ven;
			nomcli=objCli.nom_conacli;
			
			//se�ores="Estimado(a) Sr(a). "+objEnvioCot.nombreCliente+"\n\n";
			if(sexcli.equals("0")){
				sexo="o(a)";
			}else if(sexcli.equals("1")){
				sexo="o";
			}else{
				sexo="a";
			}
			
			if(referencia.equals("")){
				refi="";
			}else{
				refi=referencia;
			}
			
			cargar();
			texto=texto.replace("[oa]", sexo);
			texto=texto.replace("[Nombre]", nomcli );
			texto=texto.replace("[Vendedor]", nomven);
			texto=texto.replace("[Ref]", refi.toLowerCase());
			super.ekitCore.jtpMain.setText(texto);
			
			
				
		}
		
		
 }
			
	
		
		
		
		
		
		

	

	
	
	



