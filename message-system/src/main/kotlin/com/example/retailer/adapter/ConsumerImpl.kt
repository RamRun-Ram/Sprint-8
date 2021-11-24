package com.example.retailer.adapter

import com.example.retailer.api.distributor.OrderInfo
import com.example.retailer.service.OrderService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConsumerImpl : Consumer {
    @Autowired
    lateinit var orderService: OrderService

    @RabbitListener(queues = ["consumer"])
    override fun receiveUpdate(orderInfo: String) {
        val mapper = ObjectMapper()
        val info = mapper.readValue(orderInfo, OrderInfo::class.java)
        orderService.updateOrderInfo(info)
    }
}