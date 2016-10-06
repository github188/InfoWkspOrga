REM Generate XSD and JAXB Files from XML File

set COMMON_PROJECT=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\src\main\java
set preferences_Bindings=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\preferences\preferences_Bindings.xml
set preferences_xsd=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\preferences\preferences.xsd

set preferences_xml=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\preferences\preferences_Template_001_Initializer.xml

call java -jar -verbose  C:\JAVA\Trang\trang.jar %preferences_xml%  %preferences_xsd%
pause

call K:\jdk\bin\xjc  %preferences_xsd% -extension -b %preferences_Bindings% -d %COMMON_PROJECT%
pause

echo "/!\/!\/!\/!\ Now Execute Manually ModifyJaxbGeneratedClass.java /!\/!\/!\/!\"
pause