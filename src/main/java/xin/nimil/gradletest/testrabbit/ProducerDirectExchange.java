package xin.nimil.gradletest.testrabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:nimil e-mail:nimilgg@qq.com
 * @Date:2018/9/13
 * @Time:22:14
 */
public class ProducerDirectExchange {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.199.101");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection =  connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        for (int i=0;i<5;i++) {
            channel.basicPublish("test_direct_exchange", "test.direct", null, "hello direct routing key".getBytes());
        }
        channel.close();
        connection.close();
    }
}
