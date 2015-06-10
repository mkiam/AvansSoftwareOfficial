package modelViewControler;
import display.PanelAffichage;
import display.Welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.Observer;

/**
 * Fenetre principale du programme : est une vue du ModelInsertion.
 * 
 */
@SuppressWarnings("serial")
public class AffichageDuModele extends JFrame implements Observer {
	private JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

	private JMenuBar jmenubar;
	public JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	private boolean recherche = false;
	private boolean dessine = false;
	private String cheminGlobal;

	private ImageIcon closeXIcon = new ImageIcon(AffichageDuModele.class.getResource("/imageMenu/close.gif")
			/*"./ressources/imageMenu/close.gif"*/);
	private Dimension closeButtonSize = new Dimension(
			closeXIcon.getIconWidth() + 2, closeXIcon.getIconHeight() + 2);

	protected Controleur controler;
	private ModelInsertion model;
	private Recherche r;

	/**
	 * Constructeur de AffichageDuModele
	 */
	public AffichageDuModele() {
		model = new ModelInsertion();
		controler = new Controleur(this, model);
		model.addObserver(this);
		r = new Recherche(this, this.model);
		jmenubar = new JMenuBar();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		int X = 900;
		int Y = 700;
		this.setSize(X, Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setBackground(Color.WHITE);
		this.setTitle("Avans Citation Map Tool");
		JMenu j1 = new JMenu("File");
		JMenu j2 = new JMenu("Options");
		JMenu j3 = new JMenu("?");
		JMenuItem ajout = new JMenuItem("add an article");
		JMenuItem recherche = new JMenuItem("Search an article");
		JMenuItem fermer = new JMenuItem("Close");
		JMenuItem aide = new JMenuItem("Help");
		JMenuItem aPropos = new JMenuItem(" About this tool");
		JMenu resolution = new JMenu("Resolution");
		JRadioButtonMenuItem full = new JRadioButtonMenuItem("Full screen");
		JRadioButtonMenuItem full2 = new JRadioButtonMenuItem("900*700", true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(full);
		bg.add(full2);
		final Welcome welcome = new Welcome();
		JPanel tab = new JPanel();
		tab.setOpaque(false);

		JLabel tabLabel = new JLabel("Welkom");

		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int closeTabNumber = tabbedPane.indexOfComponent(welcome);
				tabbedPane.removeTabAt(closeTabNumber);
			}
		});

		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);
		tabbedPane.addTab("Welkom", welcome);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);



		// raccourci clavier
		ajout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				KeyEvent.CTRL_MASK));
		recherche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				KeyEvent.CTRL_MASK));
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				KeyEvent.ALT_DOWN_MASK));
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));


		j1.add(ajout);
		j1.add(recherche);
		j1.add(fermer);
		j2.add(resolution);
		j3.add(aide);
		j3.add(aPropos);
		resolution.add(full);
		resolution.add(full2);
		jmenubar.add(j1);
		jmenubar.add(j2);
		jmenubar.add(j3);
		this.setJMenuBar(jmenubar);

		this.add(tabbedPane, BorderLayout.CENTER);

		sp.add(tabbedPane, JSplitPane.RIGHT);
		sp.setVisible(true);
		this.add(sp);

		// ouvre l'ajout d'objet
		ajout.addActionListener(new ActionListenerAjout(this));

		// ferme l'application
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Ajout de l'aide
		aide.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				String mess = "..................................\n";
				mess += "...........................................\n";
				mess += "............................................\n";
				mess += "..............................................";
				mess += "\n___________________________________________________\n\n";
				mess += "................. -> .....................\n";
				mess += "..................-> .......................\n";
				mess += "................... -> ....................... \n";
				jop.showMessageDialog(null, mess, "Help",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// A Propos
		aPropos.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				String mess = ".................................\n";
				mess += "........................................\n";
				mess += ".........................................\n";
				mess += "_____________________________________________________________________\n\n";
				mess += "Made by Yoann Lameire, Karen Migan\n";
				jop.showMessageDialog(null, mess, "A propos",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});



		// ouvre la recherche d'objet

		recherche.addActionListener(new RechercheListener(this));

		// si case cocher alors plein ecran
		full.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dimension tailleEcran = Toolkit.getDefaultToolkit()
						.getScreenSize();
				int Y = (int) tailleEcran.getHeight();
				int X = (int) tailleEcran.getWidth();
				AffichageDuModele.this.setSize(X, Y);
				AffichageDuModele.this.setLocationRelativeTo(null);
			}
		});

		// si case cocher alors taille fenetre = 900/700
		full2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AffichageDuModele.this.setSize(900, 700);

			}
		});



		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setVisible(true);

	}

	/**
	 * Permet de faire un nouvel onglet.
	 * 
	 * @param name
	 *            nom de l'objet .gts a afficher (sans l'extension)
	 */
	public void nouvelOnglet(final String name) {
		ref(name);
		pdf(name);


	}
	protected void ref(String name){
		final PanelAffichage p = new PanelAffichage(this, name);
		p.afficheRef();
		JPanel tab = new JPanel();
		tab.setOpaque(false);

		JLabel tabLabel = new JLabel(name);

		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int closeTabNumber = tabbedPane.indexOfComponent(p);
				tabbedPane.removeTabAt(closeTabNumber);
			}
		});

		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);
		tabbedPane.addTab(name, p);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
	}


	protected void pdf(String name){
		final PanelAffichage p1 = new PanelAffichage(this, name);
		p1.affichePDF();
		JPanel tab1 = new JPanel();
		tab1.setOpaque(false);

		JLabel tabLabel1 = new JLabel(name+"pdf");

		JButton tabCloseButton1 = new JButton(closeXIcon);
		tabCloseButton1.setPreferredSize(closeButtonSize);
		tabCloseButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				int closeTabNumber1 = tabbedPane.indexOfComponent(p1);
				tabbedPane.removeTabAt(closeTabNumber1);
			}
		});

		tab1.add(tabLabel1, BorderLayout.WEST);
		tab1.add(tabCloseButton1, BorderLayout.EAST);
		tabbedPane.addTab(name, p1);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab1);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

	}
	public void dessine(String j){
		final PanelAffichage p2 = new PanelAffichage(this, j);
		p2.dessin(j);
		JPanel tab2 = new JPanel();
		tab2.setOpaque(false);

		JLabel tabLabel2 = new JLabel(j);

		JButton tabCloseButton2 = new JButton(closeXIcon);
		tabCloseButton2.setPreferredSize(closeButtonSize);
		tabCloseButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				int closeTabNumber2 = tabbedPane.indexOfComponent(p2);
				tabbedPane.removeTabAt(closeTabNumber2);
			}
		});

		tab2.add(tabLabel2, BorderLayout.WEST);
		tab2.add(tabCloseButton2, BorderLayout.EAST);
		tabbedPane.addTab(j, p2);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab2);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

	}
	public void dessine2(String j){
		final PanelAffichage p3 = new PanelAffichage(this, j);
		p3.dessin1(j);
		JPanel tab3 = new JPanel();
		tab3.setOpaque(false);

		JLabel tabLabel3 = new JLabel(j);

		JButton tabCloseButton3 = new JButton(closeXIcon);
		tabCloseButton3.setPreferredSize(closeButtonSize);
		tabCloseButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				int closeTabNumber3 = tabbedPane.indexOfComponent(p3);
				tabbedPane.removeTabAt(closeTabNumber3);
			}
		});

		tab3.add(tabLabel3, BorderLayout.WEST);
		tab3.add(tabCloseButton3, BorderLayout.EAST);
		tabbedPane.addTab(j, p3);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab3);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

	}
	public void dessine3(String j){
		final PanelAffichage p4 = new PanelAffichage(this, j);
		p4.dessinBulle(j);
		JPanel tab4 = new JPanel();
		tab4.setOpaque(false);

		JLabel tabLabel4 = new JLabel(j);

		JButton tabCloseButton4 = new JButton(closeXIcon);
		tabCloseButton4.setPreferredSize(closeButtonSize);
		tabCloseButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				int closeTabNumber4 = tabbedPane.indexOfComponent(p4);
				tabbedPane.removeTabAt(closeTabNumber4);
			}
		});

		tab4.add(tabLabel4, BorderLayout.WEST);
		tab4.add(tabCloseButton4, BorderLayout.EAST);
		tabbedPane.addTab(j, p4);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab4);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

	}

	/**
	 * Listener qui permet d'enlever d'afficher la fenetre recherche.
	 * 
	 */
	public class RechercheListener implements ActionListener {
		AffichageDuModele a;

		public RechercheListener(AffichageDuModele a) {
			this.a = a;
		}

		public void actionPerformed(ActionEvent arg0) {
			if (recherche) {
				sp.remove(r);
				recherche = false;
			} else {
				sp.add(r, JSplitPane.LEFT);
				recherche = true;
			}
		}
	}

	/**
	 * Permet de connaitre la hauteur de de l'onglet courant.
	 * 
	 * @return hauteur de de l'onglet courant.
	 */
	public double getHauteur() {
		return tabbedPane.getSize().getHeight();
	}

	/**
	 * Permet de connaitre la largeur de de l'onglet courant.
	 * 
	 * @return largeur de de l'onglet courant.
	 */
	public double getLargeur() {
		return tabbedPane.getSize().getWidth();
	}

	/**
	 * Listener qui permet de faire appel a la fenetre d'ajout
	 * 
	 */
	public class ActionListenerAjout implements ActionListener {

		private AffichageDuModele a;

		public ActionListenerAjout(AffichageDuModele a) {
			this.a = a;
		}

		public void actionPerformed(ActionEvent e) {
			new Ajout(a, a.model);
		}

	}

	/**
	 * Listener qui permet de faire appel a la fenetre de modification
	 * 
	 */


	/**
	 * Permet de mettre a jour la liste d'objets de la classe recherche
	 */
	public void mettreAJourRecherche() {
		r.mettreAJourListeObjet();
	}




	/**
	 * Permet de changer le nom de l'onglet cournt
	 * 
	 * @param name
	 *            nouveau nom
	 */
	public void changerNomOngletCourant(String name) {
		tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
		nouvelOnglet(name);
	}
	public String getChemin(){
		return cheminGlobal;
	}
	public void setChemin( String nouveau){
		cheminGlobal = nouveau;
	}



	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql =/*"DELETE FROM ARTICLE";*/"CREATE TABLE IF NOT EXISTS ARTICLE " +
					"(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
					" ARTICLENAME           TEXT, " +  
					" AUTHORS        TEXT, " + 
					" YEAR           TEXT, " + 
					"IDISGENERATIONOF  INTEGER, "+
					" PATHREFERENCES          TEXT, " +
					" PATHPDF           TEXT, "+
					" FOREIGN KEY(IDISGENERATIONOF) REFERENCES ARTICLE(ID))";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();

			String sql2 = "SELECT * FROM ARTICLE WHERE ARTICLENAME = '"
					+"defaultLocation"+ "'";
			ResultSet rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				AffichageDuModele aff = new AffichageDuModele();
				aff.setChemin(rs.getString("PATHREFERENCES"));
			}else{

				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );


				if( fc.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION )
				{
					String defaultPath =  fc.getSelectedFile().getAbsolutePath();
					stmt = c.createStatement();
					String sql1 ="INSERT INTO ARTICLE (ARTICLENAME, PATHREFERENCES) "
							+ "VALUES ('"
							+ "defaultLocation"
							+ "', '"
							+ defaultPath
							+  "' );";
					stmt.executeUpdate(sql1);
					AffichageDuModele aff = new AffichageDuModele();
					aff.setChemin(defaultPath);

				}
			}

		} catch (Exception e1) {
			System.err.println(e1.getClass().getName() + ": "
					+ e1.getMessage());
			System.exit(0);
		} finally {
			try {
				stmt.close();
				c.commit();
				c.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}








	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if(this.model.dessine){
			dessine((String)arg);
			dessine2((String)arg);
			dessine3((String)arg);
			nouvelOnglet((String) arg);
		}

		

	}

	public boolean isDessine() {
		return dessine;
	}

	public void setDessine(boolean dessine) {
		this.dessine = dessine;
	}
}
