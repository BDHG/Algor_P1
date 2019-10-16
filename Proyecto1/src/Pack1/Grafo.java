package Pack1;


import java.util.*;
import java.io.*;

public class Grafo {
    Map<String,Nodo> nodos ;
    Map<String,Arista> aris; 
    Grafo(){
    this.nodos = new HashMap<String,Nodo>();
    this.aris = new HashMap<String,Arista>();
    }
    public void toGiphi(String nom, boolean dir){
        String gr = "graph ";
        if(dir == true)
        {
            gr = "digraph ";
        }
        String cadena = gr + nom + " {\n";
        for(Map.Entry<String, Arista> entry : this.aris.entrySet()) {
            String key = entry.getKey();
            cadena = cadena + key;
        }
        cadena = cadena + "}\n";
        //System.out.println(cadena);
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
        
        int as = 0;
        if(dir == true)
        {
            as = G.aris.size();
        }
        else
        {
            as = G.aris.size()/2;
        }
        while (as<m)
        {
            for(int ni=0;ni<n;ni++)
            {
                for(int nj=0;nj<n;nj++)
                {
                    if(auto==true || (auto==false && ni!=nj))
                    {
                        double pr = Math.random();
                        if(dir == true)
                        {
                            as = G.aris.size();
                        }
                        else
                        {
                            as = G.aris.size()/2;
                        }
                        //System.out.println(as);
                        //System.out.println(pr);
                        if(pc>pr && as<m)
                        {
                            Nodo n1 = G.nodos.get("" + (ni+1));
                            Nodo n2 = G.nodos.get("" + (nj+1));
                                    
                            Arista aris = new Arista(n1,n2,dir);
                            G.aris.put(aris.id, aris);
                            if(dir == false)
                            {
                                Arista aris2 = new Arista(n2,n1,dir);
                                G.aris.put(aris2.id, aris2);
                            }
                        }
                    }
                }
                
            }
            if(dir == true)
            {
                as = G.aris.size();
            }
            else
            {
                as = G.aris.size()/2;
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
                        if(dir == false)
                        {
                            Arista aris2 = new Arista(n2,n1,dir);
                            G.aris.put(aris2.id, aris2);
                        }
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
                        if(dir == false)
                        {
                            Arista aris2 = new Arista(n2,n1,dir);
                            G.aris.put(aris2.id, aris2);
                            
                        }
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
            
            
            Set<Integer> set = new HashSet<>();
            int[] array = new int[ni];
            int ban=0;
            while(set.size()<ni)
            {
                int ii = (int) (Math.random()*ni) +1;
                if(set.contains(ii)==false)
                {
                    set.add(ii);
                    array[ban]=ii;
                    ban++;
                    
                }
                
            }
                
//System.out.println(Arrays.toString(array));
            for(int j=0;j<ni;j++)
            {
                
                int nj = array[j];
                //System.out.println(nj);
                if(auto==true || (auto==false && (ni+1)!=nj))
                {
                    
                    Nodo n1 = G.nodos.get("" + (nj));
                    double di = n2.getDeg()/2;
                    double pn = 1 - di/d;
                    double pba = Math.random();
                    /*
                    System.out.println((ni+1) + "  :  " + n2.getDeg());
                    System.out.println(di);
                    System.out.println(pn);
                    System.out.println(pba);
                    */
                    Arista aris = new Arista(n2,n1,dir);
                    //System.out.println(aris.id);
                    if(pn>pba && (G.aris.containsKey(aris.id) == false))
                    {
                                n2.sod();
                                n1.sid(); 
                        G.aris.put(aris.id, aris);
                        if(dir == false)
                        {
                            
                            Arista aris2 = new Arista(n1,n2,dir);
                            G.aris.put(aris2.id, aris2);
                            
                                n1.sod();
                                n2.sid(); 
                        }
                    }
                }
            }
         
        }        
        return G;
    }
    
    
    public static Grafo BFS(String n, Grafo H, String nombre)
    {
        Grafo A = new Grafo();
        Grafo G = H;
        Set<String> na = new HashSet<String>();
        na.add(n);
        while(na.isEmpty() == false)
        {
            System.out.println("QQQ");
            System.out.println(G.nodos.size());
            for(int i =1 ;i<=G.nodos.size();i++)
            {   
                String sn = String.valueOf(i);
                String sa = 'a' + n + " -- " +'a' + sn + ";\n";
                String sa2 = 'a' + sn + " -- " +'a' + n + ";\n";
                if(G.aris.containsKey(sa))
                {
                    System.out.println("cont"+sa);
                    Arista aaa = G.aris.get(sa);
                    G.aris.remove(sa);
                    G.aris.remove(sa2);
                    if(na.contains(sn) == false)
                    {
                        A.aris.put(aaa.id, aaa);
                        na.add(sn);
                    }
                    
                    
                }
                
            }
            if(na.isEmpty() == false)
            {
                n = na.iterator().next();
                na.remove(n);
            }
            
            System.out.println(G.aris.size());
        }
        System.out.println(A.aris.size());
        A.toGiphi(nombre + "_BFS",false);
        return A;
    }
    
    public static Grafo IDFS(String n, Grafo G, String nombre)
    {
        Grafo A = new Grafo();
        Set<String> np = new HashSet<String>();
        List<String> colve = new ArrayList<String>();
        Map<String,Arista> colar = new HashMap<String,Arista>();
        colve.add(n);
        while(colve.isEmpty() == false)
        {
            n = colve.get(colve.size()-1);
            colve.remove(n);
            
            for(int i =1 ;i<=G.nodos.size();i++)
            {       
                String sn = String.valueOf(i);
                String sa = 'a' + n + " -- " +'a' + sn + ";\n";
                String sa2 = 'a' + sn + " -- " +'a' + n + ";\n";
                
                if(G.aris.containsKey(sa))
                {
                    
                    //System.out.println("cont"+sa);
                    Arista aaa = G.aris.get(sa);
                    G.aris.remove(sa);
                    G.aris.remove(sa2);
                    colar.put(sn,aaa);
                    
                    np.add(n);
                    if(np.contains(sn) == false)
                    {
                        if(colve.contains(sn))
                        {
                            
                            colve.remove(sn);
                            colar.replace(sn,aaa);
                            colve.add(sn);
                        }
                        else{
                            //A.aris.put(aaa.id, aaa);
                            colve.add(sn);
                        }    
                    }
                }
                /*
                System.out.println("colve");
                System.out.println(colve);
                System.out.println("colar");
                System.out.println(colar);
                System.out.println("np");
                System.out.println(np);
                */
                if(i==G.nodos.size())
                {
                    if(colve.size()>1)
                    {
                        //System.out.println(colve);
                        n = colve.get(colve.size()-1);
                        //System.out.println(n);
                        Arista aan = colar.get(n);
                        colar.remove(n);
                        A.aris.put(aan.id, aan);
                    }
                    else
                    {
                        if(colve.size()>0)
                        {
                            n = colve.get(colve.size()-1);
                            /*
                            System.out.println(colve);
                            System.out.println(n);
                            System.out.println(colar);
                            */
                            Arista aan = colar.get(n);

                            colar.remove(n);
                            A.aris.put(aan.id, aan);
                        }
                    }
                        
                }
                
            }
            
            
            
        }
        A.toGiphi(nombre + "_IDFS" ,false);       
        return A;
    }
    public static void recDFS(String n, Grafo G, Map A, Set Exp)
    {
        //System.out.println("QqC");
        Exp.add(n);
        
        for (Arista As : G.aris.values()) {
            //System.out.println(As.id);
            String n1 = As.n1.nid;
            //System.out.println(n);
            //System.out.println(n1);
            if(n1.equals(n))
            {
                String n2 = As.n2.nid;
                if(!Exp.contains(n2))
                {
                    A.put(As.id,As);
                    recDFS(n2, G, A, Exp);
                    /*
                    System.out.println("n2");
                    System.out.println(n2);
                    System.out.println("A");
                    System.out.println(A);
                    System.out.println("Exp");
                    System.out.println(Exp);
                    */
                }
            }
        
        }
        
    }
    public static Grafo RDFS(String n, Grafo G, String nombre)
    {
        Set<String> Exp = new HashSet<String>();
        Map<String,Arista> NS = new HashMap<String,Arista>();
        
        recDFS(n, G, NS, Exp);
        Grafo A = G;
        A.aris.clear();
        A.aris = NS; 
        A.toGiphi(nombre + "_RFDS",false);
        return A;
    }
        
    public static void copying(
    Grafo O, Grafo G )
{
    Map<String, Arista> original=O.aris;
    Map<String, Arista> copy =G.aris;
    for (Map.Entry<String, Arista> entry : original.entrySet())
    {
        copy.put(entry.getKey(),
           // Or whatever List implementation you'd like here.
           entry.getValue());
        System.out.println(copy.size());
        
        
    }
    Map<String, Nodo> noriginal=O.nodos;
    Map<String, Nodo> ncopy =G.nodos;
   for (Map.Entry<String, Nodo> nentry : noriginal.entrySet())
    {
        ncopy.put(nentry.getKey(),
           // Or whatever List implementation you'd like here.
           nentry.getValue());
        System.out.println(copy.size());
        
        
    }
}
    public static void main(String [] args)
    {
       Grafo H = Grafo.genGeografico(500,.1,false,false);
       String nombre = "Geo_";
       H.toGiphi(nombre + H.nodos.size(),false);
       Grafo H1 = new Grafo();
       copying(H,H1);
       
       Grafo A = Grafo.RDFS("1",H,nombre + H.nodos.size());
       Grafo B = Grafo.IDFS("1",H,nombre + H.nodos.size());
       Grafo C = Grafo.BFS("1",H1,nombre + H.nodos.size());

       //Grafo.genGilbert(100,.3,false,false);
       //Grafo.genErdosRenyi(30,100,false,false);
       //Grafo.genGilbert(500,.2,false,false);
               
    }
    
}

    