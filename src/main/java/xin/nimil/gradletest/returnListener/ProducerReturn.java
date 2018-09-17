package xin.nimil.gradletest.returnListener;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author:nimil e-mail:nimilgg@qq.com
 * @Date:2018/9/17
 * @Time:22:25
 */
public class ProducerReturn {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.199.101");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel =
                connection.createChannel();
        channel.confirmSelect();

        String exchangeName = "test_return_exchange";
        String routingKey = "return.save";
        String errRoutingKey = "abc.save";

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("----handle  return ---");

                System.out.println("replycode:"+replyCode);
                System.out.println("replycode:"+replyText);
                System.out.println("replycode:"+exchange);
                System.out.println("replycode:"+routingKey);
                System.out.println("replycode:"+new String(body));


            }
        });
        //mandatory属性可以控制错误消息是否被删除  false默认错误消息删除，true默认错误消息保存
        channel.basicPublish(exchangeName,errRoutingKey,false,null,"hello RabbitMq return msg".getBytes());
        channel.basicPublish(exchangeName,errRoutingKey,true,null,"hello RabbitMq return msg".getBytes());


    }
}
