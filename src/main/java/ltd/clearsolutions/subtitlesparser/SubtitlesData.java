package ltd.clearsolutions.subtitlesparser;

import java.util.List;

public record SubtitlesData(
        String number,
        String time,
        List<String> text) {
}
