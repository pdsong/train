//package com.pds.common.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.module.SimpleModule;
// import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
// /**
//  *
//  * 统一注解，解决前后端交互Long类型精度丢失的问题
//  *   * 可以解决后端传值给前端id long与long的精度丢失问题
//  *   但是会将ipageination.total等数字参数  转成 字符串 导致前端异常飘红
//  *   目前不用这方式
//  * 给前端返回为String
//  *
//  * . public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) 方法
//  * 这个方法返回一个自定义配置的 ObjectMapper 对象。它使用了 Jackson2ObjectMapperBuilder 来构建 ObjectMapper。
//  * 4. Jackson2ObjectMapperBuilder builder.createXmlMapper(false).build();
//  * Jackson2ObjectMapperBuilder 是一个帮助器类，提供了一种便捷的方式来创建和配置 ObjectMapper 实例。
//  * createXmlMapper(false) 指定不创建 XmlMapper，即 ObjectMapper 将仅处理 JSON 数据。
//  * build() 方法返回一个根据指定配置构建的 ObjectMapper 实例。
//  * 5. SimpleModule simpleModule = new SimpleModule();
//  * SimpleModule 是 Jackson 提供的一个模块类，允许你注册自定义的序列化器（或反序列化器），以改变 ObjectMapper 的默认行为。
//  * 6. simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//  * 这一行代码的作用是为 Long 类型注册一个自定义的序列化器。ToStringSerializer.instance 是 Jackson 提供的一个内置序列化器，它将任何对象转换为其 toString() 的结果。
//  * 在这个配置中，它将所有的 Long 类型序列化为字符串（String）。这是一个常见的做法，尤其是在前端处理数据时，防止 JavaScript 对大整数的精度丢失问题。
//  * 7. objectMapper.registerModule(simpleModule);
//  * 这行代码将刚刚创建并配置好的 SimpleModule 注册到 ObjectMapper 实例中，使得该 ObjectMapper 可以使用你自定义的序列化规则。
//  * 8. return objectMapper;
//  * 最后，这个方法返回配置好的 ObjectMapper，使其成为 Spring 容器中的一个 Bean。
//  * 通过这个配置类，自定义了 Spring Boot 应用中使用的 ObjectMapper。特别是将 Long 类型的字段序列化为字符串，以防止在前端处理大整数时的精度丢失问题。
//  * 所有通过 Jackson 进行的 JSON 序列化操作都将应用这些自定义的配置。比如，当后端返回包含 Long 类型字段的对象时，这些 Long 类型的字段会被序列化为字符串，而不是数字。
//  */
// @Configuration
// public class JacksonConfig {
//     @Bean
//     public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
//         ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//         SimpleModule simpleModule = new SimpleModule();
//         simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//         objectMapper.registerModule(simpleModule);
//         return objectMapper;
//     }
// }
