package xin.nimil.gradletest.returnListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @Author:nimil e-mail:nimilgg@qq.com
 * @Date:2018/9/17
 * @Time:22:25
 */
public class ConsumerReturn {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.199.101");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        String exchangeName = "test_return_exchange";
        String routingKey = "return.save";
        String errToutingkey = "abc.save";
        String quueName = "test_return_queue";

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
