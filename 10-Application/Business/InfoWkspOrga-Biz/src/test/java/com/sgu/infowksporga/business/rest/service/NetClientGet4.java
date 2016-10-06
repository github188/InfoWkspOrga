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
import com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceIn;
import com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceOut;
import com.sgu.infowksporga.rest.RestServiceMapping;

public class NetClientGet4 {

  // http://localhost:8080/RESTfulExample/json/product/get
  public static void main(String[] args) {

    try {

      /*
       * UtilRest.callRestBusinessService("http://localhost:8080/maze-explorer-biz/rest/perspective/load/xml/structures",
       * new LoadWorkspaceIn(), LoadWorkspaceOut.class, HttpMethod.POST,
       * MediaType.APPLICATION_OCTET_STREAM);
       */

      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().addAll(UtilRest.messageConverters);

      final FindWorkspaceIn in = new FindWorkspaceIn();
      in.setUserLogin("sguisse");
      in.setWorkspaceId("sguisse");

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT);
      headers.setAccept(Arrays.asList(GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT));

      HttpEntity<FindWorkspaceIn> entity = new HttpEntity<FindWorkspaceIn>(in, headers);

      ResponseEntity<FindWorkspaceOut> loginResponse = restTemplate.exchange(RestServiceMapping.URL_SERVICE_FIND_WORKSPACE, HttpMethod.POST,
                                                                             entity, FindWorkspaceOut.class);
      if (loginResponse.getStatusCode() == HttpStatus.OK) {
        FindWorkspaceOut out = (FindWorkspaceOut) loginResponse.getBody();
      }
      else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        // nono... bad credentials
      }

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

}
