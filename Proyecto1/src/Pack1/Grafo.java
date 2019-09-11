package Pack1;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Grafo {
    Map<String,Nodo> nodos ;
    Map<String,Arista> aris; 
    Grafo(){
    this.nodos = new HashMap<String,Nodo>();
    this.aris = new HashMap<String,Arista>();
    }
    public void toGiphi(String nom){
        String cadena = "digraph " + nom + " {\n";
        for(Map.Entry<String, Arista> entry : this.aris.entrySet()) {
            String key = entry.getKey();
            cadena = cadena + key;
        }
        cadena = cadena + "}\n";
        System.out.println(cadena);
        FileOutputStream fop = null;
        File archivo;
        

        try {

                archivo = new File("C:\\Users\\Hp\\Desktop\\" + nom + ".gv");
                fop = new FileOutputStream(archivo);

                if (!archivo.exists()) {
                        archivo.createNewFile();
                }

                byte[] contentInBytes = cadena.getBytes();

                fop.write(contentInBytes);
                fop.flush();
                fop.close();

        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                try {
                        if (fop != null) {
                                fop.close();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
    } 
    
    public static Grafo genErdosRenyi(int n, int m, boolean dir, boolean auto)
    {
        Grafo G = new Grafo();
        for(int ni=0;ni<n;ni++)

        {
            Nodo nodo = new Nodo(ni+1);
            G.nodos.put(nodo.nid, nodo);
            
        }
        
        double pc = .01;
        while (G.aris.size()<m)
        {
            for(int ni=0;ni<n;ni++)
            {
                for(int nj=0;nj<n;nj++)
                {
                    if(auto==true || (auto==false && ni!=nj))
                    {
                        double pr = Math.random();
                        if(pc>pr && G.aris.size()<m)
                        {
                            Nodo n1 = G.nodos.get("" + (ni+1));
                            Nodo n2 = G.nodos.get("" + (nj+1));
                                    
                            Arista aris = new Arista(n1,n2,dir);
                            G.aris.put(aris.id, aris);
                        }
                    }
                }
                
            }
        }
        
        return G;
    }
    
    
    public static Grafo genGilbert(int n, double pc, boolean dir, boolean auto)
    {
        Grafo G = new Grafo();
        for(int ni=0;ni<n;ni++)

        {
            Nodo nodo = new Nodo(ni+1);
            G.nodos.put(nodo.nid, nodo);
            
        }
        
        
        for(int ni=0;ni<n;ni++)
        {
            for(int nj=0;nj<n;nj++)
            {
                if(auto==true || (auto==false && ni!=nj))
                {
                    double pr = Math.random();
                    if(pc>pr)
                    {
                        Nodo n1 = G.nodos.get("" + (ni+1));
                        Nodo n2 = G.nodos.get("" + (nj+1));

                        Arista aris = new Arista(n1,n2,dir);
                        G.aris.put(aris.id, aris);
                    }
                }
            }

        }
    
        return G;
    }
    
      public static Grafo genGeografico(int n, double r, boolean dir, boolean auto)
    {
        Grafo G = new Grafo();
        for(int ni=0;ni<n;ni++)

        {
            Nodo nodo = new Nodo(ni+1);
            double x = Math.random();
            double y = Math.random();
            nodo.x = x;
            nodo.y = y;
            G.nodos.put(nodo.nid, nodo);
        }
        
        
        for(int ni=0;ni<n;ni++)
        {
            for(int nj=0;nj<n;nj++)
            {
                if(auto==true || (auto==false && ni!=nj))
                {
                    Nodo n1 = G.nodos.get("" + (ni+1));
                    Nodo n2 = G.nodos.get("" + (nj+1));
                    double dis = n1.getDis(n2);
                    if(dis<r)
                    {
                        

                        Arista aris = new Arista(n1,n2,dir);
                        G.aris.put(aris.id, aris);
                    }
                }
            }

        }
        return G;
    }
    
        public static Grafo genBarabasiAlbert(int n, double d, boolean dir, boolean auto)
    {
        Grafo G = new Grafo();
        Nodo nodo = new Nodo(1);
        G.nodos.put(nodo.nid, nodo);
        for(int ni=1;ni<n;ni++)

        {
            Nodo n2 = new Nodo(ni+1);
            G.nodos.put(n2.nid, n2);
            int b = G.nodos.size() -1;
        
            for(int nj=0;nj<=b;nj++)
            {
                if(auto==true || (auto==false && (ni+1)!=nj))
                {
                    
                    Nodo n1 = G.nodos.get("" + (ni-nj+1));
                    double pn = 1- n2.getDeg()/d;
                    double pba = Math.random();
                    if(pn>pba)
                    {
                        Arista aris = new Arista(n2,n1,dir);
                        G.aris.put(aris.id, aris);
                    }
                }
        
            }
         
        }        
        return G;
    }
    
    
    
    public static void main(String [] args)
    {
       Grafo G = Grafo.genErdosRenyi(30,500,true,true);
       G.toGiphi("ER30n");
       Grafo H = Grafo.genErdosRenyi(100,700,true,true);
       H.toGiphi("ER100n");
       Grafo I = Grafo.genErdosRenyi(500,2000,true,true);
       I.toGiphi("ER500n");           
       Grafo J = Grafo.genGilbert(30,.3,true,true);
       J.toGiphi("Gil30n");
       Grafo K = Grafo.genGilbert(100,.3,true,true);
       K.toGiphi("Gil100n");
       Grafo L = Grafo.genGilbert(500,.3,true,true);
       L.toGiphi("Gil500n");
       Grafo M = Grafo.genGeografico(30,1.1,true,true);
       M.toGiphi("Geo30n");
       Grafo N = Grafo.genGeografico(100,1,true,true);
       N.toGiphi("Geo100n");
       Grafo O = Grafo.genGeografico(500,.3,true,true);
       O.toGiphi("Geo500n");
       Grafo P = Grafo.genBarabasiAlbert(30,5,true,false);
       P.toGiphi("BA30n");
       Grafo Q = Grafo.genBarabasiAlbert(100,10,true,false);
       Q.toGiphi("BA100n");
       Grafo R = Grafo.genBarabasiAlbert(500,100,true,false);
       R.toGiphi("BA500n");
    }
    
}
