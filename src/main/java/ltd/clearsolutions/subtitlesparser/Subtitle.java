package ltd.clearsolutions.subtitlesparser;

import java.util.List;

public record Subtitle(
        Integer number,
        long startTime,
        long endTime,
        List<String> text) {
}
