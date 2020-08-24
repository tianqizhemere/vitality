package top.tianqi.vitality.tools.utils;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import top.tianqi.vitality.exception.InputExcelException;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 导入Excel工具类
 * @author wkh
 * @Date 2020/07/17
 * <p>使用java原生注解进行参数校验</p>
 * <p>@NotNull: 使用再属性的set方法上, 为必填写，不可为空</p>
 * <p>@Pattern: 使用再属性的set方法上，获取注解中的正则表达式进行数据校验</p>
 */
@SuppressWarnings("rawtypes")
public class InputExcelUtil {

	private static Logger logger = LoggerFactory.getLogger(InputExcelUtil.class);

	/** 正则表达式 用于匹配属性的第一个字母 */
	private static final String REGEX = "[a-zA-Z]";

	/**
	 * Excel数据转 list
	 * @param originUrl Excel表的所在路径
	 * @param datePattern 日期格式 如"yyyy-MM-dd hh:mm:ss"
	 * @param filedsIndex 模板定义的属性名的行位置
	 * @param startRow 从第几行开始
	 * @param endRow 到第几行结束 (0表示所有行;正数表示到第几行结束;负数表示到倒数第几行结束)
	 * @param clazz 要返回的对象集合的类型
	 * @param <T> 参数类型泛型化
	 * @return
	 * @throws IOException
	 */
	public static <T> List<T> importExcel(String originUrl, String datePattern, int filedsIndex, int startRow, int endRow, Class<T> clazz) throws Exception {
		// 通过maven动态配置上传路径
		String uploadBase = null;
		// 判断文件是否存在
		File file = new File(uploadBase + originUrl);
		if (!file.exists()) {
			logger.debug("Excel文件不存在");
			throw new IOException("文件名为" + file.getName() + "Excel文件不存在！");
		}
		InputStream fis = new FileInputStream(file);
		return doImportExcel(fis, datePattern, filedsIndex, startRow, endRow, clazz);
	}

	/**
	 * Excel数据转 list
	 * @param inputStream Excel文件输入流
	 * @param datePattern 日期格式 如"yyyy-MM-dd hh:mm:ss"
	 * @param filedsIndex 模板定义的属性名的行位置
	 * @param startRow 从第几行开始
	 * @param endRow 到第几行结束 (0表示所有行;正数表示到第几行结束;负数表示到倒数第几行结束)
	 * @param clazz 要返回的对象集合的类型
	 * @param <T>
	 * @return
	 * @throws IOException
	 */
	public static <T> List<T> importExcel(InputStream inputStream, String datePattern, int filedsIndex, int startRow, int endRow, Class<T> clazz) throws Exception {
		return doImportExcel(inputStream, datePattern, filedsIndex, startRow, endRow, clazz);
	}

	/**
	 * 真正实现
	 * @param inputStream
	 * @param datePattern
	 * @param filedsIndex
	 * @param startRow
	 * @param endRow
	 * @param clazz
	 * @param <T>
	 * @return
	 * @throws IOException
	 */
	private static <T> List<T> doImportExcel(InputStream inputStream, String datePattern, int filedsIndex, int startRow, int endRow, Class<T> clazz) throws Exception {
		Workbook wb;
		Sheet sheet;
		Row filedsRow = null;
		List<Row> rowList = new ArrayList<Row>();
		try {
			// 去读Excel
			// HSSFWorkbook wb = new HSSFWorkbook(fis);
			// 使用workbook 支持2003/2007
			wb = WorkbookFactory.create(inputStream);
			sheet = wb.getSheetAt(0);
			// 获取最后行号
			int lastRowNum = sheet.getLastRowNum();

			int rowLength = lastRowNum;
			if (endRow > 0) {
				rowLength = endRow;
			} else if (endRow < 0) {
				rowLength = lastRowNum + endRow;
			}

			// 获取属性列字段
			filedsRow = sheet.getRow(filedsIndex);
			// 循环读取
			Row row;
			for (int i = startRow; i <= rowLength; i++) {
				row = sheet.getRow(i);
				rowList.add(row);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		return returnObjectList(datePattern, filedsRow, rowList, clazz);
	}

	/**
	 * 功能:返回指定的对象集合
	 */
	private static <T>List<T> returnObjectList(String datePattern, Row filedsRow, List<Row> rowList,Class<T> clazz) throws Exception {
		List<T> objectList=new ArrayList<T>();

		T obj;
		String attribute;
		String value;

		for (Row row : rowList) {
			obj = clazz.newInstance();
			for (int j = 0; j < filedsRow.getLastCellNum(); j++) {
				attribute = getCellValue(filedsRow.getCell(j));
				if (!attribute.equals("")) {
					value = getCellValue(row.getCell(j));
					setAttrributeValue(obj, attribute, value, datePattern, row.getRowNum());
				}
			}
			objectList.add(obj);
		}

		return objectList;
	}

	/**
	 * 功能:获取单元格的值
	 */
	private static String getCellValue(Cell cell) {
		Object result = "";
		switch (cell.getCellType()) {
			case STRING:
				result = cell.getStringCellValue();
				break;
			case NUMERIC:
				result = cell.getNumericCellValue();
				break;
			case BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case FORMULA:
				result = cell.getCellFormula();
				break;
			case ERROR:
				result = cell.getErrorCellValue();
				break;
			case BLANK:
				break;
		}
		return result.toString();
	}

	/**
	 * <p>功能:给指定对象的指定属性赋值</p>
	 * 根据@NotNull注解校验字段是否可以为空
	 */
	private static void setAttrributeValue(Object obj,String attribute,String value, String datePattern, int row) throws Exception {
		//得到该属性的set方法名
		String method_name = convertToMethodName(attribute,obj.getClass(),true);
		Method[] methods = obj.getClass().getMethods();
		for (Method method : methods) {
			/*
			 * 因为这里只是调用bean中属性的set方法，属性名称不能重复
			 * 所以set方法也不会重复，所以就直接用方法名称去锁定一个方法
			 * （注：在java中，锁定一个方法的条件是方法名及参数）
			 */
			if(method.getName().equals(method_name)) {
				// 校验是否为必填项
				NotNull notNull = method.getAnnotation(NotNull.class);
				// 是否有校验规则
				javax.validation.constraints.Pattern methodPattern = method.getAnnotation(javax.validation.constraints.Pattern.class);
				if (notNull != null) {
					// 必填项，值为空
					if (StringUtils.isEmpty(value)) {
						throw new InputExcelException("第" + (row + 1) + "行，" + notNull.message());
					}
					// 必填项，需校验规则
					if (methodPattern != null) {
						boolean result = Pattern.matches(methodPattern.regexp(), value);
						if (!result) {
							throw new InputExcelException("第" + (row + 1) + "行，" + methodPattern.message());
						}
					}
				}
				// 有校验规则，且值不为空。
				if (methodPattern != null && !StringUtils.isEmpty(value) && value.trim().length() != 0) {
					boolean result = Pattern.matches(methodPattern.regexp(), value);
					if (!result) {
						throw new InputExcelException("第" + (row + 1) + "行，" + methodPattern.message());
					}
				}
				Class<?>[] parameterC = method.getParameterTypes();
				try {
					/*如果是(整型,浮点型,布尔型,字节型,时间类型),
					 * 按照各自的规则把value值转换成各自的类型
					 * 否则一律按类型强制转换(比如:String类型)
					 */
					if(parameterC[0] == int.class || parameterC[0]== Integer.class) {
						if(value != null && value.length() > 0) {
							method.invoke(obj,Integer.valueOf(value));
						}

						break;
					} else if(parameterC[0] == long.class || parameterC[0]== Long.class) {
						if(value != null && value.length() > 0) {
							// value = value.substring(0, value.lastIndexOf("."));
							method.invoke(obj,Long.valueOf(value));
						}
						break;
					} else if(parameterC[0] == float.class || parameterC[0]== Float.class) {
						if(value != null && value.length() > 0) {
							method.invoke(obj, Float.valueOf(value));
						}
						break;
					} else if(parameterC[0] == double.class || parameterC[0]== Double.class) {
						if(value != null && value.length() > 0) {
							method.invoke(obj, Double.valueOf(value));
						}
						break;
					} else if(parameterC[0] == byte.class || parameterC[0]== Byte.class) {
						if(value != null && value.length() > 0) {
							method.invoke(obj, Byte.valueOf(value));
						}
						break;
					} else if(parameterC[0] == boolean.class|| parameterC[0]== Boolean.class) {
						if (value != null && value.length() > 0) {
							method.invoke(obj, Boolean.valueOf(value));
						}
						break;
					} else if(parameterC[0] == Date.class) {
						if(value != null && value.length() > 0) {
							SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
							Date date = null;
							try {
								date = sdf.parse(value);
							} catch (ParseException e) {
								e.printStackTrace();
								throw new InputExcelException("第" + (row + 1) + "行，" + "时间格式错误，格式为: xxxx/xx/xx");
							}
							method.invoke(obj,date);
						}
						break;
					} else if(parameterC[0] == BigDecimal.class) {
						if (value != null && value.length() > 0) {
							method.invoke(obj, new BigDecimal(value));
						}
						break;
					} else {
						if (value != null && value.length() > 0) {
							method.invoke(obj,parameterC[0].cast(value));
						}
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 功能:根据属性生成对应的set/get方法
	 */
	private static String convertToMethodName(String attribute,Class<?> objClass,boolean isSet) {
		/* 通过正则表达式来匹配第一个字符 **/
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(attribute);
		StringBuilder sb = new StringBuilder();
		/* 如果是set方法名称 **/
		if(isSet) {
			sb.append("set");
		} else {
			/* get方法名称 **/
			try {
				Field attributeField = objClass.getDeclaredField(attribute);
				/** 如果类型为boolean **/
				if(attributeField.getType() == boolean.class||attributeField.getType() == Boolean.class) {
					sb.append("is");
				} else {
					sb.append("get");
				}
			} catch (SecurityException | NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		/* 针对以下划线开头的属性 */
		if(attribute.charAt(0)!='_' && m.find()) {
			sb.append(m.replaceFirst(m.group().toUpperCase()));
		} else {
			sb.append(attribute);
		}
		return sb.toString();
	}
}