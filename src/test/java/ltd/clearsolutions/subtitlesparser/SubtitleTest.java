package ltd.clearsolutions.subtitlesparser;

import ltd.clearsolutions.subtitlesparser.exception.IncorrectTimeDataException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubtitleTest {

    @Test
    void setTime_CorrectTimeString_TimeInMillisecond() {
        String time = "00:00:49,048 --> 00:00:51,801";
        SubtitlesParser.ProcessingSubtitle processingSubtitle = new SubtitlesParser.ProcessingSubtitle();

        processingSubtitle.setTime(time);
        assertThat(49048).isEqualTo(processingSubtitle.getStartTime());
        assertThat(51801).isEqualTo(processingSubtitle.getEndTime());
    }

    @Test
    void setTime_IncorrectTimeString_ExceptionIncorrectTimeData() {
        String time = "00:00:49 --> 00:00:51,801";
        SubtitlesParser.ProcessingSubtitle processingSubtitle = new SubtitlesParser.ProcessingSubtitle();

        IncorrectTimeDataException thrown = assertThrows(IncorrectTimeDataException.class, () -> processingSubtitle.setTime(time));
        assertThat(thrown).hasMessageEndingWith("The time type is incorrect");
    }
}