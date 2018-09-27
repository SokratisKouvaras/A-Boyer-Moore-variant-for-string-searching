public class TunedBoyerMoore {
	private int R;//το μέγεθος του αλφαβήτου 
	private char[] pattern;//το pattern με την μορφή πίνακα 
	private String pat;//το pattern με την μορφή αλφαριθμητικού
	private int[] bmBc;//ο πίνακας που κρατάει τα αποτελέσματα της συνάρτηση bad-character shift απο τον αλγόριθμο Boyer-Moore
	private int shift;//η μεταβλητή shift(από τα "ιδιαίτερα" χαρακτηριστικά του αλγορίθμου
	
	/*Ο κατασκευαστής της κλάσης,εάν σαν όρισμα δοθεί το pattern με μορφή
	 *πίνακα χαρακτήρων,και R το μέγεθος του αλφαβήτου */
	
	public TunedBoyerMoore(char[] pattern , int R) {
			this.R=R;
			this.pattern = new char[pattern.length];
			for (int j = 0; j < pattern.length; j++)
				this.pattern[j] = pattern[j];
			bmBc = new int[R];
			for (int i = 0; i < R; i++)//*Σημείωση* Ο Sedgewick έδινε την τιμή -1 αλλά ακολούθησα τον αλγόριθμο στο site επακριβώς. 
				bmBc[i]=pattern.length;//Ολα τα στοιχεία του πίνακα bmBc παίρνουν τιμή,ίδια με το πλήθος των ψηφίων του pattern
			for (int i = 0; i < pattern.length - 1; i++)//Τα στοιχεία του αλφαβήτου που εμφανίζονται στο pattern παίρνουν τιμή 	
				bmBc[pattern[i]] =pattern.length - i - 1;//ανάλογα με το πόσες φορές επαναλαμβάνονται μεσα στο pattern
			this.shift=bmBc[pattern[pattern.length - 1]];//το bmBc[x[m-1]] αποθηκεύεται στη μεταβλητή shift
			bmBc[pattern[pattern.length - 1]] = 0;//και του δίνουμε την τιμή 0
      }
	
	/*Ο κατασκευαστής της κλάσης,εάν σαν όρισμα δοθεί το pattern με μορφή
	 * string*/
	
	public TunedBoyerMoore(String pat) {
		  	this.R = 256;
		  	this.pat = pat;
		  	bmBc = new int[R];
		  	/*preprocessing*/
		  	for (int i = 0; i < R; i++) //*Σημείωση* Ο Sedgewick έδινε την τιμή -1 αλλά ακολούθησα τον αλγόριθμο στο site επακριβώς.
		  		bmBc[i] = pat.length();//Ολα τα στοιχεία του πίνακα bmBc παίρνουν τιμή,ίδια με το πλήθος των ψηφίων του pattern
		  	for (int i = 0; i < pat.length() - 1; i++)//Τα στοιχεία του αλφαβήτου που εμφανίζονται στο pattern παίρνουν τιμή
		  		bmBc[pat.charAt(i)] =pat.length() - i - 1;//ανάλογα με το πόσες φορές επαναλαμβάνονται μεσα στο pattern
		  	this.shift=bmBc[pat.charAt(pat.length()-1)];//το bmBc[x[m-1]] αποθηκεύεται στη μεταβλητή shift
		  	bmBc[pat.charAt(pat.length() - 1)] = 0;//και του δίνουμε την τιμή 0
	        }
	 
	public  int search(String text) {
			String tuned_text=tuned_text(text);//Προσθέτουμε στο τέλος του κειμένο m εμφανίσεις του τελευταίου χαρακτήρα του pattern
		    int m = pat.length();
		    int n = tuned_text.length();
		    int i = 0;//To "pointer" που ακολουθεί το κείμενο και συγκρίνει με το pattern
		    int k = 0;
		    while (i <= n - m) {
		          k = bmBc[tuned_text.charAt(i + m - 1)];//το bmBc του χαρακτήρα που βρίσκεται στην ίδια θέση με το τελευταιο ψηφίο
		          while (k != 0) {//του pattern.Αν είναι 0 σημαίνει οτι τα τελευταία ψηφία ταυτίζονται.Αν δεν ταυτίζονται αυξάνει το
		        	i += k;k = bmBc[tuned_text.charAt(i + m - 1)];//το ι("μετατοπίζει" το pattern δηλαδή) και ελέγχει το
		            i += k;k = bmBc[tuned_text.charAt(i + m - 1)];//ψηφίο μέχρι το bmbc=0 δηλαδή μέχρι να βρεί το τελευταίο χαρακτήρα
		            i += k;k = bmBc[tuned_text.charAt(i + m - 1)];//του pattern
		          }
		          if (compare(pat, tuned_text, i))//συγκρίνει το pattern με τους επόμενους χαρακτήρες απο τη θέση ι που σταμάτησε το  
		        	  return i;//"pointer.Εάν ταυτίζονται θα επιστρέψει τη θέση που εντόπισε το pattern
		          						
		          i += shift;//Εαν δεν ταυτίζονται κάνει ένα shift και ξαναρχίζει να ψάχνει
		    	}	
		        return n-m;//εάν δεν εντοπίσει το pattern θα επιστρέψει το τέλος του κειμένου.n-m επειδή προσθέσαμε και m εμφανίσεις
		     }				// του τελευταίου χαρακτήρα του pattern
   
	/*Μια από τις προαπαιτήσεις αυτού του αλγορίθμου είναι η προσθήκη m εμφανίσεων του
     *τελευταίου χαρακτήρα του αλφαριθμητικού που ψάχνουμε (όπου m το μήκος της λέξης)*/
	
	private String tuned_text(String text) {
			String tuned_text=text;
    	    for (int i = 0; i <pat.length(); i++) 
    	    	tuned_text=tuned_text+pat.charAt(pat.length()-1);
    	    return tuned_text;
      }
	
	/*H συνάρτηση compare συγκρίνει τους χαρακτήρες του αλφαριθμητικού χ με το αλφαριθμητικο
	 *y απο την θέση z και μετά*/
	
	private static boolean compare(String x, String y, int z) {
	        for (int i = 0; i < x.length(); i++) 
	             if (x.charAt(i) != y.charAt(z++))//Μολις βρει το πρωτο χαρακτηρα στον οποιον διαφερουν επιστρεφει false 
	        	    return false;
	        return true;//Αν όλα συμφωνουν (εχουμε βρει τη λέξη δηλαδή) θα επιστρέψει true
	      }
	  
	public int search(char[] text) {
		char[] tuned_text=tuned_text(text);//Προσθέτουμε στο τέλος του κειμένο m εμφανίσεις του τελευταίου χαρακτήρα του pattern
	        int m = pattern.length;
	        int n = tuned_text.length;
	        int i = 0;//To "pointer" που ακολουθεί το κείμενο και συγκρίνει με το pattern
	        int k = 0;
	        while (i <= n - m) {
	          k = bmBc[tuned_text[i + m - 1]];//το bmBc του χαρακτήρα που βρίσκεται στην ίδια θέση με το τελευταιο ψηφίο
	          while (k != 0) {//του pattern.Αν είναι 0 σημαίνει οτι τα τελευταία ψηφία ταυτίζονται.Αν δεν ταυτίζονται αυξάνει το
	            i += k;k = bmBc[tuned_text[i + m - 1]];//το ι("μετατοπίζει" το pattern δηλαδή) και ελέγχει το
	            i += k;k = bmBc[tuned_text[i + m - 1]];//ψηφίο μέχρι το bmbc=0 δηλαδή μέχρι να βρεί το τελευταίο χαρακτήρα
	            i += k;k = bmBc[tuned_text[i + m - 1]];//του pattern
	          }
	          if (compare(pattern, tuned_text, i))//συγκρίνει το pattern με τους επόμενους χαρακτήρες απο τη θέση ι που σταμάτησε το 
	        	  return i;//"pointer.Εάν ταυτίζονται θα επιστρέψει τη θέση που εντόπισε το pattern          
	          i += shift;//Εαν δεν ταυτίζονται κάνει ένα shift και ξαναρχίζει να ψάχνει
	        }
	        return n-m;//εάν δεν εντοπίσει το pattern θα επιστρέψει το τέλος του κειμένου.n-m επειδή προσθέσαμε και m εμφανίσεις
	      }			   // του τελευταίου χαρακτήρα του pattern
	
	/*Μία απο τις ιδιαιτερότητες αυτού του αλγορίθμου είναι οτι στο τέλος του κειμένου
	 πρέπει να προστεθούν m εμφανίσεις του τελευταίου γράμματος του pattern
     H συνάρτηση tuned_text δημιουργεί ένα πίνακα διαστάσεω m+n,αντιγράφει στα πρώτα 
     στοιχεία του το text και στο τέλος προσθέτει m φορές το τελευταίο γράμμα του pattern*/ 
	
    private char[] tuned_text(char[] text) {
    	    char[] tuned_text=new char[text.length+pattern.length];//Δημιουργώ ένα καινουργιο πίνακα με μέγεθος οσο το text + το πλήθος
    	    for (int i = 0; i <text.length; i++) //των στοιχείων του pattern,
    	    	tuned_text[i]=text[i];   	    
    	    for (int i = 0; i <pattern.length; i++) //τα τελευταία m στοιχεία θα πρέπει να είναι ο τελευταίος χαρακτηρας του pattern
    	    	tuned_text[text.length+i]=pattern[pattern.length-1];
    	    return tuned_text;
      }
    /*H συνάρτηση compare συγκρίνει τους χαρακτήρες του πίνακα χ με τον πίνακα
	 *y απο την θέση z και μετά*/  
    private static boolean compare(char[] x, char[] y, int z) {
        	for (int i = 0; i < x.length; i++) 
        		if (x[i] != y[z++]) 
        			return false;//Μολις βρει το πρωτο χαρακτηρα στον οποιον διαφερουν επιστρεφει false
        	return true;//Αν όλα συμφωνουν (εχουμε βρει τη λέξη δηλαδή) θα επιστρέψει true
      }  
    }