import java.awt.*;
import java.util.*;
import javax.swing.*;



import java.awt.event.*;

//CLASS NAU ESPAIAL ================================================================================================================================

public class NauEspaial extends javax.swing.JFrame {

	public NauEspaial() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300,
				Short.MAX_VALUE));
		pack();
	}

//MAIN ================================================================================================================================	

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		NauEspaial f = new NauEspaial();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Naus Espaials");
		final int altura = 480;
		final int amplada = 560;
		f.setContentPane(new PanelNau(altura, amplada));
		f.setSize(altura, amplada);
		f.setVisible(true);
	}
}

//CLASS PANELNAU ================================================================================================================================

class PanelNau extends JPanel implements Runnable, KeyListener {
	private int numNaus = 10;
	Vector<Nau> nau = new Vector<Nau>();
	Nau nauPropia;
	Bala bala;
	Bala balaEnemic;
	Vector<Bala> listBalas = new Vector<Bala>();
	int altura;
	int amplada;
	
	//Afegim al Panell la altura i la amplada per poder controlar quan fer desapareixer les bales
	public PanelNau(int altura, int amplada) {
		setBackground(Color.GRAY);
		
		this.altura = altura;
		this.amplada = amplada;
		
		for (int i = 0; i < nau.capacity(); i++) {
			Random rand = new Random();
			int velocitat = (rand.nextInt(3) + 5) * 10;
			int posX = rand.nextInt(100) + 30;
			int posY = rand.nextInt(100) + 30;
			int dX = rand.nextInt(3) + 1;
			int dY = rand.nextInt(3) + 1;
			String nomNau = Integer.toString(i);
			nau.add(new Nau(nomNau, posX, posY, dX, dY, velocitat, altura, amplada, "/images/nau3.png"));
			
		}
		
		addKeyListener(this);
		setFocusable(true);
		
		// Creo la nau propia
		nauPropia = new Nau("NauNostra", 200, 400, 10, 0, 100, altura, amplada, "/images/nau2.png" );
	
		// Creo fil per anar pintant cada 0,1 segons el joc per pantalla
		Thread n = new Thread(this);
		n.start();

	}

	public void run() {
		System.out.println("Inici fil repintar");
		while(true) {
			try { Thread.sleep(10);} catch(Exception e) {} // espero 0,01 segons
			
			if(numNaus != 0){
				repaint();
				try {
					
					comprobarColisiones();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				destruirNaus();
			}else{
				String fiJoc;
				if(numNaus == 0){
					fiJoc="Tu guanyes";
				}else{ 
					fiJoc="Fi";
				}
				
				mostraMissatge(getGraphics(),fiJoc,altura,amplada/2,30);
				
				for (int i = 0; i < nau.size(); i++) {
					nau.elementAt(i).interrupt();
				}
				nauPropia.interrupt();
				
			}
		}                   
	}
	
	private void mostraMissatge(Graphics g, String string,int x, int y, int mida) {

		Font font = new Font("Arial", Font.BOLD, mida);
		FontMetrics fontmetrics = getFontMetrics(font);

		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString(string, (x - fontmetrics.stringWidth(string)) / 2,y);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0; i<nau.size();++i) {
			nau.elementAt(i).pinta(g);
			nauPropia.pinta(g);
		}
		
			for (int j = 0; j < listBalas.size(); j++) {
				listBalas.elementAt(j).pinta(g);
			}
			
	}
	
	//Borrem la nau del vector 
	public void destruirNaus(){
		for (int j = 0; j < nau.size(); j++) {
			if(nau.elementAt(j).isDestruit()){
				
				nau.elementAt(j).interrupt();
				nau.elementAt(j).setPass(false);
				nau.remove(j);
				System.out.println("Borra nau");
				
			}
		}
	} 
	
	public void comprobarColisiones() throws InterruptedException{
		
		Choque colisio = new Choque(2);
		Thread colisioBala = new Thread(colisio);
		colisioBala.start();
		

	}

	// Metodes necesaris per gestionar esdeveniments del teclat
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" +
		// e.getKeyChar());
		if (e.getKeyCode() == 37) {
			nauPropia.esquerra();
		} // System.out.println("a l'esquerra"); }
		if (e.getKeyCode() == 39) { 
			nauPropia.dreta();
		} // System.out.println("a la dreta"); }
		if (e.getKeyCode() == 32) {
			bala = new Bala(60, altura, amplada, nauPropia.getX(), nauPropia.getY(), false, "/images/bala1.png");
			listBalas.add(bala); //tecla espai, dispar normal
			bala.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
//CLASS CHOQUE ===================================================================	
	private class Choque implements Runnable{
		
		private int comproba;
		private boolean pass;
		
		public Choque(int comproba){
			this.comproba= comproba;
		}

		public void run() {
			pass = true;
			
			while(pass){
				if(comproba == 1){
					colisioEnemics();
				}else if(comproba==2){
					try {
						eliminaNausEnemigues();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					try {
						colisioBales();
					} catch (InterruptedException e) {
						e.printStackTrace(); 
					}
				}
			
			pass=false;
			}
		}

		public void eliminaNausEnemigues() throws InterruptedException{
			for (Bala m : listBalas){
				Rectangle rectangle = m.getBounds();
				for (Nau nauEnemiga : nau) {
					Rectangle rectangle2 = nauEnemiga.getBounds();
					if (rectangle.intersects(rectangle2)) {
						nauEnemiga.setDsx(0);
						nauEnemiga.setDsy(0);
						nauEnemiga.setDestruit(true);
						m.colisio(getGraphics());
						numNaus--;
					}
				}
			}
		}

		public void colisioEnemics(){
			Rectangle rectangle3 = nauPropia.getBounds();
			for (Nau nauEnemiga : nau) {
				Rectangle rectangle2 = nauEnemiga.getBounds();
				if (rectangle3.intersects(rectangle2)) {
					nauEnemiga.setDsx(0);
					nauEnemiga.setDsy(0);
					nauEnemiga.interrupt();
					
				}
			}
		}

		public void colisioBales() throws InterruptedException{
			for (int i = 0; i < nau.size(); i++) {
				for (Bala bala1 : nau.elementAt(i).listBalas) {
						Rectangle rectangle5 = bala1.getBounds();
						Rectangle rectangle4 = nauPropia.getBounds();
						
						if (rectangle5.intersects(rectangle4)) {
							bala1.colisio(getGraphics());
						}   
					}
				}
			}
		}

	
}

//CLASS NAU ================================================================================================================================

class Nau extends Thread {

	private String nomNau;
	private int x, y;
	private int dsx, dsy, v;
	private int tx = 10;
	private int ty = 10;
	private String img = "/images/nau3.png";
	private Image image;
	private int altura;
	private int amplada;
	private boolean destruit;
	private boolean pass;
	Vector<Bala> listBalas = new Vector<Bala>();

	public Nau(String nomNau, int x, int y, int dsx, int dsy, int v, int altura, int amplada, String direccioImatge) {
		this.nomNau = nomNau;
		this.x = x;
		this.y = y;
		this.dsx = dsx;
		this.dsy = dsy;
		this.v = v;
		this.img = direccioImatge;
		image = new ImageIcon(Nau.class.getResource(direccioImatge)).getImage();
		this.altura = altura;
		this.amplada = amplada;
		tx = image.getHeight(null);
		ty = image.getWidth(null);
		Thread t = new Thread(this);
		destruit = false;
		t.start();
	}
	
	public int velocitat() {
		return v;
	}

	public void moure() {
		x = x + dsx;
		y = y + dsy;
		// si arriva als marges ...
		if (x >= 450 - tx || x <= tx)
			dsx = -dsx;
		if (y >= 500 - ty || y <= ty)
			dsy = -dsy;
	}

	public void pinta(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.image, x, y, null);
	}

	public void run() {
		while (true) {
			// System.out.println("Movent nau numero " + this.nomNau);
			try {
				Thread.sleep(this.v);
			} catch (Exception e) {
			}
			moure();
		}
	}

	public void esquerra() {
		this.dsx = -10;
	}

	public void dreta() {
		this.dsx = 10;
	}
	//Getters and setters
	public String getNomNau() {
		return nomNau;
	}

	public void setNomNau(String nomNau) {
		this.nomNau = nomNau;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDsx() {
		return dsx;
	}

	public void setDsx(int dsx) {
		this.dsx = dsx;
	}

	public int getDsy() {
		return dsy;
	}

	public void setDsy(int dsy) {
		this.dsy = dsy;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getTx() {
		return tx;
	}

	public void setTx(int tx) {
		this.tx = tx;
	}

	public int getTy() {
		return ty;
	}

	public void setTy(int ty) {
		this.ty = ty;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAmplada() {
		return amplada;
	}

	public void setAmplada(int amplada) {
		this.amplada = amplada;
	}

	public boolean isDestruit() {
		return destruit;
	}

	public void setDestruit(boolean destruit) {
		this.destruit = destruit;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public Vector<Bala> getListBalas() {
		return listBalas;
	}

	public void setListBalas(Vector<Bala> listBalas) {
		this.listBalas = listBalas;
	}
	
	//Crea el marc de colisio
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(null)-15,image.getHeight(null)-15);
	}
	
}

//CLASS BALA ================================================================================================================================

class Bala extends Thread {
	
	private int x;
	private int y;
	private int velocidad;
	private String img;
	private Image image;
	private int altura;
	private int amplada;
	private boolean enemic;
	private boolean destruit;
	private boolean pass;
	
	public Bala(int velocidad, int altura, int amplada, int x, int y, boolean enemic, String direccioImatge) {
		this.velocidad = velocidad;
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.amplada = amplada;
		destruit = false;
		this.enemic = enemic;
		image = new ImageIcon(Nau.class.getResource(direccioImatge)).getImage();
		Thread t = new Thread(this); 
		t.start();
	}

	public void pinta(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(this.image, x, y, null);
	}
	
	public void colisio(Graphics g) throws InterruptedException{
		velocidad=0;
		destruit = true;
	}

	public void run() {
		pass = true;
		while (pass) {
			try {
				Thread.sleep(this.velocidad);
			} catch (Exception e) {
			}
			if (enemic) {
				moureBalaBaix();
			} else {
				moureBalaDalt();
			}
		}
	}

	public void moureBalaBaix (){
		y = y + velocidad;
		
		if(y > altura){
			destruit = true;
		}
	}
	
	public void moureBalaDalt (){
		y = y - velocidad;
		if( y < 5){
			destruit = true;
		}
	}
	
	//GETTERS AND SETTERS
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAmplada() {
		return amplada;
	}

	public void setAmplada(int amplada) {
		this.amplada = amplada;
	}

	public boolean isEnemic() {
		return enemic;
	}

	public void setEnemic(boolean enemic) {
		this.enemic = enemic;
	}

	public boolean isDestruit() {
		return destruit;
	}

	public void setDestruit(boolean destruit) {
		this.destruit = destruit;
	}

	public boolean isPass() {
		return pass;
	}
	//Crea el marc de colisio
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getHeight(null),image.getWidth(null));
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	


//=======================================================================================================================


}
