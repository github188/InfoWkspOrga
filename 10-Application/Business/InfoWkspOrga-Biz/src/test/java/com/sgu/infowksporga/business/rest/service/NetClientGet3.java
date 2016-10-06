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
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut;
import com.sgu.infowksporga.rest.RestServiceMapping;

public class NetClientGet3 {

  // http://localhost:8080/RESTfulExample/json/product/get
  public static void main(String[] args) {

    try {

      /*
       * UtilRest.callRestBusinessService("http://localhost:8080/maze-explorer-biz/rest/perspective/load/xml/structures",
       * new LoadPerspectiveIn(), LoadPerspectiveOut.class, HttpMethod.POST,
       * MediaType.APPLICATION_OCTET_STREAM);
       */

      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().addAll(UtilRest.messageConverters);

      final FindPerspectiveIn in = new FindPerspectiveIn();
      in.setUserLogin("sguisse");
      in.setAll(true);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT);
      headers.setAccept(Arrays.asList(GMediaType.APPLICATION_JSON));

      HttpEntity<FindPerspectiveIn> entity = new HttpEntity<FindPerspectiveIn>(in, headers);

      ResponseEntity<FindPerspectiveOut> loginResponse = restTemplate.exchange(RestServiceMapping.URL_SERVICE_FIND_PERSPECTIVE,
                                                                               HttpMethod.POST, entity, FindPerspectiveOut.class);
      if (loginResponse.getStatusCode() == HttpStatus.OK) {
        FindPerspectiveOut userJson = (FindPerspectiveOut) loginResponse.getBody();
        userJson.getPerspectiveLst();
      }
      else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        // nono... bad credentials
      }

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

}
