import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 每个Multiset有一个name（可选），一个id（必须），id是从1（而不是0）开始到最大值。 该数组的id必须是连续的的。
 * 
 * fileName是文件名，文件中的储存格式自由定义，可以考虑最简单的文本文档，每行是一个Multiset. 
 * Log Content： 
 * three columns in every line and split by " "; 
 * a line is a set; 
 * first column is the task name, second are post tasks and the third one is the time this task executed;
 * every post task is followed by a ",".
 */

public class WFLoad {
	
	public static WFMultiset[] fromFile(String fileName) {
		
		ArrayList<WFMultiset> ml = getMultiset(fileName);
		
		return (WFMultiset[]) ml.toArray(new WFMultiset[ml.size()]);
	}

	private static ArrayList<WFMultiset> getMultiset(String fileName) {

		ArrayList<WFMultiset> setList = new ArrayList<WFMultiset>();
		
		List<ArrayList<String>> allPosts = new ArrayList<ArrayList<String>>();
		Map<String, Integer> allNameIDPairs = new HashMap<String, Integer>();
		List<Integer> allTimes = new ArrayList<Integer>();
		List<String> allNames = new ArrayList<String>();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(fileName))));

			String line = "";
			int id = 1;
			while ((line = reader.readLine()) != null) {
				String[] oneLine = line.split(" ");

				String name = oneLine[0];
				if(!allNameIDPairs.containsKey(name)){
					allNameIDPairs.put(name, id);
					id++;
				}
				allNames.add(name);

				String posts = oneLine[1];
				ArrayList<String> postName = new ArrayList<String>();
				if (!posts.equals("")) {
					String[] postList = posts.split(",");
					for (String post_name : postList) {
						postName.add(post_name);
					}
				}
				allPosts.add(postName);

				int time = Integer.parseInt(oneLine[2]);
				allTimes.add(time);
			}
			
			reader.close();

			for (int lineNumber = 0; lineNumber < allTimes.size(); lineNumber++) {
				int taskID = allNameIDPairs.get(allNames.get(lineNumber));
				int taskTime = allTimes.get(lineNumber);

				ArrayList<String> postNames = allPosts.get(lineNumber);
				int[] postID = new int[postNames.size()];
				for (int thisPosts = 0; thisPosts < postNames.size(); thisPosts++) {
					postID[thisPosts] = allNameIDPairs.get(postNames
							.get(thisPosts));
				}
				
				postID = insertSort(postID);
				
				WFMultiset thisSet = new WFMultiset(taskID, taskTime, postID, allNames.get(lineNumber));
				
				setList.add(thisSet);
			}
			
			System.out.println(allNameIDPairs.toString());
			
			System.out.println(setList.toString());
			
			return setList;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return setList;
		}
	}

	public static int[] insertSort(int[] args) {
		for (int i = 1; i < args.length; i++) {
			for (int j = i; j > 0; j--) {
				if (args[j] < args[j - 1]) {
					int temp = args[j - 1];
					args[j - 1] = args[j];
					args[j] = temp;
				} else
					break;
			}
		}
		return args;
	}
}
