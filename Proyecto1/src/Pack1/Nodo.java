
package Pack1;

public class Nodo {
    String nid;
    double indeg;
    double outdeg;
    double x;
    double y;
    float costo;
    String a;
    String padre;
    
    Nodo(int nid){
    	this.nid = String.valueOf(nid);
    	this.indeg = 0;
        this.outdeg = 0;
        this.x = 0;
        this.y = 0;
        this.costo = 99999999999999f;
        this.a = "";
    }
    public void sid(){
        this.indeg = this.indeg + 1;
    }
    public void sod(){
        this.outdeg = this.outdeg + 1;
    }
    public void addCosto(float fl){
        this.costo = fl;
    }
    public void adda(String a){
        this.a = a;
    }
    public double getDeg(){
        double deg = this.outdeg;
        return deg;
    }
    public double getDis(Nodo n2){
        double dis = Math.sqrt((this.x - n2.x)*(this.x - n2.x) + (this.y - n2.y)*(this.y - n2.y));
        return dis;
    }
    
}

