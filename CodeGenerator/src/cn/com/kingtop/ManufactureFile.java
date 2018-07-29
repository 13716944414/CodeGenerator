package cn.com.kingtop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *	生成目标文件
 * @author jiangjiaxin
 * @date 2017-10-11 下午3:08:35
 */
public class ManufactureFile {
	
	/**
	 * 
	 *
	 * @param root
	 * @param configurationInfo
	 * @param type 1:model 2：service 3:serviceImpl 4:dao 5:xml
	 * @author jiangjiaxin
	 * @date 2017-10-11 下午4:36:02
	 */
	public void manufactureType(Map<String, Object> root, ConfigurationInfo configurationInfo){
		this.manufactureFile(root, configurationInfo.getClassPath(), configurationInfo.getOutPath(), "model.vm", "model", "java");
		this.manufactureFile(root, configurationInfo.getClassPath(), configurationInfo.getOutPath(), "service.vm", "service", "java");
		this.manufactureFile(root, configurationInfo.getClassPath(), configurationInfo.getOutPath(), "serviceImpl.vm", "service\\impl", "java");
		this.manufactureFile(root, configurationInfo.getClassPath(), configurationInfo.getOutPath(), "dao.vm", "dao", "java");
		this.manufactureFile(root, configurationInfo.getClassPath(), configurationInfo.getOutPath(), "ibatis.vm", "xml", "xml");
	}
	
	/**
	 * 生成目标文件
	 *
	 * @param root 数据集合
	 * @param classPath
	 * @param outPath 输出目录
	 * @param vmName 要读取的模板
	 * @param folder 最底级目录名称(model、service、serviceImpl、dao、xml)
	 * @param suffix 目标文件后缀
	 * @author jiangjiaxin
	 * @date 2017-10-16 下午5:15:02
	 */
	private void manufactureFile(Map<String, Object> root, String classPath, String outPath, String vmName, String folder, String suffix){
		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File("./template"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template template = cfg.getTemplate(vmName);
			classPath = classPath.replaceAll("\\.", "\\\\\\\\");
			File targetPath = new File(outPath + "\\" + classPath + "\\" + folder);// 生成的目标文件的父目录
			if (!targetPath.exists()) {
				targetPath.mkdirs();
			}
			String fileName = "";
			if("model".equals(folder)){
				fileName = root.get("tableName") + "." + suffix;
			}else if("service".equals(folder)){
				fileName = root.get("tableName") + "Service" + "." + suffix;
			}else if("service\\impl".equals(folder)){
				fileName = root.get("tableName") + "ServiceImpl" + "." + suffix;
			}else if("dao".equals(folder)){
				fileName = root.get("tableName") + "Dao" + "." + suffix;
			}else if("xml".equals(folder)){
				fileName = root.get("tableName") + "." + suffix;
			}
			File targetFile = new File(targetPath, fileName);
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(targetFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			Writer out = new BufferedWriter(osw);
			template.process(root, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} 
	}
	
}
