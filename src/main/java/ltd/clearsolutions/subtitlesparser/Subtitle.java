package ltd.clearsolutions.subtitlesparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Subtitle {
    Integer number;
    Double startTime;
    Double endTime;
    List<String> text = new ArrayList<>();

    public void addText(String line) {
        text.add(line);
    }

    public void setNumber(String number) {
        this.number = Integer.parseInt(number);
    }

    public Double getStartTime() {
        return startTime;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setTime(String line) {
        SimpleDateFormat mSdf = new SimpleDateFormat("hh:mm:ss,SSS");
        mSdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        List<String> temp = Arrays.stream(line.split("-->")).map(String::trim).toList();

        try {
            startTime = mSdf.parse(temp.get(0)).getTime() / 1000.0;
            endTime = mSdf.parse(temp.get(1)).getTime() / 1000.0;
        } catch (ParseException e) {
            throw new IncorrectTimeData("The time type is incorrect");
        }
    }

    public static class IncorrectTimeData extends RuntimeException {
        public IncorrectTimeData(String message) {
            super(message);
        }
    }
}



