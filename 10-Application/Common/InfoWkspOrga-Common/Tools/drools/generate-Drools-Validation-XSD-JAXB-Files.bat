REM Generate XSD and JAXB Files from XML File

set XML_FILE_SRC=G:\Projects\200-InfoWkspOrga-RCP\Common\InfoWkspOrga-Drools\plugins\InfoWkspOrga-Drools\src\main\java\com\sgu\core\framework\drools\rules\validation\declaration\xml\drools-validation.xml
set XSD_FILE_DEST=G:\Projects\200-InfoWkspOrga-RCP\Common\InfoWkspOrga-Drools\plugins\InfoWkspOrga-Drools\src\main\java\com\sgu\core\framework\drools\rules\validation\declaration\xml\drools-validation.xsd

set BND_FILE=G:\Projects\200-InfoWkspOrga-RCP\Common\InfoWkspOrga-Drools\plugins\InfoWkspOrga-Drools\src\main\java\com\sgu\core\framework\drools\rules\validation\declaration\xml\drools-validation_Bindings.xml
set JAVA_DEST=G:\Projects\200-InfoWkspOrga-RCP\Common\InfoWkspOrga-Drools\plugins\InfoWkspOrga-Drools\src\main\java

call java -jar -verbose  C:\java\Trang\trang.jar "%XML_FILE_SRC%" "%XSD_FILE_DEST%"
pause

call K:\jdk\bin\xjc "%XSD_FILE_DEST%" -extension -b "%BND_FILE%" -d "%JAVA_DEST%"
pause

