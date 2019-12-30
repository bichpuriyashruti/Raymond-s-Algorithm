package raymondalgorithm;

import java.util.*;

class Node{
    int holder;
    int id;
    Queue<Integer> q;
    Node(int id,int h){
        this.id=id;
        this.holder=h;
        this.q=new LinkedList<>();
    }
   
    public String toString(){
        String s="";
        s=s+"Site"+this.id+"\n";
        s=s+" Holder: "+this.holder;
        return s;
    }
}

public class RaymondAlgorithm {
    static List<Node> tree;
    static Scanner in =new Scanner(System.in);
   
    public static void request(){
        System.out.println("Enter the process making the request: ");
        int p=in.nextInt();
        if(tree.get(p-1).holder==p){
            System.out.println("Process has the token!! Process P"+p+" has entered the critical section!!");
        }
        else{
                Node n=tree.get(p-1);
                int i=n.id;
                tree.get(p-1).q.add(i);
                int h=n.holder;
                while(tree.get(h-1).holder!=h){
                    Node n1=tree.get(h-1);
                    if(!n1.q.contains(i)){
                        tree.get(h-1).q.add(i);
                    }
                    i=n1.id;
                    h=n1.holder;
                }
               
                Node n1=tree.get(h-1);
                if(!n1.q.contains(i))
                    tree.get(h-1).q.add(i);
               
        }
        display();
    }
   
    public static void exitCS(Node n){
        int n1=n.q.poll();
       /* if(n.q.isEmpty()){
         System.out.println("Node "+n.id+"remains in CS ");
         return;
     }*/
     
     if(n.id==n1){  
         System.out.println("Node "+n.id+"in CS ");
         return;
     }
                    tree.get(n1-1).holder=n1;
                    tree.get(n.id-1).holder=n1;
                    if(!n.q.isEmpty()){
                        tree.get(n1-1).q.add(n.id);
                    }
                    exitCS(tree.get(n1-1));
       
    }
   
    public static void display(){
        for(Node i:tree){
            System.out.println(i);
            System.out.println("Queue:"+i.q);
        }
    }
   
    public static void main(String[] args) {
        System.out.println("Enter the no. of processes: ");
        int p=in.nextInt();
        tree=new ArrayList<>();
        for(int i=0;i<p;i++){
            System.out.println("Enter the holder of process "+(i+1)+":");
            int h=in.nextInt();
            Node n=new Node(i+1,h);
            tree.add(n);
        }
        System.out.println("1.Requesting process\n2.Process exiting the critical section\n3.exit");
        int c;
        do{
        System.out.println("Enter your choice:");
        c=in.nextInt();
        switch(c){
            case 1: request();
                break;
            case 2: {
                Node tp=null;
                for(Node n:tree){
                    if(n.holder==n.id){
                        tp=n;
                        break;
                    }
                }
                exitCS(tp);
                display();
                break;
            }
            case 3: System.exit(0);
        }
        }while(c!=3);
    }
   
}