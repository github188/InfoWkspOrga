REM Generate XSD and JAXB Files from XML File

set COMMON_PROJECT=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\src\main\java
set perspective_Bindings=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\perspectives\perspective_Bindings.xml
set perspective_xsd=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\perspectives\perspective.xsd

set perspective_xml=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\perspectives\perspective_Template_001_Initializer.xml

call java -jar -verbose  C:\JAVA\Trang\trang.jar %perspective_xml%  %perspective_xsd%
pause

call K:\jdk\bin\xjc  %perspective_xsd% -extension -b %perspective_Bindings% -d %COMMON_PROJECT%
pause

echo "/!\/!\/!\/!\ Now Execute Manually ModifyJaxbGeneratedClass.java /!\/!\/!\/!\"
pause