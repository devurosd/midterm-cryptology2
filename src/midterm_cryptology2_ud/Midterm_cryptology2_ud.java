
package midterm_cryptology2_ud;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Midterm_cryptology2_ud {
//OTP
    public static void main(String[] args) {
        String poruka = "Kolokvijum iz Kriptologije 2";
        String kljuc = generisiRandomNiz(poruka);
        
        System.out.println("Originalna poruka:           " + poruka);
        System.out.println("Originalna poruka - bitovi:  " + byte2bin(konvertujKarakterASCIIByte(poruka)));
        System.out.println("Kljuc:                       " + kljuc);
        System.out.println("XOR-------------------------------------------------------------------------------------------");
        String sifrovanaPoruka = sifrujOTP(poruka, kljuc);
        System.out.println("Sifrovana poruka u bitovima: " + sifrovanaPoruka);
        System.out.println("Kljuc:                       " + kljuc);
        System.out.println("XOR-------------------------------------------------------------------------------------------");
        String desifrovanaPoruka = desifrujOTP(sifrovanaPoruka, kljuc);
        System.out.println("Desifrovana poruka - bitovi: " + desifrovanaPoruka);
        System.out.println("Desifrovana poruka:          " + konvertujBitoveUString(desifrovanaPoruka));
        
        
        
    }
    
    
    public static String sifrujOTP(String poruka, String kljuc){
        String sifrat = "";
        String porukaNizBitova = byte2bin(konvertujKarakterASCIIByte(poruka));  //String -> bajtovi -> bitovi
        String kljucNizBitova = kljuc;
        for(int i = 0; i < kljucNizBitova.length(); i++){
            if(porukaNizBitova.charAt(i) == kljucNizBitova.charAt(i)){
                sifrat += "0";
            }else{
                sifrat += "1";
            }
        }
        
        sifrat = konvertujByteASCIIKarakter(sifrat.getBytes());
        return sifrat;
    }
    
    public static String desifrujOTP(String sifrat, String kljuc){
        String desifrovanaPoruka = "";
        
        for(int i = 0; i < kljuc.length(); i++){
            if(sifrat.charAt(i) == kljuc.charAt(i)){
                desifrovanaPoruka += "0";
            }else{
                desifrovanaPoruka += "1";
            }
        }
        
        return desifrovanaPoruka;
    }
    

    public static String konvertujBitoveUString(String bitovi){
        String rez = "";
        char nextChar;

        for (int i = 0, pocetak = 0, kraj = 8; i < (bitovi.length() / 8); i++, pocetak += 8, kraj += 8){
            nextChar = (char) Integer.parseInt(bitovi.substring(pocetak, kraj), 2);
            rez += nextChar;
        }
        
        return rez;
    }
    
    public static byte[] konvertujKarakterASCIIByte(String poruka){
        byte[] niz = poruka.getBytes(StandardCharsets.US_ASCII);
        return niz;
    }
    
    public static String konvertujByteASCIIKarakter(byte niz[]){
        String rez = new String(niz, StandardCharsets.US_ASCII);
        return rez;
    }
    
    public static String generisiRandomNiz(String poruka){
        Random r = new Random();
        String s = byte2bin((konvertujKarakterASCIIByte(poruka)));
        int duzinaPoruke = s.length();
        String niz = "";
        for (int i = 0; i < duzinaPoruke; i++) {
            int randomBit = r.nextInt() % 2;
            if(randomBit == -1){
                randomBit = 1;
            }
            niz += randomBit;
        }
        return niz;
    }
    
    public static String byte2bin(byte podatak[]){
        String bin = "";
        for(byte c: podatak){
            bin += String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0');
        }
        
        return bin;
    }
    
    
}
