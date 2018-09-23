package com.qg.exclusiveplug;

import com.qg.exclusiveplug.service.TcpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExclusivePlugApplicationTests {

    @Autowired
    private TcpService tcpService;

    @Test
    public void contextLoads() {
        //测试一下缓存
        String message = "0:打印机6,1:1.8,2:220,3:240.4";
        System.out.println(tcpService.messageHandler(message));
    }

}
