package modelViewControler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Permet de rechercher rapidement un objet, de l'afficher, d'ajouter, modifier et supprimer
 * des mots cles : est une vue du ModelInsertion.
 *
 */

public class Recherche extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	AffichageDuModele frame;
	DefaultListModel<String> listModel_gts;
	JList<String> liste_gts;
	private JTextField tfield;
	private Controleur controler;
	protected ModelInsertion model;


	public Recherche(final AffichageDuModele a,ModelInsertion model) {
		this.model=model;
		this.controler = new Controleur(a,model);
		model.addObserver(this);
		this.frame = a;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// premier contentPane recherche.
		JPanel contentPane1 = new JPanel();
		JLabel recherche = new JLabel("Search : ");
		tfield = new JTextField(8);
		contentPane1.add(recherche);
		contentPane1.add(tfield);
		contentPane1.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2,
				Color.black));
		this.add(contentPane1);
		contentPane1.setPreferredSize((new Dimension(30, 0)));

		// 2eme contentPane resultat.
		final JPanel contentPane2 = new JPanel();
		contentPane2.setPreferredSize((new Dimension(60, 130)));
		JLabel res = new JLabel("Publication List : ");
		listModel_gts = new DefaultListModel<String>();
		liste_gts = new JList<String>(listModel_gts);
		JScrollPane scroll = new JScrollPane(liste_gts);
		scroll.setPreferredSize(new Dimension(300, 150));
		
		contentPane2.add(res);
		contentPane2.add(scroll);
		contentPane2.setBorder(BorderFactory.createMatteBorder(0, 2, 3, 2,Color.black));
		this.add(contentPane2);

		// panel text
		JPanel contentPane5 = new JPanel();
		JLabel label_modifier = new JLabel("<html><b><font size=4>Click to wath: </font></b></html>");
		contentPane5.add(label_modifier);
		contentPane5.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2,
				Color.black));
		// contentPane5.setSize(new Dimension(50, 50));
		this.add(contentPane5);

		// 3eme contentpane liste des mots cles.

		JPanel contentPane3 = new JPanel();

		JButton treeBackward = new JButton("Backward Tree");
		JButton treeForward = new JButton("Forward Tree");
		JButton pdf = new JButton("PDF and references");
		JButton bublebackward= new JButton("Backward Diagram");
		JButton bubleForward = new JButton("Forward Diagram");
		
		contentPane3.add(treeBackward);
		contentPane3.add(treeForward);
		contentPane3.add(pdf);
		contentPane3.add(bublebackward);
		contentPane3.add(bubleForward);
		


		//liste_mots_clef.setEnabled(false);
		treeBackward.setEnabled(false);
		treeForward.setEnabled(false);
		pdf.setEnabled(false);
		bublebackward.setEnabled(false);
		bubleForward.setEnabled(false);

		contentPane3.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2,
				Color.black));
		this.add(contentPane3);		
		contentPane3.setPreferredSize(new Dimension(10, 180));

		mettreAJourListeObjet();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(400, 540));


		// Permet le traitement automatique des la saisie d'un nouveau
		// caractere
		// dans le textfield.





		tfield.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				mettreAJourListeObjet();
			}

			public void removeUpdate(DocumentEvent e) {
				mettreAJourListeObjet();
			}

			public void insertUpdate(DocumentEvent e) {
				mettreAJourListeObjet();
			}
		});

		// Action quand on selectionne un element de la liste des .gts.
		liste_gts.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (e.getValueIsAdjusting()) {
					return;
				}
				liste_gts.getSelectedValue();
				
				//System.out.println((String) liste_gts.getSelectedValue()+"yes");
				if ((String) liste_gts.getSelectedValue() != null) {
					controler.affichageObjet((String) liste_gts.getSelectedValue());

				}


			/*	Connection c = null;
				Statement stmt = null;
				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite: Article.db");
					c.setAutoCommit(false);

					stmt = c.createStatement();
					String requete = "SELECT ARTICLENAME FROM ARTICLE WHERE IDISGENERATIONOF =(SELECT ARTICLE.ID FROM ARTICLE WHERE ARTICLENAME = '"
							+ ((String) liste_gts.getSelectedValue()) + "')";

					ResultSet rs = stmt.executeQuery(requete);

					//listModel_motscles.removeAllElements();
					if(rs==null){
						;
					}

					int i=0;
					while (rs.next()) {
						String tmp = rs.getString("ARTICLENAME");
						listModel_motscles.addElement((tmp));

					}
					liste_mots_clef.setEnabled(true);


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

				}*/
			}


		});
	}











	protected void mettreAJourListeObjet() {
		Connection c = null;
		Statement stmt = null;
		listModel_gts.removeAllElements();
		liste_gts.clearSelection();
		liste_gts.removeAll();

		if (tfield!=null && !tfield.getText().equals("")) {
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
				c.setAutoCommit(false);
				stmt = c.createStatement();
				/*
				 * ARTICLENAME           TEXT, " +  
    		                     " AUTHORS        TEXT, " + 
    		                     " YEAR  
				 */
				String requete = "SELECT DISTINCT ARTICLE.ARTICLENAME FROM ARTICLE WHERE ARTICLENAME LIKE '%"
						+ tfield.getText()
						+ "%' OR AUTHORS LIKE '%"
						+ tfield.getText()
						+ "%' OR YEAR LIKE '%"
						+ tfield.getText() + "%';";
				ResultSet rs = stmt.executeQuery(requete);

				while (rs.next()) {
					listModel_gts.addElement((rs.getString("ARTICLENAME")));

				}
				liste_gts.setEnabled(true);

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

	}











	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}