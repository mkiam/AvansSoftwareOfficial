package display;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class ReferencesExtractor {
	
	
	public ReferencesExtractor(){
		
	}
	
	public String getReferences(String article) throws IOException{
		String references="";
		String token="";
		boolean check = false;
		boolean separator = false;
		int j=0;
		for (int i=0; i<article.length(); ++i){
			if (article.charAt(i)==' '){
				token = article.substring(j, i);
				j=i;
				separator = true;
			}
			if (check && separator){
				references = references  +token;
			}
			if (token.contains("REFERENCES")){
				check = true;
			}
			separator = false;
		}
		references = references+"[";
		return references;
	}
	
	public String getTitle(String article){
		String title="";
		char c=' ';
		int i =0;
		while (c!= '\n'){
			c = article.charAt(i);
			title = title+c;
			++i;
		}
		return title;
	}
	
	public ArrayList<String> separateReferences(String references){
		ArrayList<String> tab = new ArrayList<String>();
		int i=0;
		char c=' ';
		String token = "";
		while (i<references.length()){
			c = references.charAt(i);
			if (c!='['){
				token = token  +c;
			} else {
				//System.out.println(token);
				tab.add(token);
				token = "";
			}
			++i;
		}
		return tab;
		
	}
	
}