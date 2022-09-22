// 예제 A-2 ReadingListApplicationTests

package readinglist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJunit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import readinglist.ReadingListApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
         classes = ReadingListApplication.class)
@WebAppConfiguration
public class ReadingListApplicationTests {

    @Test
    public void contextLoads() {
    }

}
