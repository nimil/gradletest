package xin.nimil.gradletest.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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

        String exchangeName = "test_qos_exchange";
        String routingKey = "qos.save";

        for (int i= 0;i<5;i++) {
            channel.basicPublish(exchangeName,routingKey,true,null,"test qos".getBytes());
        }

        channel.close();
        connection.close();

    }
}
