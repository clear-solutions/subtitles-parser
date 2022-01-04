package ltd.clearsolutions.subtitlesparser;

import ltd.clearsolutions.subtitlesparser.exception.SubtitleParserException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SubtitlesParserTest {


    String path = "src/test/resources/CorrectSubs.srt";
    File file = new File(path);
    SubtitlesParser subtitlesParser = new SubtitlesParser();


    @Test
    void subtitlesParser_FirstPieceOfTheFile_FirstItemText() throws SubtitleParserException {
        Subtitle expected = subtitlesParser.getSubtitles(file).get(0);

        assertThat(1).isEqualTo(expected.number());
        assertThat(49048).isEqualTo(expected.startTime());
        assertThat(51801).isEqualTo(expected.endTime());
        assertThat(List.of("EPISODE 9", "ONE LUCKY DAY")).isEqualTo(expected.text());
    }

    @Test
    void subtitlesParser_LastPieceOfTheFile_LastItemText() throws SubtitleParserException {
        List<Subtitle> expected = subtitlesParser.getSubtitles(file);
        int size = expected.size()-1;

        assertThat(405).isEqualTo(expected.get(size).number());
        assertThat(3239485).isEqualTo(expected.get(size).startTime());
        assertThat(3244407).isEqualTo(expected.get(size).endTime());
        assertThat(List.of("Subtitle translation by: Eun-sook Yoon")).isEqualTo(expected.get(size).text());
    }

    @Test
    void subtitlesParser_FileWithHTML_LastItemText() throws SubtitleParserException {
        String path = "src/test/resources/HTMLSubs.srt";
        File file = new File(path);
        SubtitlesParser subtitlesParser = new SubtitlesParser();


        List<Subtitle> expected = subtitlesParser.getSubtitles(file);
        int size = expected.size()-1;

        assertThat(663).isEqualTo(expected.get(size).number());
        assertThat(3060989).isEqualTo(expected.get(size).startTime());
        assertThat(3063992).isEqualTo(expected.get(size).endTime());
        assertThat(List.of("[CLOSING THEME MUSIC]")).isEqualTo(expected.get(size).text());
    }
}
