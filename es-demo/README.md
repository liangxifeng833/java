### 说明：
* 项目中 test/ESTest.java 单元测试 ，官方推荐的 java api client方式操作 es 注意该方式只支持ES server 版本=7.17.3
* test/EsRestTemplate.java单元测试， ElasticsearchRestTemplate方式操作es,该方式支持ES server 版本 7.17.3 和 7.6.2 (**推荐使用该方式**)
* test/repositorys/TestEsRepository.java单元测试， repository方式操作es,该方式支持ES server 版本 7.17.3 和 7.6.2
* 项目是spring boot2.3.2-release
* src下utils/EsUtils.java是我自己封装的 使用 template 方式操作es 的工具类
