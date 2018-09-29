import java.io.IOException;

/******************************************************************************
* Compilation: javac p5e1.java
* Execution: java p5e1 pattern text
* Dependencies: StdIn.java StdOut.java BoyerMoore.java KMP.java RabinKarp.java
* TunedBoyerMoore.java Stopwatch.java
* Data files: >out1.txt >out2.txt <tale.txt
******************************************************************************/

public class p5e1 {
public static void main(String[] args) throws IOException  {
       if(args.length==0) {
    	   StdOut.println("No input");
    	   System.exit(1);
       }
       if(args.length==1 && System.in.available()==0) {
    	   StdOut.println("No text to search from");
    	   System.exit(1);
       }
       
       String pat=args[0];//� 1� ���� ��� �� ����� ��� ������ ����� �� pattern
       String txt;
       if (System.in.available()==0)//�� �� input stream ����� ����� �� ����� ��� text �� ������� ������
       	   txt=args[1];
       else 
    	   txt= StdIn.readAll();
       
       char[] pattern = args[0].toCharArray();//��������� �� pattern ��� �� text �� ������� ���������� ��� �� �� ���� ��� �� 
       char[] text=txt.toCharArray();//���� ��� ������� ���� � Sedgewick
       
       /*��������� ����������� �� ����� ����������� ��� �� ���� ��������.
        *(������)������  � ���������� �� ����� ��� ����� ����� �� �����
        *����� �� �� �� ��������� ��� ����� ��� �������� ��� m ����������
        *��� ���������� ��������� ��� pattern */
       
       TunedBoyerMoore TunedBoyerMoore1 = new TunedBoyerMoore(pat);
       TunedBoyerMoore TunedBoyerMoore2 = new TunedBoyerMoore(pattern, 256);
       BoyerMoore boyermoore1 = new BoyerMoore(pat);
       BoyerMoore boyermoore2 = new BoyerMoore(pattern, 256);
       KMP kmp1 = new KMP(pat);
       KMP kmp2 = new KMP(pattern, 256);
       RabinKarp RabinKarp1 = new RabinKarp(pat);
       
       StdOut.println("Searching for pattern:"+pat);
       StdOut.println(" ");
       
       Stopwatch timer1 = new Stopwatch();//��������� ��� ����������
       int offset1 = TunedBoyerMoore1.search(txt);//����� ���� ��� �������
       double time1 = timer1.elapsedTime();//��������� ��� ����� ��� ����� ����� ��� ������ ��� �������� � ���������
       StdOut.println("Searching method used: Tuned Boyer-Moore");//� ���������� ��� ���������������
       StdOut.println("Position in text: "+offset1);//� ���� ��� ����������� � 1� �������� ��� pattern ��� text
       StdOut.println("Elapsed time:"+ time1+"s");//� ������ ��� ������ � ����������
       StdOut.println(" ");
      
       Stopwatch timer2 = new Stopwatch();//��������� ��� ����������
       int offset2 = TunedBoyerMoore2.search(text);//����� ���� ��� �������
       double time2 = timer2.elapsedTime();//��������� ��� ����� ��� ����� ����� ��� ������ ��� �������� � ���������
       StdOut.println("Searching method used: Tuned Boyer-Moore (with char array)");//� ���������� ��� ���������������
       StdOut.println("Position in text: "+offset2);//� ���� ��� ����������� � 1� �������� ��� pattern ��� text
       StdOut.println("Elapsed time:"+ time2+"s");//� ������ ��� ������ � ����������
       StdOut.println(" ");
       
       Stopwatch timer3 = new Stopwatch();//��������� ��� ����������
       int offset3 = boyermoore1.search(txt);//����� ���� ��� �������
       double time3 = timer3.elapsedTime();//��������� ��� ����� ��� ����� ����� ��� ������ ��� �������� � ���������
       StdOut.println("Searching method used: Boyer-Moore");//� ���������� ��� ���������������
       StdOut.println("Position in text: "+offset3);//� ���� ��� ����������� � 1� �������� ��� pattern ��� text
       StdOut.println("Elapsed time:"+ time3+"s");//� ������ ��� ������ � ����������
       StdOut.println(" ");
      
       Stopwatch timer4 = new Stopwatch();//��������� ��� ����������
       int offset4 = boyermoore2.search(text);//����� ���� ��� �������
       double time4 = timer4.elapsedTime();//��������� ��� ����� ��� ����� ����� ��� ������ ��� �������� � ���������
       StdOut.println("Searching method used: Boyer-Moore (with char array)");//� ���������� ��� ���������������
       StdOut.println("Position in text: "+offset4);//� ���� ��� ����������� � 1� �������� ��� pattern ��� text
       StdOut.println("Elapsed time:"+ time4+"s");//� ������ ��� ������ � ����������
       StdOut.println(" ");
        
       Stopwatch timer5 = new Stopwatch();//��������� ��� ����������
       int offset5 =kmp1.search(txt);//����� ���� ��� �������
       double time5 = timer5.elapsedTime();//��������� ��� ����� ��� ����� ����� ��� ������ ��� �������� � ���������
       StdOut.println("Searching method used: Knuth-Morris-Pratt");//� ���������� ��� ���������������
       StdOut.println("Position in text: "+offset5);//� ���� ��� ����������� � 1� �������� ��� pattern ��� text
       StdOut.println("Elapsed time:"+ time5+"s");//� ������ ��� ������ � ����������
       StdOut.println(" ");  
        
       Stopwatch timer6 = new Stopwatch();//��������� ��� ����������
       int offset6 =  kmp2.search(text);//����� ���� ��� �������
       double time6 = timer6.elapsedTime();//��������� ��� ����� ��� ����� ����� ��� ������ ��� �������� � ���������
       StdOut.println("Searching method used: Knuth-Morris-Pratt (with char array)");//� ���������� ��� ���������������
       StdOut.println("Position in text: "+offset6);//� ���� ��� ����������� � 1� �������� ��� pattern ��� text
       StdOut.println("Elapsed time:"+ time6+"s");//� ������ ��� ������ � ����������
       StdOut.println(" ");
        
       Stopwatch timer7 = new Stopwatch();//��������� ��� ����������
       int offset7 =  RabinKarp1.search(txt);//����� ���� ��� �������
       double time7 = timer7.elapsedTime();//��������� ��� ����� ��� ����� ����� ��� ������ ��� �������� � ���������
       StdOut.println("Searching method used: Rabin-Karp");//� ���������� ��� ���������������
       StdOut.println("Position in text: "+offset7);//� ���� ��� ����������� � 1� �������� ��� pattern ��� text
       StdOut.println("Elapsed time:"+ time7+"s");//� ������ ��� ������ � ����������
       StdOut.println(" ");
     
      }
}

