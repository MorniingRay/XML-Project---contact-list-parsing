# XML-Project---contact-list-parsing
##1 项目目的
通过 Java 编程实现对“通讯录”（contact9.xml）的 XML 文档的解析，并把 解析结果存到数据库的表中。并进一步实现相反的过程，即将数据库表的内容读 出来，并将其转化为 XML 文件存储起来。

##2 项目设计思路
###2.1 项目结构分解
Entity（实体类）：ContactPojo，用于表示通讯录中的联系人信息。
DAO（数据访问对象）：ContactPojoDao，用于数据库操作，如增删改查。
Service（业务逻辑层）：ContactPojoService，调用 DAO 层方法，处理业务逻辑。
Servlet（控制层）：ContactPojoServlet，处理 HTTP 请求，调用 Service 层，返回响应。
Web（表现层）：JSP 页面，用于显示通讯录数据和表单。
###2.2  项目结构设计

####2.2.1  实体类（Entity）
创建一个 ContactPojo 类，包含所有联系人的属性和相应的 getter 和 setter 方法。

####2.2.2  数据访问对象（DAO）
在 ContactPojoDao 中实现数据库操作的方法，如 insertContactPojo、updateC ontactPojo 、deleteContactPojo 和 retrieveContactPojo。

####2.2.3  业务逻辑层（Service）
在 ContactPojoService 中，提供业务逻辑处理，调用 ContactPojoDao 的方法。

####2.2.4  控制层（Servlet）
在 ContactPojoServlet 中，处理不同的 HTTP 请求，调用 Service 层的方法，并根据请求返回不同的响应：
写入 XML 文件：提供一个方法，从数据库读取所有联系人信息，使用 DOM 解析器将 XML 文件解析成 DOM 树结构，遍历 DOM 树中的节点，提取需要的数据，使用 DomWriteXml 类将数据写入 XML 文件。
数据库操作：提供 CRUD 操作的接口，调用 Service 层的方法。
HTML 显示：根据请求，从数据库获取联系人信息，转发到 JSP 页面显示。

####2.2.5  表现层（JSP）
创建 JSP 页面用于显示通讯录数据和表单：
列表页面：显示所有联系人的列表。
编辑页面：显示表单，用于添加或编辑联系人信息。
结果页面：显示操作结果，如“联系人已添加”。
##3  项目内容展示

###3.1  xml 与数据库
定义了一个名为 ContactPojo  的 Java  类，用于表示联系人信息 ContactPoj o  类是一个典型的用于存储联系人信息的简单 Java  对象，设计上遵循了 POJO的概念，提供了基本的构造方法、 getter/setter  方法和 toString()  方法，适合用作数据存储和传输。从一个现有的 XML 文件中读取数据，创建一个新的 contac t 元素，并将其添加到该 XML 文件中。写入 xml 流程：1.解析 XML  文件：读取 XML  文件并将其转化为 Document  对象；2.修改 XML  文件：基于传入的数据（通过 ContactPojo  对象），创建新的元素并添加到 DOM  树中；3.写回 X ML  文件：通过 Transformer  将修改后的 XML  数据写回原文件，完成更新。
####3.1.1 总体思路
数据库设计思路：
1.ContactPojo  只是一个简单的数据容器，它的设计思路是让每个字段都与现实中的联系人信息相关联。例如， firstName 、lastName 、phone  等字段直接与人的基本信息相关；该类可以扩展添加更多联系人相关的信息，例如添加电子邮件、生日等字段，或者根据需求修改字段类型。
2.面向数据传输：这种 POJO  类非常适合用来进行数据传输，尤其是与前端、数据库或外部服务交互时，可以轻松地将数据封装到对象中。
3 良好的封装：所有字段都使用 private  修饰，并通过 getter  和 setter  方法提供对外访问接口，这遵循了面向对象的封装原则。
4.通过实现 toString()  方法，可以方便地输出对象的信息，便于调试和日志记录。
写入 xml 的思路：
1.读取原始 XML  文件：首先创建一个 DocumentBuilder  实例来读取 XML文件，将其转换为 DOM 树的 Document 对象。
2.创建新的 XML  元素：基于传入的 ContactPojo  对象，构建新的 contact元素，并为其添加属性和子元素。
3.将新元素添加到 DOM  树中：将创建的新 contact  元素附加到现有的 XML  文档中。
4.将修改后的 XML  文件写回磁盘：使用 Transformer  将更新后的 Document  对象写回文件。
####3.1.2 数据库连接

####3.1.3 定义类和变量并构造方法
构造带参数的构造方法，用于创建一个完全初始化的 ContactPojo  对象。通过传递构造参数，可以一次性设置所有的字段值。成员变量是该 ContactPojo  类的基本数据。每个字段都对应了一个特定的联系人信息，分别与xml中的部分信息相对应，例如：
id:  用来表示联系人在某个集合中的唯一标识符。
person:  联系人的姓名。
tags:  联系人的标签，用来对联系人进行分类（例如“朋友”、“同事”等）。
title,  firstName,  middleName,  lastName:  分别存储联系人姓名的称呼、名字的第一部分、中间部分和最后部分。
address,  latitude,  longitude:  存储联系人的地址及地理位置信息。
king:  电话备注字段，可以存储例如“工作”、“紧急”等的备注信息。
phone:  联系人的电话号码。
knows:  表示联系人与当前用户的关系（例如：朋友、同事等）。
description:  对联系人的简短描述，可以存储其他相关信息。



####3.1.4 toString方法和取值
toString()  方法是 Java  中常用的一个方法，用来返回该对象的字符串表示。在这里，toString()  方法将所有字段的值拼接成一个字符串，方便在打印 Contac tPojo  对象时查看对象的状态。每个字段的名称和对应的值都会被显示出来。通过 getter  和 setter  方法用于获取和设置对象的属性值。

####3.1.5 初始化documentBuild并读取xml
调用工厂方法创建一个 DocumentBuilder，该对象用于将 XML  文件解析为 Document  对象，使用 DocumentBuilder  解析 XML  文件，返回一个 Docume  nt  对象。这个对象表示 XML  文件的 DOM  树，后续可以在该 DOM  树上操作。




####3.1.6 创建新的contact元素及其子元素
创建一个新的 Element  对象，名字是 contact 。一个元素代表一个信息，将在 XML  文件中作为 <contact>  标签存在，并为新创建的 contact  元素设置属性，将多个子元素（如 nameElement 、locationElement  等）添加到 contactElem ent  中。

####3.1.7 将信息写入xml
创建 Transformer  实例，后续用它来将 Document  对象转换为 XML  格式并写回文件。通过 transform()  方法将 Document  转换为 XML  格式并写入到指定的 StreamResult（即文件中）。





##3.2  实现数据库到xml

3.2.1  程序结构分析
我们使用 DOM 方式读取、解析 XML 文档的方法，它将整个 XML 文档加载到内存中，并构建成一个树状结构，这个结构被称为 DOM 树。在 DOM 树中，文档中的每个元素都被表示为树中的一个节点 node，可以是元素节点、属性节点、文本节点等。
①树状结构：XML 文档被解析成一个树状结构，每个元素都是树中的一个节点，父元素包含子元素。
②随机访问：一旦 XML 文档被加载到 DOM 树中，可以随机访问文档的任何部分。即我们可以快速地查找、修改或删除任何元素或属性。
③可以修改文档：DOM 允许你修改 XML 文档的结构和内容。你可以添加、删除或替换节点，这些更改会反映在 DOM 树中。
④事件驱动解析：DOM 解析器通常是基于事件的，当解析器遇到 XML 文档的不同部分时，它会触发不同的事件，例如开始元素、结束元素和字符数据。
⑤API 操作：一旦 XML 被解析成 DOM 树，可用 DOMAPI 来操作这个树。
⑥可读性：DOM 提供了一种直观的方式来处理 XML 文档，因为它将文档表示为一个对象模型，这使得编程处理 XML 变得相对容易。
⑦DOM 方式适用于那些需要频繁随机访问和修改 XML 文档的场景。对于只需要顺序读取或处理大型 XML 文件的应用，SAX 解析器才会更高效，因为 S

AX 是流式的，不需要将整个文档加载到内存中。

3.2.2  代码设计思路
我们要实现 xml 转为 html，首先要实现了基础的 XML 文件读取与解析功能，我通过 DOMAPI 的方式解析获取 XML 数据。
已知 contact9.xml 中的数据结构为：

3.2.3  将xml文件中的数据解析并映射到java对象中
先创建 DocumentBuilderFactory 和 DocumentBuilder，使用 DocumentBuilder的 parse 方法加载并解析 XML 文件，将 XML 文件解析成 Document 对象。

接着获取所有 contact 元素，通过 Document 对象的 getElementsByTagName方法获取所有名为 contact 的元素节点，存储在一个 NodeList 中。



遍历 NodeList 中的每个 contact 元素节点，对每个节点创建一个 ContactPoj o 对象来存储解析出的数据。然后对每个 contact 节点，进一步解析其子元素 na me 、location 、phone 、knows 和 description，据节点名称获取相应的文本内容或属性值，然后设置到 ContactPojo 对象中。

处理每个标签的元素内容：

①name子元素需进一步遍历其子节点first 、middle 、last；
②location子元素遍历子address 、latitude 、longitude获取地址和经纬度；
③phone子元素获取电话号码文本内容和kind属性值；
④knows子元素获取contacts属性值；
⑤description子元素获取描述文本内容。
然后将填充好的ContactPojo对象添加到列表中，返回包含所有ContactPojo对象的列表，然后显示到html文件中。

3.3  实现增删改查操作

3.3.1  ContactPojoService类
ContactPojoService 是一个服务层（ServiceLayer），它主要负责业务逻辑的处理，如数据的增删改查（CRUD）操作。ContactPojoService 类内部有一个ContactPojoDao 类型的成员变量用于执行数据库的具体操作。ContactPojoDao类是数据访问层（DAOLayer）的实现，封装了具体的数据库操作。该类通过构造函数注入的方式将 ContactPojoDao 注入到 ContactPojoService 中，这种方式有助于实现解耦和更好的可测试性。
3.3.2  ContactPojoServ let类

Java Servlet 程序，使用了 Servlet 技术来处理 HTTP 请求并根据请求的方法执行相应的操作。作用是与 ContactPojoService 类进行交互，进行 CRUD操作（增、查、改、删）以及与 XML 文件的读写操作。该程序在实现上遵循了请求分发、编码处理以及面向对象的设计原则。
3.3.3  方法解析
（1)获取所有联系人 getContactPojos()
可以查询所有的联系人数据，并将结果返回给调用方。目的是从数据库中获取所有联系人信息。

（2）获取单个联系人 getContactPojo
根据 id 从数据库中查询单个 ContactPojo 对象，如果查询结果为null （即没有找到对应的联系人），当前代码不会做任何处理，直接返回 null。

(3)插入联系人 insertContactPojo
将一个 ContactPojo 对象插入数据库。

(4)更新联系人 updateContactPojo
用来更新数据库中已有的联系人信息。如果联系人不存在，更新操作通常不会做任何改变，可能会返回 0 或者抛出异常。



(5)删除联系人 deleteContactPojo(intid)根据 id 删除数据库中的联系人信息。

（6）doGet 方法
该方法是处理 HTTP GET 请求的。如果客户端发起 GET 请求，doGet 方法会被调用。这里的 doGet 直接调用了 doPost 方法，意味着无论是 GET 还是 POST请求，都会统一交给 doPost 方法来处理。

（7）doPost 方法
处理 HTTP POST 请求的方法。请求和响应的字符编码设置为 utf-8，确保能够正确处理包含中文字符的请求和响应。从请求参数中获取 method 字段的
值，表示客户端请求的操作类型。根据 method 的不同值，分发到相应的处理方法。每个 if 分支对应一种操作。



（8）writeXML 方法
获取请求参数，调用服务层获取联系人数据，将联系人对象写入 XML，重定向到联系人列表页面。

（9）readXML 方法
从 XML 文件中读取联系人数据，并将这些数据传递到 JSP 页面（/readxm l.jsp）进行展示。

（10）readXML2 方法
从 XML 文件中读取联系人数据并将其存入数据库，然后跳转到 readxml.j sp 页面，显示操作成功的消息。



（11）getContactPojos 方法
从数据库中获取所有联系人数据，并展示在 index.jsp 页面。

（12）getContactPojo 方法
据联系人 ID 获取单个联系人数据，并跳转到编辑页面。

（13）updateContactPojo 方法
更新指定的联系人信息，并将更新后的联系人保存到数据库中。

（14）deleteContactPojo 方法
根据联系人 ID 删除指定联系人，调用 contactPojoService.deleteConta ctPojo(id)删除，重定向到联系人列表页面（/java_xml/contactPojo?method= getContactPojos）。


（15）addContactPojo 方法
从请求中获取用户提交的联系人信息，创建一个新的  ContactPojo  对象，使用请求参数初始化该对象，调用 contactPojoService.insertContactPo jo(contactPojo)添加新的联系人信息。



3.3.4  设计思路
（1）分层设计：主要包括控制层（Servlet）、服务层（contactPojoServic e）、持久层（数据库操作），每一层都清晰地负责不同的业务逻辑。
①控制层：处理 HTTP 请求和响应，决定显示的 JSP 页面，并调用服务层进行数据处理。
②服务层：通过 contactPojoService 提供对数据库的 CRUD 操作封装。
（2）请求转发与重定向：在方法中，request.getRequestDispatcher().forwa rd()方法将请求转发到对应的 JSP 页面，显示动态内容或表单。在addContact Pojo、updateContactPojo 和 deleteContactPojo 方法中用 response.sendRedi rect()来重定向请求，确保操作后的页面刷新并避免重复提交表单。
（3）JSP 页面展示：request.setAttribute()用于将数据传递给 JSP 页面，J SP 页面通过${}  EL 表达式显示数据。通过不同的JSP 页面（如 index.jsp、r eadxml.jsp、editContactPojo.jsp）来展示不同的视图。
（4）表单提交和参数处理：对于添加和编辑联系人，代码从请求中获取多个表单字段值（如姓名、电话、地址等），然后用这些字段值构造 ContactPojo 对象。在更新和删除操作时，获取 id 参数，进行对应的数据库操作。
通过分层结构清晰地划分各个模块的职责，我们通过 Servlet 处理 HTTP 请求，与数据库交互实现基本的 CRUD 操作，同时利用 JSP 页面展示动态数据，
增强用户交互体验。

3.4  操作页面

3.4.1  xml转成表格形式的网页样式jsp代码
动态生成 HTML 表格，用于展示 ContactPojo 对象的详细信息，提供修改、删除、写入 XML 文件的操作按钮，供用户在前端页面上进行操作。

展示和操作联系人详细信息的列表

3.4.2  将数据库中的表格以xml形式显示的jsp代码
通过引用 ContactPojo 对象的属性来动态生成类似 XML 格式的内容。将 Co ntactPojo 对象的各个属性以类似 XML 的格式显示在页面上。使用&emsp;（相当于 4 个空格的缩进）来美化和格式化输出，使得内容层次更清晰。



3.4.3  返回按钮和写入数据库按钮
返回按钮：超链接将用户导航到 contactPojo?method=getContactPojos，即发送 GET 请求到服务器，执行 getContactPojos 方法，返回到联系人列表页面。



写入数据库按钮：将用户导航到/java_xml/contactPojo?method=readXML2，发送 GET 请求到服务器，执行 readXML2 方法，将联系人信息写入数据库。




3.4.4  XML声明及根元素的输出
显示 XML 的代码，目的是在页面上生成类似 XML 的格式。&lt;和&gt;是HTML 实体，表示<和>，防止 HTML 解析器将它们作为实际的标签处理。输出了<contacts>元素的开始标签，包含命名空间声明、XMLSchema 位置、数据来源和版本信息，为了描述联系人列表的根元素从 request 对象中获取联系人列表并开始遍历

3.5  合成调试代码

3.5.1  避免空值异常
如果 works 为 null，说明没有任何数据传递到页面，后续的代码不会执
行，避免空指针异常。注意表格的非空判断，之前运行时数据库中都存在数
据，将数据库中的信息彻底清空后，再运行发现网页连接失败，程序无法正常运行，经排查后发现网页缺少非空判断，导致连接的失败，增加后程序可以正常运行。
