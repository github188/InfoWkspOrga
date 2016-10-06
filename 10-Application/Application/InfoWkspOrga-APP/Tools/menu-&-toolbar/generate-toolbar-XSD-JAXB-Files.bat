REM Generate XSD and JAXB Files from XML File

call java -jar -verbose  G:\Tools\JAVA\Trang\trang.jar G:\Java\Projets\project-Manager\InfoWkspOrga\InfoWkspOrga-Prez-SharedLib\src\main\resources\toolbar-application.xml  G:\Java\Projets\project-Manager\InfoWkspOrga\InfoWkspOrga-Prez-SharedLib\src\main\resources\toolbar-application.xsd
pause

call G:\Java\jdk\jdk1.6.0_23\bin\xjc G:\Java\Projets\project-Manager\InfoWkspOrga\InfoWkspOrga-Prez-SharedLib\src\main\resources\toolbar-application.xsd -extension -b G:\Java\Projets\project-Manager\InfoWkspOrga\InfoWkspOrga-Prez-SharedLib\src\main\resources\toolbar-application_Bindings.xml -d G:\Java\Projets\InfoWkspOrga\InfoWkspOrga\InfoWkspOrga-Common\src\main\java
pause

