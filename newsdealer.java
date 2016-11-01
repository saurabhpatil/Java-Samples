import java.io.*;
import java.util.*;
public class Newsdealer
{
public static void main(String[] args)
{
System.out.println("This program demonstrates the News Dealer Problem\n\n");
newspaper newsp[]=new newspaper[3];
demand dem[]=new demand[7];
solution sol[]=new solution[10];
float totalrevenue=0,totalloss=0,scrapsale=0;
Scanner scan=new Scanner(System.in);
int purprice,salprice,scrapprice,total;
System.out.print("Please Enter Purchase price : ");
purprice=scan.nextInt();
System.out.print("\nPlease Enter Sales price : ");
salprice=scan.nextInt();
System.out.print("\nPlease Enter Scrap price : ");
scrapprice=scan.nextInt();
System.out.print("\nPlease Enter Number of papers : ");
total=scan.nextInt();
System.out.println("\nPlease Enter Newspaper Types and thier Probabilities below");
System.out.println();
for(int i=0;i<3;i++)
{
newsp[i]=new newspaper();
newsp[i].type=scan.next();
newsp[i].prob=scan.nextFloat();
if(i==0)
{
newsp[i].cum=newsp[i].prob;
newsp[i].randStart=1;
newsp[i].randEnd=(int)(newsp[i].prob*100);
}e
lse
{
newsp[i].cum=newsp[i-1].cum+newsp[i].prob;
newsp[i].randStart=newsp[i-1].randEnd+1;
newsp[i].randEnd=(int)(newsp[i].prob*100)+newsp[i-1].randEnd;
}
}
System.out.println();
System.out.println("Newspaper Types and their probabilities");
System.out.println("Type"+" "+"Prob"+" "+"Cumul"+" "+"RandomNo");
for(int i=0;i<3;i++)
{
System.out.printf("%s%8.2f%5.2f
%5d -%4d\n",newsp[i].type,newsp[i].prob,newsp[i].cum,newsp[i].randStart,newsp[i].randEnd);
}
System.out.println();
System.out.println("Please enter Demands and their probabilities");
System.out.println("Demand"+" "+newsp[0].type+" "+newsp[1].type+" "+newsp[2].type);
for(int i=0;i<7;i++)
{
dem[i]=new demand();
dem[i].demand=scan.nextInt();
dem[i].probg=scan.nextFloat();
dem[i].probf=scan.nextFloat();
dem[i].probp=scan.nextFloat();
if(i==0)
{
dem[i].cumg=dem[i].probg;
dem[i].cumf=dem[i].probf;
dem[i].cump=dem[i].probp;
dem[i].randgs=1;
dem[i].randfs=1;
dem[i].randps=1;
dem[i].randge=(int)(dem[i].probg*100);
dem[i].randfe=(int)(dem[i].probf*100);
dem[i].randpe=(int)(dem[i].probp*100);
}
else
{
dem[i].cumg=dem[i-1].cumg+dem[i].probg;
dem[i].cumf=dem[i-1].cumf+dem[i].probf;
dem[i].cump=dem[i-1].cump+dem[i].probp;
if(dem[i-1].randge==100||dem[i-1].randge==0)
{
dem[i].randgs=0;
dem[i].randge=0;
}
if(dem[i-1].randge<100&&dem[i-1].randge>0)
{
dem[i].randgs=dem[i-1].randge+1;
dem[i].randge=(int)(dem[i].probg*100)+dem[i-1].randge;
}
if(dem[i-1].randfe==100||dem[i-1].randfe==0)
{
dem[i].randfs=0;
dem[i].randfe=0;
}
if(dem[i-1].randfe<100&&dem[i-1].randfe>0)
{
dem[i].randfs=dem[i-1].randfe+1;
dem[i].randfe=(int)(dem[i].probf*100)+dem[i-1].randfe;
}
if(dem[i-1].randpe==100||dem[i-1].randpe==0)
{
dem[i].randps=0;
dem[i].randpe=0;
}
if(dem[i-1].randpe<100&&dem[i-1].randpe>0)
{
dem[i].randps=dem[i-1].randpe+1;
dem[i].randpe=(int)(dem[i].probp*100)+dem[i-1].randpe;
}
}
}
System.out.println();
System.out.println("Demand Table");
System.out.println("Demand"+" "+newsp[0].type+"_c "+newsp[1].type+"_c "+
newsp[2].type+"_c "+newsp[0].type+"_r "+newsp[1].type+"_r "+newsp[2].type+"_r ");
for(int i=0;i<7;i++)
{
System.out.printf("%4d%8.2f%8.2f%6.2f%6d%s%3d%4d%s%3d%4d%s%3d\n",dem[i].demand,dem[i].
cumg,dem[i].cumf,dem[i].cump,dem[i].randgs,"-",dem[i].randge,dem[i].randfs,"-
",dem[i].randfe,dem[i].randps,"-",dem[i].randpe);
}
for(int i=0;i<10;i++)
{
sol[i]=new solution();
sol[i].day=i+1;
sol[i].randnewsp=(int)(Math.random()*100);
sol[i].randdem=(int)(Math.random()*100);
for(int j=0;j<3;j++)
{
if(sol[i].randnewsp<=newsp[j].randEnd)
{
sol[i].type=newsp[j].type;
if(sol[i].type.equals(newsp[0].type))
{
for(int k=0;k<7;k++)
{
if(sol[i].randdem<=dem[k].randge)
{
sol[i].dem=dem[k].demand;
break;
}
}
}
if(sol[i].type.equals(newsp[1].type))
{
for(int m=0;m<7;m++)
{
if(sol[i].randdem<=dem[m].randfe)
{s
ol[i].dem=dem[m].demand;
break;
}
}
}
if(sol[i].type.equals(newsp[2].type))
{
for(int n=0;n<7;n++)
{
if(sol[i].randdem<=dem[n].randpe)
{
sol[i].dem=dem[n].demand;
break;
}
}
}
break;
}
}i
f(sol[i].dem<=total)
{
sol[i].revenue=(sol[i].dem*salprice)/100;
sol[i].loss=0;
sol[i].scrap=((float)((total-sol[i].dem)*(scrapprice)))/100;
}
if(sol[i].dem>total)
{
sol[i].revenue=(total*salprice)/100;
sol[i].loss=((float)((salprice-purprice)*(sol[i].dem-total)))/100;
sol[i].scrap=0;
}s
ol[i].profit=sol[i].revenue-(((float)(total*purprice))/100)-sol[i].loss+sol[i].scrap;
}
System.out.println();
System.out.println("Simulation Table for purchase of 70 Newspapers");
System.out.println();
System.out.println(" Day "+"News_R "+"News_Type "+"Demand_R "+"Demand "+"Revenue "+"loss "
+"ScrapSale "+"Profit");
for(int i=0;i<10;i++)
{
System.out.printf("%4d%6d%9s%9d%7d%7d%8.2f%7.2f%9.2f",sol[i].day,sol[i].randnewsp,sol[i].type,s
ol[i].randdem,sol[i].dem,sol[i].revenue,sol[i].loss,sol[i].scrap,sol[i].profit);
System.out.println();
totalrevenue=totalrevenue+sol[i].revenue;
totalloss=totalloss+sol[i].loss;
scrapsale=scrapsale+sol[i].scrap;
}
System.out.println("\n");
System.out.printf("Total Revenue: %5.2f",totalrevenue);
System.out.println();
System.out.printf("Total Loss : %5.2f",totalloss);
System.out.println();
System.out.printf("Total Scrap Sale : %5.2f",scrapsale);
System.out.println();
System.out.println("Total Purchase Cost :"+((purprice*total)/10));
System.out.println();
System.out.println("Total Profit : "+(totalrevenue-((purprice*total)/10)-totalloss+scrapsale));
}
}
class newspaper
{
String type;
float prob;
float cum;
int randStart;
int randEnd;
}
class demand
{
int demand;
float probg;
float probf;
float probp;
float cumg;
float cumf;
float cump;
int randgs;
int randge;
int randfs;
int randfe;
int randps;
int randpe;
}
class solution
{
int day;
int randnewsp;
String type;
int randdem;
int dem;
int revenue;
float loss;
float scrap;
float profit;
}