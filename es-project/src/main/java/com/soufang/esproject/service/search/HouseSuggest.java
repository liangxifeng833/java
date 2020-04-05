package com.soufang.esproject.service.search;

/**
 * 前台搜索框自动补全需要的类
 */
public class HouseSuggest {
    private String input; //输入的词汇
    private int weight = 10; // 默认权重

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
