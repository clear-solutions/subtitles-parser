package ltd.clearsolutions.subtitlesparser;

import ltd.clearsolutions.subtitlesparser.exception.IncorrectTimeDataException;
import org.jsoup.Jsoup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Subtitle {
    Integer number;
    long startTime;
    long endTime;
    List<String> text = new ArrayList<>();

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



