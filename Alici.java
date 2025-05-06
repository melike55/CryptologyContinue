import java.math.BigInteger;
import java.util.*;

public class Alici {

    public static void main(String[] args) throws Exception {
        // Alıcının anahtarını oluştur
        BigInteger p = BigInteger.probablePrime(1024, new Random());
        BigInteger q = BigInteger.probablePrime(1024, new Random());
        BigInteger g = new BigInteger(p.bitLength(), new Random());
        BigInteger y = g.modPow(q, p);

        // Göndericinin anahtarını al
        BigInteger y2 = new BigInteger(args[0]);

        // Mesajın özet bilgisini al
        byte[] sifre = new BigInteger(args[1]).toByteArray();

        // Mesajın özetini çöz
        byte[] hash = İmza.decrypt(sifre, y);

        // Mesajın özetini alıcıya gönder
        System.out.println("Şifrelenmiş mesaj özeti: " + sifre);

        // Mesajın özetini imzala
        boolean dogrulama = İmza.verify(hash, args[2], y);

        // İmzayı doğrula
        if (dogrulama) {
            // Mesajın özetini çöz
            byte[] mesaj = İmza.decrypt(sifre, y);

            // Mesajın özetini alıcıya gönder
            System.out.println("Mesaj: " + new String(mesaj));
        } else {
            System.out.println("İmza doğrulanamadı.");
        }
    }
}