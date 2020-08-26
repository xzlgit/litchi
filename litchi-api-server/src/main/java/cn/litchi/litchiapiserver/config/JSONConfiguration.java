package cn.litchi.litchiapiserver.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Configuration
public class JSONConfiguration {
    @PostConstruct
    public void init() {
        ParserConfig.getGlobalInstance().putDeserializer(LocalDate.class, new LocalDateDeserializer());
        ParserConfig.getGlobalInstance().putDeserializer(LocalDateTime.class, new LocalDateDeserializer());
        ParserConfig.getGlobalInstance().putDeserializer(Instant.class, new LocalDateDeserializer());
    }

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy.MM.dd'T'HH:mm:ss'Z'");

    /**
     * LocalDate从JSON字符串反序列化类
     *
     * @author LiuQI 2019/3/8 13:45
     * @version V1.0
     **/
    public static class LocalDateDeserializer implements ObjectDeserializer {
        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            // 如果是字符串格式
            String value = parser.getLexer().stringVal();
            parser.getLexer().nextToken();

            if (value.contains("-") && !value.contains("T")) {
                if (type.equals(LocalDateTime.class)) {
                    return (T) LocalDateTime.parse(value, dateTimeFormatter);
                } else if (type.equals(LocalDate.class)) {
                    return (T) LocalDate.parse(value, dateTimeFormatter);
                } else {
                    // 不知道为什么，前端传正确东八区的时间过来，如果还使用 ZoneOffset.of("+8") 时间会提前8小时
                    return (T) LocalDateTime.parse(value, dateTimeFormatter).toInstant(ZoneOffset.of("+0"));
                }
            } else if (value.contains("-") && value.contains("T")) {
                if (type.equals(LocalDateTime.class)) {
                    return (T) LocalDateTime.parse(value, dateTimeFormatter2);
                } else if (type.equals(LocalDate.class)) {
                    return (T) LocalDate.parse(value, dateTimeFormatter2);
                } else {
                    return (T) Instant.parse(value);
                }
            } else if (value.contains(".")) {
                if (type.equals(LocalDateTime.class)) {
                    return (T) LocalDateTime.parse(value, dateTimeFormatter1);
                } else {
                    return (T) LocalDate.parse(value, dateTimeFormatter1);
                }
            }

            try {
                long longValue = Long.parseLong(value) / 1000;
                if (type.equals(LocalDateTime.class)) {
                    return (T) LocalDateTime.ofEpochSecond(longValue, 0, ZoneOffset.ofHours(8));
                } else if (type.equals(LocalDate.class)) {
                    return (T) LocalDateTime.ofEpochSecond(longValue, 0, ZoneOffset.ofHours(8)).toLocalDate();
                } else if (type.equals(Instant.class)) {
                    return (T) Instant.ofEpochSecond(longValue);
                }
            } catch (Exception e) {
                return null;
            }

            return null;
        }

        @Override
        public int getFastMatchToken() {
            return 0;
        }
    }
}
