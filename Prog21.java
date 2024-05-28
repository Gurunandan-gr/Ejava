import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Prog21 {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Lenovo\\Desktop\\2 SEM MCA\\EJava\\Ass5\\hii.txt";
        countCharactersInFile(filePath);
    }

    public static void countCharactersInFile(String filePath) {
        int vowelCount = 0,consonantCount = 0,digitCount = 0,specialCount = 0;

        String vowels = "aeiouAEIOU";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            int c;
            while ((c = br.read()) != -1) 
            {
                char ch = (char) c;
                if (Character.isLetter(ch)) 
                    if (vowels.indexOf(ch) == -1) 
                        consonantCount += 1;
                    else
                        vowelCount += 1; 
                else if (Character.isDigit(ch)) 
                    digitCount += 1;
                else
                    specialCount += 1;
            }
        } catch (IOException e) {
            System.err.println("Error: The file at " + filePath + " was not found.");
            e.printStackTrace();
        }

        System.out.println("Vowels: " + vowelCount);
        System.out.println("Consonants: " + consonantCount);
        System.out.println("Digits: " + digitCount);
        System.out.println("Special Characters: " + specialCount);
    }
}
