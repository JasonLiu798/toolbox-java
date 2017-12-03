package com.atjl.dbservice.manager;

import com.atjl.dbservice.domain.*;
import com.atjl.dbservice.mapper.biz.DataTransferMapper;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;

/**
 * 数据插入辅助类
 *
 */
@Component
public class DataImportManager {
	/**
	 * 生成 需要插入的数据
	 */
	public TgtTableDataPkg tgtDataGenInsert(List<Map> datas, DbTableTransferConfig config) {
		TgtTableDataPkg res = new TgtTableDataPkg();
		//List<TgtTableData> res = new ArrayList<>();
		if(CollectionUtil.isEmpty(datas)){
			return res;
		}
		List<TgtTableData> tgtDatas = new ArrayList<>();
		
		for(Map data:datas){
			TgtTableData pkg = new TgtTableData();
			//List<KeyValue> l = new ArrayList<>();
			Map<String,String> tgtData = new TreeMap<>();//初始化
			//主键部分
			if(!CollectionUtil.isEmpty(config.getPkFieldMapping())) {
//				l.addAll(raw2tgtKV(data, config.getPkFieldMapping()));
				tgtData.putAll(raw2tgt(data, config.getPkFieldMapping()));
			}
			//普通字段
			if(!CollectionUtil.isEmpty(config.getFieldMapping())){
//				l.addAll(raw2tgtKV(data, config.getFieldMapping()));
				tgtData.putAll(raw2tgt(data, config.getFieldMapping()));
			}
			//json 部分
			if(!CollectionUtil.isEmpty(config.getJsonFieldMapping())) {
				Map<String,String> jsonMap = raw2tgt(data, config.getJsonFieldMapping());
				String json = JSONFastJsonUtil.objectToJson(jsonMap);
				tgtData.put(config.getJsonField(),json);
//				l.add(new KeyValue(config.getJsonField(), json));
			}
			
			//目标表主键字段，已经用于 更新已经存在的数据
			Object tgtPk = data.get(config.getTgtTablePk());
			if(!StringCheckUtil.isEmpty(tgtPk)){
				//tgtData.put( config.getTgtTablePk(), String.valueOf(tgtPk));
				pkg.setPk(new KeyValue(config.getTgtTablePk(), String.valueOf(tgtPk)));
			}
			
			List<KeyValue> kvs = config.getAllTgtSortKV();
			for(KeyValue kv:kvs){
				kv.setValue(tgtData.get(kv.getKey()));
			}
			pkg.setItems(kvs);
			tgtDatas.add(pkg);
		}
		res.setDatas(tgtDatas);
		res.setFields(config.getAllTgtSortFields());
		return res;
	}
	
	
	
	
	private List<KeyValue> raw2tgtKV(Map rawKV,Map<String,String> mapping){
		List<KeyValue> res = new ArrayList<>();
		for(Map.Entry<String,String> pk:mapping.entrySet()){
			String raw = pk.getKey();
			String tgt = pk.getValue();
			Object v = rawKV.get(raw);
			KeyValue kv = new KeyValue();
			kv.setKey(tgt);
			if(v!=null){
				kv.setValue(String.valueOf(v));
			}
		}
		return res;
	}
	
	private Map<String,String> raw2tgt(Map rawKV,Map<String,String> mapping){
		Map<String,String> res = new HashMap<>();
		for(Map.Entry<String,String> pk:mapping.entrySet()){
			String raw = pk.getKey();
			String tgt = pk.getValue();
			Object v = rawKV.get(raw);
			if(v==null){
				res.put(tgt, "");
			}else{
				res.put(tgt, String.valueOf(v));
			}
		}
		return res;
	}
	
	/**
	 * 生成 需要插入的数据
	 */
	public TgtTableDataUpdatePkg tgtDataGenUpdate(List<Map> datas, DbTableTransferConfig config) {
		TgtTableDataUpdatePkg res = new TgtTableDataUpdatePkg();
		//List<TgtTableData> res = new ArrayList<>();
		if(CollectionUtil.isEmpty(datas)){
			return res;
		}
		List<TgtTableData> tgtDatas = new ArrayList<>();
		Map<String,List<KeyValue>> map = new HashMap<>();

		
		for(Map data:datas){
			Object tgtPk = data.get(config.getTgtTablePk());
			if(StringCheckUtil.isEmpty(tgtPk)){
				continue;
			}
			String pk = String.valueOf(tgtPk);
			
			
			//tgtData.put( config.getTgtTablePk(), String.valueOf(tgtPk));
//				pkg.setPk(new KeyValue(config.getTgtTablePk(), String.valueOf(tgtPk)));
//			}
			
			
//			TgtTableData pkg = new TgtTableData();
			//List<KeyValue> l = new ArrayList<>();
//			Map<String,String> tgtData = new TreeMap<>();//初始化
			//主键部分
			if(!CollectionUtil.isEmpty(config.getPkFieldMapping())) {
//				l.addAll(raw2tgtKV(data, config.getPkFieldMapping()));
//				tgtData.putAll(raw2tgt(data, config.getPkFieldMapping()));
				
			}
			//普通字段
			if(!CollectionUtil.isEmpty(config.getFieldMapping())){
//				l.addAll(raw2tgtKV(data, config.getFieldMapping()));
//				tgtData.putAll(raw2tgt(data, config.getFieldMapping()));
			}
			//json 部分
			if(!CollectionUtil.isEmpty(config.getJsonFieldMapping())) {
				Map<String,String> jsonMap = raw2tgt(data, config.getJsonFieldMapping());
				String json = JSONFastJsonUtil.objectToJson(jsonMap);
//				tgtData.put(config.getJsonField(),json);
//				l.add(new KeyValue(config.getJsonField(), json));
			}
			
			//目标表主键字段，已经用于 更新已经存在的数据

			
			List<KeyValue> kvs = config.getAllTgtSortKV();
			for(KeyValue kv:kvs){
//				kv.setValue(tgtData.get(kv.getKey()));
			}
//			pkg.setItems(kvs);
//			tgtDatas.add(pkg);
		}
//		res.setDatas(tgtDatas);
//		res.setFields(config.getAllTgtSortFields());
		return res;
	}
	
	@Resource
	DataTransferMapper dataTransferMapper;
	
	public void insert(TgtTableDataPkg dataPkg, DbTableTransferConfig config){
		dataTransferMapper.insertBatch(config,dataPkg);
	}
	
	
	public void update(TgtTableDataPkg dataPkg, DbTableTransferConfig config){
		dataTransferMapper.updateBatch(config,dataPkg);
	}
	
	
}
