package com.cmsz.cmup.commons.utils;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

/**
 * @ClassName: com.cmsz.cmup.commons.utils.FixLengthDirective
 * @Description: velocity 定长字符串处理指令(对数据库中num类型字段（java 类型BigDecimal ）值补成固定长度)，不足固定长度的原数据前补0
 * @Date: Mar 19, 2016
 * @Author: LeucotheaShi
 */
public class FixLengthDirective extends Directive {


	/**
	 * 
	 * @Description:
	 * @Date:Mar 19, 2016 5:57:45 PM
	 * @Author:LeucotheaShi
	 */
	public FixLengthDirective() {
		
	}

	public static String getFixLengthString(String sourceString, int targetLength) {

		String resultString = sourceString;

		if (targetLength > sourceString.length()) {

			for (int i = 0; i < (targetLength - sourceString.length()); i++) {
				resultString = "0" + resultString;
			}
		}//

		return resultString;

	}// getFixLengthString


	/**
	 * @Title: getName
	 * @Description:指令名称，也就是在模板中使用的指令名字
	 * @return
	 * @Date:Mar 19, 2016 6:23:39 PM
	 * @Author:LeucotheaShi
	 */
	@Override
	public String getName() {
		return "FixLength";
	}

	/**
	 * @Title: getType
	 * @Description:当前有LINE,BLOCK两个值，line行指令，不要end结束符，block块指令，需要end结束符
	 * @return
	 * @Date:Mar 19, 2016 6:23:39 PM
	 * @Author:LeucotheaShi
	 */
	@Override
	public int getType() {
		return LINE;
	}

	/**
	 * @Title: render
	 * @Description:
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 * @throws ParseErrorException
	 * @throws MethodInvocationException
	 * @Date:Mar 19, 2016 6:23:39 PM
	 * @Author:LeucotheaShi
	 */
	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException,
			MethodInvocationException {
		
		// #FixLength（第一个参数，第二个参数）
		// 获取第一个参数
		SimpleNode sn_region = (SimpleNode) node.jjtGetChild(0);
		BigDecimal bigDecimal = (BigDecimal) sn_region.value(context);
		String sourceString = bigDecimal.toString();

		// 获取第二个参数
		SimpleNode sn_key = (SimpleNode) node.jjtGetChild(1);
		int targetLength = (int) sn_key.value(context);

		// 将结果写入到writer中，相当于把结果输出
		writer.write(FixLengthDirective.getFixLengthString(sourceString, targetLength));
		return true;
	}

}// end
