package ltd.clearsolutions.subtitlesparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubtitlesParser {

    public static List<SubtitlesData> subtitlesParser(String pathToFile) throws IOException {

        // Масивы хранения данных.
        List<SubtitlesData> piecesOfSubtitles = new ArrayList<>();
        List<String> temporary = new ArrayList<>();

        // В место "0, 1, 2 -> index + 1, index + 2".
        int index = 0;

        // Чтение файла в список.
        Path path = Paths.get(pathToFile);
        List<String> readFile = Files.readAllLines(path);

        // Счетчик для блока текста
        for (int i = 0; i < readFile.size(); i++) {

            // Строки вида String, String, String, ""
            while (!readFile.get(i).equals("")) {
                // Запись во временный масив, чтоб легче было записывать элементы.
                temporary.add(readFile.get(i));
                // Счеткик для строки
                i++;
                if (i == readFile.size())
                    break;
            }

            // Запись одного куска + всех субтитров после 2 строки.
            piecesOfSubtitles.add(new SubtitlesData(
                    temporary.get(index),
                    temporary.get(index + 1),
                    temporary.stream().skip(index + 2).collect(Collectors.toList())));

            // Каждый раз очищаеться временный масив чтоб заходили только следующие элементы.
            temporary.clear();
        }

        return piecesOfSubtitles;
    }
}
