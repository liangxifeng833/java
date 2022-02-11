package com.lxf.rocketdemo.order;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderStep {
    private Integer id;
    private String desc;

    public static List<OrderStep> buildOrders() {
        List<OrderStep> orderList = new ArrayList<>();
        // 1039 : 创建，付款，推送，完成
        // 1065 ： 创建，付款
        // 7235 : 创建，付款


        OrderStep orderStep = new OrderStep();
        orderStep.setId(1039);
        orderStep.setDesc("创建");
        orderList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setId(1065);
        orderStep.setDesc("创建");
        orderList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setId(1039);
        orderStep.setDesc("付款");
        orderList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setId(1039);
        orderStep.setDesc("推送");
        orderList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setId(1039);
        orderStep.setDesc("完成");
        orderList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setId(1065);
        orderStep.setDesc("付款");
        orderList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setId(7235);
        orderStep.setDesc("创建");
        orderList.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setId(7235);
        orderStep.setDesc("付款");
        orderList.add(orderStep);

        return orderList;
    }


}
