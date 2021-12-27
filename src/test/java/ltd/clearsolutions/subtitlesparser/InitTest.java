package ltd.clearsolutions.subtitlesparser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InitTest {

    @Test
    void init() {
        assertThat(42).isEqualTo(new Init().init());
    }
}
