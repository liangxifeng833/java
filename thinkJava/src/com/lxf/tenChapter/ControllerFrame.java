package src.com.lxf.tenChapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 内部类的应用，控制框架(时间框架，到达一定时间可以做一些事情)
 * 内部类讲可变的事物与不可变的事物分开
 * 使用内部类可以在单一的类里面产生对同一个基类Event的多种导出版本
 * 比如：对于温室系统的每一种行为，都继承一个新的Event内部类，并在要实现的action()中编写控制代码
 */
abstract class Event {
    private long eventTime;
    protected final long delayTime;
    public Event(long delayTime){
        this.delayTime = delayTime;
        start();
    }
    public void start() {
        eventTime = System.nanoTime() + delayTime;
    }
    public boolean ready() {
        return System.nanoTime() >= eventTime;
    }
    //可变的事物在这个action中实现
    public abstract void action();
}
public class ControllerFrame {
    private List<Event> eventList = new ArrayList<Event>();
    public void addEvent(Event c) { eventList.add(c); }
    public void run() {
        while (eventList.size() > 0) {
            for (Event event : eventList) {
                if(event.ready()) {
                    System.out.println(event);
                    event.action();
                    eventList.remove(event);
                }
            }
        }
    }
}

class GreehouseControls extends ControllerFrame {
    private boolean light = false;
    //开灯内部类
    public class LightOn extends Event {
        public LightOn(long delayTime) { super(delayTime); }
        @Override
        public void action() {
            light = true;//开灯
        }
        public String toString() { return "Light is on!"; }
    }
    //关灯内部类
    public class LightOff extends  Event {
        public LightOff(long delayTime) { super(delayTime); }
        @Override
        public void action() {
            light = false; //关灯
        }
        public String toString() { return "Light is off!"; }
    }
    private boolean water = false;
    //开水内部类
    public class WaterOn extends Event {
        public WaterOn(long delayTime) { super(delayTime); }
        @Override
        public void action() {
            water = true; //浇水动作
        }
        public String toString() { return "Greehouse water is on!"; }
    }
    //关水内部类
    public class WaterOff extends Event {
        public WaterOff(long delayTime) { super(delayTime); }
        @Override
        public void action() {
            water = false; //浇水动作
        }
        public String toString() { return "Greehouse water is off!"; }
    }
    //响铃内部类
    public class Bell extends Event {
        public Bell(long delayTime) { super(delayTime); }
        @Override
        public void action() {
            addEvent(new Bell(delayTime)); //响铃动作是需要重复进行的
        }
    }
    //重启系统内部类
    public class Restart extends Event {
        private Event[] eventList;
        public Restart(long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event event : eventList) {
                addEvent(event);
            }
        }
        @Override
        public void action() {
            for (Event event : eventList) {
                event.start(); //重新开启每一个事件
                addEvent(event);
            }
            start();
            addEvent(this);
        }
        public String toString() { return "Restarting system"; }
    }
    //终止系统内部类
    public static class Terminate extends Event {
        public Terminate(long delayTime) { super(delayTime); }
        @Override
        public void action() { System.exit(0); }
        public String toString() { return "Terminating system"; }
    }
}

class GreenhouseController {
    public static void main(String[] args) {
        GreehouseControls gc = new GreehouseControls();
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
        };
        gc.addEvent(gc.new Restart(2000,eventList));
        if(args.length == 1) {
            gc.addEvent(new GreehouseControls.Terminate(new Integer(args[0])));
            gc.run();
        }
    }
}
