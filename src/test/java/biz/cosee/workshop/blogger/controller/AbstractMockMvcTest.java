package biz.cosee.workshop.blogger.controller;

import biz.cosee.workshop.blogger.AbstractSpringContextTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public abstract class AbstractMockMvcTest extends AbstractSpringContextTest {
    @Autowired
    MockMvc mockMvc;
}
