package lagavu.acceptance.component.cbr.config;

import lagavu.acceptance.component.cbr.rate_parser.CbrRateParserInterface;
import lagavu.acceptance.component.cbr.rate_parser.parser.CbrRateXmlParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CbrRateParserConfig {

    @Bean
    public CbrRateParserInterface cbrRateParser() {
        return new CbrRateXmlParser();
    }
}
