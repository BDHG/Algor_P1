
package Pack1;

public class Arista {
    String id;
    Nodo n1;
    Nodo n2;
    boolean dir;
            
 
    Arista(Nodo n1, Nodo n2, boolean dir){
        String f = "";
    	if(dir == true)
        {
            f = " -> ";
        }
        else
        {
            f = " -- ";
        }
        this.id = 'a' + n1.nid + f +'a' + n2.nid + ";\n";
        this.n1 = n1;
        this.n2 = n2;
    	this.dir = dir;

        
    }
    
}
