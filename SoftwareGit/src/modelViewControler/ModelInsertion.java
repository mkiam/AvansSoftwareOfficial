package modelViewControler;

import display.ReferencesExtractor;
import display.Test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;

/**

 * Classe serveant de modele aux vues de recherhce de fenetre principale et d'ajout
 *
 */
public class ModelInsertion extends Observable {

	Connection c = null;
	Statement stmt = null;
	public  boolean dessine = false;
	Dimension d = new Dimension(100, 27);
	/**
	 * Constructeur de la classe
	 */
	public ModelInsertion(){
		super();

	}
	// Extract text from PDF Document
	static String pdftoText(String fileName) {
		PDFParser parser;
		String parsedText = null;;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		File file = new File(fileName);
		if (!file.isFile()) {
			System.err.println("File " + fileName + " does not exist.");
			return null;
		}
		try {
			parser = new PDFParser(new FileInputStream(file));
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			return null;
		}
		try {
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (Exception e) {
			System.err
			.println("An exception occured in parsing the PDF Document."
					+ e.getMessage());
		} finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return parsedText;
	}
	@SuppressWarnings("resource")
	public String wordToTest(String path){

		WordExtractor extractor = null;
		try {
			extractor = new WordExtractor (
					new FileInputStream (path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return extractor.getText ();



	}



	/**
	 * pour inserer l'objet dans la base
	 * @param fichier
	 *  pour recuperer le fichier
	 *  
	 * @param nomObjet
	 *  nom de l'objet
	 *  
	 * @param auteur
	 *  auteur de l'objet
	 * @throws IOException 
	 */
	public void insertion(String chemin, AffichageDuModele f,String publication) throws IOException{


		System.out.println(publication);
		String article = pdftoText(chemin);
		String references;
		
		ReferencesExtractor extractor = new ReferencesExtractor(f);


		references = extractor.getReferences(article);
		
		JOptionPane pane = new JOptionPane(
				"Are you agree with these references?"+
						references);


		
		final JDialog dialog = new JDialog();
		dialog.setLayout(new BorderLayout());

	

		dialog.setLocation(395, 10);
		JScrollPane scroll = new JScrollPane(pane); //scroll pane code added


		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		yes.setSize(d);
		no.setSize(d);
		JPanel pa1 = new JPanel();
		pa1.setLayout(new GridLayout(1,2));
		pa1.add(yes);
		pa1.add(no);



		dialog.getContentPane().add(scroll,BorderLayout.CENTER);
		dialog.add(pa1, BorderLayout.SOUTH);
		dialog.setSize(800,800);

		dialog.setVisible(true);
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				dialog.dispose();
				
				try{
					int articleMere=0;
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(new File(f.getChemin()+"/"
							+"pdf"+ publication + ".txt")));
					// normalement si le fichier n'existe pas, il est crée à la racine du projet
					writer.write(String.valueOf(article));

					writer.close();

					BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File(f.getChemin()+"/"
							+ publication + ".txt")));
					// normalement si le fichier n'existe pas, il est crée à la racine du projet
					writer2.write(String.valueOf(references));

					writer2.close();


					List <String>ref;
					String sql1;
					String sql2;
					String sql3;
					try {
						Class.forName("org.sqlite.JDBC");
						c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
						c.setAutoCommit(false);
						stmt = c.createStatement();
						sql3 ="SELECT ARTICLE.ID FROM ARTICLE WHERE ARTICLENAME LIKE '%"
								+ publication + "%';";
						ResultSet rs1=stmt.executeQuery(sql3);

						if(rs1.next()){
							articleMere =rs1.getInt("ID");
						}else{




							sql1 ="INSERT INTO ARTICLE (ARTICLENAME) "
									+ "VALUES ('"
									+ publication
									+  "' );";
							stmt.executeUpdate(sql1);
							sql2 = "SELECT ARTICLE.ID FROM ARTICLE WHERE ARTICLENAME = '"
									+ publication + "'";
							ResultSet rs = stmt.executeQuery(sql2);
							while (rs.next()) {
								articleMere =rs.getInt("ID");

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

					//On separe les references pour stocker chacune d'entre-elles dans une list
					Test test = new  Test();
					ref= test.separateur(f.getChemin()+"/"
							+ publication + ".txt");
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
					c.setAutoCommit(false);
					stmt = c.createStatement();
					

					for(int i = 0; i<ref.size();i++){

						String sql = "INSERT INTO ARTICLE (ARTICLENAME,IDISGENERATIONOF) "
								+ "VALUES ('"
								+ref.get(i)
								+ "', '"
								+ articleMere
								+  "' );";
						stmt.executeUpdate(sql);
						
					}
					
					stmt.close();
					c.commit();
					c.close();

					//this.setChanged();
					//this.notifyObservers(articleName);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});


	}
	public void insertion2(String text, AffichageDuModele a, String text2) throws IOException {
		String article = wordToTest(text);
		System.out.println(article);
		String references;
		
		ReferencesExtractor extractor = new ReferencesExtractor(a);


		references = extractor.getReferences(article);
		JOptionPane pane = new JOptionPane(
				"Are you agree with these references?"+
						references);



		final JDialog dialog = new JDialog();
		dialog.setLayout(new BorderLayout());

		

		dialog.setLocation(395, 10);
		JScrollPane scroll = new JScrollPane(pane); 


		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		yes.setSize(d);
		no.setSize(d);
		JPanel pa1 = new JPanel();
		pa1.setLayout(new GridLayout(1,2));
		pa1.add(yes);
		pa1.add(no);



		dialog.getContentPane().add(scroll,BorderLayout.CENTER);
		dialog.add(pa1, BorderLayout.SOUTH);
		dialog.setSize(800,800);

		dialog.setVisible(true);
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dialog.dispose();
				try{
					int articleMere=0;
					


					BufferedWriter writer = new BufferedWriter(new FileWriter(new File(a.getChemin()+"/"
							+"pdf"+ text2 + ".txt")));
					// normalement si le fichier n'existe pas, il est crée à la racine du projet
					writer.write(String.valueOf(article));

					writer.close();

					BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File(a.getChemin()+"/"
							+ text2 + ".txt")));
					// normalement si le fichier n'existe pas, il est crée à la racine du projet
					writer2.write(String.valueOf(references));

					writer2.close();


					List <String>ref;
					String sql1;
					String sql2;
					String sql3;
					try {
						Class.forName("org.sqlite.JDBC");
						c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
						c.setAutoCommit(false);
						stmt = c.createStatement();
						sql3 ="SELECT ARTICLE.ID FROM ARTICLE WHERE ARTICLENAME LIKE '%"
								+ text2 + "%';";
						ResultSet rs1=stmt.executeQuery(sql3);

						if(rs1.next()){
							articleMere =rs1.getInt("ID");
							System.out.println(articleMere);
						}else{




							sql1 ="INSERT INTO ARTICLE (ARTICLENAME) "
									+ "VALUES ('"
									+ text2
									+  "' );";
							stmt.executeUpdate(sql1);
							sql2 = "SELECT ARTICLE.ID FROM ARTICLE WHERE ARTICLENAME = '"
									+ text2 + "'";
							ResultSet rs = stmt.executeQuery(sql2);
							while (rs.next()) {
								articleMere =rs.getInt("ID");

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

					//On separe les references pour stocker chacune d'entre-elles dans une list
					Test test= new  Test();
					ref= test.separateur(a.getChemin()+"/"
							+ text2 + ".txt");
					
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
					c.setAutoCommit(false);
					stmt = c.createStatement();
					for(int i = 0; i<ref.size();i++){
					

						String sql = "INSERT INTO ARTICLE (ARTICLENAME,IDISGENERATIONOF) "
								+ "VALUES ('"
								+ref.get(i)
								+ "', '"
								+ articleMere
								+  "' );";
						stmt.executeUpdate(sql);
						
					}
					stmt.close();
					c.commit();
					c.close();

					

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});


	}

	public void appelFenetrePrincipale(String objet){
		dessine=true;
		this.setChanged();
		this.notifyObservers(objet);

	}

}
