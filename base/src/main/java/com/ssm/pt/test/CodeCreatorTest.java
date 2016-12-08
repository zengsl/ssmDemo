package com.ssm.pt.test;

import com.ssm.pt.common.model.FiledBucket;
import com.ssm.pt.util.CodeUtil;

/**
 * @author asus
 *
 */
public class CodeCreatorTest {

	public static void main(String[] args) {
		String packeName = "com.ssm.pt.test2";
		String fileName = "user";
		FiledBucket[] filedBuckets = {new FiledBucket("id", "int"), new FiledBucket("name", "String"), new FiledBucket("phone", "String")};
//		CodeUtil.init(fileName, fileName, fileds);
		CodeUtil.generateCode("c:\\test\\WEB-INF", packeName, fileName, filedBuckets);
		
	}
}
