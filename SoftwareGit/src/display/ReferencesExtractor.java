package display;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import modelViewControler.AffichageDuModele;


public class ReferencesExtractor {
	
	private AffichageDuModele adm;
	
	public ReferencesExtractor(AffichageDuModele adm){
		this.adm = adm;
	}
	
	public String getReferences(String article) throws IOException{
		String references="";
		String token="";
		int idxref = 0;
		if (article.lastIndexOf("REFERENCES")!=-1 ){
			idxref = article.lastIndexOf("REFERENCES");
		}
		if (article.lastIndexOf("references")!=-1 && article.lastIndexOf("references")>idxref){
			idxref = article.lastIndexOf("references");
		}
		if (article.lastIndexOf("References")!=-1 && article.lastIndexOf("References")>idxref){
			idxref = article.lastIndexOf("References");
		}
		
		boolean check = false;
		boolean separator = false;
		int j=0;
		for (int i=0; i<article.length(); i++){
			if (article.charAt(i)==' '){
				token = article.substring(j, i);
				j=i;
				separator = true;
			}
			if (check && separator){
				references = references + token;
			}
			if (token.contains(article.substring(idxref, idxref+10))){
		
				check = true;
			}
			separator = false;
		}
		//references = references+"[";
		return references;
	}
	
	public String getTitle(String article){
		String title="";
		char c=' ';
		int i =0;
		while (c!= '\n'){
			c = article.charAt(i);
			title = title+c;
			i++;
		}
		return title;
	}
	
	public ArrayList<String> separateReferences( String name) throws IOException{
		ArrayList<String> tab = new ArrayList<String>();
		boolean separator = false;
		String reference="";
		int i=0;
		char c=' ';
		String token = "";
		int j=0;
		
		/*FileInputStream in = null;
		try {
			//tente d'ouvrir en mode lecture
			in = new FileInputStream(adm.getChemin()+"/"
					+ name + ".txt");
			// Lecture par segment de 0.5Mo
			byte buffer[] = new byte[512*1024];
			int nbLecture = 0;
			while( (nbLecture = in.read(buffer)) != -1  ) {
				if (in.read(buffer)==0){
					tab.add(reference);
					reference ="";
				}
				reference += (new String(buffer, 0, nbLecture)+"\n");
			}
		} catch (IOException ex) {

		} finally{
			//tente de fermer
			try {
				in.close();
			} catch (IOException ex) {

			}
		}*/
		
		FileReader fr = new FileReader(adm.getChemin()+"/"
				+ name + ".txt");
        BufferedReader br = new BufferedReader(fr);

        for (String line = br.readLine(); line != null; line = br.readLine()) {
        	if (line.equals(" ") || line.equals("") ){
        		tab.add(reference);
        		System.out.println(reference);
        		reference = "";
        	}
        	//System.out.println(line);
            reference += line;
        }

        br.close();
        fr.close();
		
		/*while (i<references.length()){
			if (references.charAt(i)==' '){
				token = references.substring(j, i);
				j=i;
				separator = true;
			}
			if (separator){
				reference += token;
				System.out.println(token);
			}
			if (token.contains("[a-zA-Z]") && separator){
				System.out.println("dans la boucle");
				System.out.println(reference);
				tab.add(reference);
				reference = "";
				token = "";
			} 
			separator = false;
			i++;
		}*/
		
		
		return tab;
		
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	private AffichageDuModele AffichageDuModele() {
		// TODO Auto-generated method stub
		return null;
	}

	/*public static void main(String[] args) throws IOException{
		PDFTextParser parser = new PDFTextParser();
		String article = parser.pdftoText("C:/Users/YOANN/Documents/1007_JTAHthematic_synthesis.pdf");
		//System.out.println(article);
		ReferencesExtractor extractor = new ReferencesExtractor();
		String references = extractor.getReferences(article);
		//System.out.println(references);
		Reference ref = new Reference();
		ArrayList<String> tab = new ArrayList<String>();
		for (int i=0; i<extractor.separateReferences("1007_JTAHthematic_synthesis").size(); i++){
			ref.extractAttributes(extractor.separateReferences("test").get(i));
			//System.out.println(extractor.separateReferences(references, "1007_JTAHthematic_synthesis").get(i));
			System.out.println("author= "+ref.getAuthor());
			System.out.println("name= "+ref.getName());
			System.out.println("year= "+ref.getYear());
		}
		System.out.println(extractor.separateReferences("1007_JTAHthematic_synthesis").size());
		for (int i=0; i<tab.size(); i++){
			System.out.println(tab.get(i));
		}
	}*/
	
}
