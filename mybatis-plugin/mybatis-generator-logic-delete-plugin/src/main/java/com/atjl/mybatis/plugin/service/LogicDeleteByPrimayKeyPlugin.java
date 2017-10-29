package com.atjl.mybatis.plugin.service;

import com.atjl.mybatis.plugin.constant.LogicDeleteConstant;
import com.atjl.mybatis.plugin.domain.PrimaryKey;
import com.atjl.mybatis.plugin.util.PrimaryKeyUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;
import java.util.logging.Logger;

public class LogicDeleteByPrimayKeyPlugin extends PluginAdapter {
    private static final Logger logger = Logger.getLogger(LogicDeleteByPrimayKeyPlugin.class.getName());

    /**
     * {@inheritDoc}
     */
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
                                                                 Interface interfaze, IntrospectedTable introspectedTable) {

//        interfaze.addMethod(generateDeleteLogicById(method, introspectedTable));
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(
            Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {

        interfaze.addMethod(generateDeleteLogicById(method, introspectedTable));

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
                                                                 TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

//        topLevelClass.addMethod(generateDeleteLogicById(method, introspectedTable));
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {

        topLevelClass.addMethod(generateDeleteLogicById(method,
                introspectedTable));
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {


        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名
        List<IntrospectedColumn> cols = introspectedTable.getPrimaryKeyColumns();
        ;

//        for (IntrospectedColumn c : cols) {
//            logger.info("c " + c.getJavaProperty() + "," + c.getFullyQualifiedJavaType() + "," + c.isIdentity()+","+c.getRemarks()+c.);
//        }
        List<PrimaryKey> keys = PrimaryKeyUtil.getPrimayKeys(cols);

        if (keys == null || keys.isEmpty()) {
            throw new IllegalArgumentException("没有主键无法生成");
        }

        StringBuilder columns = new StringBuilder();
        int lastIdx = keys.size() - 1;
        for (int i = 0; i < keys.size(); i++) {
            PrimaryKey k = keys.get(i);
            columns.append(k.getColumnName()).append("=").append("#{").append(k.getPropName()).append("}");
            if (i != lastIdx) {
                columns.append(" and ");
            }
        }
        logger.info("pk " + columns.toString());

        XmlElement parentElement = document.getRootElement();

        // 产生分页语句前半部分
        XmlElement deleteLogicByIdsElement = new XmlElement("update");
        deleteLogicByIdsElement.addAttribute(new Attribute("id", LogicDeleteConstant.FUNC_NAME));

//        String sql = "update %s set DELETED = #{deleted,jdbcType=CHAR} where %s ";
        String sql = "update %s set DELETED = '1' where %s ";
        String fmtSql = String.format(sql, tableName, columns.toString());//columns.toString());

        deleteLogicByIdsElement.addElement(new TextElement(fmtSql));

//                        "update " + tableName + " set DELETED = 'Y' where id in "
//                        "update " + tableName + " set DELETED = #{deleted,jdbcType=CHAR} where ID in "
//                                + " <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach> "
//                ));

        parentElement.addElement(deleteLogicByIdsElement);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }


    private Method generateDeleteLogicById(Method method, IntrospectedTable introspectedTable) {
        Method m = new Method(LogicDeleteConstant.FUNC_NAME);
        m.setVisibility(method.getVisibility());
        m.setReturnType(FullyQualifiedJavaType.getIntInstance());

        List<PrimaryKey> keys = PrimaryKeyUtil.getPrimayKeys(introspectedTable.getPrimaryKeyColumns());

        if (keys == null || keys.isEmpty()) {
            logger.info(LogicDeleteConstant.ERR_NO_PK);
            throw new IllegalArgumentException(LogicDeleteConstant.ERR_NO_PK);
        }

//        m.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "deleteFlag", "@Param(\"deleteFlag\")"));

        for (int i = 0; i < keys.size(); i++) {
            PrimaryKey k = keys.get(i);
            m.addParameter(new Parameter(k.getFullyQualifiedJavaType(), k.getPropName(), "@Param(\"" + k.getPropName() + "\")"));
        }

        context.getCommentGenerator().addGeneralMethodComment(m, introspectedTable);
        return m;
    }

}
