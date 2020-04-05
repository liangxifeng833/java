package com.lxf.entity;
/**
 * 分页对应的实体类
 * @author lxf
 *
 */
public class Page {
    /**
     * 总条数
     */
    private int totalNumber;
    /**
     * 当前第几页
     */
    private int currentPage;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 每页显示条数
     */
    private int pageNumber = 2;
    /**
     * 数据库中limit 参数，从第几条开始取
     */
    private int dbIndex;
    /**
     * 数据库中limit 参数，一共取多少条
     */
    private int dbNumber;
    /**
     *根据当前对象中属性值计算并设置相关属性值
     *
     */
    public void count()
    {
        //计算总页数
        int totalPageTemp = this.totalNumber/this.pageNumber;
        int plus =(this.totalNumber%this.pageNumber) ==0 ? 0 : 1;
        totalPageTemp = totalPageTemp + plus;
        if(totalPageTemp <= 0 )
        {
            totalPageTemp = 1;
        }    
        this.totalPage  = totalPageTemp;
        
        /*
         * 设置当前页
         */
        //总页数小于当前页，应将当前页设置为总页数
        if(this.totalPage < this.currentPage)
        {
            this.currentPage = this.totalPage;
        }
        //当前页＜１，设置为1
        if(this.currentPage < 1)
        {
            this.currentPage = 1;
        }
        //设置limit
        this.dbIndex = (this.currentPage-1) * this.pageNumber;
        this.dbNumber = this.pageNumber;
       
    }
    
    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        count();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(int dbNumber) {
        this.dbNumber = dbNumber;
    }

    public  static void  main(String[] args)
    {
        int a = 12;
        int b = 5;
        System.out.println(a/b);
        System.out.println(a%b);
    }
    
    

}
