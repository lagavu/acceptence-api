package lagavu.acceptance.component.cbr.rate_parser.config;

import lagavu.acceptance.component.cbr.rate_parser.ICbrRateParser;
import lagavu.acceptance.component.cbr.rate_parser.parser.CbrRateXmlParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CbrRateParserConfig {

    @Bean
    public ICbrRateParser cbrRateParser() {
        return new CbrRateXmlParser();
    }
}
