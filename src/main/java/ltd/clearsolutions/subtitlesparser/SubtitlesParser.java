package ltd.clearsolutions.subtitlesparser;

import ltd.clearsolutions.subtitlesparser.exception.SubtitleParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class SubtitlesParser {

    private static final Logger logger = LoggerFactory.getLogger(SubtitlesParser.class);

    private static final String NUMBERS = "(\\d+)";
    private static final String TIME = "([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3}).*([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3})";

    public List<Subtitle> getSubtitles(File file) throws SubtitleParserException {

        List<Subtitle> piecesOfSubtitles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            Subtitle subtitles = new Subtitle();

            while (nonNull(line = reader.readLine())) {
                if (line.matches(NUMBERS)) {
                    subtitles = new Subtitle();
                    subtitles.setNumber(line);
                    piecesOfSubtitles.add(subtitles);
                    continue;
                }
                if (line.matches(TIME)) {
                    subtitles.setTime(line);
                    continue;
                }
                if (!line.isEmpty() || !line.isBlank()) {
                    subtitles.addText(line);
                }
            }

            return piecesOfSubtitles;

        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
            throw new SubtitleParserException(e.getMessage(), e, file);
        }
    }

}
