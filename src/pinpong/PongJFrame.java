package pinpong;
import javax.swing.*;

@SuppressWarnings("serial")
public class PongJFrame extends JFrame {

	private final int VENTANA_ALTO = 650; //height
	private final int VENTANA_ANCHO = 750; //width
	
	public PongJFrame () {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(VENTANA_ANCHO, VENTANA_ALTO);
		this.setLocationRelativeTo(null);
		this.setTitle("CoheteAPP");
		this.setResizable(false);
	}
}
