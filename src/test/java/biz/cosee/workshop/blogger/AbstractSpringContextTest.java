package biz.cosee.workshop.blogger;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@SqlGroup({
        @Sql(scripts = {"classpath:sql/cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
public abstract class AbstractSpringContextTest {
}
