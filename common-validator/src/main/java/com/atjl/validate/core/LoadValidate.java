package com.atjl.validate.core;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 解析xml中的验证规则
 */
public class LoadValidate {

    private static Logger logger = LoggerFactory.getLogger(LoadValidate.class);

	/** 存放所有的验证规则 **/
	private static Map<String, ActionValidate> validateMap = new LinkedHashMap<>();
    private static HashMap<String,String> keyRuleMap = new HashMap<>();

    /*
    public static void init() throws IOException, DocumentException {
        keyRuleMap.put(ValidateConstants.MAIL_KEY, ValidateConstants.MAIL);
//        keyRuleMap.put(ValidateConstants.MOBILE_KEY, ValidateConstants.MOBILE);
//        keyRuleMap.put(ValidateConstants.NUEMRIC_KEY, ValidateConstants.NUEMRIC);
        keyRuleMap.put(ValidateConstants.ZO_KEY, ValidateConstants.ZO);
        readConfiguration();
    }*/

	/**
	 * parse xml
	 * @param xmlStr
	 * @throws DocumentException
	 *
	public static void loadValidate(String xmlStr) throws DocumentException {
		Document document = DocumentHelper.parseText(xmlStr);
		// 获取要解析的元素
		Element root = document.getRootElement();
		for (Iterator parent = root.elementIterator(); parent.hasNext();) {
			Element e = (Element) parent.next();
			ActionValidate actionValidate = new ActionValidate();
			actionValidate.setActionUrl(e.attributeValue("actionUrl"));
			HashMap<String ,ParamValidator> paramValidateMap = new HashMap<>();
			for (Iterator son = e.elementIterator(); son.hasNext();) {
				Element el = (Element) son.next();
				// 验证规则
				ParamValidator validate = new ParamValidator();
				validate.setName(el.attributeValue("name"));
                validate.setNone(false);//默认 校验不为null

                //解析设置校验规则
                String rules = el.attributeValue("rule");
                Rule r = new Rule();
                List<Pattern> patterns = new ArrayList<>();
                r.setValidate(false);//默认不校验
                if(StringUtil.isEmpty(rules)){
                    String[] ruleArr = rules.split(ValidateConstants.RULE_SEP);//按空格分隔，正则内的空格用&nbsp;替换
                    if(ruleArr!=null && ruleArr.length>0){
                        for(String rule:ruleArr){
                            if (rule.equals(ValidateConstants.NULL_KEY)){
                                validate.setNone(true);//可以为空
                            }else{
                                if(rule.startsWith(ValidateConstants.REG) && rule.length()> ValidateConstants.REG_LEN){
                                    rule = rule.substring(ValidateConstants.REG_LEN);
                                    rule = rule.replace(ValidateConstants.SPACE_CHAR," ");
                                }else {
                                    rule = keyRuleMap.get(rule);
                                }
                                if (StringUtil.isEmpty(rule)) {
                                    Pattern p = RegrexUtil.getPattern(rule);
                                    patterns.add(p);
                                    //logger.debug("rule:"+rule+",reg:"+ruleRegx+",add");
                                    logger.debug("validator rule '{}' add to {} {}",rule,actionValidate.getActionUrl(),validate.getName());
                                }else{
                                    logger.error("validator rule '{}' can't find according regrex!",rule);
                                }
                            }
                        }
                    }
                }
                if(patterns.size()>0){
                    r.setValidate(true);//有校验规则，校验位为true
                    r.setPatterns(patterns);
                    validate.setRule(r);
                }
				// 增加验证规则
                paramValidateMap.put(validate.getName(), validate);
                actionValidate.setParam2rules(paramValidateMap);
            }
			// 增加当前actiion的验证规则
            validateMap.put(e.attributeValue("actionUrl"), actionValidate);
        }
	}*/



	/**
	 * read xml
	 * @return
	 * @throws IOException
	 *
	public static List<String> getValidateXmlStr() throws IOException {
//		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;//patternResolver.getResources("classpath:validate/*-validator.xml");
        List<String> xmlStrList = new ArrayList<>();
		for (int i = 0; i < resources.length; i++) {
            logger.debug("validator resourcename {}",resources[i].getFilename());
			InputStreamReader inputStreamReader = new InputStreamReader(resources[i].getInputStream());
			BufferedReader breader = new BufferedReader(inputStreamReader);
			String temp = breader.readLine();
			String xmlStr = "";
			while (temp != null) {
				xmlStr += temp + "\n";
				temp = breader.readLine();
			}
			xmlStrList.add(xmlStr);
		}
		return xmlStrList;
	}*/

	/**
	 * 读取配置
	 * 
	 * @throws IOException
	 * @throws DocumentException
	 *
	public static void readConfiguration() throws IOException, DocumentException {
		List<String> xmlStrList = null;//getValidateXmlStr();
		for (String xmlStr : xmlStrList) {
			loadValidate(xmlStr);
		}
	}*/

	public static Map<String, ActionValidate> getValidateMap() {
		return validateMap;
	}

    public static Map<String,String> getKeyRuleMap(){
        return keyRuleMap;
    }


    public static void main(String[] args) {
        String s = "nume reg:[sdfs&nbsp;df] kd";
        String[] strs = s.split(" ");
        for(String str:strs){
            str = str.replace("&nbsp;"," ");
            System.out.println(str);
        }
    }
}
