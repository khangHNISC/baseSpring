package com.example.springkafka.rabbit;

import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Import(RabbitMQConsumer.class)
class SpringRabbitMQApplicationTests {

    @Autowired
    private RabbitListenerEndpointRegistry registry;

    @Test
    void test() throws Exception {
        AbstractMessageListenerContainer listenerContainer = (AbstractMessageListenerContainer) this.registry.getListenerContainer("foo");
        ChannelAwareMessageListener listener = (ChannelAwareMessageListener) listenerContainer.getMessageListener();
        Channel channel = mock(Channel.class);
        listener.onMessage(MessageBuilder
                        .withBody("hello".getBytes())
                        .andProperties(
                                MessagePropertiesBuilder.newInstance()
                                        .setContentType("application/json")
                                        .setDeliveryTag(42L)
                                        .build())
                        .build(),
                channel);
        verify(channel).basicAck(42L, false);
    }

}
