package xin.nimil.gradletest.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author:nimil e-mail:nimilgg@qq.com
 * @Date:2018/9/17
 * @Time:22:25
 */
public class MyProducer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.199.101");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel =
                connection.createChannel();
        channel.confirmSelect();

        String exchangeName = "test_consumer_exchange";
        String routingKey = "consumer.save";

        for (int i= 0;i<5;i++) {
            channel.basicPublish(exchangeName,routingKey,true,null,"test consumer".getBytes());
        }

        channel.close();
        connection.close();

    }
}
