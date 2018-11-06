package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.redisUtils.JedisClientPool;
import com.redisUtils.RedisService;
import com.thoughtworks.xstream.XStream;
import com.util.Data;
import com.util.DataSet;
import com.util.Item;
import com.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	RedisService redisService;
	@Autowired
	JedisClientPool jedisClientPool;
	@Test
	public void test2() throws InterruptedException {
//		redistest();
//		JedisTest();
		while(true){

		log.info(jedisClientPool.RANDOMKEY()+"---随机key");
		Thread.sleep(500);
		}

	}
	@Test
	public void redistest(){

		redisService.set("key1","wu");
		log.info(redisService.get("key1")+"++++++++++++++++");
		redisService.set("key4","行呀");
		log.info(redisService.get("key4")+"----------------");
	}

	@Test
	public void JedisTest(){
		jedisClientPool.set("key2","xingya");
		log.info(jedisClientPool.get("key2")+"----------------");
		jedisClientPool.set("key3","行呀");
		log.info(jedisClientPool.get("key3")+"----------------");
	}

	@Test
	public void contextLoads() {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		Item item = new Item();
		item.setKey("WUXINGYA");
		item.setVal("aaaa1");
		item.setRmk("asdfasdfasdf1");

		Item item2 = new Item();
		item2.setKey("WUXINGYA1");
		item2.setVal("aaaa2");
		item2.setRmk("asdfasdfasdf2");

		Item item3 = new Item();
		item3.setKey("WUXINGYA2");
		item3.setVal("aaaa3");
		item3.setRmk("asdfasdfasdf3");

		Item item4 = new Item();
		item4.setKey("WUXINGYA3");
		item4.setVal("aaaa4");
		item4.setRmk("asdfasdfasdf4");

		List<Item> list = new ArrayList<>();
		list.add(item);
		list.add(item2);
		List<Item> list2 = new ArrayList<>();
		list2.add(item3);
		list2.add(item4);

		Data data = new Data();
		data.setItem(list);


		Data data2 = new Data();
		data2.setItem(list2);
		List<Data> listdata = new ArrayList<>();
		listdata.add(data);
		listdata.add(data2);

		DataSet dataSet = new DataSet();
		dataSet.setData(listdata);
		dataSet.setName("NAME001");
		dataSet.setRmk("biubiubiu");
		Message message = new Message();
		message.setDataSet(dataSet);
		System.out.println(message.toString());
		System.out.println(xstream.toXML(message));
	}

}
