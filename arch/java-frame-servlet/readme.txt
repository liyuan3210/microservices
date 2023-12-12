项目导入
import->General->Existing Projects into Workspace

问题
导入ecplise项目报"No projects are found to import

解决方法
1.项目根目录下创建.project文件,内容如下:
<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name>项目名称</name>
	<comment></comment>
</projectDescription>

2.再进行导入
选中项目(右键)->Properties->Project Facets(选中java与Dynamic Web Module)

