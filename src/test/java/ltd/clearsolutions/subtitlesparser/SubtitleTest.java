package ltd.clearsolutions.subtitlesparser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SubtitleTest {

    @Test
    void setTime_CorrectTimeString_TimeInMillisecond() {
        String time = "00:00:49,048 --> 00:00:51,801";
        Subtitle subtitle = new Subtitle();
        subtitle.setTime(time);
        assertThat(49048).isEqualTo(subtitle.getStartTime());
        assertThat(51801).isEqualTo(subtitle.getEndTime());
    }
}