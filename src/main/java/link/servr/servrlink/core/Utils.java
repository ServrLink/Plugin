package link.servr.servrlink.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Utils {

    public static String readURLToString(URL url) throws IOException {
        StringBuilder builder = new StringBuilder();

        try(InputStream in = url.openStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            while((line = br.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        }

        return builder.substring(0, builder.length() - 1); // Remove final \n
    }
}
