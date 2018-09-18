package xin.nimil.gradletest.limit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @Author:nimil e-mail:nimilgg@qq.com
 * @Date:2018/9/18
 * @Time:21:22
 */
public class MyTestConsumer extends DefaultConsumer {


    private Channel channel;

    public MyTestConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("-------------consume message -------------------");
        System.out.println(consumerTag);
        System.out.println(envelope);
        System.out.println(properties);
        System.out.println(new String(body));
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.basicAck(envelope.getDeliveryTag(),false);

    }
}
