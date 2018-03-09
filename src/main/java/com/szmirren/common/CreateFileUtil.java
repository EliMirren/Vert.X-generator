package com.szmirren.common;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.szmirren.models.AttributeCVF;
import com.szmirren.models.BizCondition;
import com.szmirren.models.BizConfig;
import com.szmirren.models.CommonName;
import com.szmirren.models.DaoConfig;
import com.szmirren.models.EntityAttribute;
import com.szmirren.models.HistoryConfig;
import com.szmirren.models.RouterConfig;

/**
 * 创建文件的 的工具
 * 
 * @author Mirren
 *
 */
public class CreateFileUtil {
	private static Logger LOG = Logger.getLogger(CreateFileUtil.class);
	private static final String TEMPLATE_DIR = CommonName.TEMPLATE_DIR_NAME.getValue() + "/";// 模板文件夹名称

	/**
	 * 创建实体类
	 * 
	 * @param config
	 * @param attr
	 * @throws Exception
	 */
	public static void CreateEntity(HistoryConfig config, EntityAttribute attr) throws Exception {
		if (!config.isCreateEntity()) {
			return;
		}
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(config.getEntityPackage()));// 文件路径
		String fileName = attr.getEntityName() + ".java";// 文件名称
		String entityStr = ClassUtil.getEntityStr(attr);
		String addEntity = "";
		if (attr.getConfig().isEntityAdd()) {
			LOG.debug("执行创建实体类附加内容...");
			if (!StrUtil.isNullOrEmpty(config.getTemplateConfig().getEntityAddName())) {
				String addStr = IoUtil.fileToStr(TEMPLATE_DIR + config.getTemplateConfig().getEntityAddName());
				addEntity = addStr.replace("{*className*}", attr.getEntityName());
			} else {
				LOG.debug("执行创建实体类附加内容-->失败:没有找到附加内容模板!");
			}
		}
		boolean mode = attr.getConfig().isDelOldFile();
		IoUtil.StrToFile(dir, fileName, entityStr.replace("{*addEntity*}", addEntity), config.getCodeFormat(), mode);
	}

	/**
	 * 创建dao
	 * 
	 * @param config
	 * @param attr
	 * @throws Exception
	 */
	public static void createDao(HistoryConfig config, EntityAttribute attr) throws Exception {
		if (!config.isCreateDao()) {
			return;
		}
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(config.getDaoPackage()));// 文件路径
		String fileName = config.getDaoName() + ".java";// 文件名称
		String packg = getPackageStr(config.getDaoPackage());
		String className = attr.getEntityName();
		String classNameFL = StrUtil.fristToLoCase(attr.getEntityName());
		String impt = getImportStr(config.getAssistPackage(), CommonName.ASSIST.getValue());
		impt += getImportStr(config.getSqlPackage(), config.getSqlName());
		String daoName = config.getDaoName();
		String sqlName = config.getSqlName();
		String sqlObjName = StrUtil.fristToLoCase(sqlName);
		String addSQLFunName = config.getSqlConfig().getFunInsertBatch();

		// 得到dao的相关配置
		DaoConfig daoConfig = config.getDaoConfig();

		String funGetCount = daoConfig.getFunGetCount();
		String funSelectAll = daoConfig.getFunSelectAll();
		String funSelectAllByPage = daoConfig.getFunSelectAllByPage();
		String funSelectObj = daoConfig.getFunSelectObj();
		String funSelectId = daoConfig.getFunSelectId();
		String funInsert = daoConfig.getFunInsert();
		String funUpdate = daoConfig.getFunUpdate();
		String funDelete = daoConfig.getFunDelete();
		String funInsertBatch = daoConfig.getFunInsertBatch();

		String bsGetCount = daoConfig.getBsGetCount();
		String bsSelectAll = daoConfig.getBsSelectAll();
		String bsSelectAllByPage = daoConfig.getBsSelectAllByPage();
		String bsSelectObj = daoConfig.getBsSelectObj();
		String bsSelectId = daoConfig.getBsSelectId();
		String bsInsert = daoConfig.getBsInsert();
		String bsUpdate = daoConfig.getBsUpdate();
		String bsDelete = daoConfig.getBsDelete();
		String bsInsertBatch = daoConfig.getBsInsertBatch();

		boolean isInsertBatch = daoConfig.isInsertBatch();// 是否创建附加内容
		String daoAdd;// dao的附加内容
		String notes = "//";// 附加内容的注释
		if (isInsertBatch) {
			daoAdd = IoUtil.fileToStr(TEMPLATE_DIR + config.getTemplateConfig().getDaoAddName());
			notes = "";
		} else {
			daoAdd = "";
		}
		String dao = IoUtil.fileToStr(TEMPLATE_DIR + config.getTemplateConfig().getDaoName());
		List<String[]> rep = new ArrayList<>();
		rep.add(StrUtil.asStrArray("{*daoAdd*}", daoAdd));
		rep.add(StrUtil.asStrArray("{*funGetCount*}", funGetCount));
		rep.add(StrUtil.asStrArray("{*funSelectAll*}", funSelectAll));
		rep.add(StrUtil.asStrArray("{*funSelectAllByPage*}", funSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*funSelectObj*}", funSelectObj));
		rep.add(StrUtil.asStrArray("{*funSelectId*}", funSelectId));
		rep.add(StrUtil.asStrArray("{*funInsert*}", funInsert));
		rep.add(StrUtil.asStrArray("{*funUpdate*}", funUpdate));
		rep.add(StrUtil.asStrArray("{*funDelete*}", funDelete));
		rep.add(StrUtil.asStrArray("{*funInsertBatch*}", funInsertBatch));
		rep.add(StrUtil.asStrArray("{*bsGetCount*}", bsGetCount));
		rep.add(StrUtil.asStrArray("{*bsSelectAll*}", bsSelectAll));
		rep.add(StrUtil.asStrArray("{*bsSelectAllByPage*}", bsSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*bsSelectObj*}", bsSelectObj));
		rep.add(StrUtil.asStrArray("{*bsSelectId*}", bsSelectId));
		rep.add(StrUtil.asStrArray("{*bsInsert*}", bsInsert));
		rep.add(StrUtil.asStrArray("{*bsUpdate*}", bsUpdate));
		rep.add(StrUtil.asStrArray("{*bsDelete*}", bsDelete));
		rep.add(StrUtil.asStrArray("{*bsInsertBatch*}", bsInsertBatch));
		rep.add(StrUtil.asStrArray("{*addSQLFunName*}", addSQLFunName));
		rep.add(StrUtil.asStrArray("{c}", StrUtil.fristToLoCase(className)));
		rep.add(StrUtil.asStrArray("{C}", className));
		rep.add(StrUtil.asStrArray("{*sqlObjName*}", sqlObjName));
		rep.add(StrUtil.asStrArray("{*package*}", packg));
		rep.add(StrUtil.asStrArray("{*daoName*}", daoName));
		rep.add(StrUtil.asStrArray("{*sqlName*}", sqlName));
		rep.add(StrUtil.asStrArray("{*className*}", className));
		rep.add(StrUtil.asStrArray("{*JsonObject*}", "JsonObject"));
		rep.add(StrUtil.asStrArray("{*classNameFL*}", classNameFL));
		rep.add(StrUtil.asStrArray("{*//*}", notes));
		// 添加实体类包名的包名
		if (dao.indexOf("{*className*}") >= 0) {
			impt += getImportStr(config.getEntityPackage(), config.getEntityName());
		}
		rep.add(StrUtil.asStrArray("{*import*}", impt));
		String materi = StrUtil.replace(dao, rep);
		boolean mode = attr.getConfig().isDelOldFile();
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat(), mode);

	}

	/**
	 * 创建biz
	 * 
	 * @param config
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static void createBiz(HistoryConfig config, EntityAttribute attr) throws Exception {
		if (!config.isCreateBiz()) {
			return;
		}
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(config.getBizPackage()));// 文件路径
		String fileName = config.getBizName() + ".java";// 文件名称
		String bizName = config.getBizName();
		String daoName = config.getDaoName();
		String packg = getPackageStr(config.getBizPackage());
		String className = attr.getEntityName();
		String classNameFL = StrUtil.fristToLoCase(attr.getEntityName());
		String impt = getImportStr(config.getAssistPackage(), CommonName.ASSIST.getValue());
		if (config.isCreateEntity()) {
			impt += getImportStr(config.getEntityPackage(), config.getEntityName());
		}
		// 得到dao的地址配置配置
		DaoConfig daoConfig = config.getDaoConfig();
		String daoBsGetCount = daoConfig.getBsGetCount();
		String daoBsSelectAll = daoConfig.getBsSelectAll();
		String daoBsSelectAllByPage = daoConfig.getBsSelectAllByPage();
		String daoBsSelectObj = daoConfig.getBsSelectObj();
		String daoBsSelectId = daoConfig.getBsSelectId();
		String daoBsInsert = daoConfig.getBsInsert();
		String daoBsUpdate = daoConfig.getBsUpdate();
		String daoBsDelete = daoConfig.getBsDelete();
		String daoBsInsertBatch = daoConfig.getBsInsertBatch();

		// biz相关配置
		BizConfig bizConfig = config.getBizConfig();
		String funGetCount = bizConfig.getFunGetCount();
		String funSelectAll = bizConfig.getFunSelectAll();
		String funSelectAllByPage = bizConfig.getFunSelectAllByPage();
		String funSelectObj = bizConfig.getFunSelectObj();
		String funSelectId = bizConfig.getFunSelectId();
		String funInsert = bizConfig.getFunInsert();
		String funUpdate = bizConfig.getFunUpdate();
		String funDelete = bizConfig.getFunDelete();
		String funInsertBatch = bizConfig.getFunInsertBatch();

		String bsGetCount = bizConfig.getBsGetCount();
		String bsSelectAll = bizConfig.getBsSelectAll();
		String bsSelectAllByPage = bizConfig.getBsSelectAllByPage();
		String bsSelectObj = bizConfig.getBsSelectObj();
		String bsSelectId = bizConfig.getBsSelectId();
		String bsInsert = bizConfig.getBsInsert();
		String bsUpdate = bizConfig.getBsUpdate();
		String bsDelete = bizConfig.getBsDelete();
		String bsInsertBatch = bizConfig.getBsInsertBatch();

		boolean isInsertBatch = bizConfig.isInsertBatch();// 是否创建附加内容
		String bizAdd;// biz的附加内容
		String notes = "//";// 附加内容的注释
		if (isInsertBatch) {
			bizAdd = IoUtil.fileToStr(TEMPLATE_DIR + config.getTemplateConfig().getBizAddName());
			notes = "";
		} else {
			bizAdd = "";
		}
		String biz = IoUtil.fileToStr(TEMPLATE_DIR + config.getTemplateConfig().getBizName());
		List<String[]> rep = new ArrayList<>();
		// 附加内容
		rep.add(StrUtil.asStrArray("{*bizAdd*}", bizAdd));
		// 装载方法体用属性
		rep.add(StrUtil.asStrArray("{*funGetCount*}", funGetCount));
		rep.add(StrUtil.asStrArray("{*funSelectAll*}", funSelectAll));
		rep.add(StrUtil.asStrArray("{*funSelectAllByPage*}", funSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*funSelectObj*}", funSelectObj));
		rep.add(StrUtil.asStrArray("{*funSelectId*}", funSelectId));
		rep.add(StrUtil.asStrArray("{*funInsert*}", funInsert));
		rep.add(StrUtil.asStrArray("{*funUpdate*}", funUpdate));
		rep.add(StrUtil.asStrArray("{*funDelete*}", funDelete));
		rep.add(StrUtil.asStrArray("{*funInsertBatch*}", funInsertBatch));
		// 装载地址替换属性
		rep.add(StrUtil.asStrArray("{*bsGetCount*}", bsGetCount));
		rep.add(StrUtil.asStrArray("{*bsSelectAll*}", bsSelectAll));
		rep.add(StrUtil.asStrArray("{*bsSelectAllByPage*}", bsSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*bsSelectObj*}", bsSelectObj));
		rep.add(StrUtil.asStrArray("{*bsSelectId*}", bsSelectId));
		rep.add(StrUtil.asStrArray("{*bsInsert*}", bsInsert));
		rep.add(StrUtil.asStrArray("{*bsUpdate*}", bsUpdate));
		rep.add(StrUtil.asStrArray("{*bsDelete*}", bsDelete));
		rep.add(StrUtil.asStrArray("{*bsInsertBatch*}", bsInsertBatch));
		// 装载dao地址替换属性
		rep.add(StrUtil.asStrArray("{*daoBsGetCount*}", daoBsGetCount));
		rep.add(StrUtil.asStrArray("{*daoBsSelectAll*}", daoBsSelectAll));
		rep.add(StrUtil.asStrArray("{*daoBsSelectAllByPage*}", daoBsSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*daoBsSelectObj*}", daoBsSelectObj));
		rep.add(StrUtil.asStrArray("{*daoBsSelectId*}", daoBsSelectId));
		rep.add(StrUtil.asStrArray("{*daoBsInsert*}", daoBsInsert));
		rep.add(StrUtil.asStrArray("{*daoBsUpdate*}", daoBsUpdate));
		rep.add(StrUtil.asStrArray("{*daoBsDelete*}", daoBsDelete));
		rep.add(StrUtil.asStrArray("{*daoBsInsertBatch*}", daoBsInsertBatch));
		// 装载非空与长度的配置属性
		BizCondition condtion = getBizCondtion(config, attr);
		rep.add(StrUtil.asStrArray("{*jsonPrimary*}", condtion.getJsonPrimary()));
		rep.add(StrUtil.asStrArray("{*jsonPrimaryEqNull*}", condtion.getJsonPrimaryEqNull()));
		rep.add(StrUtil.asStrArray("{*jsonPrimaryNeqNull*}", condtion.getJsonPrimaryNeqNull()));
		rep.add(StrUtil.asStrArray("{*clzPrimary*}", condtion.getClzPrimary()));
		rep.add(StrUtil.asStrArray("{*clzPrimaryEqNull*}", condtion.getClzPrimaryEqNull()));
		rep.add(StrUtil.asStrArray("{*clzPrimaryNeqNull*}", condtion.getClzPrimaryNeqNull()));

		rep.add(StrUtil.asStrArray("{*jsonAttr*}", condtion.getJsonAttr()));
		rep.add(StrUtil.asStrArray("{*jsonPeqNull*}", condtion.getJsonPeqNull()));
		rep.add(StrUtil.asStrArray("{*jsonPneqNull*}", condtion.getJsonPneqNull()));
		rep.add(StrUtil.asStrArray("{*jsonLenLt*}", condtion.getJsonLenLt()));
		rep.add(StrUtil.asStrArray("{*jsonLenLte*}", condtion.getJsonLenLte()));
		rep.add(StrUtil.asStrArray("{*jsonLenGt*}", condtion.getJsonLenGt()));
		rep.add(StrUtil.asStrArray("{*jsonLenGte*}", condtion.getJsonLenGte()));

		rep.add(StrUtil.asStrArray("{*clzAttr*}", condtion.getClzAttr()));
		rep.add(StrUtil.asStrArray("{*clzPeqNull*}", condtion.getClzPeqNull()));
		rep.add(StrUtil.asStrArray("{*clzPneqNull*}", condtion.getClzPneqNull()));
		rep.add(StrUtil.asStrArray("{*clzLenLt*}", condtion.getClzLenLt()));
		rep.add(StrUtil.asStrArray("{*clzLenLte*}", condtion.getClzLenLte()));
		rep.add(StrUtil.asStrArray("{*clzLenGt*}", condtion.getClzLenGt()));
		rep.add(StrUtil.asStrArray("{*clzLenGte*}", condtion.getClzLenGte()));

		rep.add(StrUtil.asStrArray("{c}", StrUtil.fristToLoCase(className)));
		rep.add(StrUtil.asStrArray("{C}", className));

		// 装载通用替换属性
		rep.add(StrUtil.asStrArray("{*package*}", packg));
		rep.add(StrUtil.asStrArray("{*bizName*}", bizName));
		rep.add(StrUtil.asStrArray("{*daoName*}", daoName));
		rep.add(StrUtil.asStrArray("{*className*}", className));
		rep.add(StrUtil.asStrArray("{*JsonObject*}", "JsonObject"));
		rep.add(StrUtil.asStrArray("{*classNameFL*}", classNameFL));
		rep.add(StrUtil.asStrArray("{*//*}", notes));
		// 添加dao的包名
		if (biz.indexOf("{*daoName*}") >= 0) {
			impt += getImportStr(config.getDaoPackage(), config.getDaoName());
		}
		rep.add(StrUtil.asStrArray("{*import*}", impt));
		String materi = StrUtil.replace(biz, rep);
		boolean mode = attr.getConfig().isDelOldFile();
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat(), mode);
	}

	/**
	 * 创建Router
	 * 
	 * @param config
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static void createRouter(HistoryConfig config, EntityAttribute attr) throws Exception {
		if (!config.isCreateRouter()) {
			return;
		}
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(config.getRouterPackage()));// 文件路径
		String fileName = config.getRouterName() + ".java";// 文件名称
		String routerName = config.getRouterName();
		String daoName = config.getDaoName();
		String bizName = config.getBizName();
		String sqlName = config.getSqlName();
		String packg = getPackageStr(config.getRouterPackage());
		String className = attr.getEntityName();
		String classNameFL = StrUtil.fristToLoCase(attr.getEntityName());
		String impt = "";
		if (config.isCreateEntity()) {
			impt += getImportStr(config.getEntityPackage(), config.getEntityName());
		}
		// biz相关配置
		BizConfig bizConfig = config.getBizConfig();
		String bizBsGetCount = bizConfig.getBsGetCount();
		String bizBsSelectAll = bizConfig.getBsSelectAll();
		String bizBsSelectAllByPage = bizConfig.getBsSelectAllByPage();
		String bizBsSelectObj = bizConfig.getBsSelectObj();
		String bizBsSelectId = bizConfig.getBsSelectId();
		String bizBsInsert = bizConfig.getBsInsert();
		String bizBsUpdate = bizConfig.getBsUpdate();
		String bizBsDelete = bizConfig.getBsDelete();
		String bizBsInsertBatch = bizConfig.getBsInsertBatch();

		// 得到dao的地址配置配置
		DaoConfig daoConfig = config.getDaoConfig();
		String daoBsGetCount = daoConfig.getBsGetCount();
		String daoBsSelectAll = daoConfig.getBsSelectAll();
		String daoBsSelectAllByPage = daoConfig.getBsSelectAllByPage();
		String daoBsSelectObj = daoConfig.getBsSelectObj();
		String daoBsSelectId = daoConfig.getBsSelectId();
		String daoBsInsert = daoConfig.getBsInsert();
		String daoBsUpdate = daoConfig.getBsUpdate();
		String daoBsDelete = daoConfig.getBsDelete();
		String daoBsInsertBatch = daoConfig.getBsInsertBatch();

		// router相关配置
		RouterConfig routerConfig = config.getRouterConfig();
		String funGetCount = routerConfig.getFunGetCount();
		String funSelectAll = routerConfig.getFunSelectAll();
		String funSelectAllByPage = routerConfig.getFunSelectByPage();
		String funSelectObj = routerConfig.getFunSelectObj();
		String funSelectId = routerConfig.getFunSelectId();
		String funInsert = routerConfig.getFunInsert();
		String funUpdate = routerConfig.getFunUpdate();
		String funDelete = routerConfig.getFunDelete();
		String funInsertBatch = routerConfig.getFunInsertBatch();

		String bsGetCount = routerConfig.getBsGetCount();
		String bsSelectAll = routerConfig.getBsSelectAll();
		String bsSelectAllByPage = routerConfig.getBsSelectByPage();
		String bsSelectObj = routerConfig.getBsSelectObj();
		String bsSelectId = routerConfig.getBsSelectId();
		String bsInsert = routerConfig.getBsInsert();
		String bsUpdate = routerConfig.getBsUpdate();
		String bsDelete = routerConfig.getBsDelete();
		String bsInsertBatch = routerConfig.getBsInsertBatch();

		boolean isInsertBatch = routerConfig.isInsertBatch();// 是否创建附加内容
		String routerAdd;// biz的附加内容
		String notes = "//";// 附加内容的注释
		if (isInsertBatch) {
			routerAdd = IoUtil.fileToStr(TEMPLATE_DIR + config.getTemplateConfig().getWebRouterAddName());
			notes = "";
		} else {
			routerAdd = "";
		}

		String router = IoUtil.fileToStr(TEMPLATE_DIR + config.getTemplateConfig().getWebRouterName());

		List<String[]> rep = new ArrayList<>();
		// 附加内容
		rep.add(StrUtil.asStrArray("{*routerAdd*}", routerAdd));
		// 装载方法体用属性
		rep.add(StrUtil.asStrArray("{*funGetCount*}", funGetCount));
		rep.add(StrUtil.asStrArray("{*funSelectAll*}", funSelectAll));
		rep.add(StrUtil.asStrArray("{*funSelectAllByPage*}", funSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*funSelectObj*}", funSelectObj));
		rep.add(StrUtil.asStrArray("{*funSelectId*}", funSelectId));
		rep.add(StrUtil.asStrArray("{*funInsert*}", funInsert));
		rep.add(StrUtil.asStrArray("{*funUpdate*}", funUpdate));
		rep.add(StrUtil.asStrArray("{*funDelete*}", funDelete));
		rep.add(StrUtil.asStrArray("{*funInsertBatch*}", funInsertBatch));
		// 装载地址替换属性
		rep.add(StrUtil.asStrArray("{*bsGetCount*}", bsGetCount));
		rep.add(StrUtil.asStrArray("{*bsSelectAll*}", bsSelectAll));
		rep.add(StrUtil.asStrArray("{*bsSelectAllByPage*}", bsSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*bsSelectObj*}", bsSelectObj));
		rep.add(StrUtil.asStrArray("{*bsSelectId*}", bsSelectId));
		rep.add(StrUtil.asStrArray("{*bsInsert*}", bsInsert));
		rep.add(StrUtil.asStrArray("{*bsUpdate*}", bsUpdate));
		rep.add(StrUtil.asStrArray("{*bsDelete*}", bsDelete));
		rep.add(StrUtil.asStrArray("{*bsInsertBatch*}", bsInsertBatch));

		// 装载biz地址替换属性
		rep.add(StrUtil.asStrArray("{*bizBsGetCount*}", bizBsGetCount));
		rep.add(StrUtil.asStrArray("{*bizBsSelectAll*}", bizBsSelectAll));
		rep.add(StrUtil.asStrArray("{*bizBsSelectAllByPage*}", bizBsSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*bizBsSelectObj*}", bizBsSelectObj));
		rep.add(StrUtil.asStrArray("{*bizBsSelectId*}", bizBsSelectId));
		rep.add(StrUtil.asStrArray("{*bizBsInsert*}", bizBsInsert));
		rep.add(StrUtil.asStrArray("{*bizBsUpdate*}", bizBsUpdate));
		rep.add(StrUtil.asStrArray("{*bizBsDelete*}", bizBsDelete));
		rep.add(StrUtil.asStrArray("{*bizBsInsertBatch*}", bizBsInsertBatch));

		// 装载dao地址替换属性
		rep.add(StrUtil.asStrArray("{*daoBsGetCount*}", daoBsGetCount));
		rep.add(StrUtil.asStrArray("{*daoBsSelectAll*}", daoBsSelectAll));
		rep.add(StrUtil.asStrArray("{*daoBsSelectAllByPage*}", daoBsSelectAllByPage));
		rep.add(StrUtil.asStrArray("{*daoBsSelectObj*}", daoBsSelectObj));
		rep.add(StrUtil.asStrArray("{*daoBsSelectId*}", daoBsSelectId));
		rep.add(StrUtil.asStrArray("{*daoBsInsert*}", daoBsInsert));
		rep.add(StrUtil.asStrArray("{*daoBsUpdate*}", daoBsUpdate));
		rep.add(StrUtil.asStrArray("{*daoBsDelete*}", daoBsDelete));
		rep.add(StrUtil.asStrArray("{*daoBsInsertBatch*}", daoBsInsertBatch));
		rep.add(StrUtil.asStrArray("{c}", StrUtil.fristToLoCase(className)));
		rep.add(StrUtil.asStrArray("{C}", className));

		// 装载非空与长度的配置属性
		BizCondition condtion = getBizCondtion(config, attr);
		rep.add(StrUtil.asStrArray("{*jsonPrimary*}", condtion.getJsonPrimary()));
		rep.add(StrUtil.asStrArray("{*jsonPrimaryEqNull*}", condtion.getJsonPrimaryEqNull()));
		rep.add(StrUtil.asStrArray("{*jsonPrimaryNeqNull*}", condtion.getJsonPrimaryNeqNull()));
		rep.add(StrUtil.asStrArray("{*clzPrimary*}", condtion.getClzPrimary()));
		rep.add(StrUtil.asStrArray("{*clzPrimaryEqNull*}", condtion.getClzPrimaryEqNull()));
		rep.add(StrUtil.asStrArray("{*clzPrimaryNeqNull*}", condtion.getClzPrimaryNeqNull()));

		rep.add(StrUtil.asStrArray("{*jsonAttr*}", condtion.getJsonAttr()));
		rep.add(StrUtil.asStrArray("{*jsonPeqNull*}", condtion.getJsonPeqNull()));
		rep.add(StrUtil.asStrArray("{*jsonPneqNull*}", condtion.getJsonPneqNull()));
		rep.add(StrUtil.asStrArray("{*jsonLenLt*}", condtion.getJsonLenLt()));
		rep.add(StrUtil.asStrArray("{*jsonLenLte*}", condtion.getJsonLenLte()));
		rep.add(StrUtil.asStrArray("{*jsonLenGt*}", condtion.getJsonLenGt()));
		rep.add(StrUtil.asStrArray("{*jsonLenGte*}", condtion.getJsonLenGte()));

		rep.add(StrUtil.asStrArray("{*clzAttr*}", condtion.getClzAttr()));
		rep.add(StrUtil.asStrArray("{*clzPeqNull*}", condtion.getClzPeqNull()));
		rep.add(StrUtil.asStrArray("{*clzPneqNull*}", condtion.getClzPneqNull()));
		rep.add(StrUtil.asStrArray("{*clzLenLt*}", condtion.getClzLenLt()));
		rep.add(StrUtil.asStrArray("{*clzLenLte*}", condtion.getClzLenLte()));
		rep.add(StrUtil.asStrArray("{*clzLenGt*}", condtion.getClzLenGt()));
		rep.add(StrUtil.asStrArray("{*clzLenGte*}", condtion.getClzLenGte()));
		// 装载通用替换属性
		rep.add(StrUtil.asStrArray("{*package*}", packg));
		rep.add(StrUtil.asStrArray("{*routerName*}", routerName));
		rep.add(StrUtil.asStrArray("{*daoName*}", daoName));
		rep.add(StrUtil.asStrArray("{*bizName*}", bizName));
		rep.add(StrUtil.asStrArray("{*sqlName*}", sqlName));
		rep.add(StrUtil.asStrArray("{*className*}", className));
		rep.add(StrUtil.asStrArray("{*JsonObject*}", "JsonObject"));
		rep.add(StrUtil.asStrArray("{*classNameFL*}", classNameFL));
		rep.add(StrUtil.asStrArray("{*//*}", notes));
		rep.add(StrUtil.asStrArray("{c}", StrUtil.fristToLoCase(className)));
		rep.add(StrUtil.asStrArray("{C}", className));
		// 添加dao的包名
		if (router.indexOf("{*daoName*}") >= 0) {
			impt += getImportStr(config.getDaoPackage(), config.getDaoName());
		}
		// 添加biz的包名
		if (router.indexOf("{*bizName*}") >= 0) {
			impt += getImportStr(config.getBizPackage(), config.getBizName());
		}
		// 添加sql的包名
		if (router.indexOf("{*sqlName*}") >= 0) {
			impt += getImportStr(config.getSqlPackage(), config.getSqlName());
		}
		if (router.indexOf("SqlAssist") >= 0) {
			impt += getImportStr(config.getAssistPackage(), CommonName.ASSIST.getValue());
		}
		rep.add(StrUtil.asStrArray("{*import*}", impt));
		String materi = StrUtil.replace(router, rep);
		boolean mode = attr.getConfig().isDelOldFile();
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat(), mode);
	}

	/**
	 * 创建SQL
	 * 
	 * @param config
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static void createSQL(HistoryConfig config, EntityAttribute attr) throws Exception {
		String packg = config.getSqlPackage();
		String abstractSqlPackage = config.getAbstractSqlPackage();
		String sqlParamsPackage = config.getSqlParamsPackage();
		String sqlName = config.getSqlName();
		String tableName = attr.getTableName();
		String primaryKey = attr.getPrimaryKey();
		String columns = getColumnsStr(attr);
		String impt = "";
		String materiRUl;

		String className = attr.getEntityName();
		String classNameFL = StrUtil.fristToLoCase(attr.getEntityName());
		// String jsonClassName = CommonName.JSON_OBJECT_NAME.getValue();
		String propertyValue = getPropertyValueStrByJson(attr);
		String propertyValueByClz = getPropertyValueStr(attr);
		String batchSQL = getAddSQL(config, attr);
		if (!StrUtil.isNullOrEmpty(batchSQL)) {
			batchSQL = batchSQL.replace("{c}", StrUtil.fristToLoCase(className));
			batchSQL = batchSQL.replace("{C}", className);
			if (batchSQL.contains("JsonArray")) {
				impt += getImportStr(CommonName.JSON_ARRAY_PACKAGE.getValue(), CommonName.JSON_ARRAY_NAME.getValue());
			}
		}
		if (StrUtil.isNullOrEmpty(primaryKey)) {
			materiRUl = TEMPLATE_DIR + config.getTemplateConfig().getSqlNonIdName();
			impt += getImportStr(config.getSqlParamsPackage(), CommonName.SQL_AND_PARAMS.getValue());
		} else {
			materiRUl = TEMPLATE_DIR + config.getTemplateConfig().getSqlName();
		}
		String temp = IoUtil.fileToStr(materiRUl);
		List<String[]> rep = new ArrayList<>();
		rep.add(StrUtil.asStrArray("{*AddSQL*}", batchSQL));
		rep.add(StrUtil.asStrArray("{*package*}", getPackageStr(packg)));
		rep.add(StrUtil.asStrArray("{*AbstractSQLPackage*}", abstractSqlPackage));
		rep.add(StrUtil.asStrArray("{*SqlAndParamsPackage*}", sqlParamsPackage));
		rep.add(StrUtil.asStrArray("{*sqlName*}", sqlName));
		rep.add(StrUtil.asStrArray("{*className*}", className));
		rep.add(StrUtil.asStrArray("{*JsonObject*}", "JsonObject"));
		rep.add(StrUtil.asStrArray("{*classNameFL*}", classNameFL));
		rep.add(StrUtil.asStrArray("{*tableName*}", tableName));
		rep.add(StrUtil.asStrArray("{*primaryId*}", primaryKey));
		rep.add(StrUtil.asStrArray("{*columns*}", columns));
		rep.add(StrUtil.asStrArray("{*SqlPropertyValue*}", propertyValue));
		rep.add(StrUtil.asStrArray("{*SqlPropertyValueByClz*}", propertyValueByClz));
		rep.add(StrUtil.asStrArray("{c}", StrUtil.fristToLoCase(className)));
		rep.add(StrUtil.asStrArray("{C}", className));
		// 导入实体类包名
		if (temp.indexOf("{*className*}") >= 0 && config.isCreateEntity()) {
			impt += getImportStr(config.getEntityPackage(), config.getEntityName());
		}
		// 导入JsonObject包名
		if (temp.indexOf("JsonObject") >= 0) {
			impt = getImportStr(CommonName.JSON_OBJECT_PACKAGE.getValue(), CommonName.JSON_OBJECT_NAME.getValue());
		}

		rep.add(StrUtil.asStrArray("{*import*}", impt));

		String materi = StrUtil.replace(temp, rep);
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(packg));// 文件路径
		String fileName = sqlName + ".java";// 文件名称
		boolean mode = attr.getConfig().isDelOldFile();
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat(), mode);
	}

	/**
	 * 创建AbstractSQL
	 * 
	 * @param config
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static void createAbstractSQL(HistoryConfig config) throws Exception {
		String packg = config.getAbstractSqlPackage();
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(packg));// 文件路径
		String fileName = CommonName.ABSTRACT_SQL.getValue() + ".java";// 文件名称
		// 如果文件已经存在不再创建
		if (IoUtil.isExists(dir, fileName)) {
			LOG.debug(CommonName.ABSTRACT_SQL.getValue() + " 已经存在,不再执行创建...");
			return;
		}
		String materiRUl;
		if (config.getTemplateConfig().getAbstractSQLName().contains("{*DBType*}")) {
			String abstractSQLName = config.getTemplateConfig().getAbstractSQLName().replace("{*DBType*}", config.getDbType());
			materiRUl = TEMPLATE_DIR + abstractSQLName;
		} else {
			materiRUl = TEMPLATE_DIR + config.getTemplateConfig().getAbstractSQLName();
		}

		String assistPackage = config.getAssistPackage();
		String sqlParamsPackage = config.getSqlParamsPackage();
		StringBuffer impt = new StringBuffer();
		if (!packg.equals(assistPackage)) {
			impt.append(MessageFormat.format("import {0}.{1};\r\n", assistPackage, CommonName.ASSIST.getValue()));
		}
		if (!packg.equals(sqlParamsPackage)) {
			impt.append(MessageFormat.format("import {0}.{1};\r\n", sqlParamsPackage, CommonName.SQL_AND_PARAMS.getValue()));
		}
		String sql = IoUtil.fileToStr(materiRUl);
		String materi = sql.replace("{*package*}", getPackageStr(packg)).replace("{*AssistPackage*}", assistPackage).replace("{*import*}",
				impt.toString());
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat());
		createSqlPropertyValue(config);
	}

	/**
	 * 创建SqlPropertyValue
	 * 
	 * @param config
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static void createSqlPropertyValue(HistoryConfig config) throws Exception {
		String packg = config.getAbstractSqlPackage();
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(packg));// 文件路径
		String fileName = CommonName.SQL_PROPERTY_VALUE.getValue() + ".java";// 文件名称
		// 如果文件已经存在不再创建
		if (IoUtil.isExists(dir, fileName)) {
			LOG.debug(fileName + " 已经存在,不再执行创建...");
			return;
		}
		String materiRUl = TEMPLATE_DIR + config.getTemplateConfig().getSqlPropertyValueName();
		String tempStr = IoUtil.fileToStr(materiRUl);
		String materi = tempStr.replace("{*package*}", getPackageStr(packg));
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat());
	}

	/**
	 * 创建SqlAssist
	 * 
	 * @param config
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static void createSqlAssist(HistoryConfig config) throws Exception {
		String packg = config.getAssistPackage();
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(packg));// 文件路径
		String fileName = CommonName.ASSIST.getValue() + ".java";// 文件名称
		// 如果文件已经存在不再创建
		if (IoUtil.isExists(dir, fileName)) {
			LOG.debug(fileName + " 已经存在,不再执行创建...");
			return;
		}
		String materiRUl = TEMPLATE_DIR + config.getTemplateConfig().getAssistName();
		String tempStr = IoUtil.fileToStr(materiRUl);
		String materi = tempStr.replace("{*package*}", getPackageStr(packg));
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat());
	}

	/**
	 * 创建Assist的条件类,既SqlWhereCondition
	 * 
	 * @param config
	 * @throws Exception
	 */
	public static void createSqlAssistCondition(HistoryConfig config) throws Exception {
		String packg = config.getAssistPackage();
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(packg));// 文件路径
		String fileName = CommonName.SQL_WHERE_CONDITION.getValue() + ".java";// 文件名称
		// 如果文件已经存在不再创建
		if (IoUtil.isExists(dir, fileName)) {
			LOG.debug(fileName + " 已经存在,不再执行创建...");
			return;
		}

		String materiRUl = TEMPLATE_DIR + config.getTemplateConfig().getAssistConditionName();
		String tempStr = IoUtil.fileToStr(materiRUl);
		String materi = tempStr.replace("{*package*}", getPackageStr(packg));
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat());
	}

	/**
	 * 创建SQLAndParams
	 * 
	 * @param config
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static void createSQLAndParams(HistoryConfig config) throws Exception {
		String packg = config.getSqlParamsPackage();
		Path dir = Paths.get(config.getProjectPath(), IoUtil.toURL(packg));// 文件路径
		String fileName = CommonName.SQL_AND_PARAMS.getValue() + ".java";// 文件名称
		// 如果文件已经存在不再创建
		if (IoUtil.isExists(dir, fileName)) {
			LOG.debug(fileName + " 已经存在,不再执行创建...");
			return;
		}
		String materiRUl = TEMPLATE_DIR + config.getTemplateConfig().getSqlAndParamsName();
		String tempStr = IoUtil.fileToStr(materiRUl);
		String materi = tempStr.replace("{*package*}", getPackageStr(packg));
		IoUtil.StrToFile(dir, fileName, materi, config.getCodeFormat());
	}

	// ----------------function------------------------

	/**
	 * 获得包名字符串
	 * 
	 * @param packg
	 * @return
	 */
	public static String getPackageStr(String packg) {
		return MessageFormat.format("package {0};", packg);
	}

	/**
	 * 将报名与类合成一个引入
	 * 
	 * @param packg
	 * @param name
	 * @return
	 */
	public static String getImportStr(String packg, String name) {
		return MessageFormat.format("import {0}.{1};\r\n", packg, name);
	}

	/**
	 * 获得表列名并将id放在最后
	 * 
	 * @param attr
	 * @return
	 */
	public static String getColumnsStr(EntityAttribute attr) {
		StringBuffer result = null;
		for (AttributeCVF cvf : attr.getAttrs()) {
			if (!cvf.getCheck()) {
				continue;
			}
			if (result == null) {
				result = new StringBuffer(MessageFormat.format(" {0} AS {1} ", cvf.getConlumn(), cvf.getPropertyName()));
			} else {
				result.append(MessageFormat.format(", {0} AS {1} ", cvf.getConlumn(), cvf.getPropertyName()));
			}
		}

		return result == null ? "" : result.toString();
	}

	/**
	 * 获得列名属性与属性值
	 * 
	 * @param attr
	 * @return
	 */
	private static String getPropertyValueStr(EntityAttribute attr) {
		StringBuffer result = null;
		List<AttributeCVF> cvfs = new ArrayList<>(attr.getAttrs());
		AttributeCVF exchange = cvfs.remove(0);
		cvfs.add(exchange);
		for (AttributeCVF cvf : cvfs) {
			if (!cvf.getCheck()) {
				continue;
			}
			if (result == null) {
				result = new StringBuffer(MessageFormat.format("result.add(new SqlPropertyValue<>(\"{0}\", obj.get{1}())); ", cvf.getConlumn(),
						StrUtil.fristToUpCase(cvf.getPropertyName())));
			} else {
				result.append(MessageFormat.format("\r\n        result.add(new SqlPropertyValue<>(\"{0}\", obj.get{1}())); ", cvf.getConlumn(),
						StrUtil.fristToUpCase(cvf.getPropertyName())));
			}
		}
		return result == null ? "" : result.toString();
	}

	/**
	 * 获得列名属性与属性值
	 * 
	 * @param attr
	 * @return
	 */
	private static String getPropertyValueStrByJson(EntityAttribute attr) {
		StringBuffer result = null;
		List<AttributeCVF> cvfs = new ArrayList<>(attr.getAttrs());
		AttributeCVF exchange = cvfs.remove(0);
		cvfs.add(exchange);
		for (AttributeCVF cvf : cvfs) {
			if (!cvf.getCheck()) {
				continue;
			}
			String temp = getJsonGetType("obj", cvf.getJavaTypeValue());
			String value = MessageFormat.format(temp, cvf.getPropertyName());
			if (result == null) {
				result = new StringBuffer(MessageFormat.format("result.add(new SqlPropertyValue<>(\"{0}\", {1})); ", cvf.getConlumn(), value));
			} else {
				result.append(MessageFormat.format("\r\n        result.add(new SqlPropertyValue<>(\"{0}\", {1})); ", cvf.getConlumn(), value));
			}
		}
		return result == null ? "" : result.toString();
	}

	/**
	 * 获得json的获取类型<br>
	 * 返回结果 prefix.getType(\"{0}\")
	 * 
	 * @param prefix
	 *          前缀
	 * @param type
	 *          原类型
	 * @return
	 */
	private static String getJsonGetType(String prefix, String type) {
		String result;
		if ("java.time.Instant".equalsIgnoreCase(type)) {
			result = prefix + ".getInstant(\"{0}\")";
		} else if ("Double".equalsIgnoreCase(type)) {
			result = prefix + ".getDouble(\"{0}\")";
		} else if ("Float".equalsIgnoreCase(type)) {
			result = prefix + ".getFloat(\"{0}\")";
		} else if ("Integer".equalsIgnoreCase(type)) {
			result = prefix + ".getInteger(\"{0}\")";
		} else if ("Long".equalsIgnoreCase(type)) {
			result = prefix + ".getLong(\"{0}\")";
		} else if ("String".equalsIgnoreCase(type)) {
			result = prefix + ".getString(\"{0}\")";
		} else if ("JsonObject".equalsIgnoreCase(type)) {
			result = prefix + ".getJsonObject(\"{0}\")";
		} else {
			result = prefix + ".getValue(\"{0}\")";
		}
		return result;
	}

	/**
	 * 获得实体类的获取类型<br>
	 * 返回结果 prefix.getType(\"{0}\")
	 * 
	 * @param prefix
	 *          前缀
	 * @param type
	 *          原类型
	 * @return
	 */
	private static String getClzGet(String prefix, String type) {
		return prefix + ".get" + StrUtil.fristToUpCase(type) + "()";
	}

	/**
	 * 获得批量插入SQL语句
	 * 
	 * @param config
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public static String getAddSQL(HistoryConfig config, EntityAttribute attr) throws Exception {
		if (config.getSqlConfig().isInsertBatch()) {
			// 附加SQL的方法名称
			String insertFunName = config.getSqlConfig().getFunInsertBatch();
			// 类的名称
			String className = attr.getEntityName();
			StringBuffer column = new StringBuffer();// 存储列名
			StringBuffer values = new StringBuffer();// 存储?号
			StringBuffer params = new StringBuffer();// 存储获取值
			StringBuffer paramsIfNull = new StringBuffer();// 存储带条件判断的获取值

			boolean frist = true;// 用于标记是否第一次执行
			boolean fristParam = true;// 用于标记是否第一次执行
			for (AttributeCVF cvf : attr.getAttrs()) {
				if (!cvf.getCheck()) {
					continue;
				}
				// 得到?号
				if (frist) {
					column.append(cvf.getConlumn());
					values.append("?");
					frist = false;
				} else {
					column.append("," + cvf.getConlumn());
					values.append(",?");
				}

				// 得到json获取属性
				if (fristParam) {
					String temp = getJsonGetType("obj", cvf.getJavaTypeValue());
					String value = MessageFormat.format(temp, cvf.getPropertyName());
					params.append(MessageFormat.format("param.add({0});", value));
					paramsIfNull.append(MessageFormat.format("if ({0} != null) '{'param.add({0});} else '{'param.addNull();}", value));
					fristParam = false;
				} else {
					String temp = getJsonGetType("obj", cvf.getJavaTypeValue());
					String value = MessageFormat.format(temp, cvf.getPropertyName());
					params.append(MessageFormat.format("\r\n            param.add({0});", value));
					paramsIfNull
							.append(MessageFormat.format("\r\n            if ({0} != null) '{'param.add({0});} else '{'param.addNull();}", value));
				}
				//
				// if (config.isCreateEntity()) {
				// // 得到实体类获取属性第一次不换行,第二次换行,params获取不需要判断的,paramsIfNull获取需要判断的
				// if (fristParam) {
				// params.append(MessageFormat.format("param.add(item.get{0}());",
				// StrUtil.fristToUpCase(cvf.getPropertyName())));
				// paramsIfNull.append(MessageFormat.format(
				// "if (item.get{0}() != null) '{'param.add(item.get{0}());}
				// else '{'param.addNull();}",
				// StrUtil.fristToUpCase(cvf.getPropertyName())));
				// fristParam = false;
				// } else {
				// params.append(MessageFormat.format("\r\n
				// param.add(item.get{0}());",
				// StrUtil.fristToUpCase(cvf.getPropertyName())));
				// paramsIfNull.append(MessageFormat.format(
				// "\r\n if (item.get{0}() != null)
				// '{'param.add(item.get{0}());} else '{'param.addNull();}",
				// StrUtil.fristToUpCase(cvf.getPropertyName())));
				// }
				// } else {
				// // 得到json获取属性
				// }
			}

			// // 实体类不判断的for循环
			// String entityFor = " for ({*className*} item : batch) {\r\n
			// JsonArray param = new JsonArray();\r\n {*param*}\r\n
			// batchParams.add(param);\r\n }\r\n"
			// .replace("{*className*} ", className).replace("{*param*}",
			// params);
			// // 实体类判断是否为空的for循环
			// String entityIfNUllFor = " for ({*className*} item : batch) {\r\n
			// JsonArray param = new JsonArray();\r\n {*param*}\r\n
			// batchParams.add(param);\r\n }\r\n"
			// .replace("{*className*} ", className).replace("{*param*}",
			// paramsIfNull);

			// json不判断的for循环
			String jsonFor = "		for (int i = 0; i < batch.size(); i++) {\r\n			JsonArray param = new JsonArray();\r\n			JsonObject obj = batch.getJsonObject(i);\r\n			{*param*}\r\n			batchParams.add(param);\r\n		}\r\n"
					.replace("{*param*}", params);
			// json判断是否为空的for循环
			String jsonIfNullFor = "		for (int i = 0; i < batch.size(); i++) {\r\n			JsonArray param = new JsonArray();\r\n			JsonObject obj = batch.getJsonObject(i);\r\n			{*param*}\r\n			batchParams.add(param);\r\n		}\r\n"
					.replace("{*param*}", paramsIfNull);

			String forIfNull = jsonIfNullFor;// 判断是否为空的添加参数值
			String forAll = jsonFor;// 不判断是否为空的添加参数值
			// String argsType;// 方法参数接收的类型
			// 得到json参数
			// argsType = "JsonArray";
			// if (config.isCreateEntity()) {
			// argsType = MessageFormat.format("List<{0}>", className);
			// forAll = entityFor;
			// forIfNull = entityIfNUllFor;
			// } else {
			// 得到json参数
			// }

			// 读取模板的路径
			String materiUrl = TEMPLATE_DIR + config.getTemplateConfig().getAddSQLName();
			// 得到模板的内容
			String temp = IoUtil.fileToStr(materiUrl);

			List<String[]> rep = new ArrayList<>();
			rep.add(StrUtil.asStrArray("{*addSQLFunName*}", insertFunName));
			// rep.add(StrUtil.asStrArray("{*argsType*}", argsType));
			rep.add(StrUtil.asStrArray("{*forIfNull*}", forIfNull));
			rep.add(StrUtil.asStrArray("{*forAll*}", forAll));
			rep.add(StrUtil.asStrArray("{*column*}", column.toString()));
			rep.add(StrUtil.asStrArray("{*column?*}", values.toString()));
			rep.add(StrUtil.asStrArray("{*className*}", className));
			rep.add(StrUtil.asStrArray("{*tableName*}", attr.getTableName()));
			// 替换模板得到结果
			String result = StrUtil.replace(temp, rep);
			return result;
		}
		return "";
	}

	/**
	 * 获得biz的条件语句
	 * 
	 * @param config
	 * @param attr
	 * @return
	 */
	private static BizCondition getBizCondtion(HistoryConfig config, EntityAttribute attr) {
		List<AttributeCVF> attrs = attr.getAttrs();
		String jsonPrimary = "";
		String jsonPrimaryEqNull = "";
		String jsonPrimaryNeqNull = "";
		String clzPrimary = "";
		String clzPrimaryEqNull = "";
		String clzPrimaryNeqNull = "";

		StringBuilder jsonAttr = new StringBuilder();
		StringBuilder jsonPeqNull = new StringBuilder();
		StringBuilder jsonPneqNull = new StringBuilder();
		StringBuilder jsonLenLt = new StringBuilder();
		StringBuilder jsonLenLte = new StringBuilder();
		StringBuilder jsonLenGt = new StringBuilder();
		StringBuilder jsonLenGte = new StringBuilder();
		StringBuilder clzAttr = new StringBuilder();
		StringBuilder clzPeqNull = new StringBuilder();
		StringBuilder clzPneqNull = new StringBuilder();
		StringBuilder clzLenLt = new StringBuilder();
		StringBuilder clzLenLte = new StringBuilder();
		StringBuilder clzLenGt = new StringBuilder();
		StringBuilder clzLenGte = new StringBuilder();

		boolean nullFlag = true;// 标记是否为第一次创建属性
		boolean lenFlag = true;// 标记是否为第一次判断长度

		for (int i = 0; i < attrs.size(); i++) {
			AttributeCVF cvf = attrs.get(i);
			if (cvf.getPropertyName().equals(attr.getPrimaryKey())) {
				// 获得主键属性
				String temp = getJsonGetType("", cvf.getJavaTypeValue());
				String value = MessageFormat.format(temp, cvf.getPropertyName());
				jsonPrimary = value;
				jsonPrimaryEqNull = value + " == null";
				jsonPrimaryNeqNull = value + " != null";
				String clzGet = getClzGet("", cvf.getPropertyName());
				clzPrimary = clzGet;
				clzPrimaryEqNull = clzGet + " == null";
				clzPrimaryNeqNull = clzGet + " != null";
			}

			String jsonType = getJsonGetType("body", cvf.getJavaTypeValue());
			String jAttr = MessageFormat.format(jsonType, cvf.getPropertyName());
			String cAttr = getClzGet("body", cvf.getPropertyName());
			if (i == 0) {
				jsonAttr.append(jAttr);
				clzAttr.append(cAttr);
			} else {
				jsonAttr.append("," + jAttr);
				clzAttr.append("," + cAttr);
			}

			if (nullFlag) {
				// 将列中没有默认属性值的加上非空判断
				if (cvf.getColumnDefult() == null && !cvf.isNullable()) {
					jsonPeqNull.append(jAttr + " == null");
					jsonPneqNull.append(jAttr + " != null");
					clzPeqNull.append(cAttr + " == null");
					clzPneqNull.append(cAttr + " != null");
					nullFlag = false;
				}
			} else {
				// 将列中没有默认属性值的加上非空判断
				if (cvf.getColumnDefult() == null && !cvf.isNullable()) {
					jsonPeqNull.append(" || " + jAttr + " == null");
					jsonPneqNull.append(" || " + jAttr + " != null");
					clzPeqNull.append(" || " + cAttr + " == null");
					clzPneqNull.append(" || " + cAttr + " != null");
				}
			}

			if (lenFlag) {
				// 将字符串加上长度判断
				if (cvf.getJavaTypeValue().contains("String")) {
					jsonLenLt.append(jAttr + ".length() < " + cvf.getColumnSize());
					jsonLenLte.append(jAttr + ".length() <= " + cvf.getColumnSize());
					jsonLenGt.append(jAttr + ".length() > " + cvf.getColumnSize());
					jsonLenGte.append(jAttr + ".length() >= " + cvf.getColumnSize());
					clzLenLt.append(cAttr + ".length() < " + cvf.getColumnSize());
					clzLenLte.append(cAttr + ".length() <= " + cvf.getColumnSize());
					clzLenGt.append(cAttr + ".length() > " + cvf.getColumnSize());
					clzLenGte.append(cAttr + ".length() >= " + cvf.getColumnSize());
					lenFlag = false;
				}
			} else {
				// 将字符串加上长度判断
				if (cvf.getJavaTypeValue().contains("String")) {
					jsonLenLt.append(" || " + jAttr + ".length() < " + cvf.getColumnSize());
					jsonLenLte.append(" || " + jAttr + ".length() <= " + cvf.getColumnSize());
					jsonLenGt.append(" || " + jAttr + ".length() > " + cvf.getColumnSize());
					jsonLenGte.append(" || " + jAttr + ".length() >= " + cvf.getColumnSize());

					clzLenLt.append(" || " + cAttr + ".length() < " + cvf.getColumnSize());
					clzLenLte.append(" || " + cAttr + ".length() <= " + cvf.getColumnSize());
					clzLenGt.append(" || " + cAttr + ".length() > " + cvf.getColumnSize());
					clzLenGte.append(" || " + cAttr + ".length() >= " + cvf.getColumnSize());
				}
			}
		}
		BizCondition bizCondition = new BizCondition(jsonPrimary, jsonPrimaryEqNull, jsonPrimaryNeqNull, clzPrimary, clzPrimaryEqNull,
				clzPrimaryNeqNull, jsonAttr, jsonPeqNull, jsonPneqNull, jsonLenLt, jsonLenLte, jsonLenGt, jsonLenGte, clzAttr, clzPeqNull,
				clzPneqNull, clzLenLt, clzLenLte, clzLenGt, clzLenGte);
		return bizCondition;
	}

}
