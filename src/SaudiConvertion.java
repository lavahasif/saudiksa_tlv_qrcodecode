import javax.sound.sampled.AudioFormat;
import java.io.Console;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import static kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.string;

public class SaudiConvertion {


    public final String getTest() {
        String sellername = "Salah Hospital";
        String vatregistration = "31012239350000311123";
        String timestamp = "2023-01-01";
        String invoiceamount = "200.00";
        String vatamoun = "-125.00";
// outputlikethis        AQ5TYWxhaCBIb3NwaXRhbAIUMzEwMTIyMzkzNTAwMDAzMTExMjMDCjIwMjMtMDEtMDEEBjIwMC4wMAUHLTEyNS4wMA
        return this.getBase64(sellername, vatregistration, timestamp, invoiceamount, vatamoun);
    }

    public final String getBase64(String sellername, String vatregistration, String timestamp, String invoiceamount, String vatamoun) {
        byte[] seller = this.getTlvVAlue("1", sellername);
        byte[] vatno = this.getTlvVAlue("2", vatregistration);
        byte[] time = this.getTlvVAlue("3", timestamp);
        byte[] invamt = this.getTlvVAlue("4", invoiceamount);
        byte[] vatamt = this.getTlvVAlue("5", vatamoun);


        ByteBuffer bb = ByteBuffer.allocate(seller.length + vatno.length + time.length + invamt.length + vatamt.length);
        bb.put(seller);
        bb.put(vatno);
        bb.put(time);
        bb.put(invamt);
        bb.put(vatamt);
        byte[] result = bb.array();


        final String encoded = ConvertTobase64(result);
        return encoded;
    }

    private String ConvertTobase64(byte[] result) {
        final String encoded = Base64.getEncoder().encodeToString(result);
        System.out.println("encode" + " => " + encoded);
        return encoded;
    }

    public byte[] to_byte(String[] strs) {
        byte[] bytes = new byte[strs.length];
        for (int i = 0; i < strs.length; i++) {
            bytes[i] = Byte.parseByte(strs[i]);
        }
        return bytes;
    }

    public final byte[] getTlvVAlue(String tagnums, String tagvalue) {
        String[] tagnums_array = {tagnums};
        String[] taglength = {
                String.valueOf(tagvalue.length())};

        byte[] tagnum = to_byte(tagnums_array);
        byte[] tagvaluelength = to_byte(taglength);
        byte[] tagvalueb = tagvalue.getBytes(StandardCharsets.UTF_8);
        ByteBuffer bb = ByteBuffer.allocate(tagnum.length + tagvaluelength.length + tagvalueb.length);
        bb.put(tagnum);
        bb.put(tagvaluelength);
        bb.put(tagvalueb);
        byte[] result = bb.array();
        byte[] tlvVAlue = result;
        return tlvVAlue;
    }

    byte[] toPrimitives(Byte[] oBytes) {

        byte[] bytes = new byte[oBytes.length];
        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;

    }
}



