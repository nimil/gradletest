package xin.nimil.gradletest.requeack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;

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

        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.save";
        var header = new HashMap<String,Object>();
        header.put("num",1);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().deliveryMode(2).contentEncoding("UTF-8").headers(header).build();

        for (int i= 0;i<5;i++) {
            String msg = "msg "+i;
              channel.basicPublish(exchangeName,routingKey,true,properties,msg.getBytes());

        }

        channel.close();
        connection.close();

    }
}
