package ltd.clearsolutions.subtitlesparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

public class SubtitlesParser {

    private static final Logger logger = LoggerFactory.getLogger(SubtitlesParser.class);

    private static final String NUMBERS = "(\\d+)";
    private static final String TIME = "([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3}).*([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3})";

    public List<Subtitle> subtitlesParser(File file) {

        List<Subtitle> piecesOfSubtitles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Subtitle subtitles = new Subtitle();

            while (nonNull(line = reader.readLine())) {
                if (line.matches(NUMBERS)) {
                    subtitles = new Subtitle();
                    piecesOfSubtitles.add(subtitles);
                    subtitles.setNumber(line);
                } else if (line.matches(TIME)) {
                    subtitles.setTime(line);
                } else if (!line.isEmpty() || !line.isBlank()) {
                    subtitles.addText(line);
                }
            }

            return piecesOfSubtitles;

        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }
        return Collections.emptyList();
    }
}
