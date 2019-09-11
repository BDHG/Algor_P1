
package Pack1;

public class Arista {
    String id;
    Nodo n1;
    Nodo n2;
    boolean dir;
            
 
    Arista(Nodo n1, Nodo n2, boolean dir){
    	
        this.id = 'a' + n1.nid + " -> " +'a' + n2.nid + ";\n";
        this.n1 = n1;
        this.n2 = n2;
    	this.dir = dir;
        n1.sod();
        n2.sid();       
    }
    
}
