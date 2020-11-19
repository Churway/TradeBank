package com.churway.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/11/17
 * @since 1.0.0
 */
@Configuration
public class MqConfig {
/*    @Bean
    public Queue readyToActionQ() {
        return new Queue("readyToActionQ");
    }*/

    @Bean
    public Queue readyToStopActionQ() {
        return new Queue("readyToStopActionQ");
    }

/*    @Bean
    public Queue oneMinDelayQ() {
        return QueueBuilder.durable("oneMinDelayQ").withArgument("x-dead-letter-exchange", "directEx")
                .withArgument("x-dead-letter-routing-key", "rta").withArgument("x-message-ttl", 60000).build();
    }*/

    @Bean
    public Queue tenMinDelayQ() {
        return QueueBuilder.durable("tenMinDelayQ").withArgument("x-dead-letter-exchange", "directEx")
                .withArgument("x-dead-letter-routing-key","rtsa").withArgument("x-message-ttl",600000).build();
    }

    @Bean
    public DirectExchange directEx() {
        return new DirectExchange("directEx");
    }

/*    @Bean
    public Binding bindToRTA() {
        return BindingBuilder.bind(readyToActionQ()).to(directEx()).with("rta");
    }*/

    @Bean
    public Binding bindToRTSA() {
        return BindingBuilder.bind(readyToStopActionQ()).to(directEx()).with("rtsa");
    }


}
