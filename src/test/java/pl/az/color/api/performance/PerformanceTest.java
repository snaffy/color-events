package pl.az.color.api.performance;

import io.micronaut.test.annotation.MicronautTest;
import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.Scenario;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@LoadWith("server_host.properties")
@RunWith(ZeroCodeUnitRunner.class)
//@MicronautTest
public class PerformanceTest {

    @Test
    @DisplayName("1sec gap per user - Firing parallel load for X and Y scenarios")
    @Scenario("load_tests/post/post_new_color_event.json")
    public void testLoad_xy() {

    }
}
