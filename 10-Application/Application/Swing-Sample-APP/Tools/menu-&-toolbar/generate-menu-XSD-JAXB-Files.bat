REM Generate XSD and JAXB Files from XML File

call java -jar -verbose  G:\Tools\JAVA\Trang\trang.jar G:\Java\Projets\project-Manager\InfoWrkspOrga\InfoWrkspOrga-Prez-SharedLib\src\main\resources\menu-application.xml  G:\Java\Projets\project-Manager\InfoWrkspOrga\InfoWrkspOrga-Prez-SharedLib\src\main\resources\menu-application.xsd
pause

call G:\Java\jdk\jdk1.6.0_23\bin\xjc G:\Java\Projets\project-Manager\InfoWrkspOrga\InfoWrkspOrga-Prez-SharedLib\src\main\resources\menu-application.xsd -extension -b G:\Java\Projets\project-Manager\InfoWrkspOrga\InfoWrkspOrga-Prez-SharedLib\src\main\resources\menu-application_Bindings.xml -d G:\Java\Projets\InfoWrkspOrga\InfoWrkspOrga\InfoWrkspOrga-Common\src\main\java
pause

