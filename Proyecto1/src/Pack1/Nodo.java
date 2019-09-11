
package Pack1;

public class Nodo {
    String nid;
    int indeg;
    int outdeg;
    double x;
    double y;
 
    Nodo(int nid){
    	this.nid = String.valueOf(nid);
    	this.indeg = 0;
        this.outdeg = 0;
        this.x = 0;
        this.y = 0;
        
    }
    public void sid(){
        this.indeg = this.indeg + 1;
    }
    public void sod(){
        this.outdeg = this.outdeg + 1;
    }
    public int getDeg(){
        int deg = this.outdeg + this.indeg;
        return deg;
    }
    public double getDis(Nodo n2){
        double dis = Math.sqrt((this.x - n2.x)*(this.x - n2.x) + (this.y - n2.y)*(this.y - n2.y));
        return dis;
    }
    
}

