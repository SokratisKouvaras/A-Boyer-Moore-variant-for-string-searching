public class TunedBoyerMoore {
	private int R;//�� ������� ��� ��������� 
	private char[] pattern;//�� pattern �� ��� ����� ������ 
	private String pat;//�� pattern �� ��� ����� ��������������
	private int[] bmBc;//� ������� ��� ������� �� ������������ ��� ��������� bad-character shift ��� ��� ��������� Boyer-Moore
	private int shift;//� ��������� shift(��� �� "���������" �������������� ��� ����������
	
	/*� ������������� ��� ������,��� ��� ������ ����� �� pattern �� �����
	 *������ ����������,��� R �� ������� ��� ��������� */
	
	public TunedBoyerMoore(char[] pattern , int R) {
			this.R=R;
			this.pattern = new char[pattern.length];
			for (int j = 0; j < pattern.length; j++)
				this.pattern[j] = pattern[j];
			bmBc = new int[R];
			for (int i = 0; i < R; i++)//*��������* � Sedgewick ����� ��� ���� -1 ���� ���������� ��� ��������� ��� site ���������. 
				bmBc[i]=pattern.length;//��� �� �������� ��� ������ bmBc �������� ����,���� �� �� ������ ��� ������ ��� pattern
			for (int i = 0; i < pattern.length - 1; i++)//�� �������� ��� ��������� ��� ������������ ��� pattern �������� ���� 	
				bmBc[pattern[i]] =pattern.length - i - 1;//������� �� �� ����� ����� ���������������� ���� ��� pattern
			this.shift=bmBc[pattern[pattern.length - 1]];//�� bmBc[x[m-1]] ������������ ��� ��������� shift
			bmBc[pattern[pattern.length - 1]] = 0;//��� ��� ������� ��� ���� 0
      }
	
	/*� ������������� ��� ������,��� ��� ������ ����� �� pattern �� �����
	 * string*/
	
	public TunedBoyerMoore(String pat) {
		  	this.R = 256;
		  	this.pat = pat;
		  	bmBc = new int[R];
		  	/*preprocessing*/
		  	for (int i = 0; i < R; i++) //*��������* � Sedgewick ����� ��� ���� -1 ���� ���������� ��� ��������� ��� site ���������.
		  		bmBc[i] = pat.length();//��� �� �������� ��� ������ bmBc �������� ����,���� �� �� ������ ��� ������ ��� pattern
		  	for (int i = 0; i < pat.length() - 1; i++)//�� �������� ��� ��������� ��� ������������ ��� pattern �������� ����
		  		bmBc[pat.charAt(i)] =pat.length() - i - 1;//������� �� �� ����� ����� ���������������� ���� ��� pattern
		  	this.shift=bmBc[pat.charAt(pat.length()-1)];//�� bmBc[x[m-1]] ������������ ��� ��������� shift
		  	bmBc[pat.charAt(pat.length() - 1)] = 0;//��� ��� ������� ��� ���� 0
	        }
	 
	public  int search(String text) {
			String tuned_text=tuned_text(text);//����������� ��� ����� ��� ������� m ���������� ��� ���������� ��������� ��� pattern
		    int m = pat.length();
		    int n = tuned_text.length();
		    int i = 0;//To "pointer" ��� ��������� �� ������� ��� ��������� �� �� pattern
		    int k = 0;
		    while (i <= n - m) {
		          k = bmBc[tuned_text.charAt(i + m - 1)];//�� bmBc ��� ��������� ��� ��������� ���� ���� ���� �� �� ��������� �����
		          while (k != 0) {//��� pattern.�� ����� 0 �������� ��� �� ��������� ����� �����������.�� ��� ����������� ������� ��
		        	i += k;k = bmBc[tuned_text.charAt(i + m - 1)];//�� �("�����������" �� pattern ������) ��� ������� ��
		            i += k;k = bmBc[tuned_text.charAt(i + m - 1)];//����� ����� �� bmbc=0 ������ ����� �� ���� �� ��������� ���������
		            i += k;k = bmBc[tuned_text.charAt(i + m - 1)];//��� pattern
		          }
		          if (compare(pat, tuned_text, i))//��������� �� pattern �� ���� ��������� ���������� ��� �� ���� � ��� ��������� ��  
		        	  return i;//"pointer.��� ����������� �� ���������� �� ���� ��� �������� �� pattern
		          						
		          i += shift;//��� ��� ����������� ����� ��� shift ��� ���������� �� ������
		    	}	
		        return n-m;//��� ��� ��������� �� pattern �� ���������� �� ����� ��� ��������.n-m ������ ���������� ��� m ����������
		     }				// ��� ���������� ��������� ��� pattern
   
	/*��� ��� ��� ������������� ����� ��� ���������� ����� � �������� m ���������� ���
     *���������� ��������� ��� �������������� ��� �������� (���� m �� ����� ��� �����)*/
	
	private String tuned_text(String text) {
			String tuned_text=text;
    	    for (int i = 0; i <pat.length(); i++) 
    	    	tuned_text=tuned_text+pat.charAt(pat.length()-1);
    	    return tuned_text;
      }
	
	/*H ��������� compare ��������� ���� ���������� ��� �������������� � �� �� �������������
	 *y ��� ��� ���� z ��� ����*/
	
	private static boolean compare(String x, String y, int z) {
	        for (int i = 0; i < x.length(); i++) 
	             if (x.charAt(i) != y.charAt(z++))//����� ���� �� ����� ��������� ���� ������ ��������� ���������� false 
	        	    return false;
	        return true;//�� ��� ��������� (������ ���� �� ���� ������) �� ���������� true
	      }
	  
	public int search(char[] text) {
		char[] tuned_text=tuned_text(text);//����������� ��� ����� ��� ������� m ���������� ��� ���������� ��������� ��� pattern
	        int m = pattern.length;
	        int n = tuned_text.length;
	        int i = 0;//To "pointer" ��� ��������� �� ������� ��� ��������� �� �� pattern
	        int k = 0;
	        while (i <= n - m) {
	          k = bmBc[tuned_text[i + m - 1]];//�� bmBc ��� ��������� ��� ��������� ���� ���� ���� �� �� ��������� �����
	          while (k != 0) {//��� pattern.�� ����� 0 �������� ��� �� ��������� ����� �����������.�� ��� ����������� ������� ��
	            i += k;k = bmBc[tuned_text[i + m - 1]];//�� �("�����������" �� pattern ������) ��� ������� ��
	            i += k;k = bmBc[tuned_text[i + m - 1]];//����� ����� �� bmbc=0 ������ ����� �� ���� �� ��������� ���������
	            i += k;k = bmBc[tuned_text[i + m - 1]];//��� pattern
	          }
	          if (compare(pattern, tuned_text, i))//��������� �� pattern �� ���� ��������� ���������� ��� �� ���� � ��� ��������� �� 
	        	  return i;//"pointer.��� ����������� �� ���������� �� ���� ��� �������� �� pattern          
	          i += shift;//��� ��� ����������� ����� ��� shift ��� ���������� �� ������
	        }
	        return n-m;//��� ��� ��������� �� pattern �� ���������� �� ����� ��� ��������.n-m ������ ���������� ��� m ����������
	      }			   // ��� ���������� ��������� ��� pattern
	
	/*��� ��� ��� �������������� ����� ��� ���������� ����� ��� ��� ����� ��� ��������
	 ������ �� ���������� m ���������� ��� ���������� ��������� ��� pattern
     H ��������� tuned_text ���������� ��� ������ ��������� m+n,���������� ��� ����� 
     �������� ��� �� text ��� ��� ����� ��������� m ����� �� ��������� ������ ��� pattern*/ 
	
    private char[] tuned_text(char[] text) {
    	    char[] tuned_text=new char[text.length+pattern.length];//��������� ��� ���������� ������ �� ������� ��� �� text + �� ������
    	    for (int i = 0; i <text.length; i++) //��� ��������� ��� pattern,
    	    	tuned_text[i]=text[i];   	    
    	    for (int i = 0; i <pattern.length; i++) //�� ��������� m �������� �� ������ �� ����� � ���������� ���������� ��� pattern
    	    	tuned_text[text.length+i]=pattern[pattern.length-1];
    	    return tuned_text;
      }
    /*H ��������� compare ��������� ���� ���������� ��� ������ � �� ��� ������
	 *y ��� ��� ���� z ��� ����*/  
    private static boolean compare(char[] x, char[] y, int z) {
        	for (int i = 0; i < x.length; i++) 
        		if (x[i] != y[z++]) 
        			return false;//����� ���� �� ����� ��������� ���� ������ ��������� ���������� false
        	return true;//�� ��� ��������� (������ ���� �� ���� ������) �� ���������� true
      }  
    }