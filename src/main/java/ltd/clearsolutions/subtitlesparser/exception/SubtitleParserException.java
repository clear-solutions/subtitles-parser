package ltd.clearsolutions.subtitlesparser.exception;

import java.io.File;

public class SubtitleParserException extends Exception {

    private final File file;

    public SubtitleParserException(String message, Throwable th, File file) {
        super(message, th);
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
