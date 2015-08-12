package display;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public Test(){
		
	}
	public List<String> separateur(String chemin){
	
		
		List <String>li= new ArrayList<String>();
		String tmp="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(chemin); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				if(ligne.endsWith(". ")||ligne.endsWith(".")){
					li.add(tmp+" "+ligne);
					tmp="";
				}
				else{
				tmp+=ligne+" ";
				}
			
			
			}
		
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return li;
	}
}
