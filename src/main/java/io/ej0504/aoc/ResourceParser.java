package io.ej0504.aoc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class ResourceParser {

    public List<String> parse(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try {
                File file = new File(resource.toURI());

                return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            } catch (IOException | URISyntaxException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
