REM Generate XSD and JAXB Files from XML File

set XML_FILE_SRC=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\cmmi\CMMI-DEV-v1.3-Referential.xml
set XSD_FILE_DEST=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\cmmi\CMMI-DEV-v1.3-Referential.xsd

set BND_FILE=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\Tools\cmmi\CMMI-DEV-v1.3-model_Bindings.xml
set JAVA_DEST=G:\Projects\300-InfoWkspOrga\10-Application\Common\InfoWkspOrga-Common\src\main\java

call java -jar -verbose  C:\java\Trang\trang.jar "%XML_FILE_SRC%" "%XSD_FILE_DEST%"
pause

call K:\jdk\bin\xjc "%XSD_FILE_DEST%" -extension -b "%BND_FILE%" -d "%JAVA_DEST%"
pause

