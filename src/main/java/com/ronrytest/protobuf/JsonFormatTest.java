package com.ronrytest.protobuf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.googlecode.protobuf.format.JsonFormat;
import com.ronrytest.protobuf.lib.PrimaryBeanProtos.PrimaryBean;

public class JsonFormatTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Bean bean = new Bean();

		bean.setEmail("sdfsd");
		bean.setLastLoginTime(new java.util.Date());
		bean.setZhiwu(Arrays.asList("xxx", "yyyy"));
		bean.setGonghao(new int[] { 1, 2 });

		Map<String, String> shouru = new HashMap<String, String>();
		shouru.put("2013", "2000000");
		bean.setShouru(shouru);

		PrimaryBean.Builder builder = PrimaryBean.newBuilder();
		JsonFormat.merge(JSON.toJSONString(bean), builder);

	}
}
