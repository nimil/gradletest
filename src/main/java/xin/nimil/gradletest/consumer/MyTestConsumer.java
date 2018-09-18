package xin.nimil.gradletest.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author:nimil e-mail:nimilgg@qq.com
 * @Date:2018/9/18
 * @Time:21:22
 */
public class MyTestConsumer extends DefaultConsumer {


    public MyTestConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("-------------consume message -------------------");
        System.out.println(consumerTag);
        System.out.println(envelope);
        System.out.println(properties);
        System.out.println(new String(body));
    }
}
