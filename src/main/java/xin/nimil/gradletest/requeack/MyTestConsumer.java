package xin.nimil.gradletest.requeack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.Objects;

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


        if (!Objects.isNull(properties)&&(Integer)properties.getHeaders().get("num")==1){
            channel.basicNack(envelope.getDeliveryTag(),false,true);

        }else{
            channel.basicAck(envelope.getDeliveryTag(),false);
        }
    }
}
