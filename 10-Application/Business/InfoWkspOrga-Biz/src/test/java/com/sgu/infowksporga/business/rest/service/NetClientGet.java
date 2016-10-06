package com.sgu.infowksporga.business.rest.service;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sgu.core.framework.web.rest.GJavaObjectToByteArrayHttpMessageConverter;
import com.sgu.core.framework.web.rest.http.GMediaType;
import com.sgu.infowksporga.business.pivot.perspective.LoadPerspectivesStructureIn;
import com.sgu.infowksporga.business.pivot.perspective.LoadPerspectivesStructureOut;

public class NetClientGet {

  // http://localhost:8080/RESTfulExample/json/product/get
  public static void main(String[] args) {

    try {

      /*
       * UtilRest.callRestBusinessService("http://localhost:8080/maze-explorer-biz/rest/perspective/load/xml/structures",
       * new LoadPerspectiveStructureIn(), LoadPerspectiveStructureOut.class, HttpMethod.POST,
       * MediaType.APPLICATION_OCTET_STREAM);
       */

      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().add(new GJavaObjectToByteArrayHttpMessageConverter());

      String url = "http://localhost:8080/maze-explorer-biz/";
      String requestJson = "rest/perspective/load/xml/structures";
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT);
      headers.setAccept(Arrays.asList(GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT));

      LoadPerspectivesStructureIn in = new LoadPerspectivesStructureIn();
      in.setPerspectivesConfig(new HashMap<String, String>());
      in.getPerspectivesConfig().put("prez.host.port", "http://localhost:8080");
      in.getPerspectivesConfig().put("biz.host.port", "http://localhost:8080");
      in.getPerspectivesConfig().put("prez.app.name", "maze-explorer-prez");
      in.getPerspectivesConfig().put("biz.app.name", "maze-explorer-biz");
      in.getPerspectivesConfig().put("", "");

      in.getPerspectivesConfig().put("perspectives.init.xml.ur.base", "${biz.host.port}/${biz.app.name}/perspectives/");
      in.getPerspectivesConfig().put("perspectives.init.xml.files", "perspective_default.xml;perspective_gfi.xml");

      in.setFilesToLoadKey("perspectives.init.xml.files");
      HttpEntity<LoadPerspectivesStructureIn> entity = new HttpEntity<LoadPerspectivesStructureIn>(in, headers);

      ResponseEntity<LoadPerspectivesStructureOut> loginResponse = restTemplate.exchange(url + requestJson, HttpMethod.POST, entity,
                                                                                         LoadPerspectivesStructureOut.class);
      if (loginResponse.getStatusCode() == HttpStatus.OK) {
        LoadPerspectivesStructureOut userJson = (LoadPerspectivesStructureOut) loginResponse.getBody();
      }
      else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        // nono... bad credentials
      }

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

}
