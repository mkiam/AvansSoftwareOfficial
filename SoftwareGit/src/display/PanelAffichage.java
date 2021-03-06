package display;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import modelViewControler.AffichageDuModele;

/**
 * Classe qui cree un JPanel dans lequel est dessine l'objet 3D.
 */
@SuppressWarnings("serial")
public class PanelAffichage extends JPanel {
	private JTextArea j;
	private AffichageDuModele fenetrePrincipale;
	private String name;


	/**
	 * Constructeur de la classe PanelAffichage.
	 * 
	 * @param fenetrePrincipale
	 *            fenetre principale du programme
	 * @param name
	 *            nom du fichier .gts sans l'extension
	 * @param musiqueActive
	 *            booleen qui permet de savoir si la musique est active ou non
	 */
	public PanelAffichage(AffichageDuModele fenetrePrincipale, String name
			) {
		this.fenetrePrincipale = fenetrePrincipale;
		this.name = name;
	}
	public void afficheRef(){
		j= new JTextArea();


		FileInputStream in = null;
		try {
			//tente d'ouvrir en mode lecture
			in = new FileInputStream(fenetrePrincipale.getChemin()+"/"
					+ name + ".txt");
			// Lecture par segment de 0.5Mo
			byte buffer[] = new byte[512*1024];
			int nbLecture = 0;
			while( (nbLecture = in.read(buffer)) != -1 ) {
				j.append(new String(buffer, 0, nbLecture)+"\n");
				j.setEditable(false);;
			}
		} catch (IOException ex) {

		} finally{
			//tente de fermer
			try {
				in.close();
			} catch (IOException ex) {

			}
		}




		this.add(j);
		this.setVisible(true);
	}
	public void affichePDF(){
		j= new JTextArea();






		FileInputStream in = null;
		try {

			//tente d'ouvrir en mode lecture
			in = new FileInputStream(fenetrePrincipale.getChemin()+"/"
					+"pdf"+name+ ".txt");
			// Lecture par segment de 0.5Mo
			byte buffer[] = new byte[512*1024];
			int nbLecture = 0;
			while( (nbLecture = in.read(buffer)) != -1 ) {
				j.append(new String(buffer, 0, nbLecture)+"\n");
				j.setEditable(false);


			}
			//scroll pane code added
			j.setSize(new Dimension(800,450));
			JScrollPane scroll = new JScrollPane(j);
			//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			JPanel inter = new JPanel();
			inter.add(scroll);
			this.add(inter);


		} catch (IOException ex) {

		} finally{
			//tente de fermer
			try {
				in.close();
			} catch (IOException ex) {

			}
		}










		//this.setSize(new Dimension(800, 450));

		this.setVisible(true);

	}
	public void dessin(String j){
		JTree arbre; 

		//On invoque la m�thode de construction de notre arbre

		//Cr�ation d'une racine
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode(name);

		//Nous allons ajouter des branches et des feuilles � notre racine
		//	for(int i = 1; i <3; i++){
		DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Generation n�1 forward"/*+i*/);
		Statement stmt = null;

		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			System.out.println(j);
			String requete = "SELECT DISTINCT ARTICLENAME FROM ARTICLE WHERE ID IN (SELECT ARTICLE.IDISGENERATIONOF FROM ARTICLE WHERE ARTICLENAME LIKE '%"
					+ j + "%')";

			ResultSet rs = stmt.executeQuery(requete);





			
			while (rs.next()) {
				//Et une branche en plus ! Une !
				//for(int j1 = 0; j1 < j.size(); j1++){
				DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode(/*"Fils" +i+"."+j1*/rs.getString("ARTICLENAME"));
				//Cette fois, on ajoute les feuilles
				rep.add(rep2);
			}

			//On ajoute la feuille ou la branche � la racine
			racine.add(rep);






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



		//Nous cr�ons, avec notre hi�rarchie, un arbre
		this.setLayout(new GridBagLayout());
		arbre = new JTree(racine);
		arbre.setPreferredSize(new Dimension(500,500));
		this.add(arbre);


		this.setVisible(true);
	}

	public void dessin1(String j){


		JTree arbre; 

		//On invoque la m�thode de construction de notre arbre

		//Cr�ation d'une racine
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode(name);

		//Nous allons ajouter des branches et des feuilles � notre racine
		//	for(int i = 1; i <3; i++){
		DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Generation n�1 backward"/*+i*/);
		Statement stmt = null;

		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String requete = "SELECT DISTINCT ARTICLENAME FROM ARTICLE WHERE IDISGENERATIONOF IN (SELECT ARTICLE.ID FROM ARTICLE WHERE ARTICLENAME LIKE '%"
					+ j + "%')";

			ResultSet rs = stmt.executeQuery(requete);


			if(!rs.next()){
				return;
			}

		
			while (rs.next()) {
				//Et une branche en plus ! Une !
				//for(int j1 = 0; j1 < j.size(); j1++){
				DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode(/*"Fils" +i+"."+j1*/rs.getString("ARTICLENAME"));
				//Cette fois, on ajoute les feuilles
				rep.add(rep2);
			}

			//On ajoute la feuille ou la branche � la racine
			racine.add(rep);






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



		//Nous cr�ons, avec notre hi�rarchie, un arbre
		this.setLayout(new GridBagLayout());
		arbre = new JTree(racine);
		arbre.setPreferredSize(new Dimension(500,500));
		this.add(arbre);


		this.setVisible(true);
	}

	public void dessinBulle(String j){
		

			mxICell a,b;
			final mxGraph graph = new mxGraph();
			Object parent = graph.getDefaultParent();

			graph.getModel().beginUpdate();

			Statement stmt = null;

			Connection con = null;
			try {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
				con.setAutoCommit(false);

				stmt = con.createStatement();
				String requete = "SELECT DISTINCT ARTICLENAME FROM ARTICLE WHERE IDISGENERATIONOF IN (SELECT ARTICLE.ID FROM ARTICLE WHERE ARTICLENAME LIKE '%"
						+j+ "%')";

				ResultSet rs = stmt.executeQuery(requete);


				a = (mxICell) graph.insertVertex(parent, null, j+" "+"backward", 0, 0, 680, 40);
				

				while (rs.next()) {
					String tmp=rs.getString("ARTICLENAME");
					int size= tmp.length()/3;
					b = (mxICell) graph.insertVertex(parent, null,tmp.substring(0,size)+"\n"+tmp.substring(size,tmp.length()), 0, 0, 4*tmp.length(), 100);
					graph.insertEdge(parent, null, "", a, b);
				}


				









			} catch (Exception e1) {
				System.err.println(e1.getClass().getName() + ": "
						+ e1.getMessage());
				System.exit(0);
				return;
			} finally {
				try {
					graph.getModel().endUpdate();
					stmt.close();
					con.commit();
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}


			}








			// define layout
			mxIGraphLayout layout = new mxHierarchicalLayout(graph);
			layout.execute(graph.getDefaultParent());






			mxGraphComponent graphComponent = new mxGraphComponent(graph);
			this.setLayout(new BorderLayout());
			this.add(graphComponent, BorderLayout.CENTER);
			this.setVisible(true);

		}
	}


