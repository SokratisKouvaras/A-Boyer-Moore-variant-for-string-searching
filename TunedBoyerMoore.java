public class TunedBoyerMoore {
	private int R;
	private char[] pattern;
	private String pat;
	private int[] bmBc;
	private int shift;
	
	public TunedBoyerMoore(char[] pattern , int R) {
			this.R=R;
			this.pattern = new char[pattern.length];
			for (int j = 0; j < pattern.length; j++)
				this.pattern[j] = pattern[j];
			bmBc = new int[R];
			for (int i = 0; i < R; i++) 
				bmBc[i]=pattern.length;
			for (int i = 0; i < pattern.length - 1; i++) 	
				bmBc[pattern[i]] =pattern.length - i - 1;
			this.shift=bmBc[pattern[pattern.length - 1]];
			bmBc[pattern[pattern.length - 1]] = 0;
      }
	
	public TunedBoyerMoore(String pat) {
		  	this.R = 256;
		  	this.pat = pat;
		  	bmBc = new int[R];
		  	/*preprocessing*/
		  	for (int i = 0; i < R; i++) 
		  		bmBc[i] = pat.length()
		  	for (int i = 0; i < pat.length() - 1; i++)
		  		bmBc[pat.charAt(i)] =pat.length() - i - 1;
		  	this.shift=bmBc[pat.charAt(pat.length()-1)];
		  	bmBc[pat.charAt(pat.length() - 1)] = 0;
	        }
	 
	public  int search(String text) {
			String tuned_text=tuned_text(text);
		    int m = pat.length();
		    int n = tuned_text.length();
		    int i = 0;
		    int k = 0;
		    while (i <= n - m) {
		          k = bmBc[tuned_text.charAt(i + m - 1)];
		          while (k != 0) {
		        	i += k;k = bmBc[tuned_text.charAt(i + m - 1)];
		            i += k;k = bmBc[tuned_text.charAt(i + m - 1)];
		            i += k;k = bmBc[tuned_text.charAt(i + m - 1)];
		          }
		          if (compare(pat, tuned_text, i)) 
		        	  return i;
		          						
		          i += shift;
		    	}	
		        return n-m;
		     }				   
	
	
	private String tuned_text(String text) {
			String tuned_text=text;
    	    for (int i = 0; i <pat.length(); i++) 
    	    	tuned_text=tuned_text+pat.charAt(pat.length()-1);
    	    return tuned_text;
      }
	
	
	
	private static boolean compare(String x, String y, int z) {
	        for (int i = 0; i < x.length(); i++) 
	             if (x.charAt(i) != y.charAt(z++)) 
	        	    return false;
	        return true;
	      }
	  
	public int search(char[] text) {
		char[] tuned_text=tuned_text(text);
	        int m = pattern.length;
	        int n = tuned_text.length;
	        int i = 0;
	        int k = 0;
	        while (i <= n - m) {
	          k = bmBc[tuned_text[i + m - 1]];
	          while (k != 0) {
	            i += k;k = bmBc[tuned_text[i + m - 1]];
	            i += k;k = bmBc[tuned_text[i + m - 1]];
	            i += k;k = bmBc[tuned_text[i + m - 1]];
	          }
	          if (compare(pattern, tuned_text, i))
	        	  return i;         
	          i += shift;
	        }
	        return n-m;
	      }			   
	
	
    private char[] tuned_text(char[] text) {
    	    char[] tuned_text=new char[text.length+pattern.length];
    	    for (int i = 0; i <text.length; i++) 
    	    	tuned_text[i]=text[i];   	    
    	    for (int i = 0; i <pattern.length; i++) 
    	    	tuned_text[text.length+i]=pattern[pattern.length-1];
    	    return tuned_text;
      }
    
    private static boolean compare(char[] x, char[] y, int z) {
        	for (int i = 0; i < x.length; i++) 
        		if (x[i] != y[z++]) 
        			return false;
        	return true;
      }  
    }
