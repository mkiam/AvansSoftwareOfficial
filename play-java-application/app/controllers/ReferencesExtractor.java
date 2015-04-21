import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


public class ReferencesExtractor {
	
	
	public ReferencesExtractor(){
		
	}
	
	public String getReferences(String article) throws IOException{
		String references="";
		String token="";
		boolean check = false;
		boolean separator = false;
		int j=0;
		for (int i=0; i<article.length(); i++){
			if (article.charAt(i)==' '){
				token = article.substring(j, i);
				j=i;
				separator = true;
			}
			if (token.contains("REFERENCES")){
				check = true;
			}
			if (check && separator){
				references = references + token;
			}
			separator = false;
		}
		
		return references;
	}
	
	
}
