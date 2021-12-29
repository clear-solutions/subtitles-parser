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
    void subtitlesParser_FirstPieceOfTheFile_FirstItemText() throws IOException {
        SubtitlesParser subtitlesParser = new SubtitlesParser();
        subtitlesParser.subtitlesParser(file);

        assertThat(1).isEqualTo(subtitlesParser.piecesOfSubtitles.get(0).number);
        assertThat(49.048).isEqualTo(subtitlesParser.piecesOfSubtitles.get(0).startTime);
        assertThat(51.801).isEqualTo(subtitlesParser.piecesOfSubtitles.get(0).endTime);
        assertThat(List.of("EPISODE 9", "ONE LUCKY DAY")).isEqualTo(subtitlesParser.piecesOfSubtitles.get(0).text);
    }

    @Test
    void subtitlesParser_LastPieceOfTheFile_LastItemText() throws IOException {
        SubtitlesParser subtitlesParser = new SubtitlesParser();
        subtitlesParser.subtitlesParser(file);
        int size = subtitlesParser.piecesOfSubtitles.size() - 1;

        assertThat(405).isEqualTo(subtitlesParser.piecesOfSubtitles.get(size).number);
        assertThat(3239.485).isEqualTo(subtitlesParser.piecesOfSubtitles.get(size).startTime);
        assertThat(3244.407).isEqualTo(subtitlesParser.piecesOfSubtitles.get(size).endTime);
        assertThat(List.of("Subtitle translation by: Eun-sook Yoon")).isEqualTo(subtitlesParser.piecesOfSubtitles.get(size).text);
    }
}
