package org.fundaciobit.plugins.certificate.afirma;

import java.util.Properties;

import java.security.cert.X509Certificate;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.certificate.ICertificatePlugin;
import org.fundaciobit.plugins.certificate.ResultatValidacio;
import org.fundaciobit.plugins.utils.AbstractPluginProperties;

/**
 * 
 * @author anadal
 * 
 */
public class AfirmaCertificatePlugin extends AbstractPluginProperties
  implements ICertificatePlugin {

  protected final Logger log = Logger.getLogger(getClass());

  private static ValidaCertificat validaCertificat = null;
  
  public static final String BASE_PROPERTIES = CERTIFICATE_BASE_PROPERTY + "afirma.";

  public static final String ENDPOINT = BASE_PROPERTIES + "endpoint";
  public static final String APPLICATION_ID = BASE_PROPERTIES + "applicationid";

  // UsernameToken
  public static final String AUTH_UP_USERNAME = BASE_PROPERTIES + "authorization.username";
  public static final String AUTH_UP_PASSWORD = BASE_PROPERTIES + "authorization.password";

  // CERTIFICATE Token
  public static final String AUTH_KS_PATH = BASE_PROPERTIES + "authorization.ks.path";
  public static final String AUTH_KS_TYPE = BASE_PROPERTIES + "authorization.ks.type";
  public static final String AUTH_KS_PASSWORD = BASE_PROPERTIES + "authorization.ks.password";
  public static final String AUTH_KS_ALIAS = BASE_PROPERTIES + "authorization.ks.cert.alias";
  public static final String AUTH_KS_CERT_PASSWORD = BASE_PROPERTIES
      + "authorization.ks.cert.password";

  
  
  
  /**
   * 
   */
  public AfirmaCertificatePlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AfirmaCertificatePlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AfirmaCertificatePlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }

  @Override
  public ResultatValidacio getInfoCertificate(X509Certificate certificat) throws Exception {

    try {

      final boolean obtenirDadesCertificat = true;

      ResultatValidacio rv = checkValidaCertificat(certificat, obtenirDadesCertificat);

      return rv;

    } catch (CheckCertificateException checkExc) {
      throw new Exception(checkExc.getMessage(), checkExc);

    }
  }

  @Override
  public String checkCertificate(X509Certificate cert) throws Exception {

    try {

      final boolean obtenirDadesCertificat = false;
      ResultatValidacio rv = checkValidaCertificat(cert, obtenirDadesCertificat);

      if (rv.getResultatValidacioCodi() == ResultatValidacio.RESULTAT_VALIDACIO_OK) {
        return null;
      } else {
        String desc = rv.getResultatValidacioDescripcio();
        if (desc == null) {
          return "Certificat Invalid";
        } else {
          return desc;
        }
      }

    } catch (CheckCertificateException checkExc) {
      log.error("Error controlat cridant a @firma " + checkExc.getMessage(), checkExc);

      return checkExc.getMessage();

    } catch (Exception e) {

      log.error("Error no controlat cridant a @firma " + e.getMessage(), e);

      return e.getMessage();
    }

  }

  private ResultatValidacio checkValidaCertificat(X509Certificate cert,
    boolean obtenirDadesCertificat) throws Exception {
    
    
    if (validaCertificat == null) {
      
      log.info("Valida Certificat es NULL");

      

      String endPoint = getProperty(ENDPOINT);
      checkNullProperty(ENDPOINT, endPoint);

      String applicationID = getProperty(APPLICATION_ID);
      checkNullProperty(APPLICATION_ID, applicationID);

      if (getProperty(AUTH_UP_USERNAME) != null) {
        String username = getProperty(AUTH_UP_USERNAME);
        String password = getProperty(AUTH_UP_PASSWORD);
        validaCertificat = new ValidaCertificat(endPoint, applicationID, username, password);
      } else if (getProperty(AUTH_KS_PATH) != null) {

          String path = getProperty(AUTH_KS_PATH);
          String type = getProperty(AUTH_KS_TYPE);
          String password = getProperty(AUTH_KS_PASSWORD);
          String alias = getProperty(AUTH_KS_ALIAS);
          String certPassword = getProperty(AUTH_KS_CERT_PASSWORD);

          validaCertificat = new ValidaCertificat(endPoint, applicationID, path, type,
              password, alias, certPassword);

      } else {
        throw new Exception("No s'ha definit en les propietats del sistema les dades"
            + " d'autorització per accedir al serveis d'@firma");
      }
      
    }

    final int modeValidacio = ValidaCertificat.MODE_VALIDACIO_AMB_REVOCACIO;
    // int modeValidacio = ValidaCertificat.MODE_VALIDACIO_CADENA;
    // int modeValidacio = ValidaCertificat.MODE_VALIDACIO_SIMPLE;

    ResultatValidacio rv = validaCertificat.validar(cert, obtenirDadesCertificat,
        modeValidacio);

    return rv;
  }

  private void checkNullProperty(String key, String value) throws Exception {
    if (value == null) {
      throw new Exception("Property " + key + " for " + this.getClass().getName()
          + " must be defined.");
    }
  }

}
