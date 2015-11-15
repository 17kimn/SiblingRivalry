/**20. An ISBN (International Standard Book Number) has thirteen digits. The first twelve digits identify the country in which the book was printed, the publisher, and the individual book. The thirteenth digit is the “check digit.” It is chosen in such a way that 
	(d1 + 3d2 + d3  + 3d4 + … + d11 + 3d12 + d13) mod 10 = 0 
where d1, d2, d13 are the digits of the ISBN. “mod” stands for modulo division (same as % in Java). 

Note that if we simply took the sum of all the digits, the check digit would remain valid for any permutation of the digits. Different coefficients make the number invalid when any two consecutive digits are swapped, catching a common typo. 

Write a method
	
	public static boolean isValidISBN(String isbn) 

that returns true if the isbn represents a valid ISBN, false otherwise. Test your method thoroughly: use ISBNs from several books as well as several entries that are not valid ISBN numbers to test your method. Hint: the Character class has the static int method 
digit(char ch, int base) that returns the numeric value of the digit in the specified base. For example, Character.digit(‘7’, 10) returns 7.  */

public class Ch8 {
// here's my method, but it doesn't work 
	public static boolean isValidISBN(String isbn) {
        if(isbn.length() != 13)
            return false;
        
        int sum = 0;
		for(int i = 0; i < isbn.length(); i++)    {
            if(i % 2 == 0)
                sum += Character.digit(isbn.substring(i, i+1), 10);
            else
                sum += 3 * Character.digit(isbn.substring(i, i+1), 10);
        }
        return (sum % 10 == 0) ? true : false;

        /*
                String str = "";
        str += isbn.substring(0,3);
        str += isbn.substring(4, 5); 
        str += isbn.substring(6,8); 
        str += isbn.substring(9, 15); 
        str += isbn.substring(16, isbn.length());
        int sum = 0; 
        for(int i = 1; i < isbn.length(); i+=2) {
            sum += Integer.parseInt(str.substring(i, i+1)); 
        }
        for(int n = 0; n < isbn.length(); n+=2) {
            sum += 3*(Integer.parseInt(str.substring(n, n+1))); 
        }
        if((sum%10) == 0); 
            return true; 
        */
	}
}
