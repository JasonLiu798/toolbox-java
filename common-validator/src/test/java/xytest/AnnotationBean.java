package xytest;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by wesley lee
 */
@Component
public class AnnotationBean implements DisposableBean, BeanPostProcessor, ApplicationContextAware {
    //BeanFactoryPostProcessor,

    private static String annotationPackage;

    private static String[] annotationPackages;

    private static ApplicationContext applicationContext;

    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackage() {
        return annotationPackage;
    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        if (annotationPackage == null || annotationPackage.length() == 0) {
//            return;
//        }
        /*
        if (beanFactory instanceof BeanDefinitionRegistry) {
            try {
                // init scanner
                Class<?> scannerClass = ReflectUtils.forName("org.springframework.context.annotation.ClassPathBeanDefinitionScanner");
                Object scanner = scannerClass.getConstructor(new Class<?>[] {BeanDefinitionRegistry.class, boolean.class}).newInstance(new Object[] {(BeanDefinitionRegistry) beanFactory, true});
//                // add filter
                Class<?> filterClass = ReflectUtils.forName("org.springframework.core.type.filter.AnnotationTypeFilter");
                Object filter = filterClass.getConstructor(Class.class).newInstance(Procedure.class);
                Method addIncludeFilter = scannerClass.getMethod("addIncludeFilter", ReflectUtils.forName("org.springframework.core.type.filter.TypeFilter"));
                addIncludeFilter.invoke(scanner, filter);
                // scan packages
                String[] packages = StaticContainer.COMMA_SPLIT_PATTERN.split(annotationPackage);
                Method scan = scannerClass.getMethod("scan", new Class<?>[]{String[].class});
                scan.invoke(scanner, new Object[] {packages});
            } catch (Throwable e) {
                // spring 2.0
            }
        }*/
//    }


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        if (! isMatchPackage(bean)) {
//            return bean;
//        }
        System.out.println("afterbeaninit| "+beanName);
        Cache.putObj(beanName, bean);
        /*
        Method[] methods = bean.getClass().getMethods();
        Procedure procedure= bean.getClass().getAnnotation(Procedure.class);
        if(procedure!=null){
            Flow flow=new Flow();
            flow.setFlag(procedure.flag());
            flow.setFlowCount(procedure.flowCount());
            flow.setFlowId(procedure.id());
            flow.setProcessor(bean);
            flow.setTriggerType(procedure.triggerType());
            flow.setVersion(procedure.version());
            flow.setTimeout(procedure.timeout());
            FlowCache.putFlow(flow.getFlowId(), flow);

            for (Method method : methods) {
                String name = method.getName();
                ProcedureCommand procedureCommand = method.getAnnotation(ProcedureCommand.class);

                if (procedureCommand != null){
                    FlowUnit flowUnit=flow.getFlowUnit(procedureCommand.sequence());
                    if(flowUnit==null){
                        flowUnit=new FlowUnit();
                        flow.addFlowUnit(procedureCommand.sequence(),flowUnit);
                    }
                    flowUnit.setSequence(procedureCommand.sequence());
                    CommandMethod commandMethod=new CommandMethod();
                    commandMethod.setSequence(procedureCommand.sequence());
                    commandMethod.setDomain(procedureCommand.domain());
                    commandMethod.setFlag(procedureCommand.flag());
                    commandMethod.setId(procedureCommand.id());
                    commandMethod.setMethod(method);
                    commandMethod.setMustSuccess(procedureCommand.mustSuccess());
                    commandMethod.setOpenTrigger(procedureCommand.openTrigger());
                    commandMethod.setVersion(procedureCommand.version());
                    if(commandMethod.isOpenTrigger()){
                        flow.addTriggerUnit(commandMethod.getSequence(),commandMethod.getDomain(),commandMethod.getFlag());
                    }
                    flowUnit.addCommandMethod(commandMethod);
                }
                ExceptionFlow exceptionFlow = method.getAnnotation(ExceptionFlow.class);
                if (exceptionFlow != null){
                    FlowUnit flowUnit=flow.getFlowUnit(exceptionFlow.sequence());
                    if(flowUnit==null){
                        flowUnit=new FlowUnit();
                        flow.addFlowUnit(exceptionFlow.sequence(),flowUnit);
                    }
                    ExceptionMethod exceptionMethod=new ExceptionMethod();
                    exceptionMethod.setSequence(exceptionFlow.sequence());
                    exceptionMethod.setMethod(method);
                    exceptionMethod.setId(exceptionFlow.id());
                    exceptionMethod.setDomain(exceptionFlow.domain());
                    exceptionMethod.setType(exceptionFlow.type());
                    exceptionMethod.setDomainAttention(exceptionFlow.domainAttention());
                    exceptionMethod.setVersion(exceptionFlow.version());
                    if(exceptionFlow.type()==1){
                        flow.setNotSendFailCommand(true);
                    }
                    flowUnit.addExceptionMethod(exceptionMethod);
                }

                if(name.equals("load")){
                    flow.setLoadMethod(method);
                }
                if(name.equals("callBack")){
                    flow.setCallbackMethod(method);
                }
                if(name.equals("setDataContext")){
                    flow.setDataContextMethod(method);
                }
                if(name.equals("setFlowContext")){
                    flow.setFlowContextMethod(method);
                }
                if(name.equals("clear")){
                    flow.setClearMethod(method);
                }
            }
        }
        */
        return bean;
    }


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    public void destroy() throws Exception {

    }


//    private boolean isMatchPackage(Object bean) {
//        if (annotationPackages == null || annotationPackages.length == 0) {
//            return true;
//        }
//        String beanClassName = bean.getClass().getName();
//        for (String pkg : annotationPackages) {
//            if (beanClassName.startsWith(pkg)) {
//                return true;
//            }
//        }
//        return false;
//    }
}