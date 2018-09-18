package xin.nimil.gradletest.requeack;

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


        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.*";
        String quueName = "test_ack_queue";

        channel.exchangeDeclare(exchangeName,"topic",true);
        channel.queueDeclare(quueName,true,false,false,null);
        channel.queueBind(quueName,exchangeName,routingKey);
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //必须关闭autoack
        channel.basicConsume(quueName,false,new MyTestConsumer(channel));




    }
}
