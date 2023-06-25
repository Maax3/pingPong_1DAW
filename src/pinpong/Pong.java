package pinpong;

import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Pong implements KeyListener,ActionListener {

	private PongJFrame vista = new PongJFrame();
	private PongJPanel lamina = new PongJPanel();
	private Timer tarea;
	private static int rebote = 0;
	private static File score = new File ("score.txt");
	private int desplazamientoX = random();
	private int desplazamientoY = random();
	private boolean derrota = false;
	
	public void iniciar() {
		vista.add(lamina);
		vista.addKeyListener(this);
		vista.setVisible(true);
		tarea = new Timer(16,this); 
		tarea.start();

	}
	
	private void reiniciarJuego() {
	    rebote = 0;
	    desplazamientoX = random();
	    desplazamientoY = random();
	    derrota = false;
	    lamina.reiniciarPosicion();
	}
	
	//Orienta el asteroide hacia la derecha o izquierda al inicio del juego
	public int random () {
		int resultado = 3;
		int n = (int) (Math.random()*2);
		
		if (n == 0) {
			resultado = resultado * -1;
		}
		return resultado;
	}
	//Acumula el score
	public static int getRebote() {
		return rebote;
	}
	
	//Acumula el scoreMaximo
	public static int scoreMaximo() {
		if(!score.exists())
			try {
				score.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return Score.nuevoScore(score);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		/*
		 * Si la posicion X/Y del asteroide es mayor que el ancho/alto (del frame+dibujo) o menor que 0, 
		 * se invierte su valor para cambiar de direccion
		 */
		
	    if (lamina.getAsteroidPosY() < 0)
	    	 desplazamientoY = desplazamientoY * -1;
		
		if ((lamina.getAsteroidPosX() > lamina.PANEL_ANCHO - lamina.getAnchoAst()) || lamina.getAsteroidPosX() < 0) {
		
			desplazamientoX = desplazamientoX * -1;
			invertirVelocidad();		
		} 
		
		comprobarReboteConBarra();
		
		if (isDerrota()) {
				finDePartida();
		}
		lamina.pintarAsteroide(desplazamientoX,desplazamientoY);
	
	}	
	
	
	public boolean isDerrota() {
			if (lamina.getAsteroidPosY() > lamina.PANEL_ALTO) {
				derrota = true;
			}
			return derrota;
	}
	
	public void finDePartida() {
		Score.guardarScore(score,rebote);
		JOptionPane.showMessageDialog(null, "HAS PERDIDO");
		int	continuar =	JOptionPane.showConfirmDialog(lamina, "¿DESEA CONTINUAR?", "Game Over", JOptionPane.YES_NO_OPTION);
		
		if (continuar == 0) {
			reiniciarJuego();
			lamina.repaint();
			vista.repaint();
		} else 
			System.exit(0);
	}
	
	public void invertirVelocidad() {
		//si el desplazamiento actual es negativo, para aumentar su velocidad le sumamos un negativo
		if (desplazamientoX < 0)
			desplazamientoX += (-1);
		
		//si el desplazamiento actual es positivo, para aumentar su velocidad le sumamos un positivo
		if (desplazamientoX > 0)
			desplazamientoX += 1;
	}
	
	/*
	 * La primera condicion compara el eje Y, es decir, la altura del asteroide con la altura de la barra/panel
	 * La segunda condicion se establece para el lado izquierdo de la barra
	 * La tercera condicion se establece para el lado derecho de la barra
	 * La última condicion es para que rebote contra el "techo"
	 */
	
	public void comprobarReboteConBarra() {

		if (lamina.getAsteroidPosY() >= lamina.PANEL_ALTO - lamina.getAltoAst()
			    && (lamina.getAsteroidPosX() + lamina.getAnchoAst() >= (lamina.getBarraPosX()+25)
			    && ((lamina.getAsteroidPosX()) - lamina.getAnchoAst()) <= (lamina.getBarraPosX() + lamina.getAnchoAst())))
		{
			    desplazamientoY = desplazamientoY * -1;
			    rebote +=10;
			    //si el desplazamiento actual es negativo, para aumentar su velocidad le sumamos un negativo
				if (desplazamientoY < 0)
					desplazamientoY += (-1);
				
				//si el desplazamiento actual es positivo, para aumentar su velocidad le sumamos un positivo
				if (desplazamientoY > 0)
					desplazamientoY += 1;	
		}
	}
	

	public void keyTyped(KeyEvent e) {
	
		
		switch(e.getKeyChar()) {
			
			case 'a':   
				if(lamina.getBarraPosX() > 0)
					lamina.pintarBarra(-20,0);
				break;
		 	case 'w':
		 		if(lamina.getBarraPosY() > 0)
		 			lamina.pintarBarra(0,-20);
				break;
				
			case 'd':
				if(lamina.getBarraPosX() < (lamina.PANEL_ANCHO - lamina.getBarraAncho()))
					lamina.pintarBarra(20,0);
				break;
				
			case 's':
				if(lamina.getBarraPosY() < (lamina.PANEL_ALTO - lamina.getBarraAlto()))
					lamina.pintarBarra(0,20);
				break;
	
		}
	}
	

	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	
	
}
