package com.moma.zoffy.wapper;

import com.moma.zoffy.helper.JacksonHelper;
import java.util.function.Consumer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * HttpMessageConverterWrapper
 *
 * <p>Wrapper Http Message Converter
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 5:25 PM.
 */
public class HttpMessageConverterWrapper {

  /**
   * @return java.util.function.Consumer<org.springframework.http.converter.HttpMessageConverter <
   *     ?>>
   * @author Created by ivan on 5:28 PM 12/13/18.
   *     <p>//Wrapper Jackson Converter with Customed
   */
  public static Consumer<HttpMessageConverter<?>> objectMapperWrapper() {
    return converter -> {
      if (converter instanceof MappingJackson2HttpMessageConverter) {
        MappingJackson2HttpMessageConverter httpMessageConverter =
            (MappingJackson2HttpMessageConverter) converter;
        JacksonHelper.getObjectMapper(httpMessageConverter.getObjectMapper());
      }
    };
  }
}
