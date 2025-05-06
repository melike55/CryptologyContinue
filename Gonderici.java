import java.math.BigInteger;
import java.util.*;

public class Gonderici {

    public static void main(String[] args) throws Exception {
        // Göndericinin anahtarını oluştur
        BigInteger p = BigInteger.probablePrime(1024, new Random());
        BigInteger q = BigInteger.probablePrime(1024, new Random());
        BigInteger g = new BigInteger(p.bitLength(), new Random());
        BigInteger y = g.modPow(q, p);

        // Alıcının anahtarını oluştur
        BigInteger a = new BigInteger(1024, new Random());
        BigInteger x = g.modPow(a, p);

        // Göndericinin anahtarını alıcıya gönder
        System.out.println("Göndericinin anahtarı: " + y);

        // Mesaj oluştur
        String mesaj = "Merhaba Dünya!";

        // Mesajın özet bilgisini oluştur
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(mesaj.getBytes());

        // Mesajın özetini şifrele
        byte[] sifre = İmza.encrypt(hash, y);

        // Mesajın özetini alıcıya gönder
        System.out.println("Şifrelenmiş mesaj özeti: " + sifre);

        // Mesajın özetini imzala
        İmza.sign(sifre, y);

        // İmzayı alıcıya gönder
        byte[] imza = İmza.sign(sifre, y);
        System.out.println("İmza: " + imza);
    }
}