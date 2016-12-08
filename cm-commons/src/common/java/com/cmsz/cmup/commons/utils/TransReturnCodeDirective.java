package com.cmsz.cmup.commons.utils;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import com.cmsz.cmup.commons.utils.constant.ReturnCodeConstant;

/**
 * 
 * @ClassName:com.cmsz.cmup.commons.utils.TransReturnCodeDirective
 * @Description: TODO
 * @Date: Mar 24, 2016
 * @Author: LeucotheaShi
 */
public class TransReturnCodeDirective extends Directive {

	/**
	 * 
	 * @Description:
	 * @Date:Mar 19, 2016 5:57:45 PM
	 * @Author:LeucotheaShi
	 */
	public TransReturnCodeDirective() {

	}

	public static String transformReturnCode(String sourceCode, String codeType) {

		String targetCode;

		if (codeType.equalsIgnoreCase(ReturnCodeConstant.BOSS_RETURN_CODE)) {

			if (sourceCode.equalsIgnoreCase("0")) {
				targetCode = "F000";
			} else if (sourceCode.equalsIgnoreCase("1")) {
				targetCode = "F114";
			} else if (sourceCode.equalsIgnoreCase("2")) {
				targetCode = "F113";
			} else if (sourceCode.equalsIgnoreCase("3")) {
				targetCode = "F115";
			} else {
				targetCode = sourceCode;// 无此类型，不做任何处理
			}

		} else if (codeType.equalsIgnoreCase(ReturnCodeConstant.NUMBER_RETURN_CODE)) {
			if (sourceCode.equalsIgnoreCase("F000")) {
				targetCode = "0";
			} else if (sourceCode.equalsIgnoreCase("F114")) {
				targetCode = "1";
			} else if (sourceCode.equalsIgnoreCase("F113")) {
				targetCode = "2";
			} else if (sourceCode.equalsIgnoreCase("F115")) {
				targetCode = "3";
			} else {
				targetCode = sourceCode;// 无此类型，不做任何处理
			}
		} else {
			targetCode = sourceCode;// 无此类型，不做任何处理
		}

		return targetCode;

	}// transformReturnCode

	/**
	 * @Title: getName
	 * @Description:指令名称，也就是在模板中使用的指令名字
	 * @return
	 * @Date:Mar 19, 2016 6:23:39 PM
	 * @Author:LeucotheaShi
	 */
	@Override
	public String getName() {
		return "TransReturnCode";
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
		SimpleNode sn_sourceCode = (SimpleNode) node.jjtGetChild(0);
		String sourceCode = sn_sourceCode.value(context).toString();

		// 获取第二个参数
		SimpleNode sn_codeType = (SimpleNode) node.jjtGetChild(1);
		String codeType = sn_codeType.value(context).toString();

		// 将结果写入到writer中，相当于把结果输出
		writer.write(TransReturnCodeDirective.transformReturnCode(sourceCode, codeType));
		return true;
	}

	public static void main(String[] args) {

		System.out.println("result:");
		System.out.println("0 : " + TransReturnCodeDirective.transformReturnCode("0", ReturnCodeConstant.BOSS_RETURN_CODE));
		System.out.println("1 : " + TransReturnCodeDirective.transformReturnCode("1", ReturnCodeConstant.BOSS_RETURN_CODE));
		System.out.println("2 : " + TransReturnCodeDirective.transformReturnCode("2", ReturnCodeConstant.BOSS_RETURN_CODE));
		System.out.println("3 : " + TransReturnCodeDirective.transformReturnCode("3", ReturnCodeConstant.BOSS_RETURN_CODE));
		System.out.println("4 : " + TransReturnCodeDirective.transformReturnCode("4", ReturnCodeConstant.BOSS_RETURN_CODE));
		System.out.println("F000 : " + TransReturnCodeDirective.transformReturnCode("F000", ReturnCodeConstant.NUMBER_RETURN_CODE));
		System.out.println("F113 : " + TransReturnCodeDirective.transformReturnCode("F113", ReturnCodeConstant.NUMBER_RETURN_CODE));
		System.out.println("F114 : " + TransReturnCodeDirective.transformReturnCode("F114", ReturnCodeConstant.NUMBER_RETURN_CODE));
		System.out.println("F115 : " + TransReturnCodeDirective.transformReturnCode("F115", ReturnCodeConstant.NUMBER_RETURN_CODE));
		System.out.println("F116 : " + TransReturnCodeDirective.transformReturnCode("F116", ReturnCodeConstant.NUMBER_RETURN_CODE));
	}

}// end
