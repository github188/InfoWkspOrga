REM Generate XSD and JAXB Files from XML File

set XML_FILE_SRC=G:\Projects\200-InfoWkspOrga-RCP\Common\InfoWkspOrga-Common\plugins\InfoWkspOrga-Common\src\main\resources\forCmmiReferentialGeneration\CMMI-DEV-v1.3-model.xml
set XSD_FILE_DEST=G:\Projects\200-InfoWkspOrga-RCP\Common\InfoWkspOrga-Common\plugins\InfoWkspOrga-Common\src\main\resources\forCmmiReferentialGeneration\CMMI-DEV-v1.3-model.xsd

set BND_FILE=G:\Projects\200-InfoWkspOrga-RCP\Common\InfoWkspOrga-Common\plugins\InfoWkspOrga-Common\src\main\resources\forCmmiReferentialGeneration\CMMI-DEV-v1.3-model_Bindings.xml
set JAVA_DEST=G:\Projects\200-InfoWkspOrga-RCP\Common\InfoWkspOrga-Common\plugins\InfoWkspOrga-Common\src\main\java

call java -jar -verbose  C:\java\Trang\trang.jar "%XML_FILE_SRC%" "%XSD_FILE_DEST%"
pause

call K:\jdk\bin\xjc "%XSD_FILE_DEST%" -extension -b "%BND_FILE%" -d "%JAVA_DEST%"
pause

