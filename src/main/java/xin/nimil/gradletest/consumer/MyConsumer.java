package xin.nimil.gradletest.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @Author:nimil e-mail:nimilgg@qq.com
 * @Date:2018/9/17
 * @Time:22:25
 */
public class MyConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.199.101");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        String exchangeName = "test_consumer_exchange";
        String routingKey = "consumer.save";
        String quueName = "test_consumer_queue";

        channel.exchangeDeclare(exchangeName,"topic",true);
        channel.queueDeclare(quueName,true,false,false,null);
        channel.queueBind(quueName,exchangeName,routingKey);
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(quueName,true,new MyTestConsumer(channel));




    }
}
