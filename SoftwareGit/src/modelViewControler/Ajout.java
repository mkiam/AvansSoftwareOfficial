package modelViewControler;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;


/**
 * Classe permettant a l'utilsateur d'ajouter un objet et de completer 
 * les differentes informations associees: est une vue du ModelInsertion.
 *
 */
public class Ajout extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	AffichageDuModele aff;
	final JTextField fichier;
	final JFileChooser fc;
	private Controleur controler;
	protected ModelInsertion model;
	protected boolean sourceOfchange;
	String name;


	/**
	 * Constructeur de la Classe
	 * @param a
	 * Une instance de la fentre principale pour visualiser le fichier apres Ajout
	 */

	public Ajout(AffichageDuModele a,ModelInsertion model) {
		this.model=model;

		model.addObserver(this);

		// pour communiquer avec la fenetre principale
		this.aff = a;
		this.controler = new Controleur(a,model);
		//dimension utilisee le plus souvent
		Dimension d = new Dimension(100, 27);

		// pour charger le fichier
		JButton charger = new JButton("Choose file");
		final JPanel pannelCharger = new JPanel();
		fc = new JFileChooser(".");
		charger.setPreferredSize(new Dimension(150, 27));
		fichier = new JTextField("Select your publication please");
		fichier.setPreferredSize(new Dimension(350, 27));
		pannelCharger.add(fichier);
		pannelCharger.add(charger);

		charger.addActionListener(new ActionListenerKarenNumero2(this));

		Container c = this.getContentPane();
		c.setLayout(new GridLayout(8, 1, 9, 9));
		c.add(pannelCharger);
		

		JPanel pa1 = new JPanel();
		final JButton valider = new JButton(" Submit ");
		valider.addActionListener(new ActionListenerKaren(this));

		valider.setPreferredSize(d);
		pa1.add(valider);
		c.add(pa1);

		JPanel pa2 = new JPanel();
		JButton annuler = new JButton(" Cancel ");
		annuler.setPreferredSize(d);
		pa2.add(annuler);
		c.add(pa2);

		// pour annuler l'ajout

		annuler.addActionListener(new ActionListenerKarenNumero3(this));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(540, 400);
		this.setResizable(false);
		this.setVisible(true);

	}

	/**
	 * Classe pour ecouter le bouton annuler
	 *
	 */
	private class ActionListenerKarenNumero3 implements ActionListener {
		private Ajout a;

		/**
		 * Constructeur de la classe
		 * @param a
		 * Une instance de Ajout
		 */
		private ActionListenerKarenNumero3(Ajout ajout) {
			this.a = ajout;
		}

		public void actionPerformed(ActionEvent e) {
			a.dispose();
		}

	}

	/**
	 * Classe pour ecouter le pannel qui s'occupe du chargement du gts
	 */
	private class ActionListenerKarenNumero2 implements ActionListener {

		private Ajout a;

		/**
		 * Constructeur de la classe
		 * @param a
		 * Une instance de Ajout
		 */
		private  ActionListenerKarenNumero2(Ajout ajout) {
			this.a = ajout;
		}

		// choix du fichier

		public void actionPerformed(ActionEvent e) {
			fc.addChoosableFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".pdf");
				}

				/**
				 * @return
				 * Description du fichier a charger
				 */
				@Override
				public String getDescription() {
					return "Files (.pdf)";
				}
			});
			fc.addChoosableFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".doc");
				}

				/**
				 * @return
				 * Description du fichier a charger
				 */
				@Override
				public String getDescription() {
					return "Files (.doc)";
				}
			});
			fc.addChoosableFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".docx");
				}

				/**
				 * @return
				 * Description du fichier a charger
				 */
				@Override
				public String getDescription() {
					return "Files (.docx)";
				}
			});
			fc.addChoosableFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().endsWith(".odt");
				}

				/**
				 * @return
				 * Description du fichier a charger
				 */
				@Override
				public String getDescription() {
					return "Files (.odt)";
				}
			});
			fc.showOpenDialog(a);
			try {
				fichier.setText(fc.getSelectedFile().getAbsolutePath());
			} catch (NullPointerException ex) {
				if (fichier.getText().isEmpty()) {
					fichier.setText("");

				}
			}

		}
	}

	/**
	 * Classe qui permet d'ecouter le bouton annuler
	 *
	 */
	public class ActionListenerKaren implements ActionListener {
		Ajout a;

		/**
		 * Constructeur de la classe
		 * @param a
		 * Une instance de Ajout
		 */
		public ActionListenerKaren(Ajout a) {
			this.a = a;
		}

		public void actionPerformed(ActionEvent e) {

			//Pour les utilisateurs malins

			if (fichier.getText().equals("Select your publication please")|| fichier.getText().isEmpty()) {
				JOptionPane.showMessageDialog(a,
						"Choose an file", "Be careful :)",
						JOptionPane.WARNING_MESSAGE);
			} else {
			
				try {
					String nomCourt = (fc.getSelectedFile().getName()!= null) ? fc.getSelectedFile().getName().substring(0,fc.getSelectedFile().getName().indexOf('.')) : "";
				
					if (fc.getSelectedFile().getName().endsWith(".doc")){
						controler.envoieGts2(fichier.getText(),nomCourt);
						a.dispose();}
					if (fc.getSelectedFile().getName().endsWith(".pdf")){
						
					controler.envoieGts(fichier.getText(),nomCourt);
					a.dispose();}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*//si le conroleur ne dectetecte pas de doublons dans la base 
					if(!controler.alerteDoublon)*/
			
			}
		}
	}



	//methode heritee de Obsrever
	@Override
	public void update(Observable o, Object arg) {

	}


}
