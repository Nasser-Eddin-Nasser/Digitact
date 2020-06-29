package Digitact.Backend.Model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class KeyCompetenceTest {
    @Test
    void createKeyCompetence() {
        KeyCompetence keyCompetence = new KeyCompetence();
        Assertions.assertThat(keyCompetence).isNotNull();
    }
}
