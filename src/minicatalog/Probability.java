/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicatalog;

/**
 *
 * @author luthf
 */
public class Probability {
    double q, px, nCx;

    public Probability(){
        this.q=0;
        this.px=0;
        this.nCx=0;
    }
          
        //System.out.println("Menghitung Probabilitas Bernouli");
        //System.out.print("Berapa kali kejadian diulang? "); n=in.nextDouble();
        //System.out.print("Berapa kali diharapkan berhasil? "); x=in.nextDouble();
        //System.out.print("Berapa peluang tiap kejadian? "); p=in.nextDouble();
    public double probabilitasBernouli(double peluang, double nkali,double xsize){
        q = 1-peluang;
        nCx = kombinasi(nkali, xsize);
        px = nCx * (Math.pow(peluang, xsize)) * (Math.pow(q, (nkali-xsize)));
        return px;
    }
    
    public double faktorial(double a) {
        if (a==0) 
            {return 1;}
        else 
            {return (a*faktorial(a-1));}
    }
    
    public double kombinasi(double b, double c) {
        double combinasi = faktorial(b)/(faktorial(c)*faktorial(b-c));
        return combinasi;
    }
}


