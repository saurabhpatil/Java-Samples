import java.io.*;
import java.util.*;

public class bomberman
{

    public static void main(String[] args)
    {

        System.out.println("This program demonstrates Random Normal Numbers - Bomber Man Problem");
        float u,sigmax,sigmay;
        float x1,y1,x2,y2,x3,y3,x4,y4,m1,m2,m3,m4,c1,c2,c3,c4;
        int v1=0,v2=0,v3=0,v4=0;
        rnn nque[]=new rnn[10];
        Scanner scan=new Scanner(System.in);
        System.out.println("Please Input the value of u (mu)");
        u=scan.nextFloat();
        System.out.println("");
        System.out.println("Please Input the value of sigmax");
        sigmax=scan.nextFloat();
        System.out.println("");
        System.out.println("Please Input the value of sigmay");
        sigmay=scan.nextFloat();
        System.out.println("\nPlease Input the values of x and y for Co-Ordinate 1");
        x1=scan.nextFloat();
        y1=scan.nextFloat();
        System.out.println("Please Input the values of x and y for Co-Ordinate 2");
        x2=scan.nextFloat();
        y2=scan.nextFloat();
        System.out.println("Please Input the values of x and y for Co-Ordinate 3");
        x3=scan.nextFloat();
        y3=scan.nextFloat();
        System.out.println("Please Input the values of x and y for Co-Ordinate 4");
        x4=scan.nextFloat();
        y4=scan.nextFloat();

        if(y1==y2)
        {m1=0;c1=y1;}
        else
        {m1=(y2-y1)/(x2-x1);c1=y1-(m1*x1);}

        if(x3==x2)
        {m2=0;c2=x2;}
        else
        {m2=(y3-y2)/(x3-x2);c2=y2-(m2*x2);}

        if(y4==y3)
        {m3=0;c3=y3;}
        else
        {m3=(y4-y3)/(x4-x3);c3=y3-(m3*x3);}

        if(x1==x4)
        {m4=0;c4=x4;}
        else
        {m4=(y1-y4)/(x1-x4);c4=y4-(m4*x4);}        
        
        System.out.println("");
        
        Random R=new Random();
        Random sign=new Random();
        Random sign1=new Random();
        int rn=0,rn1=0;
        System.out.printf("Bomb       RNNx    X Co-Ord      RNNy    Y Co-Ord    Result\n");
        for(int i=0;i<=9;i++)
        {
            nque[i]=new rnn();
            nque[i].bombno=i+1;
            rn=sign.nextInt(2);
            rn1=sign.nextInt(2);
            if(rn==1)
            {
                nque[i].RNNx=(float)(R.nextInt(39999))/(float)(10000.00);
                nque[i].XCord=(sigmax*nque[i].RNNx)+u;
            }
            else
            {
                nque[i].RNNx=(float)(R.nextInt(39999))/(float)(10000.00);
                nque[i].RNNx=-nque[i].RNNx;
                nque[i].XCord=sigmax*nque[i].RNNx+u;
            }

            if(rn1==1)
            {
                nque[i].RNNy=(float)(R.nextInt(39999))/(float)(10000.00);
                nque[i].YCord=sigmay*nque[i].RNNy+u;
            }
            else
            {
                nque[i].RNNy=(float)(R.nextInt(39999))/(float)(10000.00);
                nque[i].RNNy=-nque[i].RNNy;
                nque[i].YCord=sigmay*nque[i].RNNy+u;
            }

            if((nque[i].YCord-(m1*nque[i].XCord))<c1)
            {
                v1=1;
            }

            if((nque[i].YCord-(m3*nque[i].XCord))>c3)
            {
                v3=1;
            }

            if(m2<0)
            {
                if((nque[i].YCord-(m2*nque[i].XCord))>c2)
                {
                    v2=1;
                }
            }

            if(m2==0)
            {
                if(nque[i].XCord>c2)
                {
                    v2=1;
                }
            }

            if(m2>0)
            {
                if((nque[i].YCord-(m2*nque[i].XCord))<c2)
                {
                    v2=1;
                }
            }

            if(m4<0)
            {
                if((nque[i].YCord-(m4*nque[i].XCord))<c4)
                {
                    v4=1;
                }
            }

            if(m4>0)
            {
                if((nque[i].YCord-(m4*nque[i].XCord))>c4)
                {
                    v4=1;
                }
            }

            if(m4==0)
            {
                if(nque[i].XCord<c4)
                {
                    v4=1;
                }
            }
            
            if(v1==1 && v2==1 && v3==1 && v4==1)
            {nque[i].result="Hit";}
            else
            {nque[i].result="Miss";}
            
            System.out.printf("%5d%10.2f%12.2f%10.2f%12.2f%10s\n",nque[i].bombno,nque[i].RNNx,nque[i].XCord,nque[i].RNNy,nque[i].YCord,nque[i].result);
            v1=v2=v3=v4=0;
        }
    }
}

class rnn
{
    int bombno;
    float RNNx;
    float XCord;
    float RNNy;
    float YCord;
    String result;
}