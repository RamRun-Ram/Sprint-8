package com.example.retailer

import com.example.retailer.adapter.DistributorPublisherImpl
import com.example.retailer.adapter.ConsumerImpl
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.TopicExchange
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.amqp.core.Queue

@SpringBootApplication
class RetailerApplication {
    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange("distributor_exchange")
    }

    @Bean
    fun queue(): Queue {
        return Queue("retailer_queue", false)
    }


    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding? {
        return BindingBuilder
            .bind(queue)
            .to(exchange)
            .with("retailer.RamRun-Ram.#")
    }

    @Bean
    fun receiver() = ConsumerImpl()


    @Bean
    fun publisher() = DistributorPublisherImpl()

}

fun main(args: Array<String>) {
    runApplication<RetailerApplication>(*args)
}
