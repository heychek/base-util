package com.github.codinghck.base.util.common.base.json;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.IOException;

/**
 * @author hck 2019-03-02 14:23
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class JsonSchemaUtils {

  private JsonSchemaUtils() {}

  private final static JsonSchemaFactory FACTORY = JsonSchemaFactory.byDefault();

  /**
   * <p>根据 json 校验规则校验 json 字符串的合法性</p>
   *
   * @param rule 校验规则
   * @param json 待校验 json 字符串
   * @return 校验结果
   * @throws IOException 传入 json 不合法
   * @throws ProcessingException 校验规则不合法
   */
  public static ProcessingReport validate(String rule, String json)
      throws ProcessingException, IOException {
    JsonNode main = JsonLoader.fromString(rule);
    JsonNode instance = JsonLoader.fromString(json);
    JsonSchema schema = FACTORY.getJsonSchema(main);
    return schema.validate(instance);
  }

  /**
   * <p>根据 json 校验规则校验对象转成 json 字符串后的合法性</p>
   *
   * @param rule 校验规则
   * @param obj 待校验对象
   * @param <T> 对象类型
   * @return 校验结果
   * @throws IOException 传入 json 不合法
   * @throws ProcessingException 校验规则不合法
   */
  public static <T> ProcessingReport validate(String rule, T obj)
      throws IOException, ProcessingException {
    return validate(rule, JSONObject.toJSONString(obj));
  }
}
