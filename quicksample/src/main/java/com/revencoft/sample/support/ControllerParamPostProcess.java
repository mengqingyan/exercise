/**
 * 
 */
package com.revencoft.sample.support;

/**
 * （扩展点）
 * 用于controller层的参数被赋值后，进行参数实例的后处理
 * @author mengqingyan
 * @version 
 */
public interface ControllerParamPostProcess {

	/**
	 * controller层的参数被赋值后，进行参数实例的后处理
	 */
	void postProcessParam();
}
