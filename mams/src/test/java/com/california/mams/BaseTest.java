package com.california.mams;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fnn on 17/4/25.
 * 所有的test case 均需继承自该类
 *
 * @CreatedBy fnn
 * @Date 17/4/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = {"spring.cloud.consul.enabled=false"})
public class BaseTest extends AbstractJUnit4SpringContextTests {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
