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
       
       String pat=args[0];//η 1η λέξη που θα δοθεί σαν όρισμα είναι το pattern
       String txt;
       if (System.in.available()==0)//αν το input stream είναι άδειο θα πάρει σαν text το δεύτερο όρισμα
       	   txt=args[1];
       else 
    	   txt= StdIn.readAll();
       
       char[] pattern = args[0].toCharArray();//Μετατρέπω το pattern και το text σε πίνακες χαρακτήρων για να το κάνω και με 
       char[] text=txt.toCharArray();//τους δυο τρόπους όπως ο Sedgewick
       
       /*Δημιουργώ αντικείμενα με όλους αλγορίθμους για να τους συγκρίνω.
        *(Σχόλιο)Λογικά  ο αλγόριθμος να είναι πιο αργός γιατί θα χάνει
        *χρόνο με το να προσθέτει στο τέλος του κειμένου τις m εμφανίσεις
        *του τελευταίου χαρακτήρα του pattern */
       
       TunedBoyerMoore TunedBoyerMoore1 = new TunedBoyerMoore(pat);
       TunedBoyerMoore TunedBoyerMoore2 = new TunedBoyerMoore(pattern, 256);
       BoyerMoore boyermoore1 = new BoyerMoore(pat);
       BoyerMoore boyermoore2 = new BoyerMoore(pattern, 256);
       KMP kmp1 = new KMP(pat);
       KMP kmp2 = new KMP(pattern, 256);
       RabinKarp RabinKarp1 = new RabinKarp(pat);
       
       StdOut.println("Searching for pattern:"+pat);
       StdOut.println(" ");
       
       Stopwatch timer1 = new Stopwatch();//Δημιουργώ ένα χρονόμετρο
       int offset1 = TunedBoyerMoore1.search(txt);//ψάχνω μέσα στο κείμενο
       double time1 = timer1.elapsedTime();//αποθηκεύω τον χρόνο που έκανε μέχρι την στιγμή που τελείωσε η αναζήτηση
       StdOut.println("Searching method used: Tuned Boyer-Moore");//ο αλγόριθμος που χρησιμοποιήθηκε
       StdOut.println("Position in text: "+offset1);//η θέση που εντοπίστηκε η 1η εμφάνιση του pattern στο text
       StdOut.println("Elapsed time:"+ time1+"s");//ο χρόνος που έτρεξε ο αλγόριθμος
       StdOut.println(" ");
      
       Stopwatch timer2 = new Stopwatch();//Δημιουργώ ένα χρονόμετρο
       int offset2 = TunedBoyerMoore2.search(text);//ψάχνω μέσα στο κείμενο
       double time2 = timer2.elapsedTime();//αποθηκεύω τον χρόνο που έκανε μέχρι την στιγμή που τελείωσε η αναζήτηση
       StdOut.println("Searching method used: Tuned Boyer-Moore (with char array)");//ο αλγόριθμος που χρησιμοποιήθηκε
       StdOut.println("Position in text: "+offset2);//η θέση που εντοπίστηκε η 1η εμφάνιση του pattern στο text
       StdOut.println("Elapsed time:"+ time2+"s");//ο χρόνος που έτρεξε ο αλγόριθμος
       StdOut.println(" ");
       
       Stopwatch timer3 = new Stopwatch();//Δημιουργώ ένα χρονόμετρο
       int offset3 = boyermoore1.search(txt);//ψάχνω μέσα στο κείμενο
       double time3 = timer3.elapsedTime();//αποθηκεύω τον χρόνο που έκανε μέχρι την στιγμή που τελείωσε η αναζήτηση
       StdOut.println("Searching method used: Boyer-Moore");//ο αλγόριθμος που χρησιμοποιήθηκε
       StdOut.println("Position in text: "+offset3);//η θέση που εντοπίστηκε η 1η εμφάνιση του pattern στο text
       StdOut.println("Elapsed time:"+ time3+"s");//ο χρόνος που έτρεξε ο αλγόριθμος
       StdOut.println(" ");
      
       Stopwatch timer4 = new Stopwatch();//Δημιουργώ ένα χρονόμετρο
       int offset4 = boyermoore2.search(text);//ψάχνω μέσα στο κείμενο
       double time4 = timer4.elapsedTime();//αποθηκεύω τον χρόνο που έκανε μέχρι την στιγμή που τελείωσε η αναζήτηση
       StdOut.println("Searching method used: Boyer-Moore (with char array)");//ο αλγόριθμος που χρησιμοποιήθηκε
       StdOut.println("Position in text: "+offset4);//η θέση που εντοπίστηκε η 1η εμφάνιση του pattern στο text
       StdOut.println("Elapsed time:"+ time4+"s");//ο χρόνος που έτρεξε ο αλγόριθμος
       StdOut.println(" ");
        
       Stopwatch timer5 = new Stopwatch();//Δημιουργώ ένα χρονόμετρο
       int offset5 =kmp1.search(txt);//ψάχνω μέσα στο κείμενο
       double time5 = timer5.elapsedTime();//αποθηκεύω τον χρόνο που έκανε μέχρι την στιγμή που τελείωσε η αναζήτηση
       StdOut.println("Searching method used: Knuth-Morris-Pratt");//ο αλγόριθμος που χρησιμοποιήθηκε
       StdOut.println("Position in text: "+offset5);//η θέση που εντοπίστηκε η 1η εμφάνιση του pattern στο text
       StdOut.println("Elapsed time:"+ time5+"s");//ο χρόνος που έτρεξε ο αλγόριθμος
       StdOut.println(" ");  
        
       Stopwatch timer6 = new Stopwatch();//Δημιουργώ ένα χρονόμετρο
       int offset6 =  kmp2.search(text);//ψάχνω μέσα στο κείμενο
       double time6 = timer6.elapsedTime();//αποθηκεύω τον χρόνο που έκανε μέχρι την στιγμή που τελείωσε η αναζήτηση
       StdOut.println("Searching method used: Knuth-Morris-Pratt (with char array)");//ο αλγόριθμος που χρησιμοποιήθηκε
       StdOut.println("Position in text: "+offset6);//η θέση που εντοπίστηκε η 1η εμφάνιση του pattern στο text
       StdOut.println("Elapsed time:"+ time6+"s");//ο χρόνος που έτρεξε ο αλγόριθμος
       StdOut.println(" ");
        
       Stopwatch timer7 = new Stopwatch();//Δημιουργώ ένα χρονόμετρο
       int offset7 =  RabinKarp1.search(txt);//ψάχνω μέσα στο κείμενο
       double time7 = timer7.elapsedTime();//αποθηκεύω τον χρόνο που έκανε μέχρι την στιγμή που τελείωσε η αναζήτηση
       StdOut.println("Searching method used: Rabin-Karp");//ο αλγόριθμος που χρησιμοποιήθηκε
       StdOut.println("Position in text: "+offset7);//η θέση που εντοπίστηκε η 1η εμφάνιση του pattern στο text
       StdOut.println("Elapsed time:"+ time7+"s");//ο χρόνος που έτρεξε ο αλγόριθμος
       StdOut.println(" ");
     
      }
}

