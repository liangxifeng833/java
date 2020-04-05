package com.concurrency.geekTime.Immutability;


/**
 * 思考：给类是不是不可变类
 * 对于以下对象来讲:StringBuffer是可变的，
 * 因为在对象外部可以通过getUser获取它的引用并对其修改：
 *       StringBuffer userB = account.getUser();
        　userB.append(",hello");
 　但是pass是不可边的，因为String是不可变的．
 * Create by liangxifeng on 19-8-22
 */
public final class AccountExample {
    private final StringBuffer user;
    private final String pass;


    public AccountExample(StringBuffer user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public StringBuffer getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "AccountExample{" +
                "user=" + user +
                ", pass='" + pass + '\'' +
                '}';
    }

    public static void main(String[] args) {
        StringBuffer userA = new StringBuffer("zhangsan");
        String passA = "121212";
        AccountExample account = new AccountExample(userA, passA);

        //String pass 不可边
        String passB = account.getPass();
        //以下输出：1450513827
        System.out.println("passA="+passA.hashCode());
        //以下输出: 48669555,代表passB内存地址的hashCode发生了变化
        //所以String pass在account对象中是不可变的
        System.out.println("passB="+passB.hashCode());



        StringBuffer userB = account.getUser();
        userB.append(",hello");
        StringBuffer userC = account.getUser();
        //输出：989110044
        System.out.println("userA="+userA.hashCode());
        //输出：989110044代表userB的引用和对象内部user指向一个内存地址
        //所以StringBuffer user对于account对象是可变的，所以对应类是可变的
        System.out.println("userB="+userB.hashCode());
    }
}
