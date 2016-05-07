package database;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MakeFileURL {
    public String getURL(String path) throws MalformedURLException {
        File file = new File(path);
        URL fileUrl = file.toURI().toURL();
        System.out.println("URL:" + fileUrl);
        return fileUrl.toString();
    }
}
