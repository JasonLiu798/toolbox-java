package xytest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 参数验证的Filter
 * 
 * @author yangjie
 */
public class Advice {

	private static Logger logger = LoggerFactory.getLogger(Advice.class);
//	private static JSONHelper jsonHelper = JSONHelper.buildNormalIgnoreBinder();

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object doAround(ProceedingJoinPoint jp) throws Throwable {
		long startTime = System.currentTimeMillis();

        System.out.println("_______AOPIN_______");
//
        Object target = jp.getTarget();
        String simpleName = target.getClass().getSimpleName();
        String clsname = target.getClass().getName();
        System.out.println("simplename "+simpleName+",name "+clsname);

        System.out.println("_______AOPOUT______");
//
//        System.out.println("Object in advice "+target);
//
////        jp.getTarget().getClass();
//        Class tgtClass = target.getClass();
////        Helper tgt = (Helper) tgtClass.cast(target);
////        System.out.println("data in "+tgt.getData());
//
//        System.out.println("jp class "+tgtClass.getName());
////        clz.cast(jp);
//        Method processMethod = tgtClass.getMethod("process");
//        Method getDateMethod = tgtClass.getMethod("getData");
//        Class dataContext = Class.forName("com.juanpi.validate.test.DataContext");
//        Method getValueMethod = dataContext.getMethod("getValue");
//
////        Method getTestMethod = tgtClass.getMethod("getTest");
////        String res = (String) getTestMethod.invoke(target);
////        System.out.println("GET TEST RES "+res);
//        ValidateAnnotation va = processMethod.getAnnotation(ValidateAnnotation.class);
//        Object dataContextInstance = getDateMethod.invoke(target);
//        System.out.println("GetdataContext "+dataContextInstance);
//        Object data = getValueMethod.invoke(dataContextInstance);
//        System.out.println("Getdata "+data);
//
//        Class dataclass = va.dataclass();
//        System.out.println("dataclass "+dataclass.getName());
//
//        Method[] methods = dataclass.getMethods();
//        for (Method m:methods) {
//            String name = m.getName();
////            System.out.println("method name " + name);
//            if (name.equals("getId")) {
//                Integer a = (Integer) m.invoke(data);
//                System.out.println("data id is " + a);
//            }
//        }
//        */

//            /*
//            }else if(name.equals("getValue")){
//                String b = (String)m.invoke(data);
//                System.out.println("data value is "+b);
//            }
//
//            /*
//            if (name.length()>3 && name.substring(0,3).equals("get")){
//                Object a = m.invoke(data);
//            }
//            */

//
//        Object res =  jp.proceed();
//        System.out.println("after---------------------");
//
//        return res;

        /*
		// 获取类执行的方法
		String classname = jp.getTarget().getClass().getName();
		String method = jp.getSignature().getName();
        
		Object[] object = jp.getArgs();
		String requestURI = classname + "." + method;
		logger.info("requestURI is : " + requestURI);
		// 获取所有的请求参数
		Map paramMap = BeanHelper.getFieldValueMap(object[0]);
		String msg = "";
		Map<String, Object> map = null;
		// 验证参数
		Object retObject = validate(requestURI, paramMap);
		if (retObject instanceof Map) {
			map = (HashMap<String, Object>) retObject;
		} else {
			msg = (String) retObject;
		}
		// Map 返回消息
		if (map != null) {
			Integer.parseInt(String.valueOf(map.get(ValidateConstants.SYSTEM_CODE_STRING)));
			ServiceResponse result = getErrorResult(String.valueOf(map.get(ValidateConstants.SYSTEM_MESSAGE_STRING)), Integer.parseInt(String.valueOf(map.get(ValidateConstants.SYSTEM_CODE_STRING))));
			logger.info("validate error:" + jsonHelper.format(result));
			return result;
		}
		if (!StringHelper.isEmpty(msg)) {
			ServiceResponse result = getErrorResult(msg);
			logger.info("validate error:" + jsonHelper.format(result));
			return result;
		}
		logger.info("requestURI : " + requestURI + " exec success!");
		*/
//		logger.debug("params validate runtime is : [ " + (System.currentTimeMillis() - startTime) + " ] ms");

//	}

	/**
	 * 
	 * 错误消息返回
	 * 
	 * @param msg
	 * @return
	 *
	@SuppressWarnings("rawtypes")
	public ServiceResponse getErrorResult(String msg, int... code) {
		ServiceResponse result = new ServiceResponse();
		result.setMessage("error : " + msg);
		result.setCode(ValidateConstants.system_returncode_parmas_error);
		if (code.length > 0) {
			result.setCode(code[0]);
		}
		return result;
	}
    */

	/**
	 * 
	 * 验证方法
	 * 
	 * @param requestURI
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Object validate(String requestURI, Map paramMap) {
        /*
		ActionValidate actionValidate = LoadValidate.getValidateMap().get(requestURI);
		// 没有找到验证规则
		if (actionValidate == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ValidateConstants.SYSTEM_MESSAGE_STRING, "没有找到服务的验证规则");
			map.put(ValidateConstants.SYSTEM_CODE_STRING, ValidateConstants.system_returncode_not_validate_error);
			logger.warn("Did not find the validation rules [ " + requestURI + " ]");
			return map;
		}
		List<ParamValidator> paramValidate = new ArrayList<ParamValidator>();
		// 获取当前接口的验证规则
		List<ParamValidator> current = actionValidate.getParamList();
		paramValidate.addAll(current);
		if (!StringHelper.isEmpty(actionValidate.getReferenceName())) { // 获取当前接口引用的验证规则
			List<ParamValidator> reference = LoadValidate.getValidateMap().get(actionValidate.getReferenceName()).getParamList();
			paramValidate.addAll(reference);
		}
		Object msg = null;
		// 循环验证规则
		for (ParamValidator p : paramValidate) {
			// 参数验证
			try {
//				msg = validateParam(p, paramMap);
			} catch (Exception e) {
				logger.error("praram validate error!", e);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(ValidateConstants.SYSTEM_MESSAGE_STRING, "验证规则异常");
				map.put(ValidateConstants.SYSTEM_CODE_STRING, ValidateConstants.system_returncode_validate_error);
				return map;
			}
			if (!StringHelper.isEmpty(msg)) {
				break;
			}
		}
		*/
		return null;//msg;
	}


}