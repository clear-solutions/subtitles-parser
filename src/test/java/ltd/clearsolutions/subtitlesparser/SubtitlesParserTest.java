package ltd.clearsolutions.subtitlesparser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SubtitlesParserTest {

    String pathToFile = "src/test/resources/Squid.Game.S01E09.1080p.NF.WEB-DL.DDP5.1.Atmos.x264-UGM.srt";

    @Test
    void subtitlesParser_FirstPieceOfTheFile_FirstItemText() throws IOException {

        assertThat("1").isEqualTo(SubtitlesParser.subtitlesParser(pathToFile).get(0).number());
        assertThat("00:00:49,048 --> 00:00:51,801").isEqualTo(SubtitlesParser.subtitlesParser(pathToFile).get(0).time());
        assertThat(List.of("EPISODE 9", "ONE LUCKY DAY")).isEqualTo(SubtitlesParser.subtitlesParser(pathToFile).get(0).text());
    }

    @Test
    void subtitlesParser_LastPieceOfTheFile_LastItemText() throws IOException {
        int index = SubtitlesParser.subtitlesParser(pathToFile).size() - 1;

        assertThat("405").isEqualTo(SubtitlesParser.subtitlesParser(pathToFile).get(index).number());
        assertThat("00:53:59,485 --> 00:54:04,407").isEqualTo(SubtitlesParser.subtitlesParser(pathToFile).get(index).time());
        assertThat(List.of("Subtitle translation by: Eun-sook Yoon")).isEqualTo(SubtitlesParser.subtitlesParser(pathToFile).get(index).text());
    }
}
