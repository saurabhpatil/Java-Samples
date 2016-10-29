import java.io.*;
import java.util.*;
public class single_queue
{
public static void main(String[] args)
{
System.out.println("This program demonstrates Single Server queue");
float avgwait=0,avgser=0,probwait=0;
Arrtime tInterval[]=new Arrtime[4];
Sertype sType[] = new Sertype[4];
Queue serQueue[]=new Queue[10];
Scanner scan=new Scanner(System.in);
System.out.println("Please Input Probabilities for InterArrival-Time Table");
System.out.println("");
for(int i=0;i<=3;i++)
{
tInterval[i]=new Arrtime();
tInterval[i].arrInt=(i+1);
tInterval[i].prob=scan.nextFloat();
if(i==0)
{
tInterval[i].cum=tInterval[i].prob;
tInterval[i].randStart=1;
tInterval[i].randEnd=(int)(tInterval[i].cum*100);
} else
{
tInterval[i].cum=tInterval[i-1].cum+tInterval[i].prob;
tInterval[i].randStart=(int)((tInterval[i-1].cum*100)+1);
tInterval[i].randEnd=(int)(tInterval[i].cum*100);
}
}
System.out.println("InterArrival" + " " + "Probability" + " " + "CumulativeProb" + " " + "Random No");
for(int j=0;j<=3;j++)
{
System.out.printf("%5d%15.2f%15.2f%
10d-%3d",tInterval[j].arrInt,tInterval[j].prob,tInterval[j].cum,(int)tInterval[j].randStart,(int)tInterval[j].randEnd);
System.out.println();
}
System.out.println("Please Input Probabilities for Service-Time Table");
for(int i=0;i<=3;i++)
{
sType[i]=new Sertype();
sType[i].serNo=(i+1);
sType[i].prob=scan.nextFloat();
if(i==0)
{
sType[i].cum=sType[i].prob;
sType[i].randStart=1;
sType[i].randEnd=(int)(sType[i].cum*100);
} else
{
sType[i].cum=sType[i-1].cum+sType[i].prob;
sType[i].randStart=(int)((sType[i-1].cum*100)+1);
sType[i].randEnd=(int)(sType[i].cum*100);
}
}
System.out.println("SerTime" + " " + "Probability" + " " + "CumulativeProb" + " " + "Random No");
for(int j=0;j<=3;j++)
{
System.out.printf("%5d%12.2f%12.2f
%12d-%3d",sType[j].serNo,sType[j].prob,sType[j].cum,(int)sType[j].randStart,(int)sType[j].randEnd);
System.out.println();
}
for(int k = 0;k<=9;k++)
{
serQueue[k]=new Queue();
serQueue[k].cusNo = k+1;
serQueue[k].arrRno = (int)(Math.random()*100);
serQueue[k].serRno = (int)(Math.random()*100);
if(k==0)
{
serQueue[k].interArr=0;
serQueue[k].cumArr=0;
serQueue[k].serBegins=0;
for(int m=0;m<=3;m++)
{
if(serQueue[k].serRno <= sType[m].randEnd)
{
serQueue[k].serTime=sType[m].serNo;
break;
}
}
serQueue[k].serEnds=serQueue[k].serBegins + serQueue[k].serTime;
serQueue[k].cuswait=0;
serQueue[k].sysidle=0;
} else
{
for(int l=0;l<=3;l++)
{
if(serQueue[k].arrRno <= tInterval[l].randEnd)
{
serQueue[k].interArr=tInterval[l].arrInt;
break;
}
}
serQueue[k].cumArr=serQueue[k-1].cumArr + serQueue[k].interArr;
for(int n=0;n<=3;n++)
{
if(serQueue[k].serRno <= sType[n].randEnd)
{
serQueue[k].serTime=sType[n].serNo;
break;
}
}
if(serQueue[k].cumArr<=serQueue[k-1].serEnds)
{
serQueue[k].serBegins=serQueue[k-1].serEnds;
}
else
{
serQueue[k].serBegins=serQueue[k].cumArr;
}
serQueue[k].serEnds=serQueue[k].serBegins+serQueue[k].serTime;
serQueue[k].cuswait=serQueue[k].serBegins-serQueue[k].cumArr;
serQueue[k].sysidle=serQueue[k].serBegins-serQueue[k-1].serEnds;
}
}
System.out.println("\n\n");
System.out.println("Single Server Queue Table");
System.out.println();
System.out.println("CustomerNo"+" "+"Arr_RNo"+" "+"InterArr"+" "+"CumArr"+" "+"Serv_RNo"+"
"+"ServTime"+" "+"ServBeg"+" "+"ServEnds"+" "+"WaitTime"+" "+"IdleTime");
for(int p=0;p<=9;p++)
{
System.out.printf("%5d%10d%9d%10d%9d%9d%10d%9d%9d%9d",serQueue[p].cusNo,serQueue[p].arrRno,ser
Queue[p].interArr,serQueue[p].cumArr,serQueue[p].serRno,serQueue[p].serTime,serQueue[p].serBegins,serQueu
e[p].serEnds,serQueue[p].cuswait,serQueue[p].sysidle);
System.out.println();
avgwait=avgwait+serQueue[p].cuswait;
avgser=avgser+serQueue[p].serTime;
if(serQueue[p].cuswait>=1)
{
probwait=probwait+1;
}
}
System.out.println();
System.out.println("Average Wait = "+avgwait/10);
System.out.println("Average Service Time = "+avgser/10);
System.out.println("Probability Wait = "+probwait/10);
}
}
class Arrtime
{
int arrInt;
float prob;
float cum;
int randStart;
int randEnd;
}
class Sertype
{
int serNo;
float prob;
float cum;
int randStart;
int randEnd;
}
class Queue
{
int cusNo;
int arrRno;
int interArr;
int cumArr;
int serRno;
int serTime;
int serBegins;
int serEnds;
int cuswait;
int sysidle;
}