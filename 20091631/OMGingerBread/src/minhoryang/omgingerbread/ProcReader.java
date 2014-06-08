package minhoryang.omgingerbread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcReader {
	public static String read(boolean oneline) throws IOException{
		FileReader fp = new FileReader("/proc/stat");
		BufferedReader br = new BufferedReader(fp);
		String output = br.readLine();
		if(!oneline){
			String tmp;
			while((tmp = br.readLine()) != null)
				output = output.concat('\n' + tmp);
		}
		br.close();
		fp.close();
		return output;
	}
	
	public static ProcInfo parse() throws IOException{
		String[] ret = read(true).split(" ");
		ProcInfo a = new ProcInfo();
		a.user = Integer.parseInt(ret[2]);
		a.nice = Integer.parseInt(ret[3]);
		a.system = Integer.parseInt(ret[4]);
		a.idle = Integer.parseInt(ret[5]);
		return a;
	}
}
