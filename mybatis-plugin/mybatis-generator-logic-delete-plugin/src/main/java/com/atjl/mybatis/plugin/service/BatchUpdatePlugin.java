package com.atjl.mybatis.plugin.service;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class BatchUpdatePlugin extends PluginAdapter {
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addBatchUpdateMethod(interfaze, introspectedTable);
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        addBatchUpdateXml(document, introspectedTable);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

    private void addBatchUpdateMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        Set importedTypes = new TreeSet();
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        importedTypes.add(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        Method ibsmethod = new Method();

        ibsmethod.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType ibsreturnType = FullyQualifiedJavaType.getIntInstance();
        ibsmethod.setReturnType(ibsreturnType);

        ibsmethod.setName("updateBatchByPrimaryKeySelective");

        FullyQualifiedJavaType paramType = FullyQualifiedJavaType.getNewListInstance();
        FullyQualifiedJavaType paramListType;
        if (introspectedTable.getRules().generateBaseRecordClass()) {
            paramListType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        } else {
//            FullyQualifiedJavaType paramListType;
            if (introspectedTable.getRules().generatePrimaryKeyClass())
                paramListType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
            else
                throw new RuntimeException(Messages.getString("RuntimeError.12"));
        }
//        FullyQualifiedJavaType paramListType;
        paramType.addTypeArgument(paramListType);

        ibsmethod.addParameter(new Parameter(paramType, "records"));

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(ibsmethod);
    }

    public void addBatchUpdateXml(Document document, IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        String keyColumn = ((IntrospectedColumn) introspectedTable.getPrimaryKeyColumns().get(0)).getActualColumnName();

        XmlElement insertBatchElement = new XmlElement("update");
        insertBatchElement.addAttribute(new Attribute("id", "updateBatchByPrimaryKeySelective"));
        insertBatchElement.addAttribute(new Attribute("parameterType", "java.util.List"));

        XmlElement foreach = new XmlElement("foreach");
        foreach.addAttribute(new Attribute("collection", "list"));
        foreach.addAttribute(new Attribute("item", "item"));
        foreach.addAttribute(new Attribute("index", "index"));
        foreach.addAttribute(new Attribute("separator", ";"));

        foreach.addElement(new TextElement("update " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

        XmlElement trim1Element = new XmlElement("set");
        String columnName;
        for (IntrospectedColumn introspectedColumn : columns) {
            columnName = introspectedColumn.getActualColumnName();
            if (!columnName.toUpperCase().equalsIgnoreCase(keyColumn)) {
                XmlElement ifxml = new XmlElement("if");
                ifxml.addAttribute(new Attribute("test", "item." + introspectedColumn.getJavaProperty() + "!=null"));
                ifxml.addElement(new TextElement(columnName + "=#{item." + introspectedColumn.getJavaProperty() + ",jdbcType=" + introspectedColumn.getJdbcTypeName() + "},"));
                trim1Element.addElement(ifxml);
            }
        }
        foreach.addElement(trim1Element);

        foreach.addElement(new TextElement("where "));
        int index = 0;
        for (IntrospectedColumn i : introspectedTable.getPrimaryKeyColumns()) {
            foreach.addElement(new TextElement((index > 0 ? " AND " : "") + i.getActualColumnName() + " = #{item." + i.getJavaProperty() + ",jdbcType=" + i.getJdbcTypeName() + "}"));
        }

        insertBatchElement.addElement(foreach);

        document.getRootElement().addElement(insertBatchElement);
    }
}
