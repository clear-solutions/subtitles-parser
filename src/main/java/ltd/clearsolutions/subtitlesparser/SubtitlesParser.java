package ltd.clearsolutions.subtitlesparser;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SubtitlesParser {

    private static final Logger logger = LoggerFactory.getLogger(SubtitlesParser.class);

    private static final String NUMBERS = "(\\d+)";
    private static final String TIME = "([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3}).*([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3})";
    List<Subtitle> piecesOfSubtitles = new ArrayList<>();

    public void subtitlesParser(File file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int index = 0;
            Subtitle subtitles = new Subtitle();

            while ((line = reader.readLine()) != null) {

                if (line.matches(NUMBERS)) {
                    index++;
                    if (index > 1) {
                        piecesOfSubtitles.add(subtitles);
                        subtitles = new Subtitle();
                    }
                    subtitles.setNumber(line);
                } else if (line.matches(TIME)) {
                    subtitles.setTime(line);
                } else if (line.length() > 0) {
                    subtitles.addText(removeHTML(line));
                }
            }

            piecesOfSubtitles.add(subtitles);

        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }
    }

    private String removeHTML(String line) {
        return Jsoup.parse(line).text();
    }
}
