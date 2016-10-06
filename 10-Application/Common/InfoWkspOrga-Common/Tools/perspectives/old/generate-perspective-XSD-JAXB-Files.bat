REM Generate XSD and JAXB Files from XML File

call java -jar -verbose  G:\Tools\JAVA\Trang\trang.jar G:\Java\Projets\InfoWkspOrga\InfoWkspOrga\InfoWkspOrga-Biz-Webapp\src\main\webapp\perspectives\perspectiveWeb_BNPP.xml  G:\Java\Projets\InfoWkspOrga\InfoWkspOrga\InfoWkspOrga-Biz-SharedLib\src\main\resources\perspective.xsd
pause

call G:\Java\jdk\jdk1.6.0_23\bin\xjc  G:\Java\Projets\InfoWkspOrga\InfoWkspOrga\InfoWkspOrga-Biz-SharedLib\src\main\resources\perspective.xsd -extension -b G:\Java\Projets\InfoWkspOrga\InfoWkspOrga\InfoWkspOrga-Biz-SharedLib\src\main\resources\perspective_Bindings.xml -d G:\Java\Projets\InfoWkspOrga\InfoWkspOrga\InfoWkspOrga-Common\src\main\java
pause

echo "/!\/!\/!\/!\ Now Execute Manually ModifyJaxbGeneratedClass.java /!\/!\/!\/!\"
pause