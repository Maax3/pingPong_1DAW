package pinpong;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class PongJPanel extends JPanel {

	public final int PANEL_ALTO = 650; //height
	public final int PANEL_ANCHO = 750; //width
	public  Image barra = new ImageIcon ("img/barra.png").getImage();
	private Image fondo = new ImageIcon("img/fondo.png").getImage();
	private Image asteroide = new ImageIcon("img/asteroide.png").getImage();
	private int posX = 230; //barra 
	private int posY = 570; //barra
	private int asteroidPosX = 300;
	private int asteroidPosY = 0;
	
	public PongJPanel () {
		this.setLayout(null);
		this.setSize(PANEL_ANCHO, PANEL_ALTO);
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(fondo, 0, 0, null);
		g.drawImage(barra, posX, posY,null);
		g.drawImage(asteroide, asteroidPosX, asteroidPosY,null);
		pintarScore(g);
		pintarTopScore(g);
	
	}
	
	public void pintarScore (Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Sans Serif",Font.BOLD,22));
		g.drawString("Score: "+Pong.getRebote(), 20, 30);
	}
	
	public static void pintarTopScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Sans Serif",Font.BOLD,22));
		g.drawString("Top score: "+Pong.scoreMaximo(), 550, 30);
	}
	
	public void pintarBarra (int posX,int posY) {
		this.posX += posX;
		this.posY += posY;
		this.repaint();
	}
	
	/**
	 *  Repinta la posicion del asteroide con las coordenadas pasadas por parametro
	 */
	
	public void pintarAsteroide (int posX,int posY) {
		asteroidPosX += posX;
		asteroidPosY += posY;
		this.repaint();
	}
	
	public void reiniciarPosicion() {
		posX = 230; //barra 
		posY = 570; //barra
		asteroidPosX = 300;
		asteroidPosY = 0;
	}
	
	/**
	 * @return Posicion de la barra en el EJE X
	 */
	public int getBarraPosX () {
		return posX;
	}
	
	/**
	 * @return Posicion de la barra en el EJE Y
	 */
	public int getBarraPosY() {
		return posY;
	}
	
	/**
	 * @return Devuelve la anchura de la barra
	 */
	public int getBarraAncho() {
		return barra.getWidth(null);
	}
	
	/**
	 * @return Devuelve la altura de la barra
	 */
	public int getBarraAlto() {
		return barra.getHeight(null);
	}
	
	/**
	 * @return Devuelve la anchura del asteroide
	 */
	
	public int getAnchoAst() {
		return asteroide.getWidth(null);
	}
	
	/**
	 * @return Devuelve la altura del asteroide
	 */
	public int getAltoAst() {
		return asteroide.getHeight(null)+50;
	}
	
	/**
	 * @return Posicion del asteroide en el EJE X
	 */
	public int getAsteroidPosX () {
		return asteroidPosX;
	}
	
	/**
	 * @return Posicion del asteroide en el EJE Y
	 */
	public int getAsteroidPosY() {
		return asteroidPosY;
	}
	
}
