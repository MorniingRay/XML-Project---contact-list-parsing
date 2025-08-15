# XML-Project---contact-list-parsing

项目目的：通过 Java 编程实现对“通讯录”（contact9.xml）的 XML 文档的解析，并把解析结果存到数据库的表中。并进一步实现相反的过程，即将数据库表的内容读出来，并将其转化为 XML 文件存储起来。

# Java XML与数据库双向转换系统

![Java](https://img.shields.io/badge/Java-17-blue) 
![MySQL](https://img.shields.io/badge/MySQL-8.0-007bff)
![Servlet](https://img.shields.io/badge/Servlet-5.0-brightgreen)

## 项目概述
### 1.1 核心目标
实现XML文档与MySQL数据库的双向转换系统，包含：
- XML解析与数据持久化
- 数据库CRUD操作
- Web层交互与可视化展示

### 1.2 技术架构
mermaid
graph TD
    A[用户界面] --> B[Servlet控制器]
    B --> C[业务逻辑层]
    C --> D[数据访问层]
    D --> E[(MySQL数据库)]
    A --> F[XML文件]
    F --> D


## 系统设计
### 2.1 分层架构
| 层级         | 实现类                  | 职责说明                     |
|--------------|-------------------------|------------------------------|
| 表示层       | JSP页面                 | 数据展示与用户交互           |
| 控制层       | ContactPojoServlet      | 请求分发与响应处理           |
| 业务逻辑层   | ContactPojoService      | 事务管理与业务规则           |
| 数据访问层   | ContactPojoDao          | 数据库CRUD操作               |
| 数据模型     | ContactPojo             | 联系人信息载体               |

### 2.2 核心模块说明
#### 2.2.1 XML处理模块
java
// 示例：DOM解析关键代码
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(new File("contact9.xml"));

// XML写入流程
TransformerFactory transformerFactory = TransformerFactory.newInstance();
Transformer transformer = transformerFactory.newTransformer();
transformer.transform(new DOMSource(doc), new StreamResult(new File("output.xml")));


#### 2.2.2 数据库交互模块
sql
-- 联系人表结构
CREATE TABLE contacts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) UNIQUE,
    address TEXT,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);


## 功能实现
### 3.1 XML与数据库交互
#### 3.1.1 数据模型(ContactPojo.java)
java
public class ContactPojo {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    // 其他字段及getter/setter省略...
}


#### 3.1.2 DAO层实现(ContactPojoDao.java)
java
public class ContactPojoDao {
    // 数据库连接配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/contact_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    // 查询所有联系人
    public List<ContactPojo> findAll() {
        // 使用JDBC实现数据库查询
    }
}


### 3.2 核心业务流程
#### 3.2.1 XML转数据库流程
mermaid
sequenceDiagram
    participant Servlet
    participant Service
    participant Dao
    participant DB
    
    Servlet->>Service: 调用readXML2()
    Service->>Dao: 调用findAll()
    Dao->>DB: 执行SQL查询
    DB-->>Dao: 返回结果集
    Dao-->>Service: 封装ContactPojo列表
    Service-->>Servlet: 返回数据
    Servlet->>JSP: 转发展示页面


#### 3.2.2 数据库转XML流程
mermaid
sequenceDiagram
    participant Servlet
    participant Service
    participant Dao
    participant XML
    
    Servlet->>Service: 调用writeXML()
    Service->>Dao: 调用findAll()
    Dao->>DB: 执行SQL查询
    DB-->>Dao: 返回结果集
    Dao-->>Service: 封装ContactPojo列表
    Service->>XML: 生成DOM树
    XML-->>Servlet: 返回XML文件
    Servlet->>File: 写入磁盘


## 代码规范
### 4.1 异常处理
java
try {
    // 数据库操作
} catch (SQLException e) {
    logger.error("数据库操作失败", e);
    throw new ServiceException("数据访问异常", e);
}


### 4.2 配置管理
properties
database.properties

db.url=jdbc:mysql://localhost:3306/contact_db
db.username=root
db.password=password


## 部署指南
### 5.1 环境要求
- JDK 17+
- MySQL 8.0+
- Maven 3.8+

### 5.2 快速启动
bash
克隆仓库

git clone https://github.com/your/repo.git

构建项目
mvn clean package

运行应用
java -jar target/contact-system.jar


## 贡献指南
1. **分支策略**
   - `main`：稳定版本
   - `develop`：开发分支
   - `feature/*`：功能开发分支

2. **提交规范**
   bash
   git commit -m "[feat] 新增XML批量导入功能"


## 许可证
本项目采用 [MIT License](LICENSE)

## 常见问题
### Q1: 如何处理XML解析异常？
java
// 添加错误处理器
builder.setErrorHandler(new ErrorHandler() {
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        logger.error("XML解析致命错误: {}", e.getMessage());
        throw e;
    }
});


### Q2: 数据库连接失败如何排查？
1. 检查`application.properties`配置
2. 确认MySQL服务状态
3. 验证防火墙设置

> 提示：建议使用Docker部署数据库服务：
> bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=secret -d mysql:8.0

```

