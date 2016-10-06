package com.sgu.infowksporga.business.rest.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sgu.core.framework.util.UtilRest;
import com.sgu.core.framework.web.rest.http.GMediaType;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut;
import com.sgu.infowksporga.rest.RestServiceMapping;

public class NetClientGet2 {

  // http://localhost:8080/RESTfulExample/json/product/get
  public static void main(String[] args) {

    try {

      /*
       * UtilRest.callRestBusinessService("http://localhost:8080/maze-explorer-biz/rest/perspective/load/xml/structures",
       * new LoadPerspectiveStructureIn(), LoadPerspectiveStructureOut.class, HttpMethod.POST,
       * MediaType.APPLICATION_OCTET_STREAM);
       */

      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().addAll(UtilRest.messageConverters);

      final FindPerspectiveStructureIn in = new FindPerspectiveStructureIn();
      in.setUserLogin("sguisse");
      Perspective perspective = new Perspective();
      perspective.setId(2);
      in.setPerspective(perspective);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT);
      headers.setAccept(Arrays.asList(GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT));

      HttpEntity<FindPerspectiveStructureIn> entity = new HttpEntity<FindPerspectiveStructureIn>(in, headers);

      ResponseEntity<FindPerspectiveStructureOut> loginResponse = restTemplate.exchange(RestServiceMapping.URL_SERVICE_FIND_PERSPECTIVE_STRUCTURE,
                                                                                        HttpMethod.POST, entity,
                                                                                        FindPerspectiveStructureOut.class);
      if (loginResponse.getStatusCode() == HttpStatus.OK) {
        FindPerspectiveStructureOut userJson = (FindPerspectiveStructureOut) loginResponse.getBody();
        userJson.getWorkspaces().get(0).getViews().size();
      }
      else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        // nono... bad credentials
      }

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

}
