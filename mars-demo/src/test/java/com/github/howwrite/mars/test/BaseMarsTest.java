package com.github.howwrite.mars.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author howwrite
 * @date 2020/3/7 上午9:24:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MarsTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseMarsTest {
}
