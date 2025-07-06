package com.cnblogs.yjmyzz.mcp.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

/**
 * @author junmingyang
 */
@Service
public class OrderService {


    @Tool(name = "queryOrderStatus",
            description = "根据订单号查询订单状态")
    public String queryOrderStatus(@ToolParam(required = true, description = "订单号,格式为8位数字,比如：25070601") String orderNo) {
        return switch (orderNo) {
            case "25070601" -> "订单号：" + orderNo + "，订单状态：已发货";
            case "25070602" -> "订单号：" + orderNo + "，订单状态：已完成";
            case "25070603" -> "订单号：" + orderNo + "，订单状态：已取消";
            default -> "订单号：" + orderNo + "，订单状态：未知";
        };
    }
}
