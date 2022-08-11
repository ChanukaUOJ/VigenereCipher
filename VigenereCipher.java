import java.util.Scanner;
import java.util.ArrayList;

public class VigenereCipher {

    //method for newKey
    public static String makeNewKey(String key, String message){

        String newKey = "";
        for(int i=0; i<message.length()/key.length(); i++){
            newKey += key;
        }

        for(int i=0; i<message.length()%key.length(); i++){
            newKey += key.charAt(i);
        }

        return newKey;
    }

    //main Method
    public static void main(String[] args){

        char[] characterSet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        Scanner scan = new Scanner(System.in);
        System.out.println("1.Encrypt or \n2.Decrypt");
        int firstStep = scan.nextInt();

        //encrypt
        if(firstStep == 1){

            System.out.println("Enter the Key");
            String key = scan.next();
            scan.nextLine();
            System.out.println("Enter the Message");
            String message = scan.nextLine();

            String newKey = makeNewKey(key,message);

            ArrayList<Integer> sumList = new ArrayList<Integer>(message.length());

            for(int i=0; i<message.length(); i++){

                int sum = 0;

                for(int j=0; j<characterSet.length; j++){

                    if(message.charAt(i) == characterSet[j]){
                        sum += j;
                    }
                    if(newKey.charAt(i) == characterSet[j]){
                        sum += j;
                    }
                }

                sumList.add(sum);
            }

            //convert sumList values to Text
            String convertedText = "";

            for(int i=0; i<sumList.size(); i++){

                int val = sumList.get(i);

                if(val > 25){
                    val -= 26;
                }

                convertedText += characterSet[val];
            }

            System.out.print("Converted Text is : ");
            System.out.println(convertedText);

        }

        //Decrypt
        else if(firstStep == 2){

            System.out.println("Enter the key");
            String decKey = scan.next();
            scan.nextLine();
            System.out.println("Enter the Converted Text");
            String conText = scan.nextLine();

            String decNewKey = makeNewKey(decKey,conText);

            ArrayList<Integer> subList = new ArrayList<Integer>();

            for(int i=0; i<conText.length(); i++){
                int val1 = 0;
                int val2 = 0;
                for(int j=0; j<characterSet.length; j++){

                    if(conText.charAt(i) == characterSet[j]){
                        val1 += j;
                    }
                    if(decNewKey.charAt(i) == characterSet[j]){
                        val2 += j;
                    }
                }

                int sub = val1 - val2;

                if(sub < 0) {
                    sub += 26;
                }

                subList.add(sub);
            }

            String decryptedText = "";
            for(int i=0; i<subList.size(); i++){
                decryptedText += characterSet[subList.get(i)];
            }

            System.out.print("Decrypted Text is : " + decryptedText);

        } else {
            System.out.println("Enter a Valid Number");
        }

    }

}
