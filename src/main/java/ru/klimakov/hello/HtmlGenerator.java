package ru.klimakov.hello;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

public class HtmlGenerator {
    public static final String TITLE = "Generate Password";
    public static final String HEAD = "<html><head><title>" + TITLE + "</title></head><body>";
    public static final String TAIL = "</body></html>";

    final byte[] buffer;
    int[] indices = new int[10];
    Random random = new Random();


    public HtmlGenerator() {
        buffer = generatePasswordPage().getBytes(Charset.forName("ASCII"));
    }

    public String generatePasswordPage() {
        StringBuilder builder = new StringBuilder();
        builder.append(HEAD);
        for (int i=0; i<10; i++) {
            builder.append("<p><b>Password: </b>");
            indices[i] = builder.length();
            generatePasswordLine(builder);
            builder.append("</p>");
        }
        builder.append(TAIL);
        return builder.toString();
    }

    private void generatePasswordLine(StringBuilder builder) {
        builder.append(UUID.randomUUID().toString().substring(0,8));
    }

    public byte[] generatePasswordPageBytes() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                int rand = random.nextInt(16);
                buffer[indices[i] + j] = (byte) (rand < 10
                        ? '0' + rand
                        : 'a' + rand - 10);
            }
        }
        return buffer;
    }
}
