package ltd.clearsolutions.subtitlesparser;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SubtitlesParserTest {


    String path = "src/test/resources/Squid.Game.S01E09.1080p.NF.WEB-DL.DDP5.1.Atmos.x264-UGM.srt";
    File file = new File(path);


    @Test
    void subtitlesParser_FirstPieceOfTheFile_FirstItemText() {
        SubtitlesParser subtitlesParser = new SubtitlesParser();
        subtitlesParser.subtitlesParser(file);
        Subtitle expected = subtitlesParser.subtitlesParser(file).get(0);

        assertThat(1).isEqualTo(expected.number);
        assertThat(49048).isEqualTo(expected.startTime);
        assertThat(51801).isEqualTo(expected.endTime);
        assertThat(List.of("EPISODE 9", "ONE LUCKY DAY")).isEqualTo(expected.text);
    }

    @Test
    void subtitlesParser_LastPieceOfTheFile_LastItemText() {
        SubtitlesParser subtitlesParser = new SubtitlesParser();
        subtitlesParser.subtitlesParser(file);
        int size = subtitlesParser.subtitlesParser(file).size() - 1;
        Subtitle expected = subtitlesParser.subtitlesParser(file).get(size);

        assertThat(405).isEqualTo(expected.number);
        assertThat(3239485).isEqualTo(expected.startTime);
        assertThat(3244407).isEqualTo(expected.endTime);
        assertThat(List.of("Subtitle translation by: Eun-sook Yoon")).isEqualTo(expected.text);
    }
}
