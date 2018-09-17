package xin.nimil.gradletest.confirm;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author:nimil e-mail:nimilgg@qq.com
 * @Date:2018/9/17
 * @Time:22:04
 */
public class ConfimConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.199.101");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel =
                connection.createChannel();


        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.*";
        String quueName = "test_confirm_queue";
        channel.exchangeDeclare(exchangeName,"topic",true);
        channel.queueDeclare(quueName,true,false,false,null);
        channel.queueBind(quueName,exchangeName,routingKey);
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(quueName,true,queueingConsumer);
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println(msg);
        }
    }
}
