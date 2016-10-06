package com.sgu.infowksporga.spring.tool;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.spring.service.remote.IGetRemoteSpringObjectService;
import com.sgu.core.framework.util.UtilString;

/**
 * Description : Get Remote Spring Object Service class<br>
 *
 * @author SGU
 */
public class GetRemoteSpringObjectService implements IGetRemoteSpringObjectService, ApplicationContextAware {
  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(GetRemoteSpringObjectService.class);

  /**
   * Keep a reference to the Spring context
   */
  private ApplicationContext applicationContext;

  /**
   * Constructor<br>
   */

  public GetRemoteSpringObjectService() {
    LOGGER.debug("GetRemoteSpringObjectService constructor");
  }

  /** {@inheritDoc} */
  @Override
  public Object getRemoteSpringObject(final String beanName) {
    LOGGER.debug("get Remote Spring Object for id : '{}'", beanName);
    return applicationContext.getBean(beanName);
  }

  /** {@inheritDoc} */
  @Override
  public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ImageIcon> getCarrouselImages() {
    try {
      final Properties prezProps = (Properties) applicationContext.getBean("prez.properties");
      String carrouselImageLst = prezProps.getProperty("application.carrousel.images");

      // Replace variables
      final String[] variables = UtilString.getListOfStringBetween(carrouselImageLst, "${", "}");
      for (int i = 0; i < variables.length; i++) {
        carrouselImageLst = UtilString.replaceString(carrouselImageLst, "${" + variables[i] + "}", prezProps.getProperty(variables[i]));
      }

      final List<ImageIcon> imageLst = new ArrayList<ImageIcon>(10);
      final String[] urlImages = UtilString.getListOfStringBetween(carrouselImageLst, "<link>", "</link>");
      final String[] classpathImages = UtilString.getListOfStringBetween(carrouselImageLst, "<classpath>", "</classpath>");

      for (final String fullUrlPath : urlImages) {
        ImageIcon image = null;
        try {
          URL url = new URL(fullUrlPath);
          BufferedImage img = ImageIO.read(url);
          image = new ImageIcon(img);
          imageLst.add(image);
          LOGGER.debug("Carrousel image added : '{}'", fullUrlPath);
        } catch (Exception e) {
          // On ne bloque pas si une image n'est pas trouvée
          LOGGER.error(e.getMessage(), e);
        }
      }

      for (final String element : classpathImages) {
        final ImageIcon image = getImageIcon(element);
        imageLst.add(image);
      }

      return imageLst;

    } catch (final Exception e) {
      throw new TechnicalException(e);
    }

  }

  /**
   * Description : Duplicate from GUIUtil due to visibility problem <br>
   *
   * @param classpathImagePath The image located in the classpath "/com/bnpp/.../image.png"
   * @return The image object
   */
  private ImageIcon getImageIcon(final String classpathImagePath) {
    ImageIcon imageIcon = null;
    final URL url = GetRemoteSpringObjectService.class.getResource(classpathImagePath);
    if (url == null) {
      throw new TechnicalException("Icon : " + classpathImagePath + " not found");
    }
    else {
      final Image image = Toolkit.getDefaultToolkit().getImage(url);
      imageIcon = new ImageIcon(image);
    }

    return imageIcon;
  }

}
