package ltd.clearsolutions.subtitlesparser;

import ltd.clearsolutions.subtitlesparser.exception.IncorrectTimeDataException;
import ltd.clearsolutions.subtitlesparser.exception.SubtitleParserException;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import static java.util.Objects.nonNull;

public class SubtitlesParser {

    private static final Logger logger = LoggerFactory.getLogger(SubtitlesParser.class);

    private static final String NUMBERS = "(\\d+)";
    private static final String TIME = "([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3}).*([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3})";

    public List<Subtitle> getSubtitles(File file) throws SubtitleParserException {

        List<ProcessingSubtitle> piecesOfSubtitles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            ProcessingSubtitle subtitles = new ProcessingSubtitle();

            while (nonNull(line = reader.readLine())) {
                if (line.matches(NUMBERS)) {
                    subtitles = new ProcessingSubtitle();
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

            return convectorNormalToRecord(piecesOfSubtitles);

        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
            throw new SubtitleParserException(e.getMessage(), e, file);
        }
    }

    static class ProcessingSubtitle {
        private Integer number;
        private long startTime;
        private long endTime;
        private final List<String> text = new ArrayList<>();

        public void addText(String line) {
            text.add(removeHTML(line));
        }

        public void setNumber(String number) {
            this.number = Integer.parseInt(number);
        }

        public long getStartTime() {
            return startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setTime(String line) {
            SimpleDateFormat mSdf = new SimpleDateFormat("hh:mm:ss,SSS");
            mSdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            List<String> temp = Arrays.stream(line.split("-->")).map(String::trim).toList();

            try {
                startTime = mSdf.parse(temp.get(0)).getTime();
                endTime = mSdf.parse(temp.get(1)).getTime();
            } catch (ParseException e) {
                throw new IncorrectTimeDataException("The time type is incorrect");
            }
        }

        private String removeHTML(String line) {
            return Jsoup.parse(line).text();
        }
    }

    private List<Subtitle> convectorNormalToRecord(List<ProcessingSubtitle> processingSubtitles){
        List<Subtitle> piecesOfSubtitles = new ArrayList<>();
        for (ProcessingSubtitle processingSubtitle : processingSubtitles) {
            piecesOfSubtitles.add(new Subtitle(processingSubtitle.number, processingSubtitle.startTime, processingSubtitle.endTime, processingSubtitle.text));
        }
        return piecesOfSubtitles;
    }

}
