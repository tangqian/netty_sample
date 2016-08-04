package netty_sample;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * @author tangqian
 *
 */
public class StudyTest {
	
	public void replaceFile(String newPath, String curPath) throws IOException{
		File newFile = new File(newPath);
		File curFile = new File(curPath);
		FileUtils.copyFile(newFile, curFile);
		
		// 重命名
		String absolutePath = curFile.getAbsolutePath();
		String dir = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator) + 1);
		String fileName = curFile.getName();
		String prefix = fileName.substring(0, fileName.lastIndexOf('.'));
		String suffix = newFile.getName().substring(newFile.getName().lastIndexOf('.'));
		
		String newFileName = prefix + "_1" + suffix;
		String newFilePath = dir + newFileName;
		curFile.renameTo(new File(newFilePath));
	}
	
	public static void main(String[] args) throws IOException {
		new StudyTest().replaceFile("d:\\tmp\\21321.txt", "d:\\test.txt");
	}

	@Test
	public void lambda1(){
		List<String> names = Arrays.asList("111","222","aaaa","大");
		names.forEach(System.out::println);
	}
	
	@Test
	public void lambda2(){
		List<String> names = Arrays.asList("111","222","2aaaa","111大");
		Predicate<String> p = (s) -> s.startsWith("2");
		Predicate<String> p1 = (s) -> true;
		names.stream().filter(p).forEach(System.out::println);
	}
	
	@Test
	public void lambda3(){
		List<Integer> names = Arrays.asList(1,null,3,4,null);
		names.stream().filter(n-> n != null).count();
	}
}
