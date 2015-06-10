package display;
import java.io.FileInputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;


 

public class Test {
	public static void main(String []args){
		try{
	   WordExtractor extractor = new WordExtractor (
               new FileInputStream ("C:/Users/MJ/Documents/test.doc"));

String tmp = extractor.getText ();

String[] tmp2 = tmp.split(".",4);
for(int i =0;i<tmp2.length;i++){
	System.out.println(tmp2[i]+"\n");
}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

}