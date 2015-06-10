package modelViewControler;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;



public class Menu extends JFrame {
	private JPanel defaul = new JPanel();
  private JTree arbre; 
 
  public Menu(){
    this.setSize(300, 300);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Les arbres");
    //On invoque la méthode de construction de notre arbre
    buildTree();
    this.setVisible(true);
  }

  private void buildTree(){
    //Création d'une racine
    DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Article mere");

    //Nous allons ajouter des branches et des feuilles à notre racine
    for(int i = 1; i <3; i++){
      DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Generation n°"+i);

     
        //Et une branche en plus ! Une !
        for(int j = 1; j < 5; j++){
          DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode("Fils" +i+"."+j);
          //Cette fois, on ajoute les feuilles
          rep.add(rep2);
        }
     
      //On ajoute la feuille ou la branche à la racine
      racine.add(rep);
    }
    //Nous créons, avec notre hiérarchie, un arbre
    defaul.setLayout(new GridBagLayout());
  arbre = new JTree(racine);
  arbre.setPreferredSize(new Dimension(500,500));
  defaul.add(arbre);
  
  this.add(defaul);

    //Que nous plaçons sur le ContentPane de notre JFrame à l'aide d'un scroll 
    //this.getContentPane().add(new JScrollPane(arbre));
  }
  public static void main(String []args){
	  new Menu();
  }

   
}