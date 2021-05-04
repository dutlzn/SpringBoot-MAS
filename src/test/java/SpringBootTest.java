import com.project.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Date;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = MainApplication.class)
public class SpringBootTest {
    @Autowired
    DataSource dataSource;
    @Test
    public void contextLoads() {
        System.out.println(dataSource.getClass());

        Date now = new Date();
        System.out.println(now);
    }
}
