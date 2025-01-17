package proje;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Encryption_and_Decryption{
    private static Map<String, String> credentials = new HashMap<>();

    // random şekilde oluşturcak Encrypt kodlarıın kullanması gereken harfleri tanımlı olması ve düznelenmesi için kodları burda
    
    public static String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz><>|!é'^#%&+-*0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        boolean running = true;
        while (running) {
        	
            // Decryption ve random oluşturulcak Encrypt kodunun kodları burda
        	
            System.out.print("Lütfen Şifrelencek Veriyi Giriniz:");
            String username = scanner.nextLine();
            
            // Decryption kodunun  daha önce kullanılıp kullanılmadığını kontrol eden kodlar burda
            
            if (credentials.containsKey(username)) {
                System.out.println("Bu Veri Adı Zaten Kullanımda.Lütfen Farklı Bir Veri adı belirleyin.");
                continue;
            }
            
            String password = generatePassword();
            credentials.put(username, password); 
            System.out.println("Şifreniz:" + password);

            int correctCount = 0; 
            int wrongCount = 0; 
            while (true) {

            	
                System.out.print("Veri veya Şifreniz Giriniz:(Yeni bilgiler için 'Q' yazın, çıkmak için 'X' yazın):");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("X")) {
                    System.out.println("Programdan Çıkış Yapılıyor . . . :)");
                    running = false;
                    break;
                } else if (input.equalsIgnoreCase("Q")) {
                    break;
                }

                // sistemin Encrypt yapısnı sağlayan kodları burda
                
                if (credentials.containsKey(input)) {
                    System.out.println("Şifrelenmiş Veri:" + credentials.get(input));
                    correctCount++;
                } 
                
                //Sistemin Decryption yapısını sağlayan kodları burda 
                
                else if (credentials.containsValue(input)) {
                    for (Map.Entry<String, String> entry : credentials.entrySet()) {
                        if (entry.getValue().equals(input)) {
                            System.out.println("Veri Adı veya Şifreniz:" + entry.getKey());
                            correctCount++;
                            break;
                        }
                    }
                } else {
                    System.out.println("Girdiğiniz Veri Adına veya Şifreye ait Bir Kayıt Bulunamadı.");
                    wrongCount++;
                }
                
               //2 işlem yapıldıktan sonra sistemimizin  yeniden başlatmasını sağlayan kodlar burda
                
                if (correctCount >= 2) {
                    System.out.println("Sistem Yeniden başlatılıyor. . .");
                    correctCount = 0;
                    wrongCount = 0;
                    break;
                }
                
               //Dört hattalı işlem yapınca bizim kendi sistemmiizin çıkışını yapan kod burda
                
                if (wrongCount >= 4) {
                    System.out.println("4 yanlış giriş yaptınız. Program sonlandırılıyor . . .");
                    running = false;
                    break;
                }
            }
        }
        
        scanner.close();
    }
}
