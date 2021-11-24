package com.example.retailer.storage

import com.example.retailer.api.distributor.Order
import com.example.retailer.api.distributor.OrderInfo
import com.example.retailer.api.distributor.OrderStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component("orderStorage")
class OrderStorageImpl : OrderStorage {

    @Autowired
    lateinit var orderRepository: OrderRepo

    @Autowired
    lateinit var orderInfoRepository: OrderInfoRepo

    override fun createOrder(draftOrder: Order): PlaceOrderData {
        val result = orderRepository.save(draftOrder)
        val orderInfo = orderInfoRepository.save(
            OrderInfo(
                result.id!!,
                OrderStatus.SENT,
                "orderInfo"
            )
        )
        return PlaceOrderData(result, orderInfo)
    }

    override fun updateOrder(order: OrderInfo): Boolean {
        if (orderRepository.existsById(order.orderId)) {
            orderInfoRepository.save(order)
            return true
        } else
            return false
    }

    override fun getOrderInfo(id: String): OrderInfo? {
        return orderInfoRepository.findByIdOrNull(id)
    }
}
